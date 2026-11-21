package com.example.demo.order;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;

record CreateOrderRequest(
        @NotBlank @Email String customerEmail,
        @NotNull @Positive BigDecimal total) {}

record OrderResponse(Long id, String customerEmail, OrderStatus status,
                     BigDecimal total, Instant createdAt) {
    static OrderResponse from(Order o) {
        return new OrderResponse(o.getId(), o.getCustomerEmail(), o.getStatus(),
                o.getTotal(), o.getCreatedAt());
    }
}