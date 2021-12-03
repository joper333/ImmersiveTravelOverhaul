package com.joper333.sextant.mixins;


import com.joper333.sextant.registry.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public abstract class Spyglass extends LivingEntity {

    protected Spyglass(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Redirect(method = "isUsingSpyglass", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean spyglass(ItemStack instance, Item item){
        if(this.isUsingItem() && this.getActiveItem().isOf(ModItems.NAVIGATION_KIT)){
            return true;
        }
        if(this.isUsingItem() && this.getActiveItem().isOf(ModItems.SEXTANT)){
            return true;
        }
        return this.isUsingItem() && this.getActiveItem().isOf(Items.SPYGLASS);
    }
}

