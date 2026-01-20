package com.samekoro0321.thermalfin_industrial.blockentities;

import com.samekoro0321.thermalfin_industrial.tfienergy.EnergyStorage;

public class CustomEnergyStorage extends EnergyStorage {

    // setEnergyメソッドを持つカスタムクラス
    public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    // NBTから読み込む用のメソッド
    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
