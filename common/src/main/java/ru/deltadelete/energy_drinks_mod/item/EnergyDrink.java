package ru.deltadelete.energy_drinks_mod.item;

import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.deltadelete.energy_drinks_mod.EnergyDrinksMod;
import ru.deltadelete.energy_drinks_mod.effects.EnergyDrinkEffect;

import java.util.List;

public class EnergyDrink extends Item {

    public EnergyDrink() {
        super(ModItemSettings.drink().rarity(Rarity.UNCOMMON));
    }

    @SafeVarargs
    public EnergyDrink(Pair<MobEffectInstance, Float>... effects) {
        super(
                ModItemSettings.drink(effects).rarity(Rarity.UNCOMMON)
        );
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        MobEffectInstance currentEffect = null;

        for (MobEffectInstance effect :
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

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        var effects = itemStack.getItem().getFoodProperties().getEffects();
        for (Pair<MobEffectInstance, Float> effect : effects) {
            var effectInstance = effect.getFirst();
            float seconds = effectInstance.getDuration() * 0.05F;
            String format;
            String durationString;
            if (seconds >= 3600) {
                format = "%d:%02d:%02d";
                durationString = String.format(format, (int)(seconds/3600), (int)((seconds % 3600) / 60), (int)(seconds % 60));
            } else {
                format = "%02d:%02d";
                durationString = String.format(format, (int)((seconds % 3600) / 60), (int)(seconds % 60));
            }
            list.add(
                    effect.getFirst().getEffect().getDisplayName().copy()
                            .append(Component.literal(" ("))
                            .append(Component.literal(durationString))
                            .append(Component.literal(")"))
                            .setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE))
            );
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (this.isEdible()) {
            ItemStack itemStack = player.getItemInHand(interactionHand);
            if (player.canEat(this.getFoodProperties().canAlwaysEat())) {
                player.startUsingItem(interactionHand);
                return InteractionResultHolder.consume(itemStack);
            } else {
                return InteractionResultHolder.fail(itemStack);
            }
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
        }
    }

    @Override
    public SoundEvent getEatingSound() {
        return getDrinkingSound();
    }

    @Override
    public @NotNull SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.DRINK;
    }
}
