package de.theunknownlarry.betterenchantments.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class FireResistanceEnchantment extends EnchantmentParent {
	public FireResistanceEnchantment() {
		super(3, EnchantmentCategory.ARMOR, EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
	}

}
