package com.samekoro0321.thermalfin_industrial.Blocks;

import com.samekoro0321.thermalfin_industrial.BlockEntities.ModBlockEntities;
import com.samekoro0321.thermalfin_industrial.BlockEntities.Energy.PowerBlockEntity;
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

        // クライアント側では何もしない
        if (level.isClientSide) {
            return null;
        }

        // サーバー側でのみtick
        return createServerTicker(type);
    }

    /**
     * Tick登録ヘルパー関数
     * @param type Minecraftから渡される型
     * @return 一致するならtick処理を返してしない場合nullを返す
     */
    @Nullable
    private <T extends BlockEntity> BlockEntityTicker<T> createServerTicker(BlockEntityType<T> type) {
        // PowerBlockEntityの型かチェック
        if (type == ModBlockEntities.POWER_BLOCK.get()) {
            // PowerBlockEntityのtickメソッドを返す
            return (lvl, pos, st, blockEntity) -> {
                ((PowerBlockEntity) blockEntity).tick(lvl, pos, st);
            };
        }
        return null; // 違う型なら何もしない
    }
}
