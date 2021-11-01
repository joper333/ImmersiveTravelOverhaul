package com.joper333.sextant;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.text.TranslatableText;
import java.lang.Math;


import java.util.List;

public class Sextant_item extends Item{


    private float radians;
    private float pitch;
    private float yaw;

    public Sextant_item(Settings settings) {
        super(settings);
    }

    public int getMaxUseTime(ItemStack stack) {
        return 24000;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        playerEntity.playSound(SoundEvents.ITEM_SPYGLASS_USE, 1.0F, 1.0F);
        return ItemUsage.consumeHeldItem(world, playerEntity, hand);
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity playerentity, int remainingUseTicks) {
        PlayerEntity player = playerentity.getEntityWorld().getClosestPlayer(playerentity, 1);
        if (world.getRegistryKey() == World.OVERWORLD && world.isClient) {
            double SkyAng = world.getSkyAngleRadians(radians);
            SkyAng = Math.toDegrees(SkyAng);
            SkyAng = Math.round(SkyAng);
            float Pitch = playerentity.getPitch(pitch);
            float Yaw = MathHelper.wrapDegrees(playerentity.getYaw());
            if(Yaw > 0) {
                Pitch = Pitch + 90;
                Pitch = Math.round(Pitch);
            }
            if(Yaw < 0){
                Pitch = Pitch -90;
                Pitch = Math.abs(Pitch);
                Pitch = Pitch + 180;
                Pitch= Math.round(Pitch);
            }
            /*if(world.isNight()){ //fix isNight always throwing out false
                SkyAng = 0;

            }*/
            Yaw = Math.round(Yaw);
            if(Yaw >= 88 && Yaw <= 92 && Pitch >= SkyAng - 2 && Pitch <= SkyAng + 2 || Yaw >= -92 && Yaw <= -88 && Pitch >= SkyAng - 2 && Pitch <= SkyAng + 2) {
                int X = playerentity.getBlockPos().getX();
                int Z = playerentity.getBlockPos().getZ();
                player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F);
                player.sendMessage(new TranslatableText("My position is X:" + X + " Z:" + Z), true);
            }else
            {player.playSound(SoundEvents.ITEM_SPYGLASS_STOP_USING, 1.0F, 1.0F);}
        }
        else {player.playSound(SoundEvents.ITEM_SPYGLASS_STOP_USING, 1.0F, 1.0F);}
    }
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.sextant.sextant.tooltip").formatted(Formatting.WHITE));
    }
}