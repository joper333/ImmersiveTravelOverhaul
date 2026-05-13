package com.beatrix.itoneo.item.custom;

import com.beatrix.itoneo.item.custom.katometer.KatometerItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class BarometerItem extends Item {

    public BarometerItem(Item.Properties properties) {
        super(properties);
    }

    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 20;
    }

    public UseAnim getUseAnimation(ItemStack p_151224_) {
        return UseAnim.BLOCK;
    }


    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player && !level.isClientSide()) {
            int y = livingEntity.getBlockY();
            // Server side: send message and trigger sound
            player.displayClientMessage(Component.literal("My height is " + y), true);
            player.playSound(SoundEvents.BOOK_PAGE_TURN, 1.0F, 1.0F);
        }
        return itemStack;
    }


    // Let's handle our use method so that we activate the animation when right-clicking while holding the box
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        System.out.print("blaaahhh");
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));

    }

}
