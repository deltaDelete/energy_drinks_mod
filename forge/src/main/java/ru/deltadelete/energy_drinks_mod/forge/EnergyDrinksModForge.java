package ru.deltadelete.energy_drinks_mod.forge;

import dev.architectury.platform.forge.EventBuses;
import ru.deltadelete.energy_drinks_mod.EnergyDrinksMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EnergyDrinksMod.MOD_ID)
public class EnergyDrinksModForge {
    public EnergyDrinksModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(EnergyDrinksMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        EnergyDrinksMod.init();
    }
}
