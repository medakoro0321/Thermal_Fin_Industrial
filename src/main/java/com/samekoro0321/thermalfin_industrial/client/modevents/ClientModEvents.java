package com.samekoro0321.thermalfin_industrial.client.modevents;

import com.samekoro0321.thermalfin_industrial.Thermalfin_industrial;
import com.samekoro0321.thermalfin_industrial.client.screen.TFIBlockScreen;
import com.samekoro0321.thermalfin_industrial.common.menu.TFIMenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = Thermalfin_industrial.MOD_ID, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent registerMenuScreensEvent) {
        registerMenuScreensEvent.register(TFIMenuType.ELECTRIC_FURNACE_BLOCKENTITY_MENU.get(), TFIBlockScreen::new);
    }
}
