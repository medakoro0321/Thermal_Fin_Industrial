package com.samekoro0321.thermalfin_industrial.common.blocks;

import com.samekoro0321.thermalfin_industrial.common.blockentities.BaseEnergyBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public abstract class BaseEnergyBlock extends Block implements EntityBlock {

    public BaseEnergyBlock(Properties properties) {
        super(properties);
    }

    // どのBlockEntityを作るか
    @Nullable
    @Override
    public abstract BlockEntity newBlockEntity(BlockPos pos, BlockState state);

    // どのBlockEntityTypeか
    // protected abstract BlockEntityType<?> getBlockEntityType();

    // tick処理を登録
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        // クライアント側では何もしない
        if (level.isClientSide) return null;

        // サーバー側でのみtick
        return (level1, pos, blockState, blockEntity) -> {
            if (blockEntity instanceof BaseEnergyBlockEntity energyBlockEntity) {
                energyBlockEntity.tick(level1, pos, blockState);
            }
        };
    }
}