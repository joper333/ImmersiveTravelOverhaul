package com.joper333.sextant.registry;

import com.joper333.sextant.*;
import com.joper333.sextant.katometer.Katometer_item;
import com.joper333.sextant.multi_katometer.MultiKatometer_item;
import com.joper333.sextant.telosmeter.Telosmeter_item;
import com.joper333.sextant.viator.Viator_item;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.*;


public class ModItems{

    public static final Sextant_item SEXTANT = new Sextant_item(new Sextant_item.Settings().maxCount(1));
    public static final Barometer_item BAROMETER = new Barometer_item(new Barometer_item.Settings().maxCount(1));
    public static final Navigation_kit_item NAVIGATION_KIT = new Navigation_kit_item(new Navigation_kit_item.Settings().maxCount(1));
    public static final Katometer_item KATOMETER = new Katometer_item(new Katometer_item.Settings().maxCount(1));
    public static final MultiKatometer_item MKATOMETER = new MultiKatometer_item(new MultiKatometer_item.Settings().maxCount(1));
    public static final Telosmeter_item TELOSMETER = new Telosmeter_item(new Telosmeter_item.Settings());
    public static final Viator_item VIATOR = new Viator_item(new Viator_item.Settings().maxCount(1));

    public static void registerItems() {
        Registry.register(Registries.ITEM, new Identifier(Sextant.MOD_ID, "sextant"), SEXTANT);
        Registry.register(Registries.ITEM, new Identifier(Sextant.MOD_ID, "barometer"), BAROMETER);
        Registry.register(Registries.ITEM, new Identifier(Sextant.MOD_ID, "navigation_kit"), NAVIGATION_KIT);
        Registry.register(Registries.ITEM, new Identifier(Sextant.MOD_ID, "katometer"), KATOMETER);
        Registry.register(Registries.ITEM, new Identifier(Sextant.MOD_ID, "multi_katometer"), MKATOMETER);
        Registry.register(Registries.ITEM, new Identifier(Sextant.MOD_ID, "telosmeter"), TELOSMETER);
        Registry.register(Registries.ITEM, new Identifier(Sextant.MOD_ID, "viator"), VIATOR);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(SEXTANT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(BAROMETER));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(NAVIGATION_KIT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(KATOMETER));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(MKATOMETER));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(TELOSMETER));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(VIATOR));
    }
}