package com.samekoro0321.thermalfin_industrial.common.blockentities.energy;

import com.samekoro0321.thermalfin_industrial.common.blockentities.BaseEnergyBlockEntity;
import com.samekoro0321.thermalfin_industrial.common.blockentities.TFIBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ElectricFurnaceBlockEntity extends BaseEnergyBlockEntity {

    private static final int MAX_CAPACITY = 100000;
    private static final int MAX_RECEIVE = 1000;
    private static final int MAX_EXTRACT = 1000;

    public ElectricFurnaceBlockEntity(BlockPos pos, BlockState blockState) {
        super(TFIBlockEntities.ELECTRIC_FURNACE_BLOCKENTITY.get(), pos, blockState, MAX_CAPACITY, MAX_RECEIVE, MAX_EXTRACT);
    }

    public void tick(Level level, BlockPos pos, BlockState blockState) {
        if (level.isClientSide) return;

        receiveEnergyFromNeighbors(level, pos);
    }
}
