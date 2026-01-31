package com.samekoro0321.thermalfin_industrial.common.blocks.energy;

import com.samekoro0321.thermalfin_industrial.common.blockentities.energy.ElectricFurnaceBlockEntity;
import com.samekoro0321.thermalfin_industrial.common.blocks.BaseEnergyBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class ElectricFurnaceBlock extends BaseEnergyBlock {

    public ElectricFurnaceBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    // ブロックエンティティの作成
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ElectricFurnaceBlockEntity(pos, state);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {

        if (!level.isClientSide) {

            BlockEntity blockEntity = level.getBlockEntity(blockPos);

            if (blockEntity instanceof ElectricFurnaceBlockEntity furnace) {
                player.openMenu(furnace, blockPos);
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    // アイテムドロップ処理
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (blockEntity instanceof ElectricFurnaceBlockEntity furnace) {
                for (int i = 0; i < furnace.getItemHandler().getSlots(); i++) {
                    popResource(level, pos, furnace.getItemHandler().getStackInSlot(i));
                }
            }
        }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }
}