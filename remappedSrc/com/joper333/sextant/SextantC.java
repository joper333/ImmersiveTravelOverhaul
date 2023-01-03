package com.joper333.sextant;

import com.joper333.sextant.katometer.Katometer_Renderer;
import com.joper333.sextant.multi_katometer.MultiKatometer_Renderer;
import com.joper333.sextant.registry.ModItems;
import com.joper333.sextant.telosmeter.Telosmeter_Renderer;
import com.joper333.sextant.viator.Viator_Renderer;
import net.fabricmc.api.ClientModInitializer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class SextantC implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        GeoItemRenderer.registerItemRenderer(ModItems.KATOMETER, new Katometer_Renderer());
        GeoItemRenderer.registerItemRenderer(ModItems.MKATOMETER, new MultiKatometer_Renderer());
        GeoItemRenderer.registerItemRenderer(ModItems.TELOSMETER, new Telosmeter_Renderer());
        GeoItemRenderer.registerItemRenderer(ModItems.VIATOR, new Viator_Renderer());

    }
}