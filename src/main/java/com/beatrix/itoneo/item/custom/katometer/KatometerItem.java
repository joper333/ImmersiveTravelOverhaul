package com.beatrix.itoneo.item.custom.katometer;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public final class KatometerItem extends Item implements GeoItem {
    private static final RawAnimation ACTIVATE_ANIM = RawAnimation.begin().thenPlay("use.activate");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public KatometerItem(Properties properties) {
        super(properties);

        // Register our item as server-side handled.
        // This enables both animation data syncing and server-side animation triggering
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 20;
    }

    public UseAnim getUseAnimation(ItemStack p_151224_) {
        return UseAnim.BLOCK;
    }


    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player && !level.isClientSide()) {
            if (level.dimension() == Level.NETHER) {
                int x = livingEntity.getBlockX();
                int z = livingEntity.getBlockZ();
                     // Server side: send message and trigger sound
                player.displayClientMessage(Component.literal("My position is X:" + x + " Z:" + z), true);
                player.playSound(SoundEvents.BOOK_PAGE_TURN, 1.0F, 1.0F);
                }
            }
        return itemStack;
    }

    // Utilise our own render hook to define our custom renderer
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private KatometerItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new KatometerItemRenderer();

                return this.renderer;
            }
        });
    }
    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("katometer", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0,this::predicate));
        // We've marked the "activate" animation as being triggerable from the server
    }

    // Let's handle our use method so that we activate the animation when right-clicking while holding the box
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        System.out.print("blaaahhh");

        if (level instanceof ServerLevel serverLevel)
            triggerAnim(player, GeoItem.getOrAssignId(player.getItemInHand(hand), serverLevel), "Activation", "activate");
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }



}