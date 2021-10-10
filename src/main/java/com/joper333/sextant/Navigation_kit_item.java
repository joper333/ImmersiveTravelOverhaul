package com.joper333.sextant;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class Navigation_kit_item extends Item{


    public Navigation_kit_item(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (world.isClient) {
            int X = playerEntity.getBlockPos().getX();
            int Z = playerEntity.getBlockPos().getZ();
            int Y = playerEntity.getBlockPos().getY();
            int seaLevel = Y - 63;
          if(seaLevel < 0)
          {
              if (seaLevel == -1)
              {

                  playerEntity.sendMessage(new TranslatableText("My position is X:" + X + " Z:" + Z +", "+ Math.abs(seaLevel) +" meter below sea level"), true);

              }else {playerEntity.sendMessage(new TranslatableText("My position is X:" + X + " Z:" + Z +", "+ Math.abs(seaLevel) +" meters below sea level"), true); }

          }else if (seaLevel > 0)
          {
              if (seaLevel == 1)
              {
                  playerEntity.sendMessage(new TranslatableText("My position is X:" + X + " Z:" + Z +", "+ Math.abs(seaLevel) +" meter above sea level"), true);

              }else{playerEntity.sendMessage(new TranslatableText("My position is X:" + X + " Z:" + Z +", "+ Math.abs(seaLevel) +" meters above sea level"), true); }

          }else{playerEntity.sendMessage(new TranslatableText("My position is X:" + X + " Z:" + Z +", at sea level"), true);}

            playerEntity.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F);
            //playerEntity.sendMessage(new TranslatableText(String.valueOf(seaLevel)), true);
        }
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.sextant.navigation_kit.tooltip").formatted(Formatting.WHITE));
    }
}