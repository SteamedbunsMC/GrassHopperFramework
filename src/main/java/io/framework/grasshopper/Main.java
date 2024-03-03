package io.framework.grasshopper;

import com.example.examplemod.MainFrameProxy;
import com.mojang.logging.LogUtils;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;

import net.neoforged.fml.ModLoadingContext;

import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;
import net.minecraft.core.registries.Registries;

public class Main {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Proxy.MODID);
    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Proxy.MODID);
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,Proxy.MODID);
    
    private static final int ITEMNUMBER = Proxy.ItemClassProxy.ITEMPROPS.length;
    private static final int TABNUMBER = Proxy.CreativeTabsClassProxy.TABBUILDERS.length;
    
    public static DeferredItem<Item>[] Items = new DeferredItem[ITEMNUMBER];
    public static DeferredHolder<CreativeModeTab,CreativeModeTab>[] Tabs = new DeferredHolder[TABNUMBER];
    
    public static void main(IEventBus modEventBus) {
        LOGGER.info(">> [" + Proxy.LMODID + "] >> GrassHopperFramework - Initalzation");
        io.depencies.MainInitalzation.Initalzation(modEventBus);
        for (int i = 0;i < ITEMNUMBER;i += 1) {
            Item.Properties ITEMPROP = Proxy.ItemClassProxy.ITEMPROPS[i];
            String ITEMNAME = Proxy.ItemClassProxy.ITEMNAMES[i];
            Items[i] = ITEMS.registerSimpleItem(ITEMNAME,ITEMPROP);
        };
        for (int i = 0;i < TABNUMBER;i += 1) {
            CreativeModeTab TABBUILDER = Proxy.CreativeTabsClassProxy.TABBUILDERS[i];
            String TABNAME = Proxy.CreativeTabsClassProxy.TABNAMES[i];
            Tabs[i] = CREATIVE_MODE_TABS.register(TABNAME,() -> TABBUILDER);
        };
        LOGGER.info(">> [" + Proxy.LMODID + "] >> BlockRegisteration");
        BLOCKS.register(modEventBus);
        LOGGER.info(">> [" + Proxy.LMODID + "] >> ItemRegisteration");
        ITEMS.register(modEventBus);
        LOGGER.info(">> [" + Proxy.LMODID + "] >> CreativeModeTabRegisteration");
        CREATIVE_MODE_TABS.register(modEventBus);
        LOGGER.info(">> [" + Proxy.LMODID + "] >> ConfigFileReading");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MainFrameProxy.ConfigClassProxy.SPEC);
    }
    public static void CommonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info(" >> [" + Proxy.LMODID + "] >> Common Setup Stage");
        io.depencies.MainInitalzation.CommonSetup((event));
    }
}