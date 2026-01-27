package com.samekoro0321.thermalfin_industrial.common.modevents;

import com.samekoro0321.thermalfin_industrial.common.blockentities.BaseEnergyBlockEntity;
import com.samekoro0321.thermalfin_industrial.common.blockentities.TFIBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

public class TFIEvents {


    public static final List<DeferredHolder<BlockEntityType<?>, ? extends BlockEntityType<? extends BaseEnergyBlockEntity>>> ENERGY_BLOCK_ENTITIES = List.of(
            TFIBlockEntities.POWER_BLOCKENTITY,
            TFIBlockEntities.ENERGY_CONTAINER_BLOCKENTITY,
            TFIBlockEntities.ELECTRIC_FURNACE_BLOCKENTITY
    );

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent capabilitiesEvent) {
        ENERGY_BLOCK_ENTITIES.forEach(holder ->
                capabilitiesEvent.registerBlockEntity(
                        Capabilities.EnergyStorage.BLOCK,
                        holder.get(),
                        (blockEntity, direction) -> blockEntity.getEnergyStorage()
                )
            );
        }
    }
