package ru.flashmod.fabric;

import ru.flashmod.FlashModExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class FlashModExpectPlatformImpl {
    /**
     * This is our actual method to {@link FlashModExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
