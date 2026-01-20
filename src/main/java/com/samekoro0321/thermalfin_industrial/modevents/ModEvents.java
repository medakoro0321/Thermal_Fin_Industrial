package com.samekoro0321.thermalfin_industrial.modevents;

import com.samekoro0321.thermalfin_industrial.blockentities.BaseEnergyBlockEntity;
import com.samekoro0321.thermalfin_industrial.blockentities.ModBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

public class ModEvents {


    public static final List<DeferredHolder<BlockEntityType<?>, ? extends BlockEntityType<? extends BaseEnergyBlockEntity>>> ENERGY_BLOCK_ENTITIES = List.of(
            ModBlockEntities.POWER_BLOCKENTITY,
            ModBlockEntities.ENERGY_CONTAINER_BLOCKENTITY,
            ModBlockEntities.ELECTRIC_FURNACE_BLOCKENTITY
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
