package com.beatrix.itoneo.item.custom.katometer;

import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ItemRenderer extends GeoItemRenderer<KatometerItem> {
    public ItemRenderer() {
        super(new KatometerModel());
    }
}