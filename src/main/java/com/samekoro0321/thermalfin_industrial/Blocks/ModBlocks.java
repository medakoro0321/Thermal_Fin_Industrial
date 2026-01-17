package com.samekoro0321.thermalfin_industrial.Blocks;

import com.samekoro0321.thermalfin_industrial.BlockItems.ModBlockItems;
import com.samekoro0321.thermalfin_industrial.Thermalfin_industrial;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Thermalfin_industrial.MOD_ID);

    public static final DeferredBlock<Block> TEST_BLOCK = registerBlock(
            "test_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1f)
                    .sound(SoundType.ANVIL)
            )
    );

    // ブロックを追加
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block)
    {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // ブロックアイテムを登録
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModBlockItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void Register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
