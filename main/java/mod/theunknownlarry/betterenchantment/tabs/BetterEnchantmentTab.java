package mod.theunknownlarry.betterenchantment.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BetterEnchantmentTab extends CreativeTabs {

	public BetterEnchantmentTab(String label) {
		
		super(label);
		
	}

	@Override
	public ItemStack getTabIconItem() {
		
		return new ItemStack(Item.getItemById(403));
		
	}

}
