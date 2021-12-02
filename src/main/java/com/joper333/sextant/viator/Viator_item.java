package com.joper333.sextant.viator;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;

public class Viator_item extends Item implements IAnimatable, ISyncable {
    AnimationFactory factory = new AnimationFactory(this);
    private static final String controllerName = "useController";
    public static final int ANIM = 0;

    public Viator_item(Settings settings) {
        super(settings);
        GeckoLibNetwork.registerSyncable(this);
    }

    public int getMaxUseTime(ItemStack stack) {
        return 30;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimationData data)
    {
        @SuppressWarnings({ "unchecked", "rawtypes" })
        AnimationController<Viator_item> controller = new AnimationController(this, controllerName, 1,
                this::predicate);

        data.addAnimationController(controller);

    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (!world.isClient) {
            final int id = GeckoLibUtil.guaranteeIDForStack(playerEntity.getStackInHand(hand), (ServerWorld) world);
            GeckoLibNetwork.syncAnimation(playerEntity, this, id, ANIM);
            for (PlayerEntity otherPlayer : PlayerLookup.tracking(playerEntity)) {
                GeckoLibNetwork.syncAnimation(otherPlayer, this, id, ANIM);
            }
        }
        return ItemUsage.consumeHeldItem(world, playerEntity, hand);
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        final ClientPlayerEntity playerEntity = MinecraftClient.getInstance().player;
        int X = playerEntity.getBlockPos().getX();
        int Z = playerEntity.getBlockPos().getZ();
        int Y = playerEntity.getBlockPos().getY();
        if (world.isClient) {
            playerEntity.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F);
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
        }
        return stack;
    }

    @Override
    public void onAnimationSync(int id, int state) {
        if (state == ANIM) {
            // Always use GeckoLibUtil to get AnimationControllers when you don't have
            // access to an AnimationEvent
            @SuppressWarnings("rawtypes")
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, controllerName);

            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("use", false));
            }
        }
    }

        public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.sextant.multi_katometer.tooltip").formatted(Formatting.WHITE));
    }
}