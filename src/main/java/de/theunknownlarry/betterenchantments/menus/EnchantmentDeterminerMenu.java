package de.theunknownlarry.betterenchantments.menus;

import de.theunknownlarry.betterenchantments.init.BlockInit;
import de.theunknownlarry.betterenchantments.init.MenuInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class EnchantmentDeterminerMenu extends AbstractContainerMenu
{
	private final ContainerLevelAccess levelAccess;

	protected EnchantmentDeterminerMenu(int id, Inventory playerInv, IItemHandler slots, BlockPos pos)
	{
		super(MenuInit.ENCHANTMENT_DETERMINER.get(), id);
		this.levelAccess = ContainerLevelAccess.create(playerInv.player.level, pos);
		addSlot(new SlotItemHandler(slots,0,26,35));
		addSlot(new SlotItemHandler(slots,1,55,35));
		addSlot(new SlotItemHandler(slots,2,75,35));
		addSlot(new SlowWithRestriction(slots,3,128,35));

		for(int k = 0; k < 3; ++k) {
			for(int i1 = 0; i1 < 9; ++i1) {
				this.addSlot(new Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 63 + k * 18));
			}
		}

		for(int l = 0; l < 9; ++l) {
			this.addSlot(new Slot(playerInv, l, 8 + l * 18, 121));
		}
	}

	public static EnchantmentDeterminerMenu getClientMenu(int id, Inventory playerInv)
	{
		return new EnchantmentDeterminerMenu(id, playerInv, new ItemStackHandler(4), BlockPos.ZERO);
	}

	public static MenuConstructor getServerMenu(BlockPos pos)
	{
		return (id,playerInv,player) -> new EnchantmentDeterminerMenu(id,playerInv, new ItemStackHandler(4),pos);
	}

	@Override
	public ItemStack quickMoveStack(final Player player, final int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if(slot.hasItem()) {
			ItemStack current = slot.getItem();
			itemstack = current.copy();
			if(index<4){
				if(!this.moveItemStackTo(current,4,this.slots.size(),true)){
					return ItemStack.EMPTY;
				}
			}else if(!this.moveItemStackTo(current, 0, 2, false)){
				return ItemStack.EMPTY;
			}

			if(current.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			}else{
				slot.setChanged();
			}
		}
		return itemstack;
	}

	@Override
	public boolean stillValid(final Player player)
	{
		return stillValid(this.levelAccess,player, BlockInit.ENCHANTMENT_DETERMINER.get());
	}
}
