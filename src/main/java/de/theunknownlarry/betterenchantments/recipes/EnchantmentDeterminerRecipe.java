package de.theunknownlarry.betterenchantments.recipes;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import de.theunknownlarry.betterenchantments.init.BlockInit;
import de.theunknownlarry.betterenchantments.init.RecipeInit;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.PartialNBTIngredient;
import org.jetbrains.annotations.NotNull;

public class EnchantmentDeterminerRecipe implements Recipe<Container>
{
	final Ingredient base;
	final Ingredient addition1;
	final Ingredient addition2;
	final ItemStack result;
	private final ResourceLocation id;

	public EnchantmentDeterminerRecipe(ResourceLocation id, Ingredient base, Ingredient addition1, Ingredient addition2, ItemStack result) {
		this.id = id;
		this.base = base;
		this.addition1 = addition1;
		this.addition2 = addition2;
		this.result = result;
	}

	public boolean matches(Container container, @NotNull Level level) {
		return this.base.test(container.getItem(0)) && this.addition1.test(container.getItem(1)) && this.addition2.test(container.getItem(2));
	}

	public @NotNull ItemStack assemble(Container container) {
		ItemStack itemStack = this.result.copy();
		CompoundTag compoundTag = container.getItem(0).getTag();
		if(compoundTag != null) {
			itemStack.setTag(compoundTag.copy());
		}

		return itemStack;
	}

	public boolean canCraftInDimensions(int a, int b) {
		return true;
	}

	public @NotNull ItemStack getResultItem() {
		return this.result;
	}

	public boolean isAdditionIngredient(ItemStack itemStack) {
		return this.addition1.test(itemStack) || this.addition2.test(itemStack);
	}

	@Override
	public @NotNull ItemStack getToastSymbol()
	{
		return new ItemStack(BlockInit.ENCHANTMENT_DETERMINER.get());
	}

	@Override
	public @NotNull ResourceLocation getId()
	{
		return this.id;
	}

	@Override
	public @NotNull RecipeSerializer<?> getSerializer()
	{
		return RecipeInit.ENCHANTMENT_DETERMINER_RECIPE_SERIALIZER.get();
	}

	@Override
	public @NotNull RecipeType<?> getType()
	{
		return RecipeInit.ENCHANTMENT_DETERMINER_RECIPE_TYPE.get();
	}

	@Override
	public boolean isIncomplete()
	{
		NonNullList<Ingredient> nonnulllist = this.getIngredients();
		return nonnulllist.isEmpty() || nonnulllist.stream().filter((ingredient) -> {
			return !ingredient.isEmpty();
		}).anyMatch((ingredient) -> {
			return net.minecraftforge.common.ForgeHooks.hasNoElements(ingredient);
		});
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		final NonNullList<Ingredient> ingredients = NonNullList.create();
		ingredients.add(this.base);
		ingredients.add(this.addition1);
		ingredients.add(this.addition2);
		return ingredients;
	}

	public static class Type implements RecipeType<EnchantmentDeterminerRecipe> {
		public static final String ID = "enchantment_determiner";
	}

	public static class Serializer implements RecipeSerializer<EnchantmentDeterminerRecipe> {
		public static final String ID = "enchantment_determiner";

		public @NotNull EnchantmentDeterminerRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject jsonObject) {
			Ingredient base = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "base"));
			Ingredient addition1 = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "addition1"));
			Ingredient addition2;
			try{
				addition2 = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "addition2"));
			} catch (JsonSyntaxException | NullPointerException | IllegalStateException e) {
				addition2 = Ingredient.EMPTY;
			}
			ItemStack itemStack = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(jsonObject, "result"), true);
			return new EnchantmentDeterminerRecipe(id, base, addition1, addition2, itemStack);
		}

		public EnchantmentDeterminerRecipe fromNetwork(@NotNull ResourceLocation id, @NotNull FriendlyByteBuf buf) {
			Ingredient base = Ingredient.fromNetwork(buf);
			Ingredient addition1 = Ingredient.fromNetwork(buf);
			Ingredient addition2 = Ingredient.fromNetwork(buf);
			ItemStack itemStack = buf.readItem();
			return new EnchantmentDeterminerRecipe(id, base, addition1, addition2, itemStack);
		}

		public void toNetwork(@NotNull FriendlyByteBuf buf, EnchantmentDeterminerRecipe recipe) {
			recipe.base.toNetwork(buf);
			recipe.addition1.toNetwork(buf);
			recipe.addition2.toNetwork(buf);
			buf.writeItem(recipe.result);
		}


	}
}
