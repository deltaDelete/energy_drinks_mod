package ru.deltadelete.energy_drinks_mod.registry;

import com.mojang.datafixers.util.Pair;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import ru.deltadelete.energy_drinks_mod.EnergyDrinksMod;

import java.util.function.Supplier;

import ru.deltadelete.energy_drinks_mod.item.EnergyDrink;

public enum ItemsRegistry {
    FLASH_ORIGINAL("flash_original", EnergyDrink::new),
    FLASH_BERRY("flash_berry", () -> new EnergyDrink(
            new Pair<>(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1000, 1), 1f)
    )),
    FLASH_GOLD("flash_gold", () -> new EnergyDrink(
            new Pair<>(new MobEffectInstance(MobEffects.CONFUSION, 1000), 1f),
            new Pair<>(new MobEffectInstance(MobEffects.ABSORPTION, 1000, 1), 1f)
    )),
    FLASH_ORANGE("flash_orange", () -> new EnergyDrink(
            new Pair<>(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1000), 1f)
    )),
    FLASH_LIME("flash_lime", () -> new EnergyDrink(
            new Pair<>(new MobEffectInstance(MobEffects.JUMP, 1000, 1), 1f)
    )),
    BLACK_ROCKET("black_rocket", () -> new EnergyDrink(
            new Pair<>(new MobEffectInstance(MobEffects.DIG_SPEED, 1000), 1f)
    )),
    // MILK("minecraft:milk_bucket", () -> new Milk(new Item.Properties())),
    CAN("can", () -> new Item(new Item.Properties().tab(EnergyDrinksMod.MOD_TAB)));

    private final String pathName;
    private final Supplier<Item> itemSupplier;
    private final Integer burnTime;
    private Item item;

    public static final DeferredRegister<Item> ITEMS
            = DeferredRegister.create(EnergyDrinksMod.MOD_ID, Registry.ITEM_REGISTRY);

    ItemsRegistry(String pathName, Supplier<Item> itemSupplier) {
        this(pathName, itemSupplier, null);
    }

    ItemsRegistry(String pathName, Supplier<Item> itemSupplier, Integer burnTime) {
        this.pathName = pathName;
        this.itemSupplier = itemSupplier;
        this.burnTime = burnTime;
    }

    public static void registerAll() {
        for (ItemsRegistry item :
                values()) {
            ITEMS.register(item.pathName, item::get);
        }
        ITEMS.register();
    }

    public Item get() {
        if (item == null) {
            item = itemSupplier.get();
        }
        return item;
    }

    public String getId() {
        return Registry.ITEM.getKey(get()).toString();
    }
}
