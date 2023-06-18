package ru.deltadelete.energy_drinks_mod.fabric;

import ru.deltadelete.energy_drinks_mod.EnergyDrinksMod;
import net.fabricmc.api.ModInitializer;

public class EnergyDrinksModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        EnergyDrinksMod.init();
    }
}
