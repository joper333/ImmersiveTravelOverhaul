package com.beatrix.itoneo.item.custom.telosmeter;

import com.beatrix.itoneo.Itoneo;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class TelosmeterItemRenderer extends GeoItemRenderer<TelosmeterItem> {
    public TelosmeterItemRenderer() {
        super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(Itoneo.MODID, "telosmeter")));
    }
}