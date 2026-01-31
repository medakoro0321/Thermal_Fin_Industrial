package com.samekoro0321.thermalfin_industrial.common.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

// 継承専用(abstract)
public abstract class BaseEnergyBlockEntity extends BlockEntity {

    public final CustomEnergyStorage energyStorage;
    public final int MAX_RECEIVE;
    public final int MAX_EXTRACT;

    public BaseEnergyBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState blockState, int capacity, int maxReceive, int maxExtract) {
        super(blockEntityType, pos, blockState);

        // フィールドを初期化
        this.energyStorage = new CustomEnergyStorage(capacity, maxReceive, maxExtract);
        this.MAX_RECEIVE = maxReceive;
        this.MAX_EXTRACT = maxExtract;
    }

    public abstract void tick(Level level, BlockPos pos, BlockState blockState);

    // 発電
    public void generateEnergy(int generated) {
        // TODO: 修正
        energyStorage.receiveEnergy(generated, false); // エネルギーを送る
    }

    // 隣接ブロックへエネルギーを送る
    public void distributeEnergy(Level level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = pos.relative(direction);

            // Capability取得
            IEnergyStorage neighborEnergy = level.getCapability(
                    Capabilities.EnergyStorage.BLOCK,
                    neighborPos,
                    direction.getOpposite()
            );

            if (neighborEnergy != null && neighborEnergy.canReceive()) {
                // シュミレーションを先に動かす
                int extracted = energyStorage.extractEnergy(MAX_EXTRACT, true);
                // 隣接ブロックに実際に送る
                int received = neighborEnergy.receiveEnergy(extracted, false);
                // 実際に減らす
                energyStorage.extractEnergy(received, false);

                System.out.println("Sent " + received + " energy"); // ← 追加
            }
        }
    }

    // 隣接ブロックからエネルギーを受け取る
    public void receiveEnergyFromNeighbors(Level level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = pos.relative(direction);

            // Capability取得
            IEnergyStorage neighborEnergy = level.getCapability(
                    Capabilities.EnergyStorage.BLOCK,
                    neighborPos,
                    direction.getOpposite()
            );

            if (neighborEnergy != null && neighborEnergy.canExtract()) {
                int received = energyStorage.receiveEnergy(MAX_RECEIVE, true);
                int extracted = neighborEnergy.extractEnergy(received, false);
                energyStorage.receiveEnergy(extracted, false);
            }
        }
    }


    // エネルギーストレージを取得(Capability登録用)
    public CustomEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    // NBTへの保存(ワールドセーブ時)
    @Override
    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.saveAdditional(compoundTag, provider);
        compoundTag.putInt("Energy", energyStorage.getEnergyStored());
    }

    // NBTからの読み込み(ワールドロード時)
    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.loadAdditional(compoundTag, provider);
        energyStorage.setEnergy(compoundTag.getInt("Energy"));
    }
}
