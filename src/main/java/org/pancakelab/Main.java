package org.pancakelab;

import org.pancakelab.exception.ValidationException;
import org.pancakelab.model.DeliveryAddress;
import org.pancakelab.model.Order;
import org.pancakelab.model.Pancake;
import org.pancakelab.model.PancakePresets;
import org.pancakelab.repository.InMemoryOrderRepository;
import org.pancakelab.service.OrderService;
import org.pancakelab.service.OrderServiceImpl;

import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            OrderService orderService = new OrderServiceImpl(new InMemoryOrderRepository());
            Order one = orderService.createOrder(new DeliveryAddress(1, 101));
            Order two = orderService.createOrder(new DeliveryAddress(2, 201));
            Order three = orderService.createOrder(new DeliveryAddress(3, 301));

            // PancakePresets
            orderService.addPancake(one, PancakePresets.darkChocolatePancake(), 1);
            orderService.prepareOrder(one);

            // Pancake.Build
            Pancake customPancake1 = new Pancake.Builder()
                            .withIngredient("dark chocolate")
                            .withIngredient("milk chocolate")
                            .build();
            Pancake customPancake2 = new Pancake.Builder()
                    .withIngredient("dark chocolate")
                    .withIngredient("whipped cream")
                    .build();

            orderService.addPancake(two, customPancake1, 2);
            orderService.addPancake(two, customPancake2, 3);
            orderService.removePancakes(two, customPancake2, 1);
            orderService.prepareOrder(two);
            orderService.deliverOrder(two);

            orderService.addPancake(three, PancakePresets.darkChocolateWhippedCreamHazelnutsPancake(), 4);
            orderService.cancelOrder(three);
            // orderService.deliverOrder(three);    // Cant deliver an order that was already delivered.
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
        }

        /*
        try {
            Pancake one = new Pancake.Builder()
                    .withIngredient("dark chocolate")
                    .build();
            logger.info(one.getDescription());
            System.out.println(one.getDescription());

            Pancake two = PancakePresets.darkChocolateWhippedCreamPancake();
            System.out.println(two.getDescription());

            Pancake three = new Pancake.Builder()
                    .withIngredient("dark chocolate")
                    .withIngredient("milk chocolate")
                    .build();
            System.out.println(three.getDescription());


            // This will fail...
            Pancake four = new Pancake.Builder()
                    .withIngredient("dark chocolate")
                    .withIngredient("milk chocolate")
                    .withIngredient("mustard")
                    .build();
            System.out.println(four.getDescription());

            Order firstOrder = new Order(new DeliveryAddress(1, 101));
            firstOrder.addPancake(one, 1);
            firstOrder.addPancake(two, 2);
            firstOrder.addPancake(three, 3);
            System.out.println(firstOrder);

            firstOrder.prepare();
            firstOrder.deliver();
        } catch (ValidationException ve) {
            System.out.println(ve.getMessage());
        }
        */
    }
}
