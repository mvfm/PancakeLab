package org.pancakelab.service;

import org.pancakelab.exception.ValidationException;
import org.pancakelab.model.DeliveryAddress;
import org.pancakelab.model.Order;
import org.pancakelab.model.Pancake;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    Order createOrder(DeliveryAddress deliveryAddress) throws ValidationException;
    Optional<Order> getOrder(UUID id);
    void addPancake(Order order, Pancake pancake, int quantity) throws ValidationException;
    void removePancakes(Order order, Pancake pancake, int count) throws ValidationException;
    void prepareOrder(Order order) throws ValidationException;
    void cancelOrder(Order order);
    void deliverOrder(Order order);
    List<Order> getAllOrders();
    List<Order> getNewOrders();
    List<Order> getPreparedOrders();
    List<Order> getDeliveredOrders();
    List<Order> getCancelledOrders();
}
