package org.pancakelab.exception;

import org.pancakelab.model.DeliveryAddress;

public class InvalidBuildingException extends ValidationException {
    private final int building;

    public InvalidBuildingException(DeliveryAddress deliveryAddress) {
        super("There's no building %d.".formatted(
                deliveryAddress.getBuilding()
        ));

        this.building = deliveryAddress.getBuilding();
    }

    public int getBuilding() {
        return building;
    }
}
