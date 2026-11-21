package com.example.demo.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public List<Order> findAll() {
        return repo.findAll();
    }

    public List<Order> findByCustomer(String email) {
        return repo.findByCustomerEmail(email);
    }

    public Order findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Transactional
    public Order create(String customerEmail, BigDecimal total) {
        return repo.save(new Order(customerEmail, total));
    }

    @Transactional
    public Order cancel(Long id) {
        Order order = findById(id);
        order.cancel();
        return order; // no save() needed, see note below
    }
}