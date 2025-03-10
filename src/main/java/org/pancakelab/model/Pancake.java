package org.pancakelab.model;

import org.pancakelab.exception.NoIngredientsException;
import org.pancakelab.exception.ValidationException;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Pancake {
    private final Set<Ingredient> ingredients;

    private Pancake(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return "Delicious pancake with " + ingredients.stream()
                .map(Enum::name)
                .map(String::toLowerCase)
                .map(string -> string.replaceAll("_", " "))
                .collect(Collectors.joining(", ")) + "!";
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pancake pancake = (Pancake) o;
        return Objects.equals(ingredients, pancake.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ingredients);
    }

    @Override
    public String toString() {
        return "Pancake{" +
                "ingredients=" + ingredients +
                '}';
    }

    public static class Builder {
        private final Set<Ingredient> ingredients = EnumSet.noneOf(Ingredient.class);

        public Builder withIngredient(String ingredientName) {
            Ingredient ingredient;

            try {
                ingredient = Ingredient.valueOf(ingredientName.replaceAll(" ", "_").toUpperCase());
                ingredients.add(ingredient);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid ingredient: " + ingredientName);
            }

            return this;
        }

        public Pancake build() throws ValidationException {
            if (ingredients.isEmpty()) {
                throw new NoIngredientsException("At least one ingredient must be added");
            }

            if (!IngredientRules.isValidCombination(ingredients)) {
                throw new IllegalArgumentException("Forbidden pancake ingredient combination: " + ingredients);
            }

            return new Pancake(ingredients);
        }
    }
}

