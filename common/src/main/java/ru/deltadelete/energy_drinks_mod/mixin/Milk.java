package ru.deltadelete.energy_drinks_mod.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.SoftOverride;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import ru.deltadelete.energy_drinks_mod.effects.EnergyDrinkEffect;
import ru.deltadelete.energy_drinks_mod.registry.EffectsRegistry;


@Mixin(MilkBucketItem.class)
public class Milk {
//    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;removeAllEffects()Z"),
//            method = "finishUsingItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/item/ItemStack;")
//    public void finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity, CallbackInfoReturnable<ItemStack> cir) {
//        if (livingEntity.getActiveEffects().stream().anyMatch(mobEffectInstance ->
//                mobEffectInstance.getEffect() instanceof EnergyDrinkEffect
//        )) {
//            livingEntity.removeAllEffects();
//            livingEntity.addEffect(new MobEffectInstance(EffectsRegistry.DIARRHEA.get(), 24000));
//        } else {
//            livingEntity.removeAllEffects();
//        }
//    }

    private boolean hasEffect = false;

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;removeAllEffects()Z"),
            method = "finishUsingItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/item/ItemStack;")
    public void PreEffectsRemove(ItemStack itemStack, Level level, LivingEntity livingEntity, CallbackInfoReturnable<ItemStack> cir) {
        hasEffect = (livingEntity.getActiveEffects().stream().anyMatch(mobEffectInstance ->
                mobEffectInstance.getEffect() instanceof EnergyDrinkEffect
        ));
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;removeAllEffects()Z", shift = At.Shift.AFTER),
            method = "finishUsingItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/item/ItemStack;")
    public void PostEffectsRemove(ItemStack itemStack, Level level, LivingEntity livingEntity, CallbackInfoReturnable<ItemStack> cir) {
        if (hasEffect)
            livingEntity.addEffect(new MobEffectInstance(EffectsRegistry.DIARRHEA.get(), 24000));
    }
}
