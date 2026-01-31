package com.samekoro0321.thermalfin_industrial.common.menu;

import com.samekoro0321.thermalfin_industrial.common.blockentities.energy.ElectricFurnaceBlockEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;

public class TFIBlockMenu extends AbstractContainerMenu {
    private final ElectricFurnaceBlockEntity blockEntity;
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_ROW_COUNT * HOTBAR_SLOT_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    public TFIBlockMenu(int containerId, Inventory playerInventory, ElectricFurnaceBlockEntity  blockEntity) {
        super(TFIMenuType.ELECTRIC_FURNACE_BLOCKENTITY_MENU.get(), containerId);
        this.blockEntity = blockEntity;

        // ブロックのインベントリスロット（3x3）
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                this.addSlot(new SlotItemHandler(blockEntity.getItemHandler(),
                        col + row * 3, 62 + col * 18, 17 + row * 18));
            }
        }

        // プレイヤーインベントリ
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9,
                        8 + col * 18, 84 + row * 18));
            }
        }

        // ホットバー
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            newStack = originalStack.copy();

            if (index < 9) {
                // ブロックのインベントリからプレイヤーインベントリへ
                if (!this.moveItemStackTo(originalStack, 9, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // プレイヤーインベントリからブロックのインベントリへ
                if (!this.moveItemStackTo(originalStack, 0, 9, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return newStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.blockEntity.getLevel().getBlockEntity(this.blockEntity.getBlockPos()) == this.blockEntity
                && player.distanceToSqr(this.blockEntity.getBlockPos().getX() + 0.5,
                this.blockEntity.getBlockPos().getY() + 0.5,
                this.blockEntity.getBlockPos().getZ() + 0.5) <= 64.0;
    }
}
