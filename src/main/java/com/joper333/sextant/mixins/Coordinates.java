package com.joper333.sextant.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.DebugHud;


import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Locale;


@Mixin (DebugHud.class)
public abstract class Coordinates extends DrawableHelper {


    //i think this can be done better with slices? also how fix ugly gap in the menu, idk
    @Redirect(
            method = "getLeftText",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", ordinal = 3))
    private String XYZ(Locale l, String format, Object[] args) {
        return "";
    }

    @Redirect(
            method = "getLeftText",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", ordinal = 4))
    private String Block(Locale l, String format, Object[] args) {
        return "";
    }

    @Redirect(
            method = "getLeftText",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", ordinal = 5))
    private String chunk(Locale l, String format, Object[] args) {
        return "";
    }

    //this is a horrible way to do this, and it looks ugly, but I have no idea on how to do this another way. plz help
    @Redirect(
            method = "getRightText",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;getZ()I"))
    private int Z(BlockPos instance) {
        return 0;
        //(int) Math.floor(Math.random() * 9) <-- could return this instead if it didn't run every tick
    }

    @Redirect(
            method = "getRightText",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;getY()I"))
    private int Y(BlockPos instance) {
        return 0;
    }

    @Redirect(
            method = "getRightText",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;getX()I"))
    private int X(BlockPos instance) {
        return 0;
    }
}