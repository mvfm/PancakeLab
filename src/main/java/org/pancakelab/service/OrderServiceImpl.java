package org.pancakelab.service;

import org.pancakelab.exception.ValidationException;
import org.pancakelab.model.DeliveryAddress;
import org.pancakelab.model.Order;
import org.pancakelab.model.Pancake;
import org.pancakelab.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class OrderServiceImpl implements OrderService {
    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    private final OrderRepository orderRepository;

    public OrderServiceImpl(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(DeliveryAddress deliveryAddress) throws ValidationException {
        Order order = new Order(deliveryAddress);
        orderRepository.save(order);
        return order;
    }

    @Override
    public Optional<Order> getOrder(UUID id) {
        return orderRepository.getById(id);
    }

    @Override
    public void addPancake(Order order, Pancake pancake, int count) throws ValidationException {
        order.addPancake(pancake, count);
    }

    public void removePancakes(Order order, Pancake pancake, int count) throws ValidationException {
        order.removePancakes(pancake, count);
    }

    @Override
    public void prepareOrder(Order order) throws ValidationException {
        order.prepare();
    }

    @Override
    public void cancelOrder(Order order) {
        order.cancel();
    }

    @Override
    public void deliverOrder(Order order) {
        order.deliver();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public List<Order> getNewOrders() {
        return orderRepository.getNewOrders();
    }

    @Override
    public List<Order> getPreparedOrders() {
        return orderRepository.getPreparedOrders();
    }

    @Override
    public List<Order> getDeliveredOrders() {
        return orderRepository.getDeliveredOrders();
    }

    @Override
    public List<Order> getCancelledOrders() {
        return orderRepository.getCancelledOrders();
    }
}
