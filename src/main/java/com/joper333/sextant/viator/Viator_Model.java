package com.joper333.sextant.viator;

import com.joper333.sextant.Sextant;
import com.joper333.sextant.multi_katometer.MultiKatometer_item;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Viator_Model extends AnimatedGeoModel<Viator_item>{

    @Override
    public Identifier getModelLocation(Viator_item object) {
        return new Identifier(Sextant.MOD_ID, "geo/viator.geo.json");
    }

    @Override
    public Identifier getTextureLocation(Viator_item object) {
        return new Identifier(Sextant.MOD_ID, "textures/items/viator.png");
    }

    @Override
    public Identifier getAnimationFileLocation(Viator_item animatable) {
        return new Identifier(Sextant.MOD_ID, "animations/viator.animation.json");
    }
}
