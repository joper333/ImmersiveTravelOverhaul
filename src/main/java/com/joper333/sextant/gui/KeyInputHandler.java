package com.joper333.sextant.gui;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_ITO = "key.category.sextant.ito";
    public static final String KEY_MAIN = "key.sextant.main";
    private static final Text TITLE = Text.translatable("container.simple_backpack.portable_crafter");

    public static KeyBinding mainkey;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client ->
        {
            if(mainkey.wasPressed()){
                assert client.player != null;
                client.player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, life) ->
                        new TravelogueScreenHandler(syncId, inventory, ScreenHandlerContext.create(client.world, life.getBlockPos())), TITLE));
            }
        });
    }

    public static void register() {
        mainkey = KeyBindingHelper.registerKeyBinding(new KeyBinding(KEY_MAIN, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_M, KEY_CATEGORY_ITO));
        registerKeyInputs();
    }

}
