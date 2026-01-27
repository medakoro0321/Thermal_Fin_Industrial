package com.samekoro0321.thermalfin_industrial.common.blocks.energy;

import com.samekoro0321.thermalfin_industrial.common.blockentities.energy.PowerBlockEntity;
import com.samekoro0321.thermalfin_industrial.common.blocks.BaseEnergyBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class PowerBlock extends BaseEnergyBlock {

    public PowerBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PowerBlockEntity(pos, state);
    }
}