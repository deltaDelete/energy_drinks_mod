package ru.flashmod.forge;

import dev.architectury.platform.forge.EventBuses;
import ru.flashmod.FlashMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FlashMod.MOD_ID)
public class FlashModForge {
    public FlashModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(FlashMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        FlashMod.init();
    }
}
