package com.joper333.sextant;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import java.lang.Math;


import java.util.List;

public class Sextant_item extends Item{


    private float skyAngleRadians;

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

    //this whole thing probably shouldn't be in the class for the item, but too late.
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity playerentity, int remainingUseTicks) {
        PlayerEntity player = playerentity.getEntityWorld().getClosestPlayer(playerentity, 1);
        if (world.getRegistryKey() == World.OVERWORLD) //check if world = to overworld
        {
            int X = playerentity.getBlockPos().getX();
            int Z = playerentity.getBlockPos().getZ();
            double SkyAng = world.getSkyAngleRadians(skyAngleRadians); //get the angle of the sun in radians
            float Pitch = playerentity.getPitch();
            float Yaw = MathHelper.wrapDegrees(playerentity.getYaw());//get the angle based on the f3 menu

            //convert sun angle to degrees and then round
            SkyAng = Math.round(Math.toDegrees(SkyAng));
            double SkyAngN = (SkyAng + 180); //sky angle for night time
            if (SkyAngN > 360){
                SkyAngN= SkyAngN % 360;
            }
            //logic for turning pitch into a full 360 rotation
            if (Yaw > 0) {
                Pitch = Math.round(Pitch) + 90;
            }
            if (Yaw < 0) {
                //pretty sure this can be 1 statement
                Pitch = Pitch - 90;
                Pitch = Math.abs(Pitch);
                Pitch = Pitch + 180;
                Pitch = Math.round(Pitch); //weird way of basically multiplying it by -1 and adding 90, I was sleep-deprived ok
            }
            Yaw = Math.round(Yaw);
            //checking if player is looking at sun with 2 degrees of error
                if (Yaw >= 88 && Yaw <= 92 && Pitch >= SkyAng - 2 && Pitch <= SkyAng + 2 || Yaw >= -92 && Yaw <= -88 && Pitch >= SkyAng - 2 && Pitch <= SkyAng + 2 ||
                        Yaw >= 88 && Yaw <= 92 && Pitch >= SkyAngN - 2 && Pitch <= SkyAngN + 2 || Yaw >= -92 && Yaw <= -88 && Pitch >= SkyAngN - 2 && Pitch <= SkyAngN + 2) {
                    if (world.isClient) //isClient to make sure server doesn't try to run sendMessage, which causes a crash
                    {

                        player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F);
                        player.sendMessage(Text.translatable("My position is X:" + X + " Z:" + Z ), true);
                    }
                } else {
                    player.playSound(SoundEvents.ITEM_SPYGLASS_STOP_USING, 1.0F, 1.0F);
                }
            }else {player.playSound(SoundEvents.ITEM_SPYGLASS_STOP_USING, 1.0F, 1.0F);
        }

    }

    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.sextant.sextant.tooltip").formatted(Formatting.WHITE));
    }
}