package com.joper333.sextant;

import net.fabricmc.api.ModInitializer;
import software.bernie.example.registry.ItemRegistry;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;


import static com.joper333.sextant.registry.ModItems.registerItems;

public class Sextant implements ModInitializer {

    public static final String MOD_ID = "sextant";

    @Override
    public void onInitialize()
    {
        GeckoLib.initialize();
        registerItems();
    }







}