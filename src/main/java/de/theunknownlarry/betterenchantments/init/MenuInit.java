package de.theunknownlarry.betterenchantments.init;

import de.theunknownlarry.betterenchantments.Main;
import de.theunknownlarry.betterenchantments.menus.EnchantmentDeterminerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class MenuInit
{
	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Main.MODID);
	public static final RegistryObject<MenuType<EnchantmentDeterminerMenu>> ENCHANTMENT_DETERMINER = MENUS.register("enchantment_determiner",
		() -> new MenuType<>(EnchantmentDeterminerMenu::getClientMenu));
}
