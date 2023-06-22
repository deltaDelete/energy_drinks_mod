package ru.deltadelete.energy_drinks_mod.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registries;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import ru.deltadelete.energy_drinks_mod.EnergyDrinksMod;

import java.util.function.Supplier;

import ru.deltadelete.energy_drinks_mod.effects.DiarrheaEffect;
import ru.deltadelete.energy_drinks_mod.effects.EnergyDrinkEffect;

public enum EffectsRegistry {
    ENERGY_DRINK("energy_drink", EnergyDrinkEffect::new),
    DIARRHEA("diarrhea", DiarrheaEffect::new);

    private final String pathName;
    private final Supplier<MobEffect> mobEffectSupplier;
    private MobEffect effect;

    EffectsRegistry(String pathName, Supplier<MobEffect> mobEffectSupplier) {
        this.pathName = pathName;
        this.mobEffectSupplier = mobEffectSupplier;
    }

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(EnergyDrinksMod.MOD_ID, Registry.MOB_EFFECT_REGISTRY);

    public static void registerAll() {
        for (EffectsRegistry item :
                values()) {
            MOB_EFFECTS.register(item.pathName, item::get);
        }
        MOB_EFFECTS.register();
    }

    public MobEffect get() {
        if (effect == null) {
            effect = mobEffectSupplier.get();
        }
        return effect;
    }

    public String getId() {
        return Registry.MOB_EFFECT.getResourceKey(get()).toString();
    }
}
