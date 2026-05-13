package com.beatrix.itoneo.item;

import com.beatrix.itoneo.Itoneo;
import com.beatrix.itoneo.item.custom.BarometerItem;
import com.beatrix.itoneo.item.custom.MultiKatometer.MultiKatometerItem;
import com.beatrix.itoneo.item.custom.NavigationKitItem;
import com.beatrix.itoneo.item.custom.SextantItem;
import com.beatrix.itoneo.item.custom.katometer.KatometerItem;
import com.beatrix.itoneo.item.custom.telosmeter.TelosmeterItem;
import com.beatrix.itoneo.item.custom.viator.ViatorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Itoneo.MODID);

    public static final DeferredItem<Item> SEXTANT = ITEMS.register("sextant",
            () -> new SextantItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> KATOMETER = ITEMS.register("katometer",
            () -> new KatometerItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> MULTI_KATOMETER = ITEMS.register("multi_katometer",
            () -> new MultiKatometerItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> TELOSMETER = ITEMS.register("telosmeter",
            () -> new TelosmeterItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> BAROMETER = ITEMS.register("barometer",
            () -> new BarometerItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> NAVIGATION_KIT = ITEMS.register("navigation_kit",
            () -> new NavigationKitItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> VIATOR = ITEMS.register("viator",
            () -> new ViatorItem(new Item.Properties().stacksTo(1)));





    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
