package com.samekoro0321.thermalfin_industrial.common.blockentities.energy;

import com.samekoro0321.thermalfin_industrial.common.blockentities.BaseEnergyBlockEntity;
import com.samekoro0321.thermalfin_industrial.common.blockentities.TFIBlockEntities;
import com.samekoro0321.thermalfin_industrial.common.menu.TFIBlockMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class ElectricFurnaceBlockEntity extends BaseEnergyBlockEntity implements MenuProvider {

    private static final int MAX_CAPACITY = 100000;
    private static final int MAX_RECEIVE = 1000;
    private static final int MAX_EXTRACT = 1000;

    public ElectricFurnaceBlockEntity(BlockPos pos, BlockState blockState) {
        super(TFIBlockEntities.ELECTRIC_FURNACE_BLOCKENTITY.get(), pos, blockState,
                MAX_CAPACITY, MAX_RECEIVE, MAX_EXTRACT);
    }

    // インベントリ (9スロット)
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(9) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };


    // MenuProvider 実装
    @Override
    public Component getDisplayName() {
        return Component.translatable("container.thermalfin_industrial.electric_furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new TFIBlockMenu(containerId, inventory, this);
    }

    // tick処理
    @Override
    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide) return;

        receiveEnergyFromNeighbors(level, pos);
        // TODO: 精錬処理
    }

    // インベントリアクセス
    public ItemStackHandler getItemHandler() {
        return itemStackHandler;
    }

    // NBT保存
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        tag.put("Inventory", itemStackHandler.serializeNBT(provider));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        itemStackHandler.deserializeNBT(provider, tag.getCompound("Inventory"));
    }
}