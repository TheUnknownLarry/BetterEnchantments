package de.theunknownlarry.betterenchantments.enchantments;

import de.theunknownlarry.betterenchantments.init.EnchantmentInit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class HeroOfTheVillageEnchantment extends EnchantmentParent {
	public HeroOfTheVillageEnchantment() {
		super(5, EnchantmentCategory.ARMOR_HEAD, EquipmentSlot.HEAD);
	}

	protected boolean checkCompatibility(@NotNull Enchantment p_44690_) {
		return super.checkCompatibility(p_44690_) && p_44690_ != EnchantmentInit.BAD_OMEN.get();
	}

}
