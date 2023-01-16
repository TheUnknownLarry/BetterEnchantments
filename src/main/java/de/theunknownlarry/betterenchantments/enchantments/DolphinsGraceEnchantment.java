package de.theunknownlarry.betterenchantments.enchantments;

import de.theunknownlarry.betterenchantments.init.EnchantmentInit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class DolphinsGraceEnchantment extends EnchantmentParent {
	public DolphinsGraceEnchantment() {
		super(1, EnchantmentCategory.ARMOR_LEGS, EquipmentSlot.LEGS);
	}

	protected boolean checkCompatibility(@NotNull Enchantment p_44690_) {
		return super.checkCompatibility(p_44690_) && p_44690_ != EnchantmentInit.SLOWNESS.get();
	}

}
