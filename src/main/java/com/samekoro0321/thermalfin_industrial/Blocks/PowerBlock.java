package com.samekoro0321.thermalfin_industrial.Blocks;

import com.samekoro0321.thermalfin_industrial.BlockEntities.ModBlockEntities;
import com.samekoro0321.thermalfin_industrial.BlockEntities.PowerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class PowerBlock extends Block implements EntityBlock {
    public PowerBlock(Properties properties) {
        super(properties);
    }

    // BlockEntityを作成
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new PowerBlockEntity(pos, blockState);
    }

    // tick処理を登録
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        if (level.isClientSide) {
            return null; // クライアント側では処理しない
        }

        // サーバー側でのみtickを実行
        return createTickerHelper(type, ModBlockEntities.POWER_BLOCK.get(),
                (lvl, pos, st, blockEntity) -> blockEntity.tick(lvl, pos, st));
    }

    // ヘルパーメソッド
    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(
            BlockEntityType<A> type, BlockEntityType<E> expectedType, BlockEntityTicker<? super E> ticker) {
        return expectedType == type ? (BlockEntityTicker<A>) ticker : null;
    }
}
