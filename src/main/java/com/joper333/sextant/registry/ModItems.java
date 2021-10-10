package com.joper333.sextant.registry;

import com.joper333.sextant.Barometer_item;
import com.joper333.sextant.Navigation_kit_item;
import com.joper333.sextant.Sextant;
import com.joper333.sextant.Sextant_item;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.List;


public class ModItems{


    public static final Sextant_item SEXTANT = new Sextant_item(new Sextant_item.Settings().group(ItemGroup.TOOLS).maxCount(1));
    public static final Barometer_item BAROMETER = new Barometer_item(new Barometer_item.Settings().group(ItemGroup.TOOLS).maxCount(1));
    public static final Navigation_kit_item NAVIGATION_KIT = new Navigation_kit_item(new Navigation_kit_item.Settings().group(ItemGroup.TOOLS).maxCount(1));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(Sextant.MOD_ID, "sextant"), SEXTANT);
        Registry.register(Registry.ITEM, new Identifier(Sextant.MOD_ID, "barometer"), BAROMETER);
        Registry.register(Registry.ITEM, new Identifier(Sextant.MOD_ID, "navigation_kit"), NAVIGATION_KIT);
    }



}
