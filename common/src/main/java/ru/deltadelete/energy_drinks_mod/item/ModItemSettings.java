package ru.deltadelete.energy_drinks_mod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item.Properties;
import ru.deltadelete.energy_drinks_mod.EnergyDrinksMod;
import ru.deltadelete.energy_drinks_mod.registry.EffectsRegistry;

public class ModItemSettings extends Properties {
    public static Properties base() {
        return new ModItemSettings();
    }

    public static Properties noStack() {
        return new ModItemSettings().stacksTo(1);
    }

//	public static QuiltItemSettings food(Foods food) {
//		return new ModItemSettings().food(food.get());
//	}
//
//	public static QuiltItemSettings food(Foods food, Item remainder, int maxCount) {
//		return new ModItemSettings().food(food.get()).recipeRemainder(remainder).maxCount(maxCount);
//	}

    public static Properties drink() {
        return new ModItemSettings().food(
                new FoodProperties.Builder()
                        .alwaysEat()
                        .nutrition(2)
                        .saturationMod(10)
                        .effect(new MobEffectInstance(MobEffects.REGENERATION, 1000, 2), 1)
                        .effect(new MobEffectInstance(EffectsRegistry.ENERGY_DRINK.get(), 24000), 1)
                        .build()
        );
    }

    public ModItemSettings() {
        super();
        tab(EnergyDrinksMod.MOD_TAB);
    }
}
