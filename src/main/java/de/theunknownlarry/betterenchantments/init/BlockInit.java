package de.theunknownlarry.betterenchantments.init;

import java.util.function.Supplier;

import de.theunknownlarry.betterenchantments.Main;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);
	public static final RegistryObject<Block> ENCHANTMENT_DETERMINER = register("enchantment_determiner",
		() -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(5.0F, 1200.0F)),
		new Item.Properties().tab(Main.TAB_BETTERENCHANTMENTS));

	private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> supplier, Item.Properties properties)
	{
		RegistryObject<T> block = BLOCKS.register(name, supplier);
		ItemInit.ITEMS.register(name, ()->new BlockItem(block.get(),properties));
		return block;
	}
}
