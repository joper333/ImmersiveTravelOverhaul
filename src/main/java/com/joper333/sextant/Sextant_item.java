package com.joper333.sextant;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.text.TranslatableText;


import java.util.List;
import java.util.Locale;

public class Sextant_item extends Item{


    private MinecraftClient client;

    public Sextant_item(Settings settings) {
        super(settings);
    }

    private float spyglassScale;

   @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (world.isClient) {
            int X = playerEntity.getBlockPos().getX();
            int Z = playerEntity.getBlockPos().getZ();
            playerEntity.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F);
            playerEntity.sendMessage(new TranslatableText("My position is X:" + X + " Z:" + Z), true);
        }
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.sextant.sextant.tooltip").formatted(Formatting.WHITE));
    }

    /*public static final int MAX_USE_TIME = 1200;
    public static final float field_30922 = 0.1F;

    public int getMaxUseTime(ItemStack stack) {
        return 1200;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPYGLASS;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.playSound(SoundEvents.ITEM_SPYGLASS_USE, 1.0F, 0.5F);
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        float f = this.client.getLastFrameDuration();
        this.spyglassScale = MathHelper.lerp(0.5F * f, this.spyglassScale, 1.125F);
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    public void render(MatrixStack matrices, float tickDelta) {


    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        this.playStopUsingSound(user);
        return stack;
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        this.playStopUsingSound(user);
    }

    private void playStopUsingSound(LivingEntity user) {
        user.playSound(SoundEvents.ITEM_SPYGLASS_STOP_USING, 1.0F, 1.0F);
    }*/
}


