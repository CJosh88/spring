package com.example.demo.order;

public class OrderNotCancellableException extends RuntimeException {
    public OrderNotCancellableException(Long id, OrderStatus status) {
        super("Order " + id + " cannot be cancelled because it is " + status);
    }
}