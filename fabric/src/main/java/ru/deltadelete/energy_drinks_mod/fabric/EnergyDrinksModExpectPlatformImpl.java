package ru.deltadelete.energy_drinks_mod.fabric;

import ru.deltadelete.energy_drinks_mod.EnergyDrinksModExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class EnergyDrinksModExpectPlatformImpl {
    /**
     * This is our actual method to {@link EnergyDrinksModExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
