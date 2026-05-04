package com.beatrix.itoneo.item.custom.katometer;

import com.beatrix.itoneo.Itoneo;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.GeckoLib;

public class KatometerModel {
    private final ResourceLocation model = new ResourceLocation(Itoneo.MODID, "itoneo/example.geo.json");
    private final ResourceLocation texture = new ResourceLocation(Itoneo.MODID, "textures/item/example.png");
    private final ResourceLocation animations = new ResourceLocation(Itoneo.MODID, "animations/example.animation.json");

    @Override
    public ResourceLocation getModelLocation(KatometerItem object) {
        return this.model;
    }

    @Override
    public ResourceLocation getTextureLocation(KatometerItem object) {
        return this.texture;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(KatometerItem object) {
        return this.animations;
    }
}
