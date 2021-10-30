package com.joper333.sextant;

import java.lang.*;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class Barometer_item extends Item{


    private MinecraftClient client;

    public Barometer_item(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (world.isClient) {
            int Y = playerEntity.getBlockPos().getY();
            int seaLevel = Y - 63;
          if(seaLevel < 0)
          {
              if (seaLevel == -1)
              {
                  playerEntity.sendMessage(new TranslatableText("I'm " + Math.abs(seaLevel) +" meter below sea level"), true);

              }else {playerEntity.sendMessage(new TranslatableText("I'm " + Math.abs(seaLevel) +" meters below sea level"), true); }

          }else if (seaLevel > 0)
          {
              if (seaLevel == 1)
              {
                  playerEntity.sendMessage(new TranslatableText("I'm " + Math.abs(seaLevel) +" meter above sea level"), true);

              }else{playerEntity.sendMessage(new TranslatableText("I'm " + Math.abs(seaLevel) +" meters above sea level"), true); }

          }else{playerEntity.sendMessage(new TranslatableText("I'm at sea level"), true);}

            //playerEntity.sendMessage(new TranslatableText(String.valueOf(seaLevel)), true);
        }
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.sextant.barometer.tooltip").formatted(Formatting.WHITE));
    }
}
