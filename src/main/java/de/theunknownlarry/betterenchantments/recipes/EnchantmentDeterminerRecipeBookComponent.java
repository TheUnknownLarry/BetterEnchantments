package de.theunknownlarry.betterenchantments.recipes;

import java.util.List;

import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

public class EnchantmentDeterminerRecipeBookComponent extends RecipeBookComponent
{
	@Override
	public void setupGhostRecipe(final Recipe<?> recipe, final List<Slot> slots)
	{
		ItemStack itemstack = recipe.getResultItem();
		this.ghostRecipe.setRecipe(recipe);
		this.ghostRecipe.addIngredient(Ingredient.of(itemstack), (slots.get(3)).x, (slots.get(3)).y);
		this.placeRecipe(this.menu.getGridWidth(), this.menu.getGridHeight(), this.menu.getResultSlotIndex(), recipe, recipe.getIngredients().iterator(), 0);
	}
}
