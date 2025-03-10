package org.pancakelab.model;

import java.util.Set;

public class IngredientRules {
    private static final Set<Set<Ingredient>> FORBIDDEN_COMBINATIONS = Set.of(
            Set.of(Ingredient.MILK_CHOCOLATE, Ingredient.WHIPPED_CREAM, Ingredient.MUSTARD),
            Set.of(Ingredient.DARK_CHOCOLATE, Ingredient.MUSTARD)
    );

    public static boolean isValidCombination(Set<Ingredient> ingredients) {
        for (Set<Ingredient> forbidden : FORBIDDEN_COMBINATIONS) {
            if (ingredients.containsAll(forbidden)) {
                return false;
            }
        }

        return true;
    }
}
