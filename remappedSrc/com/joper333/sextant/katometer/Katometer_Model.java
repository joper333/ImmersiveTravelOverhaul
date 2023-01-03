package com.joper333.sextant.katometer;

import com.joper333.sextant.Sextant;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Katometer_Model extends AnimatedGeoModel<Katometer_item>{

    @Override
    public Identifier getModelResource(Katometer_item object) {
        return new Identifier(Sextant.MOD_ID, "geo/katometer.geo.json");
    }

    @Override
    public Identifier getTextureResource(Katometer_item object) {
        return new Identifier(Sextant.MOD_ID, "textures/items/katometer.png");
    }

    @Override
    public Identifier getAnimationResource(Katometer_item animatable) {
        return new Identifier(Sextant.MOD_ID, "animations/katometer.animation.json");
    }
}
