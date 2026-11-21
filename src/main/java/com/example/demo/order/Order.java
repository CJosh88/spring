package com.example.demo.order;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "orders") // "order" is a reserved word in SQL
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerEmail;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal total;
    private Instant createdAt;

    protected Order() {} // JPA needs this

    public Order(String customerEmail, BigDecimal total) {
        this.customerEmail = customerEmail;
        this.total = total;
        this.status = OrderStatus.PENDING;
        this.createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public String getCustomerEmail() { return customerEmail; }
    public OrderStatus getStatus() { return status; }
    public BigDecimal getTotal() { return total; }
    public Instant getCreatedAt() { return createdAt; }

    public void cancel() {
        if (status == OrderStatus.SHIPPED || status == OrderStatus.CANCELLED) {
            throw new OrderNotCancellableException(id, status);
        }
        this.status = OrderStatus.CANCELLED;
    }
}