package org.pancakelab.model;

import org.pancakelab.exception.InvalidQuantityException;
import org.pancakelab.exception.NoPancakesInOrderException;
import org.pancakelab.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final DeliveryAddress deliveryAddress;
    private final List<Pancake> pancakes;
    private OrderStatus status;

    private static final long PREPARATION_ELAPSED_TIME_PER_PANCAKE = 1000L;
    private static final long DELIVERY_ELAPSED_TIME_PER_ORDER = 2000L;

    public Order(DeliveryAddress deliveryAddress) {
        this.id = UUID.randomUUID();
        this.deliveryAddress = deliveryAddress;
        this.pancakes = new ArrayList<>();
        this.status = OrderStatus.NEW;
    }

    public UUID getId() {
        return id;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public List<Pancake> getPancakes() {
        return List.copyOf(pancakes);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void addPancake(Pancake pancake, int count) throws ValidationException {
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException("It is too late to change the order: %s.".formatted(status.name()));
        }

        if (count <= 0) {
            throw new InvalidQuantityException(count);
        }

        for (int i = 0; i < count; i++) {
            pancakes.add(pancake);
        }
    }

    public void removePancakes(Pancake pancake, int count) throws ValidationException {
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException("It is too late to change the order: %s.".formatted(status.name()));
        }

        if (count <= 0) {
            throw new InvalidQuantityException(count);
        }

        for (int i = 0; i < count; i++) {
            pancakes.remove(pancake);
        }
    }

    public void prepare() throws ValidationException {
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException("It is too late to prepare the order: %s.".formatted(status.name()));
        }

        if (pancakes.isEmpty()) {
            throw new NoPancakesInOrderException("The order %s does not contain any pancake.".formatted(id));
        }

        status = OrderStatus.PREPARING;
        System.out.printf("Preparing %s...%n", toString());

        try {
            Thread.sleep(pancakes.size() * PREPARATION_ELAPSED_TIME_PER_PANCAKE);
        } catch (InterruptedException ignore) {
        }

        status = OrderStatus.PREPARED;
        System.out.printf("%s is prepared.%n", toString());
    }

    public void deliver() {
        if (status != OrderStatus.PREPARED) {
            throw new IllegalStateException("It is too early or too late to deliver the order: %s.".formatted(status.name()));
        }

        status = OrderStatus.DELIVERING;
        System.out.printf("Delivering %s to %s...%n", toString(), deliveryAddress.toString());

        try {
            Thread.sleep(DELIVERY_ELAPSED_TIME_PER_ORDER);
        } catch (InterruptedException ignore) {
        }

        status = OrderStatus.DELIVERED;
        System.out.printf("%s was delivered to %s.%n", toString(), deliveryAddress.toString());
    }

    public void cancel() {
        if (status.ordinal() >= OrderStatus.PREPARED.ordinal()) {
            throw new IllegalStateException("It is too late to cancel the order: %s.".formatted(status.name()));
        }

        status = OrderStatus.CANCELLED;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(deliveryAddress, order.deliveryAddress) && Objects.equals(pancakes, order.pancakes) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deliveryAddress, pancakes, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", deliveryAddress=" + deliveryAddress +
                ", pancakes=" + pancakes +
                ", status=" + status +
                '}';
    }
}
