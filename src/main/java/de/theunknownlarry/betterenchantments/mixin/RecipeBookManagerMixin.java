package de.theunknownlarry.betterenchantments.mixin;

import static net.minecraft.client.RecipeBookCategories.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import de.theunknownlarry.betterenchantments.init.ItemInit;
import de.theunknownlarry.betterenchantments.init.RecipeInit;
import de.theunknownlarry.betterenchantments.recipes.EnchantmentDeterminerRecipe;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.client.RecipeBookManager;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.ModLoadingContext;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RecipeBookManager.class, remap = false)
public abstract class RecipeBookManagerMixin
{
	@Shadow
	private static final Map<RecipeBookCategories, List<RecipeBookCategories>> AGGREGATE_CATEGORIES = new HashMap<>();
	@Shadow
	private static final Map<RecipeBookType, List<RecipeBookCategories>> TYPE_CATEGORIES = new HashMap<>();
	@Shadow
	private static final Map<RecipeType<?>, Function<Recipe<?>, RecipeBookCategories>> RECIPE_CATEGORY_LOOKUPS = new HashMap<>();

	@Inject(method = "getAggregateCategories", at = @At("HEAD"), cancellable = true)
	private static void getAggregateCategories(final CallbackInfoReturnable<Map<RecipeBookCategories, List<RecipeBookCategories>>> cir)
	{
		cir.setReturnValue(Collections.unmodifiableMap(AGGREGATE_CATEGORIES));
	}

	@Inject(method = "findCategories", at = @At("HEAD"), cancellable = true)
	private static void findCategories(final RecipeType<?> type, final Recipe<?> recipe, final CallbackInfoReturnable<RecipeBookCategories> cir) {
		if(recipe instanceof EnchantmentDeterminerRecipe && recipe.getType() == type) {
			final ItemStack resultItem = recipe.getResultItem();
			if(resultItem.getTag() != null && resultItem.getTag().contains("StoredEnchantments")) {
				final Map<Enchantment, Integer> enchantments = EnchantmentHelper.deserializeEnchantments((ListTag) resultItem.getTag().get("StoredEnchantments"));
				if (!enchantments.isEmpty()) {
					for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
						if (entry.getValue().intValue() >= 1) {
							final Enchantment enchantment = entry.getKey();
							final boolean head = enchantment.canEnchant(new ItemStack(Items.DIAMOND_HELMET));
							final boolean chest = enchantment.canEnchant(new ItemStack(Items.DIAMOND_CHESTPLATE));
							final boolean legs = enchantment.canEnchant(new ItemStack(Items.DIAMOND_LEGGINGS));
							final boolean feet = enchantment.canEnchant(new ItemStack(Items.DIAMOND_BOOTS));
							final List<RecipeBookCategories> recipeBookCategories = new ArrayList<>();
							for (Map.Entry<RecipeBookCategories, List<RecipeBookCategories>> entry2 : AGGREGATE_CATEGORIES.entrySet()) {
								if (entry2.getKey().name().contains("ENCHANTMENT_DETERMINER")) {
									recipeBookCategories.add(entry2.getKey());
									recipeBookCategories.addAll(entry2.getValue());
								}
							}
							if (head && chest && legs && feet) {
								cir.setReturnValue(recipeBookCategories.get(1));
								return;
							}
							else if (head) {
								cir.setReturnValue(recipeBookCategories.get(2));
								return;
							}
							else if (chest) {
								cir.setReturnValue(recipeBookCategories.get(3));
								return;
							}
							else if (legs) {
								cir.setReturnValue(recipeBookCategories.get(4));
								return;
							}
							else if (feet) {
								cir.setReturnValue(recipeBookCategories.get(5));
								return;
							}
							else {
								cir.setReturnValue(recipeBookCategories.get(0));
								return;
							}
						}
					}
				}
			}
			cir.setReturnValue(RecipeBookCategories.valueOf("ENCHANTMENT_DETERMINER_SEARCH"));
		}
	}

	@Inject(method = "init", at = @At("HEAD"), cancellable = true)
	private static void init(final CallbackInfo ci)
	{
		final RecipeBookCategories enchantmentDeterminerSearch = RecipeBookCategories.create("ENCHANTMENT_DETERMINER_SEARCH",
			new ItemStack(Items.COMPASS));
		final RecipeBookCategories enchantmentDeterminerArmor = RecipeBookCategories.create("ENCHANTMENT_DETERMINER_ARMOR",
			new ItemStack(Items.ARMOR_STAND), new ItemStack(Items.DIAMOND));
		final RecipeBookCategories enchantmentDeterminerHead = RecipeBookCategories.create("ENCHANTMENT_DETERMINER_HEAD",
			new ItemStack(Items.DIAMOND_HELMET));
		final RecipeBookCategories enchantmentDeterminerChest = RecipeBookCategories.create("ENCHANTMENT_DETERMINER_CHEST",
			new ItemStack(Items.DIAMOND_CHESTPLATE));
		final RecipeBookCategories enchantmentDeterminerLegs = RecipeBookCategories.create("ENCHANTMENT_DETERMINER_LEGS",
			new ItemStack(Items.DIAMOND_LEGGINGS));
		final RecipeBookCategories enchantmentDeterminerFeet = RecipeBookCategories.create("ENCHANTMENT_DETERMINER_FEET",
			new ItemStack(Items.DIAMOND_BOOTS));
		// The ImmutableMap is the patched out value of AGGREGATE_CATEGORIES
		var aggregateCategories = new HashMap<>(
			ImmutableMap.of(CRAFTING_SEARCH, ImmutableList.of(CRAFTING_EQUIPMENT, CRAFTING_BUILDING_BLOCKS, CRAFTING_MISC, CRAFTING_REDSTONE),
				FURNACE_SEARCH, ImmutableList.of(FURNACE_FOOD, FURNACE_BLOCKS, FURNACE_MISC),
				BLAST_FURNACE_SEARCH, ImmutableList.of(BLAST_FURNACE_BLOCKS, BLAST_FURNACE_MISC),
				SMOKER_SEARCH, ImmutableList.of(SMOKER_FOOD),
				enchantmentDeterminerSearch, ImmutableList.of(enchantmentDeterminerArmor, enchantmentDeterminerHead, enchantmentDeterminerChest, enchantmentDeterminerLegs, enchantmentDeterminerFeet)));
		var typeCategories = new HashMap<RecipeBookType, ImmutableList<RecipeBookCategories>>();
		var recipeCategoryLookups = new HashMap<RecipeType<?>, Function<Recipe<?>, RecipeBookCategories>>();
		var event = new RegisterRecipeBookCategoriesEvent(aggregateCategories, typeCategories, recipeCategoryLookups);
		ModLoader.get().postEventWithWrapInModOrder(event, (mc, e) -> ModLoadingContext.get().setActiveContainer(mc), (mc, e) -> ModLoadingContext.get().setActiveContainer(null));
		AGGREGATE_CATEGORIES.putAll(aggregateCategories);
		TYPE_CATEGORIES.putAll(typeCategories);
		RECIPE_CATEGORY_LOOKUPS.putAll(recipeCategoryLookups);
		ci.cancel();
	}
}
