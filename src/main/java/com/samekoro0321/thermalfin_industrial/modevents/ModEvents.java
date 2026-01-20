package com.samekoro0321.thermalfin_industrial.modevents;

import com.samekoro0321.thermalfin_industrial.blockentities.ModBlockEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class ModEvents {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        // PowerBlock
        event.registerBlockEntity(
                Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.POWER_BLOCK.get(),
                (blockEntity, direction) -> blockEntity.getEnergyStorage()
        );

        // EnergyContainerBlock
        event.registerBlockEntity(
                Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.ENERGY_CONTAINER_BLOCK.get(),
                (blockEntity, direction) -> blockEntity.getEnergyStorage()
        );
    }
}
