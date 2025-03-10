package org.pancakelab.model;

import java.util.Map;
import java.util.Set;

public class BuildingRegistry {
    private static final Map<Integer, Building> buildings = Map.of(
            1, new Building(1, Set.of(101, 102, 103, 104, 105)),
            2, new Building(2, Set.of(201, 202, 203, 204)),
            3, new Building(3, Set.of(301, 302, 303)),
            4, new Building(4, Set.of(401, 402)),
            5, new Building(5, Set.of(501, 502, 503, 504, 505, 506))
    );

    public static Building getBuilding(int buildingNumber) {
        Building building = buildings.get(buildingNumber);

        if (building == null) {
            throw new IllegalArgumentException("Invalid building number: " + buildingNumber);
        }

        return building;
    }
}
