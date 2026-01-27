package com.samekoro0321.thermalfin_industrial.datagens.Providers;

import com.samekoro0321.thermalfin_industrial.Thermalfin_industrial;
import com.samekoro0321.thermalfin_industrial.common.blocks.TFIBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TFIBlockStateProvider extends BlockStateProvider {
    public TFIBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Thermalfin_industrial.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // TODO : 将来的にforEachとか使って一斉にできるようにしたい
        registerBlockProviders(TFIBlocks.TEST_BLOCK.get(),models().withExistingParent("test_block",mcLoc("block/cobblestone")));
    }

    private void registerBlockProviders(Block models, ModelFile modelFile) {
        simpleBlock(models, modelFile);
    }
}
