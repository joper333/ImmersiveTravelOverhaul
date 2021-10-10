package com.joper333.sextant;

import net.fabricmc.api.ModInitializer;


import static com.joper333.sextant.registry.ModItems.registerItems;

public class Sextant implements ModInitializer {

    public static final String MOD_ID = "sextant";

    @Override
    public void onInitialize()
    {
        registerItems();

    }






}