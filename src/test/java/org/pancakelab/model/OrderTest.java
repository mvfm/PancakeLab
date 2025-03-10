package org.pancakelab.model;

import org.junit.jupiter.api.Test;
import org.pancakelab.exception.NoPancakesInOrderException;
import org.pancakelab.exception.ValidationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.pancakelab.util.Constants.DA_VALID;
import static org.pancakelab.util.Constants.PP_DARK_CHOCOLATE;
import static org.pancakelab.util.Constants.PP_MILK_CHOCOLATE;

class OrderTest {

    @Test
    void getId() {
        Order order = new Order(DA_VALID);

        assertNotNull(order.getId());
    }

    @Test
    void getDeliveryAddress_Valid() {
        Order order = new Order(DA_VALID);

        assertEquals(DA_VALID, order.getDeliveryAddress());
    }

    @Test
    void getPancakes() throws ValidationException {
        Order order = new Order(DA_VALID);

        order.addPancake(PP_DARK_CHOCOLATE, 1);
        order.addPancake(PP_MILK_CHOCOLATE, 1);

        assertEquals(2, order.getPancakes().size());
        assertTrue(order.getPancakes().contains(PP_DARK_CHOCOLATE));
        assertTrue(order.getPancakes().contains(PP_MILK_CHOCOLATE));
    }

    @Test
    void getStatus_New() {
        Order order = new Order(DA_VALID);

        assertEquals(OrderStatus.NEW, order.getStatus());
    }

    @Test
    void addPancake_Valid() throws ValidationException {
        Order order = new Order(DA_VALID);

        order.addPancake(PP_DARK_CHOCOLATE, 1);

        assertEquals(1, order.getPancakes().size());
        assertTrue(order.getPancakes().contains(PP_DARK_CHOCOLATE));
    }

    @Test
    void removePancakes_ExactMatch() throws ValidationException {
        Order order = new Order(DA_VALID);

        order.addPancake(PP_DARK_CHOCOLATE, 1);
        order.removePancakes(PP_DARK_CHOCOLATE, 1);

        assertEquals(0, order.getPancakes().size());
        assertFalse(order.getPancakes().contains(PP_DARK_CHOCOLATE));
    }

    @Test
    void removePancakes_MoreThanAvailable() throws ValidationException {
        Order order = new Order(DA_VALID);

        order.addPancake(PP_DARK_CHOCOLATE, 1);
        order.removePancakes(PP_DARK_CHOCOLATE, 2);

        assertEquals(0, order.getPancakes().size());
        assertFalse(order.getPancakes().contains(PP_DARK_CHOCOLATE));
    }

    @Test
    void removePancakes_WrongRecipe() throws ValidationException {
        Order order = new Order(DA_VALID);

        order.addPancake(PP_DARK_CHOCOLATE, 1);
        order.removePancakes(PP_MILK_CHOCOLATE, 1);

        assertEquals(1, order.getPancakes().size());
        assertTrue(order.getPancakes().contains(PP_DARK_CHOCOLATE));
    }

    @Test
    void prepare_OK() throws ValidationException {
        Order order = new Order(DA_VALID);

        order.addPancake(PP_DARK_CHOCOLATE, 1);
        order.prepare();

        assertEquals(OrderStatus.PREPARED, order.getStatus());
    }

    @Test
    void prepare_NoPancakesWereAdded() {
        Order order = new Order(DA_VALID);

        assertThrows(NoPancakesInOrderException.class, order::prepare);
    }

    @Test
    void prepare_NoPancakesRemaining() throws ValidationException {
        Order order = new Order(DA_VALID);

        order.addPancake(PP_DARK_CHOCOLATE, 1);
        order.removePancakes(PP_DARK_CHOCOLATE, 1);

        assertThrows(NoPancakesInOrderException.class, order::prepare);
    }

    @Test
    void deliver_OK() throws ValidationException {
        Order order = new Order(DA_VALID);

        order.addPancake(PP_DARK_CHOCOLATE, 1);
        order.prepare();
        order.deliver();

        assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }

    @Test
    void deliver_NotPrepared() throws ValidationException {
        Order order = new Order(DA_VALID);

        order.addPancake(PP_DARK_CHOCOLATE, 1);

        assertThrows(IllegalStateException.class, order::deliver);
    }

    @Test
    void deliver_AlreadyDelivered() throws ValidationException {
        Order order = new Order(DA_VALID);

        order.addPancake(PP_DARK_CHOCOLATE, 1);
        order.prepare();
        order.deliver();

        assertThrows(IllegalStateException.class, order::deliver);
    }

    @Test
    void cancel_OK() throws ValidationException {
        Order order = new Order(DA_VALID);

        order.addPancake(PP_DARK_CHOCOLATE, 1);
        order.cancel();

        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    void cancel_AfterPrepare() throws ValidationException {
        Order order = new Order(DA_VALID);

        order.addPancake(PP_DARK_CHOCOLATE, 1);
        order.prepare();

        assertThrows(IllegalStateException.class, order::cancel);
    }

    @Test
    void testEquals() {
        Order one = new Order(DA_VALID);
        Order two = new Order(DA_VALID);

        assertNotEquals(one, two);
    }

    @Test
    void testHashCode() {
        Order one = new Order(DA_VALID);
        Order two = new Order(DA_VALID);

        assertFalse(one.hashCode() == two.hashCode());    }

    @Test
    void testToString() {
        Order order = new Order(DA_VALID);

        assertNotNull(order.toString());
    }
}