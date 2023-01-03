package com.joper333.sextant.multi_katometer;

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
import net.minecraft.world.World;


import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MultiKatometer_item extends Item {

    public MultiKatometer_item(Settings settings) {
        super(settings);
    }

    public int getMaxUseTime(ItemStack stack) {
        return 25;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
            return ItemUsage.consumeHeldItem(world, playerEntity, hand);
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity playerEntity = user.getEntityWorld().getClosestPlayer(user, 1);
        int X = playerEntity.getBlockPos().getX();
        int Z = playerEntity.getBlockPos().getZ();
        int Y = playerEntity.getBlockPos().getY();
        if (world.isClient && world.getRegistryKey() == World.NETHER) {
            playerEntity.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F);
            int seaLevel = Y - 63;
            if(seaLevel < 0)
            {
                if (seaLevel == -1)
                {

                    playerEntity.sendMessage(Text.translatable("My position is X:" + X + " Z:" + Z +", "+ Math.abs(seaLevel) +" meter below sea level"), true);

                }else {playerEntity.sendMessage(Text.translatable("My position is X:" + X + " Z:" + Z +", "+ Math.abs(seaLevel) +" meters below sea level"), true); }

            }else if (seaLevel > 0)
            {
                if (seaLevel == 1)
                {
                    playerEntity.sendMessage(Text.translatable("My position is X:" + X + " Z:" + Z +", "+ Math.abs(seaLevel) +" meter above sea level"), true);

                }else{playerEntity.sendMessage(Text.translatable("My position is X:" + X + " Z:" + Z +", "+ Math.abs(seaLevel) +" meters above sea level"), true); }

            }else{playerEntity.sendMessage(Text.translatable("My position is X:" + X + " Z:" + Z +", at sea level"), true);}
        }else {
            if (world.isClient) {

                int seaLevel = Y - 63;
                if(seaLevel < 0)
                {
                    if (seaLevel == -1)
                    {
                        playerEntity.sendMessage(Text.translatable("I'm " + Math.abs(seaLevel) +" meter below sea level"), true);

                    }else {playerEntity.sendMessage(Text.translatable("I'm " + Math.abs(seaLevel) +" meters below sea level"), true); }

                }else if (seaLevel > 0)
                {
                    if (seaLevel == 1)
                    {
                        playerEntity.sendMessage(Text.translatable("I'm " + Math.abs(seaLevel) +" meter above sea level"), true);

                    }else{playerEntity.sendMessage(Text.translatable("I'm " + Math.abs(seaLevel) +" meters above sea level"), true); }

                }else{playerEntity.sendMessage(Text.translatable("I'm at sea level"), true);}
            }

        }
        return stack;
    }

        public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.sextant.multi_katometer.tooltip").formatted(Formatting.WHITE));
    }

}