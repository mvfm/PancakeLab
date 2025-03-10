package org.pancakelab.model;

import org.pancakelab.exception.InvalidBuildingException;
import org.pancakelab.exception.InvalidRoomException;

public class BuildingRoomValidator {
    public void validate(Order order) throws InvalidBuildingException, InvalidRoomException {
        try {
            Building building = BuildingRegistry.getBuilding(order.getDeliveryAddress().getBuilding());

            if (!building.hasRoom(order.getDeliveryAddress().getRoom())) {
                throw new InvalidRoomException(order.getDeliveryAddress());
            }
        } catch (IllegalArgumentException iae) {
            throw new InvalidBuildingException(order.getDeliveryAddress());
        }
    }
}
