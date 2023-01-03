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

import java.util.List;

public class Navigation_kit_item extends Item{

    private float radians;
    private float pitch;
    private float yaw;

    public Navigation_kit_item(Settings settings) {
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
        if (world.getRegistryKey() == World.OVERWORLD) {
            double SkyAng = world.getSkyAngleRadians(radians);
            SkyAng = Math.toDegrees(SkyAng);
            SkyAng = Math.round(SkyAng);
            float Pitch = playerentity.getPitch(pitch);
            float Yaw = MathHelper.wrapDegrees(playerentity.getYaw());
            double SkyAngN = SkyAng + 180;
            if (SkyAngN > 360){
                SkyAngN= SkyAngN % 360;
            }
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
            Yaw = Math.round(Yaw);
            if(Yaw >= 88 && Yaw <= 92 && Pitch >= SkyAng - 2 && Pitch <= SkyAng + 2 || Yaw >= -92 && Yaw <= -88 && Pitch >= SkyAng - 2 && Pitch <= SkyAng + 2 ||
                    Yaw >= 88 && Yaw <= 92 && Pitch >= SkyAngN - 2 && Pitch <= SkyAngN + 2 || Yaw >= -92 && Yaw <= -88 && Pitch >= SkyAngN - 2 && Pitch <= SkyAngN + 2) {
                int X = playerentity.getBlockPos().getX();
                int Z = playerentity.getBlockPos().getZ();
                int Y = playerentity.getBlockPos().getY();
                player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F);
                int seaLevel = Y - 63;
                if(seaLevel < 0)
                {
                    if (seaLevel == -1)
                    {

                        player.sendMessage(Text.translatable("My position is" + " X:" + X + " Z:" + Z +", "+ Math.abs(seaLevel) +" meter below sea level"), true);

                    }else {player.sendMessage(Text.translatable("My position is" + " X:" + X + " Z:" + Z +", "+ Math.abs(seaLevel) +" meters below sea level"), true); }

                }else if (seaLevel > 0)
                {
                    if (seaLevel == 1)
                    {
                        player.sendMessage(Text.translatable("My position is" + " X:" + X + " Z:" + Z +", "+ Math.abs(seaLevel) +" meter above sea level"), true);

                    }else{player.sendMessage(Text.translatable("My position is" + " X:" + X + " Z:" + Z +", "+ Math.abs(seaLevel) +" meters above sea level"), true); }

                }else{player.sendMessage(Text.translatable("My position is" + " X:" + X + " Z:" + Z +", at sea level"), true);}

            }else {player.playSound(SoundEvents.ITEM_SPYGLASS_STOP_USING, 1.0F, 1.0F);}
        }else {player.playSound(SoundEvents.ITEM_SPYGLASS_STOP_USING, 1.0F, 1.0F);}
    }

    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.sextant.navigation_kit.tooltip").formatted(Formatting.WHITE));
    }
}