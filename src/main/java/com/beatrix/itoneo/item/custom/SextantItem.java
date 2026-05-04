package com.beatrix.itoneo.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

public class SextantItem extends Item {

    public SextantItem(Properties properties) {
        super(properties);
    }


    public static final int USE_DURATION = 1200;
    public static final float ZOOM_FOV_MODIFIER = 0.1F;

    public int getUseDuration(ItemStack p_151222_, LivingEntity p_345399_) {
        return 1200;
    }

    public UseAnim getUseAnimation(ItemStack p_151224_) {
        return UseAnim.SPYGLASS;
    }

    public InteractionResultHolder<ItemStack> use(Level p_151218_, Player p_151219_, InteractionHand p_151220_) {
        p_151219_.playSound(SoundEvents.SPYGLASS_USE, 1.0F, 1.0F);
        p_151219_.awardStat(Stats.ITEM_USED.get(this));
        return ItemUtils.startUsingInstantly(p_151218_, p_151219_, p_151220_);
    }

    public ItemStack finishUsingItem(ItemStack p_151209_, Level p_151210_, LivingEntity p_151211_) {
        this.stopUsing(p_151211_);
        return p_151209_;
    }

    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int remainingUseTicks) {
        Player player = level.getNearestPlayer(livingEntity, 1.0F);
        if (player == null) return;
        if (level.dimension() == Level.OVERWORLD)
        {
            int x = livingEntity.getBlockX();
            int z = livingEntity.getBlockZ();

            double skyAng = level.getSunAngle(1.0f);
            float pitch = livingEntity.getXRot();
            float yaw = Mth.wrapDegrees(livingEntity.getYRot());

            skyAng = Math.round(Math.toDegrees(skyAng));
            double skyAngN = (skyAng + 180);
            if (skyAngN > 360) {
                skyAngN = skyAngN % 360;
            }

            if (yaw > 0) {
                pitch = Math.round(pitch) + 90;
            }
            if (yaw < 0) {
                pitch = pitch - 90;
                pitch = Math.abs(pitch);
                pitch = pitch + 180;
                pitch = Math.round(pitch);
            }
            yaw = Math.round(yaw);

            if (yaw >= 88 && yaw <= 92 && pitch >= skyAng - 2 && pitch <= skyAng + 2 ||
                    yaw >= -92 && yaw <= -88 && pitch >= skyAng - 2 && pitch <= skyAng + 2 ||
                    yaw >= 88 && yaw <= 92 && pitch >= skyAngN - 2 && pitch <= skyAngN + 2 ||
                    yaw >= -92 && yaw <= -88 && pitch >= skyAngN - 2 && pitch <= skyAngN + 2) {

                if (!level.isClientSide) { // Server side: send message and trigger sound
                    player.displayClientMessage(
                            Component.literal("My position is X:" + x + " Z:" + z), true
                    );
                    player.playSound(SoundEvents.BOOK_PAGE_TURN, 1.0F, 1.0F);
                }

            } else {
                player.playSound(SoundEvents.SPYGLASS_STOP_USING, 1.0F, 1.0F);
            }

        } else {
            player.playSound(SoundEvents.SPYGLASS_STOP_USING, 1.0F, 1.0F);
        }
    }

    private void stopUsing(LivingEntity user) {
        user.playSound(SoundEvents.SPYGLASS_STOP_USING, 1.0F, 1.0F);

    }

    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_SPYGLASS_ACTIONS.contains(itemAbility);
    }
}
