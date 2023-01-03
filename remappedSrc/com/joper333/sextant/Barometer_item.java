package com.joper333.sextant;

import java.lang.*;

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

public class Barometer_item extends Item{


    private MinecraftClient client;

    public Barometer_item(Settings settings) {
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

        if (world.isClient) {
            playerEntity.playSound(SoundEvents.BLOCK_LAVA_POP, 0.8F, 1.2F);
            playerEntity.playSound(SoundEvents.ITEM_BOTTLE_FILL, 0.9F, 0.1F);
            int Y = playerEntity.getBlockPos().getY();
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

            //playerEntity.sendMessage(new TranslatableText(String.valueOf(seaLevel)), true);
        }
        return stack;
    }

        public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.sextant.barometer.tooltip").formatted(Formatting.WHITE));
    }
}
