package com.beatrix.itoneo.item.custom.MultiKatometer;

import com.beatrix.itoneo.Itoneo;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class MultiKatometerItemRenderer extends GeoItemRenderer<MultiKatometerItem> {
    public MultiKatometerItemRenderer() {
        super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(Itoneo.MODID, "multi_katometer")));
    }
}