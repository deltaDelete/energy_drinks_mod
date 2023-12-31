package ru.deltadelete.energy_drinks_mod;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.platform.Platform;

import java.nio.file.Path;

public class EnergyDrinksModExpectPlatform {
    /**
     * We can use {@link Platform#getConfigFolder()} but this is just an example of {@link ExpectPlatform}.
     * <p>
     * This must be a <b>public static</b> method. The platform-implemented solution must be placed under a
     * platform sub-package, with its class suffixed with {@code Impl}.
     * <p>
     * Example:
     * Expect: ru.deltadelete.energy_drinks_mod.FlashModExpectPlatform#getConfigDirectory()
     * Actual Fabric: ru.deltadelete.energy_drinks_mod.fabric.FlashModExpectPlatformImpl#getConfigDirectory()
     * Actual Forge: ru.deltadelete.energy_drinks_mod.forge.FlashModExpectPlatformImpl#getConfigDirectory()
     * <p>
     * <a href="https://plugins.jetbrains.com/plugin/16210-architectury">You should also get the IntelliJ plugin to help with @ExpectPlatform.</a>
     */
    @ExpectPlatform
    public static Path getConfigDirectory() {
        // Just throw an error, the content should get replaced at runtime.
        throw new AssertionError();
    }
}
