package com.samekoro0321.thermalfin_industrial.BlockEntities;

import com.samekoro0321.thermalfin_industrial.Blocks.ModBlocks;
import com.samekoro0321.thermalfin_industrial.Thermalfin_industrial;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {

    // DeferredRegisterの作成
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Thermalfin_industrial.MOD_ID); // MODIDに変更

    // PowerBlockEntityの登録
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PowerBlockEntity>> POWER_BLOCK =
            BLOCK_ENTITIES.register("power_block", () ->
                    BlockEntityType.Builder.of(
                            PowerBlockEntity::new,
                            ModBlocks.POWER_BLOCK.get() // ブロックの登録
                    ).build(null)
            );

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}