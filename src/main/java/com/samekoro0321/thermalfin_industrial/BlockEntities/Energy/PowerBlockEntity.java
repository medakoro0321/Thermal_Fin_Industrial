package com.samekoro0321.thermalfin_industrial.BlockEntities.Energy;

import com.samekoro0321.thermalfin_industrial.BlockEntities.CustomEnergyStorage;
import com.samekoro0321.thermalfin_industrial.BlockEntities.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

public class PowerBlockEntity extends BlockEntity {

    private static final int MAX_CAPACITY = 10000; // エネルギーの最大容量
    private static final int MAX_RECEIVE = 100; // 最大入力レート
    private static final int MAX_EXTRACT = 100; // 最大出力レート

    // カスタムEnergyStorageクラスを使用
    private final CustomEnergyStorage energyStorage = new CustomEnergyStorage(MAX_CAPACITY, MAX_RECEIVE, MAX_EXTRACT);

    public PowerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.POWER_BLOCK.get(), pos, blockState);
    }

    // tick処理(Blockクラスで登録する)
    public void tick(Level level, BlockPos pos, BlockState blockState) {
        if (level.isClientSide) return;

        generateEnergy();
        distributeEnergy(level, pos);
    }

    // 発電処理
    private void generateEnergy() {
        int generated = 10; // 1tickあたり10エネルギー生成
        energyStorage.receiveEnergy(generated, false); // エネルギーを送る
    }

    // 隣接ブロックへエネルギーを送る
    private void distributeEnergy(Level level, BlockPos pos) {
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