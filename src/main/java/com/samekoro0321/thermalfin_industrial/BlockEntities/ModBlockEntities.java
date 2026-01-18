package com.samekoro0321.thermalfin_industrial.BlockEntities;

import com.samekoro0321.thermalfin_industrial.BlockEntities.Energy.PowerBlockEntity;
import com.samekoro0321.thermalfin_industrial.Blocks.ModBlocks;
import com.samekoro0321.thermalfin_industrial.Thermalfin_industrial;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Thermalfin_industrial.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PowerBlockEntity>> POWER_BLOCK =
            registerBlockEntity(
                    "power_block",
                    PowerBlockEntity::new,
                    ModBlocks.POWER_BLOCK
            );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PowerBlockEntity>> ENERGY_CONTAINER_BLOCK =
            registerBlockEntity(
                    "energy_container_block",
                    PowerBlockEntity::new,
                    ModBlocks.ENERGY_CONTAINER_BLOCK
            );



    /**
     * BlockEntityを登録するヘルパーメソッド
     * @param name ブロック名
     * @param blockEntity BlockEntity作成関数(AnyThingBlockEntity::new)
     * @param block 対応するブロック(ModBLocks.ANYTHING_BLOCK)
     */
    private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> registerBlockEntity(String name, BlockEntitySupplier<T> blockEntity, DeferredHolder<Block, ? extends Block> block) {
        return BLOCK_ENTITIES.register(name, () ->
                BlockEntityType.Builder.of(
                        blockEntity,
                        block.get()
                ).build(null)
        );
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}