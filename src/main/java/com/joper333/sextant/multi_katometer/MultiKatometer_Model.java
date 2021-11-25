package com.joper333.sextant.multi_katometer;

import com.joper333.sextant.Sextant;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MultiKatometer_Model extends AnimatedGeoModel<MultiKatometer_item>{

    @Override
    public Identifier getModelLocation(MultiKatometer_item object) {
        return new Identifier(Sextant.MOD_ID, "geo/multi_katometer.geo.json");
    }

    @Override
    public Identifier getTextureLocation(MultiKatometer_item object) {
        return new Identifier(Sextant.MOD_ID, "textures/items/multi_katometer.png");
    }

    @Override
    public Identifier getAnimationFileLocation(MultiKatometer_item animatable) {
        return new Identifier(Sextant.MOD_ID, "animations/katometer.animation.json");
    }
}
