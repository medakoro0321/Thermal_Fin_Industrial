package com.samekoro0321.thermalfin_industrial.common.blockentities;

import com.samekoro0321.thermalfin_industrial.common.tfienergy.TFIEnergyStorage;

public class CustomEnergyStorage extends TFIEnergyStorage {

    // setEnergyメソッドを持つカスタムクラス
    public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    // NBTから読み込む用のメソッド
    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
