package de.theunknownlarry.betterenchantments.init;

import de.theunknownlarry.betterenchantments.Main;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
	public static final RegistryObject<Item> EMPTY_ENCHANTED_BOOK = ITEMS.register("empty_enchanted_book",
		() -> new Item(new Item.Properties().tab(Main.TAB_BETTERENCHANTMENTS)));
}
