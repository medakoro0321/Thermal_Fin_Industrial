package com.samekoro0321.thermalfin_industrial.Blocks.Energy;

import com.samekoro0321.thermalfin_industrial.BlockEntities.Energy.PowerBlockEntity;
import com.samekoro0321.thermalfin_industrial.BlockEntities.ModBlockEntities;
import com.samekoro0321.thermalfin_industrial.Blocks.BaseEnergyBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
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

    @Override
    protected BlockEntityType<?> getBlockEntityType() {
        return ModBlockEntities.POWER_BLOCK.get();
    }
}