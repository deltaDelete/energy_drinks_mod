package ru.flashmod;

import com.google.common.base.Suppliers;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registries;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class FlashMod {
    public static final String MOD_ID = "flashmod";
    // We can use this if we don't want to use DeferredRegister
    public static final Supplier<Registries> REGISTRIES = Suppliers.memoize(() -> Registries.get(MOD_ID));
    // Registering a new creative tab
    public static final CreativeModeTab EXAMPLE_TAB = CreativeTabRegistry.create(new ResourceLocation(MOD_ID, "flash_tab"), () ->
            new ItemStack(FlashMod.EXAMPLE_ITEM.get()));
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_REGISTRY);
    public static final RegistrySupplier<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () ->
            new Item(new Item.Properties().tab(FlashMod.EXAMPLE_TAB)));
    public static final RegistrySupplier<Item> FLASH_ORIGINAL = ITEMS.register("flash_original", () ->
            new FlashBase(new Item.Properties().tab(FlashMod.EXAMPLE_TAB)));
    
    public static void init() {
        ITEMS.register();
        
        // System.out.println(FlashModExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}
