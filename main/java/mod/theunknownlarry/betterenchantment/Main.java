package mod.theunknownlarry.betterenchantment;

import mod.theunknownlarry.betterenchantment.tabs.BetterEnchantmentTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main
{
	
	public static final String MODID = "betterenchantment";
    public static final String NAME = "Better Enchantment";
    public static final String VERSION = "1.1";
    public static final String MC_VERSIONS = "[1.12.2]";
    public static final CreativeTabs betterenchantmenttab = new BetterEnchantmentTab("betterenchantmenttab");
    
    public enum GUI_ENUM {
    	
    	GUI_ENCHANTMENT_DETERMINER
    	
    }
    
    @Instance
    public static Main instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    	
        
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    	
    	
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    	
    	
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
    	
    	
    	
    }
}
