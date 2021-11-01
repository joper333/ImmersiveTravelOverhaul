package com.joper333.sextant.mixins;


import com.joper333.sextant.registry.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Surrogate;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class Spyglass extends LivingEntity {

    protected Spyglass(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author
     */
    @Overwrite
    public boolean isUsingSpyglass() {
        if(this.isUsingItem() && this.getActiveItem().isOf(ModItems.NAVIGATION_KIT)){
            return true;
        }
        if(this.isUsingItem() && this.getActiveItem().isOf(ModItems.SEXTANT)){
            return true;
        }
        else {
            return this.isUsingItem() && this.getActiveItem().isOf(Items.SPYGLASS);
        }
    }
}

