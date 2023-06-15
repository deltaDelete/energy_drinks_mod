package ru.flashmod;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class FlashBase extends Item {
    public FlashBase(Properties settings) {
        super(settings.food(new FoodProperties.Builder()
                .alwaysEat()
                .nutrition(100)
                .saturationMod(1f)
                .effect(new MobEffectInstance(MobEffects.SATURATION, 100, 100), 1)
                .build()));
    }

//    @Override
//    public InteractionResultHolder<ItemStack> use(Level world, Player playerEntity, InteractionHand hand) {
//        playerEntity.playSound(SoundEvents.GENERIC_DRINK, 1.0F, 1.0F);
//        return InteractionResultHolder.success(playerEntity.getItemInHand(hand));
//    }
}
