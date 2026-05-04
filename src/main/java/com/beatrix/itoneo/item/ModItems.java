package com.beatrix.itoneo.item;

import com.beatrix.itoneo.Itoneo;
import com.beatrix.itoneo.item.custom.SextantItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Itoneo.MODID);

    public static final DeferredItem<Item> SEXTANT = ITEMS.register("sextant",
            () -> new SextantItem(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
