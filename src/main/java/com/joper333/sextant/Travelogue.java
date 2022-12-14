package com.joper333.sextant;

import com.joper333.sextant.gui.TravelogueScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Travelogue extends Item
{
    private static final Text TITLE = Text.translatable("container.simple_backpack.portable_crafter");

    public Travelogue(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getStackInHand(hand);

        if(!world.isClient && !player.isSneaking())
        {
            player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, life) ->
                    new TravelogueScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, life.getBlockPos())), TITLE));
        }

        return TypedActionResult.success(stack);
    }

}