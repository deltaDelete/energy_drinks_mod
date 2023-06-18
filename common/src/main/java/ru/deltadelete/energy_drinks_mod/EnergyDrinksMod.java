package ru.deltadelete.energy_drinks_mod;

import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import ru.deltadelete.energy_drinks_mod.registry.EffectsRegistry;
import ru.deltadelete.energy_drinks_mod.registry.ItemsRegistry;

public class EnergyDrinksMod {
    public static final String MOD_ID = "energy_drinks_mod";
    // Registering a new creative tab
    public static final CreativeModeTab MOD_TAB = CreativeTabRegistry.create(new ResourceLocation(MOD_ID, "main"), () -> new ItemStack(ItemsRegistry.FLASH_ORIGINAL.get()));

    public static final DamageSource HEART_ATTACK = new CustomDamageSource("heart_attack");

    public static void init() {
        ItemsRegistry.registerAll();
        EffectsRegistry.registerAll();

        System.out.println(EnergyDrinksModExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}
