package com.joper333.sextant.mixins;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.DebugHud;


import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.Locale;


@Mixin (DebugHud.class)
public abstract class Coordinates extends DrawableHelper {

    //i think this can be done better with slices? also how fix ugly gap in the menu, idk
    @Redirect(
            method = "getLeftText",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", ordinal = 0))
    private String XYZ(Locale l, String format, Object[] args) {
        return "";
    }

    @Redirect(
            method = "getLeftText",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", ordinal = 3))
    private String Block(String format, Object[] args) {
        return "";
    }

    @Redirect(
            method = "getLeftText",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", ordinal = 4))
    private String Chunk(String format, Object[] args) {
        return "";
    }

    //this is a horrible way to do this, and it looks ugly, but I have no idea on how to do this another way. plz help
    @Redirect(
            method = "getRightText",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;getZ()I"))
    private int Z(BlockPos instance) {
        return 0;
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