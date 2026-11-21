package com.example.demo.order;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/orders")
class OrderController {

    private final OrderRepository repo;

    OrderController(OrderRepository repo) { this.repo = repo; } // dependency injection

    @GetMapping
    List<OrderResponse> all() {
        return repo.findAll().stream().map(OrderResponse::from).toList();
    }

    @GetMapping("/{id}")
    OrderResponse one(@PathVariable Long id) {
        return repo.findById(id).map(OrderResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    OrderResponse create(@Valid @RequestBody CreateOrderRequest req) {
        return OrderResponse.from(repo.save(new Order(req.customerEmail(), req.total())));
    }
}