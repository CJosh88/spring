package com.example.demo.order;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
class OrderController {

    private final OrderService service;

    OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    List<OrderResponse> list(@RequestParam(required = false) String customerEmail) {
        List<Order> orders = (customerEmail == null)
                ? service.findAll()
                : service.findByCustomer(customerEmail);
        return orders.stream().map(OrderResponse::from).toList();
    }

    @GetMapping("/{id}")
    OrderResponse one(@PathVariable Long id) {
        return OrderResponse.from(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    OrderResponse create(@Valid @RequestBody CreateOrderRequest req) {
        return OrderResponse.from(service.create(req.customerEmail(), req.total()));
    }

    @PostMapping("/{id}/cancel")
    OrderResponse cancel(@PathVariable Long id) {
        return OrderResponse.from(service.cancel(id));
    }
}