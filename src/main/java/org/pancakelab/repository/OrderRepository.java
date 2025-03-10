package org.pancakelab.repository;

import org.pancakelab.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Optional<Order> getById(UUID id);

    void save(Order order);

    void remove(UUID orderId);

    List<Order> getAllOrders();

    List<Order> getNewOrders();

    List<Order> getPreparedOrders();

    List<Order> getDeliveredOrders();

    List<Order> getCancelledOrders();
}
