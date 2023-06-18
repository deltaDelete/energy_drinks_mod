package ru.deltadelete.energy_drinks_mod.forge;

import ru.deltadelete.energy_drinks_mod.EnergyDrinksModExpectPlatform;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class EnergyDrinksModExpectPlatformImpl {
    /**
     * This is our actual method to {@link EnergyDrinksModExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
