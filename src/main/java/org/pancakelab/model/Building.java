package org.pancakelab.model;

import java.util.Set;

public class Building {
    private final int number;
    private final Set<Integer> rooms;

    Building(int number, Set<Integer> rooms) {
        this.number = number;
        this.rooms = rooms;
    }

    public boolean hasRoom(int room) {
        return rooms.contains(room);
    }
}