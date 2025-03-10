package org.pancakelab.exception;

import org.pancakelab.model.DeliveryAddress;

public class InvalidRoomException extends ValidationException {
    private final int building;
    private final int room;


    public InvalidRoomException(DeliveryAddress deliveryAddress) {
        super("There's no room %d on building %d.".formatted(
                deliveryAddress.getRoom(),
                deliveryAddress.getBuilding()
        ));

        this.building = deliveryAddress.getBuilding();
        this.room = deliveryAddress.getRoom();
    }

    public int getBuilding() {
        return building;
    }

    public int getRoom() {
        return room;
    }
}
