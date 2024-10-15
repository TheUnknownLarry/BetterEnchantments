package de.theunknownlarry.betterenchantments.init;

import de.theunknownlarry.betterenchantments.Main;
import de.theunknownlarry.betterenchantments.recipes.EnchantmentDeterminerRecipe;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeInit
{
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Main.MODID);
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Main.MODID);

	public static final RegistryObject<RecipeSerializer<EnchantmentDeterminerRecipe>> ENCHANTMENT_DETERMINER_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(EnchantmentDeterminerRecipe.Serializer.ID,
		EnchantmentDeterminerRecipe.Serializer::new);
	public static final RegistryObject<RecipeType<EnchantmentDeterminerRecipe>> ENCHANTMENT_DETERMINER_RECIPE_TYPE = RECIPE_TYPES.register(EnchantmentDeterminerRecipe.Type.ID,
		EnchantmentDeterminerRecipe.Type::new);



}
