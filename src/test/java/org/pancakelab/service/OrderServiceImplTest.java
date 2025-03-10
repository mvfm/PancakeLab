package org.pancakelab.service;

import org.junit.jupiter.api.Test;
import org.pancakelab.exception.ValidationException;
import org.pancakelab.model.Order;
import org.pancakelab.model.OrderStatus;
import org.pancakelab.repository.InMemoryOrderRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.pancakelab.util.Constants.DA_VALID;
import static org.pancakelab.util.Constants.PP_DARK_CHOCOLATE;
import static org.pancakelab.util.Constants.PP_MILK_CHOCOLATE;

class OrderServiceImplTest {

    @Test
    void createOrder() throws ValidationException {
        OrderService orderService = new OrderServiceImpl(new InMemoryOrderRepository());

        Order order = orderService.createOrder(DA_VALID);

        assertNotNull(order);
    }

    @Test
    void getOrder_ValidId() throws ValidationException {
        OrderService orderService = new OrderServiceImpl(new InMemoryOrderRepository());

        Order order = orderService.createOrder(DA_VALID);

        assertEquals(order, orderService.getOrder(order.getId()).get());
    }

    @Test
    void addPancake_One() throws ValidationException {
        OrderService orderService = new OrderServiceImpl(new InMemoryOrderRepository());

        Order order = orderService.createOrder(DA_VALID);
        orderService.addPancake(order, PP_DARK_CHOCOLATE,1);

        assertEquals(1, order.getPancakes().size());
        assertTrue(order.getPancakes().contains(PP_DARK_CHOCOLATE));
    }

    @Test
    void addPancake_Two() throws ValidationException {
        OrderService orderService = new OrderServiceImpl(new InMemoryOrderRepository());

        Order order = orderService.createOrder(DA_VALID);
        orderService.addPancake(order, PP_DARK_CHOCOLATE,1);
        orderService.addPancake(order, PP_MILK_CHOCOLATE,2);

        assertEquals(3, order.getPancakes().size());
        assertTrue(order.getPancakes().contains(PP_DARK_CHOCOLATE));
        assertTrue(order.getPancakes().contains(PP_MILK_CHOCOLATE));
    }

    @Test
    void removePancakes_One() throws ValidationException {
        OrderService orderService = new OrderServiceImpl(new InMemoryOrderRepository());

        Order order = orderService.createOrder(DA_VALID);
        orderService.addPancake(order, PP_DARK_CHOCOLATE,1);
        orderService.addPancake(order, PP_MILK_CHOCOLATE,2);

        orderService.removePancakes(order, PP_DARK_CHOCOLATE, 1);

        assertEquals(2, order.getPancakes().size());
        assertFalse(order.getPancakes().contains(PP_DARK_CHOCOLATE));
        assertTrue(order.getPancakes().contains(PP_MILK_CHOCOLATE));
    }

    @Test
    void removePancakes_Two() throws ValidationException {
        OrderService orderService = new OrderServiceImpl(new InMemoryOrderRepository());

        Order order = orderService.createOrder(DA_VALID);
        orderService.addPancake(order, PP_DARK_CHOCOLATE,1);
        orderService.addPancake(order, PP_MILK_CHOCOLATE,2);

        orderService.removePancakes(order, PP_MILK_CHOCOLATE, 2);

        assertEquals(1, order.getPancakes().size());
        assertTrue(order.getPancakes().contains(PP_DARK_CHOCOLATE));
        assertFalse(order.getPancakes().contains(PP_MILK_CHOCOLATE));
    }

    @Test
    void prepareOrder() throws ValidationException {
        OrderService orderService = new OrderServiceImpl(new InMemoryOrderRepository());

        Order order = orderService.createOrder(DA_VALID);
        orderService.addPancake(order, PP_DARK_CHOCOLATE,1);

        orderService.prepareOrder(order);

        assertEquals(OrderStatus.PREPARED, order.getStatus());
    }

    @Test
    void cancelOrder() throws ValidationException {
        OrderService orderService = new OrderServiceImpl(new InMemoryOrderRepository());

        Order order = orderService.createOrder(DA_VALID);
        orderService.addPancake(order, PP_DARK_CHOCOLATE,1);

        orderService.cancelOrder(order);

        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    void deliverOrder() throws ValidationException {
        OrderService orderService = new OrderServiceImpl(new InMemoryOrderRepository());

        Order order = orderService.createOrder(DA_VALID);
        orderService.addPancake(order, PP_DARK_CHOCOLATE,1);

        orderService.prepareOrder(order);
        orderService.deliverOrder(order);

        assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }

    @Test
    void getAllOrders() throws ValidationException {
        OrderService orderService = new OrderServiceImpl(new InMemoryOrderRepository());

        Order one = orderService.createOrder(DA_VALID);
        Order two = orderService.createOrder(DA_VALID);
        Order three = orderService.createOrder(DA_VALID);

        assertEquals(3, orderService.getAllOrders().size());
        assertTrue(orderService.getAllOrders().contains(one));
        assertTrue(orderService.getAllOrders().contains(two));
        assertTrue(orderService.getAllOrders().contains(three));
    }

    @Test
    void getNewOrders() throws ValidationException {
        OrderService orderService = new OrderServiceImpl(new InMemoryOrderRepository());

        Order one = orderService.createOrder(DA_VALID);
        Order two = orderService.createOrder(DA_VALID);
        Order three = orderService.createOrder(DA_VALID);

        assertEquals(3, orderService.getNewOrders().size());
        assertTrue(orderService.getNewOrders().contains(one));
        assertTrue(orderService.getNewOrders().contains(two));
        assertTrue(orderService.getNewOrders().contains(three));
    }

    @Test
    void getPreparedOrders() throws ValidationException {
        OrderService orderService = new OrderServiceImpl(new InMemoryOrderRepository());

        Order one = orderService.createOrder(DA_VALID);
        orderService.addPancake(one, PP_DARK_CHOCOLATE,1);
        orderService.prepareOrder(one);

        Order two = orderService.createOrder(DA_VALID);
        Order three = orderService.createOrder(DA_VALID);

        assertEquals(1, orderService.getPreparedOrders().size());
        assertTrue(orderService.getPreparedOrders().contains(one));
        assertFalse(orderService.getPreparedOrders().contains(two));
        assertFalse(orderService.getPreparedOrders().contains(three));
    }

    @Test
    void getDeliveredOrders() throws ValidationException {
        OrderService orderService = new OrderServiceImpl(new InMemoryOrderRepository());

        Order one = orderService.createOrder(DA_VALID);
        orderService.addPancake(one, PP_DARK_CHOCOLATE,1);
        orderService.prepareOrder(one);
        orderService.deliverOrder(one);

        Order two = orderService.createOrder(DA_VALID);
        Order three = orderService.createOrder(DA_VALID);

        assertEquals(1, orderService.getDeliveredOrders().size());
        assertTrue(orderService.getDeliveredOrders().contains(one));
        assertFalse(orderService.getDeliveredOrders().contains(two));
        assertFalse(orderService.getDeliveredOrders().contains(three));
    }

    @Test
    void getCancelledOrders() throws ValidationException {
        OrderService orderService = new OrderServiceImpl(new InMemoryOrderRepository());

        Order one = orderService.createOrder(DA_VALID);
        orderService.addPancake(one, PP_DARK_CHOCOLATE,1);
        orderService.cancelOrder(one);

        Order two = orderService.createOrder(DA_VALID);
        Order three = orderService.createOrder(DA_VALID);

        assertEquals(1, orderService.getCancelledOrders().size());
        assertTrue(orderService.getCancelledOrders().contains(one));
        assertFalse(orderService.getCancelledOrders().contains(two));
        assertFalse(orderService.getCancelledOrders().contains(three));
    }
}