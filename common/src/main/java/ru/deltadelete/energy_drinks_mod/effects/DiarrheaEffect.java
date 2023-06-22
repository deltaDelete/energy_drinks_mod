package ru.deltadelete.energy_drinks_mod.effects;

import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleGroup;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import ru.deltadelete.energy_drinks_mod.CustomDamageSource;

public class DiarrheaEffect extends MobEffect {

    public static final DamageSource DIARRHEA_DAMAGE_SOURCE = new CustomDamageSource("diarrhea");

    public DiarrheaEffect() {
        super(MobEffectCategory.HARMFUL, 0x7b5804);
    }

    private int tick = 0;
    @Override
    public void applyEffectTick(LivingEntity entity, int i) {
        if (tick <= 1200) {
            tick++;
            return;
        }
        double x = entity.getX();
        double y = entity.getY() + 0.5;
        double z = entity.getZ();
        ItemEntity item = new ItemEntity(
                entity.level,
                x,
                y,
                z,
                new ItemStack(Items.DIRT)
        );
        item.setPickUpDelay(1200);
        entity.level.addFreshEntity(item);
        // entity.level.explode(entity, new CustomDamageSource("diarrhea"), new ExplosionDamageCalculator(), x, y, z, 3, false, Explosion.BlockInteraction.NONE);
        if (entity instanceof Player player) {
            player.level.playSound(player, x, y, z, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1f, 1f);
            player.hurt(DIARRHEA_DAMAGE_SOURCE, 2f);
            for (int j = 0; j < 10; j++) {
                entity.level.addAlwaysVisibleParticle(ParticleTypes.EXPLOSION, x, y, z, 0, 0, 0);
            }
        }
        tick = 0;
        super.applyEffectTick(entity, i);
    }

    @Override
    public boolean isDurationEffectTick(int i, int j) {
        return true;
    }
}
