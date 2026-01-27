package com.samekoro0321.thermalfin_industrial.common.blockitems;

import com.samekoro0321.thermalfin_industrial.Thermalfin_industrial;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TFIBlockItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Thermalfin_industrial.MOD_ID);


    public static final DeferredItem<Item> TEST = ITEMS.register(
            "test",
            () -> new Item(new Item.Properties())
    );

    public static void Register(IEventBus eventbus)
    {
        ITEMS.register(eventbus);
    }
}
