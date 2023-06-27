package ru.deltadelete.energy_drinks_mod.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.deltadelete.energy_drinks_mod.effects.EnergyDrinkEffect;
import ru.deltadelete.energy_drinks_mod.registry.EffectsRegistry;


@Mixin(MilkBucketItem.class)
public abstract class Milk {
    public boolean hasEffect = false;

    @Inject(at = @At("HEAD"),
            method = "finishUsingItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/item/ItemStack;")
    public void PreEffectsRemove(ItemStack stack, Level level, LivingEntity livingEntity, CallbackInfoReturnable<ItemStack> cir) {
        if (!level.isClientSide) {
            hasEffect = livingEntity.hasEffect(EffectsRegistry.ENERGY_DRINK.get());
        }
    }

    @Inject(at = @At("RETURN"),
            method = "finishUsingItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/item/ItemStack;")
    public void PostEffectsRemove(ItemStack stack, Level level, LivingEntity livingEntity, CallbackInfoReturnable<ItemStack> cir) {
        if (!level.isClientSide && hasEffect) {
            livingEntity.addEffect(new MobEffectInstance(EffectsRegistry.DIARRHEA.get(), 24000));
        }
    }
}
