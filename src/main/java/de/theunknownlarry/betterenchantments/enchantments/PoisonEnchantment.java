package de.theunknownlarry.betterenchantments.enchantments;

import de.theunknownlarry.betterenchantments.init.EnchantmentInit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class PoisonEnchantment extends EnchantmentParent {
	public PoisonEnchantment() {
		super(4, EnchantmentCategory.ARMOR_CHEST, EquipmentSlot.CHEST);
	}

	protected boolean checkCompatibility(@NotNull Enchantment p_44690_) {
		return super.checkCompatibility(p_44690_) && p_44690_ != EnchantmentInit.INSTANT_HEALTH.get() && p_44690_ != EnchantmentInit.ABSORPTION.get() && p_44690_ != EnchantmentInit.REGENERATION.get() && p_44690_ != EnchantmentInit.HEALTH_BOOST.get();
	}
}
