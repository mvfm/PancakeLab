package org.pancakelab.model;

import org.pancakelab.exception.ValidationException;

import java.util.UUID;

public class PancakePresets {
    public static Pancake darkChocolatePancake() {
        try {
            return new Pancake.Builder()
                    .withIngredient("dark chocolate")
                    .build();
        } catch (ValidationException ignore) {
            return null;
        }
    }

    public static Pancake darkChocolateWhippedCreamPancake() {
        try {
            return new Pancake.Builder()
                    .withIngredient("dark chocolate")
                    .withIngredient("whipped cream")
                    .build();
        } catch (ValidationException ignore) {
            return null;
        }
    }

    public static Pancake darkChocolateWhippedCreamHazelnutsPancake() {
        try {
            return new Pancake.Builder()
                    .withIngredient("dark chocolate")
                    .withIngredient("whipped cream")
                    .withIngredient("hazelnuts")
                    .build();
        } catch (ValidationException ignore) {
            return null;
        }
    }

    public static Pancake milkChocolatePancake() {
        try {
            return new Pancake.Builder()
                    .withIngredient("milk chocolate")
                    .build();
        } catch (ValidationException ignore) {
            return null;
        }
    }
    public static Pancake milkChocolateHazelnutsPancake() {
        try {
            return new Pancake.Builder()
                    .withIngredient("milk chocolate")
                    .withIngredient("hazelnuts")
                    .build();
        } catch (ValidationException ignore) {
            return null;
        }
    }
}
