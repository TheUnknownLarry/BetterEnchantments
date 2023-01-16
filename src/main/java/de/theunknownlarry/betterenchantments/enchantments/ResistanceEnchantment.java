package de.theunknownlarry.betterenchantments.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ResistanceEnchantment extends EnchantmentParent {
	public ResistanceEnchantment() {
		super(4, EnchantmentCategory.ARMOR, EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
	}

}
