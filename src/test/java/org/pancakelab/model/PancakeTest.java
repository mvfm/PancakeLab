package org.pancakelab.model;

import org.junit.jupiter.api.Test;
import org.pancakelab.exception.NoIngredientsException;
import org.pancakelab.exception.ValidationException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PancakeTest {

    @Test
    void shouldBuildValidPancake() throws ValidationException {
        Pancake pancake = new Pancake.Builder()
                .withIngredient("dark chocolate")
                .withIngredient("milk chocolate")
                .build();

        assertNotNull(pancake);
        Set<Ingredient> ingredients = pancake.getIngredients();
        assertEquals(2, ingredients.size());
        assertTrue(ingredients.contains(Ingredient.DARK_CHOCOLATE));
        assertTrue(ingredients.contains(Ingredient.MILK_CHOCOLATE));
    }

    @Test
    void shouldThrowExceptionForInvalidIngredient() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Pancake.Builder()
                    .withIngredient("magic dust");
        });

        assertEquals("Invalid ingredient: magic dust", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNoIngredientsProvided() {
        NoIngredientsException exception = assertThrows(NoIngredientsException.class, () -> {
            new Pancake.Builder().build();
        });

        assertEquals("At least one ingredient must be added", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForInvalidIngredientCombination() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Pancake.Builder()
                    .withIngredient("mustard")
                    .withIngredient("dark chocolate")
                    .build();
        });

        assertTrue(exception.getMessage().contains("Forbidden pancake ingredient combination"));
    }

    @Test
    void shouldGenerateCorrectDescription() throws ValidationException {
        Pancake pancake = new Pancake.Builder()
                .withIngredient("dark chocolate")
                .withIngredient("milk chocolate")
                .build();

        String description = pancake.getDescription();
        assertEquals("Delicious pancake with dark chocolate, milk chocolate!", description);
    }

    @Test
    void testEqualsAndHashCode() throws ValidationException {
        Pancake one = new Pancake.Builder()
                .withIngredient("dark chocolate")
                .build();

        Pancake two = new Pancake.Builder()
                .withIngredient("dark chocolate")
                .build();

        Pancake three = new Pancake.Builder()
                .withIngredient("milk chocolate")
                .build();

        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());

        assertNotEquals(one, three);
    }

    @Test
    void testToString() throws ValidationException {
        Pancake pancake = new Pancake.Builder()
                .withIngredient("dark chocolate")
                .withIngredient("milk chocolate")
                .build();

        String result = pancake.toString();
        assertTrue(result.contains("ingredients="));
        assertTrue(result.contains("DARK_CHOCOLATE"));
        assertTrue(result.contains("MILK_CHOCOLATE"));
    }
}
