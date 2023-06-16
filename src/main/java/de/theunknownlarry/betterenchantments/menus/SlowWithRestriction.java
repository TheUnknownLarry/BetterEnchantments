package de.theunknownlarry.betterenchantments.menus;

import java.util.function.Predicate;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class SlowWithRestriction extends SlotItemHandler
{

	private final Predicate<ItemStack> validator;

	public SlowWithRestriction(final IItemHandler itemHandler, final int index, final int xPosition, final int yPosition, final Predicate<ItemStack> validator)
	{
		super(itemHandler, index, xPosition, yPosition);
		this.validator = validator;
	}

	public SlowWithRestriction(final IItemHandler itemHandler, final int index, final int xPosition, final int yPosition)
	{
		this(itemHandler, index, xPosition, yPosition, itemStack -> false);
	}

	@Override
	public boolean mayPlace(@NotNull final ItemStack stack)
	{
		return this.validator.test(stack);
	}
}
