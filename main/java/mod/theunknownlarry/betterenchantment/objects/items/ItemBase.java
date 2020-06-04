package mod.theunknownlarry.betterenchantment.objects.items;

import mod.theunknownlarry.betterenchantment.Main;
import mod.theunknownlarry.betterenchantment.init.ItemInit;
import mod.theunknownlarry.betterenchantment.util.IHasModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemBase extends Item implements IHasModel{

	public ItemBase(String name) {
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.betterenchantmenttab);
		
		ItemInit.ITEMS.add(this);
		
	}

	@Override
	public void registerModels() {

		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
		
	}

}
