package com.joper333.sextant.telosmeter;

import com.joper333.sextant.Sextant;
import com.joper333.sextant.multi_katometer.MultiKatometer_item;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Telosmeter_Model extends AnimatedGeoModel<Telosmeter_item> {

    @Override
    public Identifier getModelResource(Telosmeter_item object) {
        return new Identifier(Sextant.MOD_ID, "geo/telosmeter.geo.json");
    }

    @Override
    public Identifier getTextureResource(Telosmeter_item object) {
        return new Identifier(Sextant.MOD_ID, "textures/items/telosmeter.png");
    }

    @Override
    public Identifier getAnimationResource(Telosmeter_item animatable) {
        return new Identifier(Sextant.MOD_ID, "animations/telosmeter.animation.json");
    }
}