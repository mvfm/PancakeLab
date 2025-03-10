package org.pancakelab.repository;

import org.pancakelab.model.Order;
import org.pancakelab.model.OrderStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

public class InMemoryOrderRepository implements OrderRepository {
    private static final Logger logger = Logger.getLogger(InMemoryOrderRepository.class.getName());

    private final ConcurrentMap<UUID, Order> orders = new ConcurrentHashMap<>();

    @Override
    public Optional<Order> getById(UUID id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    @Override
    public void remove(UUID orderId) {
        orders.remove(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return List.copyOf(this.orders.values());
    }

    @Override
    public List<Order> getNewOrders() {
        return this.orders.values().stream()
                .filter(order -> order.getStatus().equals(OrderStatus.NEW))
                .toList();
    }

    @Override
    public List<Order> getPreparedOrders() {
        return this.orders.values().stream()
                .filter(order -> order.getStatus().equals(OrderStatus.PREPARED))
                .toList();
    }

    @Override
    public List<Order> getDeliveredOrders() {
        return this.orders.values().stream()
                .filter(order -> order.getStatus().equals(OrderStatus.DELIVERED))
                .toList();
    }

    @Override
    public List<Order> getCancelledOrders() {
        return this.orders.values().stream()
                .filter(order -> order.getStatus().equals(OrderStatus.CANCELLED))
                .toList();
    }
}
