package org.pancakelab.model;

import java.util.Objects;

public class DeliveryAddress {
    private final int building;
    private final int room;

    public DeliveryAddress(int building, int room) {
        this.building = building;
        this.room = room;
    }

    public int getBuilding() {
        return building;
    }

    public int getRoom() {
        return room;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryAddress that = (DeliveryAddress) o;
        return building == that.building && room == that.room;
    }

    @Override
    public int hashCode() {
        return Objects.hash(building, room);
    }

    @Override
    public String toString() {
        return "DeliveryAddress{" +
                "building=" + building +
                ", room=" + room +
                '}';
    }
}

