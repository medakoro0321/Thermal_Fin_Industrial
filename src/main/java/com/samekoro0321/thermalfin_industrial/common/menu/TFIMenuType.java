package com.samekoro0321.thermalfin_industrial.common.menu;

import com.samekoro0321.thermalfin_industrial.Thermalfin_industrial;
import com.samekoro0321.thermalfin_industrial.common.blockentities.energy.ElectricFurnaceBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TFIMenuType {
    public static final DeferredRegister<MenuType<?>> MENU_TYPE = DeferredRegister.create(Registries.MENU, Thermalfin_industrial.MOD_ID);

    public static final Supplier<MenuType<TFIBlockMenu>> ELECTRIC_FURNACE_BLOCKENTITY_MENU =
            MENU_TYPE.register(
                    "electric_furnace_blockentity_menu", () ->
                    IMenuTypeExtension.create(
                            (windowId, inventory, data) -> {
                        return new TFIBlockMenu(
                                windowId,
                                inventory,
                                (ElectricFurnaceBlockEntity) inventory.player.level().getBlockEntity(data.readBlockPos()));
                    }));


    public static void register(IEventBus eventBus) {
        MENU_TYPE.register(eventBus);
    }

    /*
    private static <T> DeferredHolder<?,?> registerBlockMenu(String name) {
        MENU_TYPE.register(
                name,
                () ->

        );
    }
     */
}
