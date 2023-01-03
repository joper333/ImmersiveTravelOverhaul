package com.joper333.sextant.viator;

import com.joper333.sextant.Sextant;
import com.joper333.sextant.multi_katometer.MultiKatometer_item;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Viator_Model extends AnimatedGeoModel<Viator_item>{

    @Override
    public Identifier getModelResource(Viator_item object) {
        return new Identifier(Sextant.MOD_ID, "geo/viator.geo.json");
    }

    @Override
    public Identifier getTextureResource(Viator_item object) {
        return new Identifier(Sextant.MOD_ID, "textures/items/viator.png");
    }

    @Override
    public Identifier getAnimationResource(Viator_item animatable) {
        return new Identifier(Sextant.MOD_ID, "animations/viator.animation.json");
    }
}
