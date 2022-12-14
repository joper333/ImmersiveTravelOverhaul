package com.joper333.sextant.gui;

import com.joper333.sextant.Sextant;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModScreenHandlerRegister {
    public static ScreenHandlerType<TravelogueScreenHandler> TRAVELOGUE_SCREEN;

    public static void registerAllScreenHandlers() {
        TRAVELOGUE_SCREEN = Registry.register(Registry.SCREEN_HANDLER, Sextant.MOD_ID, new ScreenHandlerType<>(TravelogueScreenHandler::new));
    }

}