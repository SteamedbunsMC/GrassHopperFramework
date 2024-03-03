package com.example.examplemod;

import com.example.examplemod.*;
import net.neoforged.bus.api.IEventBus;

public class MainFrameProxy {
    public static final String MODID = ModInfo.MODID;
    public static final String LMODID = ModInfo.LMODID;
    public class ModClassProxy extends com.example.examplemod.ExampleMod {
        public ModClassProxy(IEventBus modEventBus) {
            super(modEventBus);
        }
    }
    public class ConfigClassProxy extends com.example.examplemod.Config {}
    public class CreativeTabsClassProxy extends com.example.examplemod.CreativeTabs.CreativeTabs {}
    public class ItemClassProxy extends com.example.examplemod.Items.Items {}
}