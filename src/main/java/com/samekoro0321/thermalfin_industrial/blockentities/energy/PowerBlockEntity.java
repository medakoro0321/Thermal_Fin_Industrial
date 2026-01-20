package com.samekoro0321.thermalfin_industrial.blockentities.energy;

import com.samekoro0321.thermalfin_industrial.blockentities.BaseEnergyBlockEntity;
import com.samekoro0321.thermalfin_industrial.blockentities.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class PowerBlockEntity extends BaseEnergyBlockEntity {

    private static final int MAX_CAPACITY = 10000; // エネルギーの最大容量
    private static final int MAX_RECEIVE = 100; // 最大入力レート
    private static final int MAX_EXTRACT = 100; // 最大出力レート

    public PowerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.POWER_BLOCKENTITY.get(), pos, blockState, MAX_CAPACITY, MAX_RECEIVE, MAX_EXTRACT);
    }

    public void tick(Level level, BlockPos pos, BlockState blockState) {
        if (level.isClientSide) return;

        generateEnergy(100);
        distributeEnergy(level, pos);
    }

}