package de.theunknownlarry.betterenchantments.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class EnchantmentParent extends Enchantment
{
	private final EquipmentSlot[] slots;
	private final int maxLevel;

	public EnchantmentParent(int maxLevel, EnchantmentCategory cat, EquipmentSlot... slots) {
		super(Rarity.VERY_RARE, cat, slots);
		this.maxLevel = maxLevel;
		this.slots = slots;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public boolean isTreasureOnly() {
		return false;
	}

	public boolean isCurse() {
		return false;
	}

	public boolean isTradeable() {
		return false;
	}

	public boolean isDiscoverable() {
		return false;
	}

	public boolean canEnchant(ItemStack stack) {
		if(!(stack.getItem() instanceof ArmorItem)) {
			return false;
		}
		EquipmentSlot slot = ((ArmorItem) stack.getItem()).getSlot();
		for (EquipmentSlot equipmentSlot : slots) {
			if(slot.equals(equipmentSlot)) {
				return true;
			}
		}
		return false;
	}
	public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
		return false;
	}
	public boolean isAllowedOnBooks() {
		return true;
	}
}
