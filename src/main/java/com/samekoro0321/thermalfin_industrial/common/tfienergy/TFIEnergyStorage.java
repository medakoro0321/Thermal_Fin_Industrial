package com.samekoro0321.thermalfin_industrial.common.tfienergy;

import net.neoforged.neoforge.energy.IEnergyStorage;

public class TFIEnergyStorage implements IEnergyStorage {

    public int energy; // 現在のエネルギー量
    private final int CAPACITY;
    private final int MAX_RECEIVE;
    private final int MAX_EXTRACT;

    public TFIEnergyStorage(int CAPACITY, int MAX_RECEIVE, int MAX_EXTRACT) {
        this.CAPACITY = CAPACITY;
        this.MAX_RECEIVE = MAX_RECEIVE;
        this.MAX_EXTRACT = MAX_EXTRACT;
        this.energy = 0;
    }

    // エネルギーを受け取る
    @Override
    public int receiveEnergy(int MAX_RECEIVE, boolean simulate) {
        // 計算
        int energyReceived = Math.min(CAPACITY - energy, Math.min(this.MAX_RECEIVE, MAX_RECEIVE));
        if (!simulate) {
            // 実際に加算
            energy += energyReceived;
        }
        return energyReceived;
    }

    // エネルギーを取り出す
    @Override
    public int extractEnergy(int MAX_EXTRACT, boolean simulate) {
        // 計算
        int energyExtracted = Math.min(energy, Math.min(this.MAX_EXTRACT, MAX_EXTRACT));
        if (!simulate) {
            // 実際に引算
            energy -= energyExtracted;
        }
        return energyExtracted;
    }


    /**
     * エネルギー量を取得
     * @return [int] エネルギー量を返す
     */
    @Override
    public int getEnergyStored() {
        return energy;
    }

    @Override
    public int getMaxEnergyStored() {
        return CAPACITY;
    }

    @Override
    public boolean canExtract() {
        return MAX_EXTRACT > 0;
    }

    @Override
    public boolean canReceive() {
        return MAX_RECEIVE > 0;
    }

    // 直接エネルギー値を設定(NBT読み込み用)
    public void setEnergy(int energy) {
        this.energy = Math.min(energy, CAPACITY);
    }
}