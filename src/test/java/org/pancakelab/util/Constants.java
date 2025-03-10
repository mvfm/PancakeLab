package org.pancakelab.util;

import org.pancakelab.model.DeliveryAddress;
import org.pancakelab.model.Pancake;
import org.pancakelab.model.PancakePresets;

public class Constants {
    public static final DeliveryAddress DA_VALID = new DeliveryAddress(1, 101);
    public static final DeliveryAddress DA_INVALID = new DeliveryAddress(1, 1);
    public static final Pancake PP_DARK_CHOCOLATE = PancakePresets.darkChocolatePancake();
    public static final Pancake PP_MILK_CHOCOLATE = PancakePresets.milkChocolatePancake();
}
