package com.samekoro0321.thermalfin_industrial.datagens;


import com.samekoro0321.thermalfin_industrial.datagens.Providers.TFIBlockStateProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;



public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent gatherDataEvent) {
        DataGenerator dataGenerator = gatherDataEvent.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        ExistingFileHelper existingFileHelper = gatherDataEvent.getExistingFileHelper();

        dataGenerator.addProvider(gatherDataEvent.includeClient(), new TFIBlockStateProvider(packOutput, existingFileHelper));
    }
}
