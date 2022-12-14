package com.joper333.sextant;

import com.joper333.sextant.gui.ModScreenHandlerRegister;
import net.fabricmc.api.ModInitializer;
import software.bernie.geckolib3.GeckoLib;


import static com.joper333.sextant.registry.ModItems.registerItems;

public class Sextant implements ModInitializer {

    public static final String MOD_ID = "sextant";

    @Override
    public void onInitialize()
    {
        GeckoLib.initialize();
        registerItems();
        ModScreenHandlerRegister.registerAllScreenHandlers();
    }







}