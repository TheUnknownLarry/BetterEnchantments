package de.theunknownlarry.betterenchantments.menus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import de.theunknownlarry.betterenchantments.Main;
import de.theunknownlarry.betterenchantments.init.BlockInit;
import de.theunknownlarry.betterenchantments.init.ItemInit;
import de.theunknownlarry.betterenchantments.init.MenuInit;
import de.theunknownlarry.betterenchantments.init.RecipeInit;
import de.theunknownlarry.betterenchantments.recipes.EnchantmentDeterminerRecipe;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.RecipeBookManager;
import org.jetbrains.annotations.NotNull;

public class EnchantmentDeterminerMenu extends RecipeBookMenu<Container>
{
	final ResultContainer resultSlot = new ResultContainer();
	final Container inputSlots = new SimpleContainer(3) {
		@Override
		public void setChanged() {
			super.setChanged();
			EnchantmentDeterminerMenu.this.slotsChanged(this);
		}
	};
	private final Level level;
	private final ContainerLevelAccess levelAccess;

	private EnchantmentDeterminerRecipe selectedRecipe;
	private final List<EnchantmentDeterminerRecipe> recipes;


	protected EnchantmentDeterminerMenu(int id, Inventory playerInv, BlockPos pos)
	{
		super(MenuInit.ENCHANTMENT_DETERMINER.get(), id);
		this.level = playerInv.player.level;
		this.levelAccess = ContainerLevelAccess.create(this.level, pos);
		this.recipes = this.level.getRecipeManager().getAllRecipesFor(RecipeInit.ENCHANTMENT_DETERMINER_RECIPE_TYPE.get());
		addSlot(new Slot(this.inputSlots,0,40,27));
		addSlot(new Slot(this.inputSlots,1,69,27));
		addSlot(new Slot(this.inputSlots,2,89,27));
		addSlot(new Slot(this.resultSlot,3,142,27) {
			@Override
			public boolean mayPlace(@NotNull ItemStack itemStack) {
				return false;
			}

			@Override
			public boolean mayPickup(@NotNull Player player) {
				return EnchantmentDeterminerMenu.this.mayPickUp(player, this.hasItem());
			}

			@Override
			public void onTake(@NotNull Player player, @NotNull ItemStack itemStack) {
				EnchantmentDeterminerMenu.this.onTake(player, itemStack);
			}
		});

		for(int k = 0; k < 3; ++k) {
			for(int i1 = 0; i1 < 9; ++i1) {
				this.addSlot(new Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 63 + k * 18));
			}
		}

		for(int l = 0; l < 9; ++l) {
			this.addSlot(new Slot(playerInv, l, 8 + l * 18, 121));
		}
	}

	@Override
	public void slotsChanged(@NotNull Container container) {
		super.slotsChanged(container);
		if(container == this.inputSlots) {
			this.createResult();
		}
	}

	@Override
	public void removed(@NotNull Player player) {
		super.removed(player);
		this.levelAccess.execute((a, b) -> {
			this.clearContainer(player, this.inputSlots);
		});
	}

	@Override
	public boolean stillValid(final @NotNull Player player)
	{
		return this.levelAccess.evaluate((a, b) -> {
			return !this.isValidBlock(a.getBlockState(b)) ? false : player.distanceToSqr((double)b.getX() + 0.5D, (double)b.getY() + 0.5D, (double)b.getZ() + 0.5D) <= 64.0D;
		}, true);
	}

	public boolean shouldQuickMoveToAdditionalSlot(ItemStack itemStack) {
		return this.recipes.stream().anyMatch((a) -> {
			return a.isAdditionIngredient(itemStack);
		});
	}

	public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index == 3) {
				if (!this.moveItemStackTo(itemstack1, 4, 40, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} else if (index != 0 && index != 1 && index != 2) {
				if (index >= 4 && index < 40) {
					int i = this.shouldQuickMoveToAdditionalSlot(itemstack) ? 1 : 0;
					if (!this.moveItemStackTo(itemstack1, i, 3, false)) {
						return ItemStack.EMPTY;
					}
				}
			} else if (!this.moveItemStackTo(itemstack1, 4, 40, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, itemstack1);
		}

		return itemstack;
	}

	public boolean isValidBlock(BlockState blockState) {
		return blockState.is(BlockInit.ENCHANTMENT_DETERMINER.get());
	}

	public boolean mayPickUp(Player player, boolean b) {
		return this.selectedRecipe != null && this.selectedRecipe.matches(this.inputSlots, this.level);
	}

	public void onTake(Player player, ItemStack itemStack) {
		itemStack.onCraftedBy(player.level, player, itemStack.getCount());
		this.resultSlot.awardUsedRecipes(player);
		this.shrinkStackInSlot(0);
		this.shrinkStackInSlot(1);
		this.shrinkStackInSlot(2);
		this.levelAccess.execute((a, b) -> {
			a.levelEvent(1044, b, 0);
		});
	}

	private void shrinkStackInSlot(int slot) {
		ItemStack itemStack = this.inputSlots.getItem(slot);
		itemStack.shrink(1);
		this.inputSlots.setItem(slot, itemStack);
	}

	public void createResult() {
		List<EnchantmentDeterminerRecipe> list = this.level.getRecipeManager().getRecipesFor(RecipeInit.ENCHANTMENT_DETERMINER_RECIPE_TYPE.get(), this.inputSlots, this.level);
		if(list.isEmpty()) {
			this.resultSlot.setItem(0, ItemStack.EMPTY);
		} else {
			this.selectedRecipe = list.get(0);
			ItemStack itemStack = this.selectedRecipe.assemble(this.inputSlots);
			this.resultSlot.setRecipeUsed(this.selectedRecipe);
			this.resultSlot.setItem(0, itemStack);
		}
	}

	@Override
	public boolean canTakeItemForPickAll(@NotNull ItemStack itemStack, Slot slot) {
		return slot.container != this.resultSlot && super.canTakeItemForPickAll(itemStack, slot);
	}



	public static EnchantmentDeterminerMenu getClientMenu(int id, Inventory playerInv)
	{
		return new EnchantmentDeterminerMenu(id, playerInv, BlockPos.ZERO);
	}

	public static MenuConstructor getServerMenu(BlockPos pos)
	{
		return (id,playerInv,player) -> new EnchantmentDeterminerMenu(id,playerInv, pos);
	}

	@Override
	public void fillCraftSlotsStackedContents(final StackedContents p_40117_)
	{
		for (int i = 0; i < this.inputSlots.getContainerSize(); i++) {
			final ItemStack item = this.inputSlots.getItem(i);
			p_40117_.accountSimpleStack(item);
		}
	}

	@Override
	public void clearCraftingContent()
	{
		this.inputSlots.clearContent();
		this.resultSlot.clearContent();
	}

	@Override
	public boolean recipeMatches(final Recipe<? super Container> p_40118_)
	{
		return p_40118_.matches(this.inputSlots, level);
	}

	@Override
	public int getResultSlotIndex()
	{
		return 3;
	}

	@Override
	public int getGridWidth()
	{
		return 3;
	}

	@Override
	public int getGridHeight()
	{
		return 1;
	}

	@Override
	public int getSize()
	{
		return 4;
	}

	@Override
	public @NotNull RecipeBookType getRecipeBookType()
	{
		return Main.ENCHANTMENT_DETERMINER_RECIPE_BOOK_TYPE;
	}

	@Override
	public @NotNull List<RecipeBookCategories> getRecipeBookCategories()
	{
		final Map<RecipeBookCategories, List<RecipeBookCategories>> aggregateCategories = RecipeBookManager.getAggregateCategories();
		for (Map.Entry<RecipeBookCategories, List<RecipeBookCategories>> entry : aggregateCategories.entrySet()) {
			if (entry.getKey().name().contains("ENCHANTMENT_DETERMINER")) {
				final ArrayList<RecipeBookCategories> recipeBookCategories = new ArrayList<>(Collections.singletonList(entry.getKey()));
				recipeBookCategories.addAll(entry.getValue());
				return recipeBookCategories;
			}
		}
		return RecipeBookCategories.getCategories(this.getRecipeBookType());
	}

	@Override
	public boolean shouldMoveToInventory(final int p_150635_)
	{
		return p_150635_ != this.getResultSlotIndex();
	}

}
