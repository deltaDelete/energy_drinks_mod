package ru.deltadelete.energy_drinks_mod.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class EnergyDrinkEffect extends MobEffect {
    public EnergyDrinkEffect() {
        super(MobEffectCategory.NEUTRAL, 0x00CC00);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        assert entity instanceof Player;
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isInstantenous() {
        return false;
    }
}
