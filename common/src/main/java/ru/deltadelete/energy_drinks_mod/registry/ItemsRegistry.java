package ru.deltadelete.energy_drinks_mod.registry;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.intellij.lang.annotations.Identifier;
import ru.deltadelete.energy_drinks_mod.EnergyDrinksMod;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import ru.deltadelete.energy_drinks_mod.effects.EnergyDrinkEffect;
import ru.deltadelete.energy_drinks_mod.item.ModItemSettings;

public enum ItemsRegistry {
    FLASH_ORIGINAL("flash_original", () -> new Item(ModItemSettings.drink())
    {
        @Override
        public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
            MobEffectInstance currentEffect = null;

            for (MobEffectInstance effect:
                    user.getActiveEffects()) {
                if (effect.getEffect() instanceof EnergyDrinkEffect) {
                    currentEffect = effect;
                    break;
                }
            }
            if (currentEffect != null) {
                user.hurt(EnergyDrinksMod.HEART_ATTACK, 1000);
            }

            return super.finishUsingItem(stack, world, user);
        }
    }
    ),
    FLASH_BERRY("flash_berry", () -> new Item(ModItemSettings.drink()));

    private final String pathName;
    private final Supplier<Item> itemSupplier;
    private final Integer burnTime;
    private Item item;

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(EnergyDrinksMod.MOD_ID, Registry.ITEM_REGISTRY);

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
            // Registry.register(Registry.ITEM, new ResourceLocation(EnergyDrinksMod.MOD_ID, item.pathName), item.get());
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
