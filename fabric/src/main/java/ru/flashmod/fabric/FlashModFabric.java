package ru.flashmod.fabric;

import ru.flashmod.FlashMod;
import net.fabricmc.api.ModInitializer;

public class FlashModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        FlashMod.init();
    }
}
