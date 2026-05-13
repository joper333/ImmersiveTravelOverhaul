package com.beatrix.itoneo.item.custom.katometer;

import com.beatrix.itoneo.Itoneo;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class KatometerItemRenderer extends GeoItemRenderer<KatometerItem> {
    public KatometerItemRenderer() {
        super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(Itoneo.MODID, "katometer")));
    }
}