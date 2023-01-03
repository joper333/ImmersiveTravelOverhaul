package com.joper333.sextant.telosmeter;


import net.minecraft.client.item.TooltipContext;
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
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;

public class Telosmeter_item extends Item implements IAnimatable {
    AnimationFactory factory = new AnimationFactory(this);

    public Telosmeter_item(Settings settings) {
        super(settings);
    }

    public int getMaxUseTime(ItemStack stack) {
        return 25;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("eyedle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));

    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (world.getRegistryKey() == World.END) {
            return ItemUsage.consumeHeldItem(world, playerEntity, hand);
        }
        return ItemStack.EMPTY.use(world, playerEntity, hand);
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity playerEntity = user.getEntityWorld().getClosestPlayer(user, 1);
        int X = playerEntity.getBlockPos().getX();
        int Z = playerEntity.getBlockPos().getZ();
        if (world.isClient && world.getRegistryKey() == World.END) {
            playerEntity.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F);
            playerEntity.sendMessage(Text.translatable("My position is X:" + X + " Z:" + Z), true);

            //playerEntity.sendMessage(new TranslatableText(String.valueOf(seaLevel)), true);
        }
        return stack;
    }


        public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.sextant.telosmeter.tooltip").formatted(Formatting.WHITE));
    }

}
