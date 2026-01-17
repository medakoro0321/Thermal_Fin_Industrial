package com.samekoro0321.thermalfin_industrial;

import com.mojang.logging.LogUtils;
import com.samekoro0321.thermalfin_industrial.BlockItems.ModBlockItems;
import com.samekoro0321.thermalfin_industrial.Blocks.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(Thermalfin_industrial.MOD_ID)
public class Thermalfin_industrial {
    public static final String MOD_ID = "thermalfin_industrial";
    // slf4jロガーを直接参照します
    public static final Logger LOGGER = LogUtils.getLogger();

    // FML は IEventBus や ModContainer のような特定のパラメータ型を認識し、それらを自動的に渡します。
    /// <summary>
    /// mod クラスのコンストラクタ
    /// </summary>
    public Thermalfin_industrial(IEventBus modEventBus, ModContainer modContainer) {
        // mod のロードのために commonSetup メソッドを登録します
        modEventBus.addListener(this::commonSetup);

        // サーバーやその他の興味のあるゲームイベントのために、このクラス自体を登録します。
        // 注意: これは、このクラス（Abyssolith）がイベントに直接応答したい場合にのみ必要です。
        // 下にある onServerStarting() のような @SubscribeEvent アノテーションが付いた関数がこのクラスにない場合は、この行を追加しないでください。
        NeoForge.EVENT_BUS.register(this);


        ModBlockItems.Register(modEventBus); // アイテム
        ModBlocks.Register(modEventBus); // ブロック
        /*
        ModBlockEntities.register(modEventBus); // ブロックエンティティ
        ModMenuTypes.register(modEventBus); // メニュータイプ
        */

        // クリエイティブタブにアイテムを登録します
        modEventBus.addListener(this::addCreative);

        // FML が設定ファイルを自動的に作成およびロードできるように、mod の ModConfigSpec を登録します
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    // ビルディングブロックタブに、例のブロックアイテムを追加します
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS)
        {
            /*
            event.accept(ModItems.BISMUTH);
            event.accept(ModItems.RAW_BISMUTH);
             */
        }
    }

    // @SubscribeEvent を使用して、イベントバスに呼び出すメソッドを見つけさせることができます
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}