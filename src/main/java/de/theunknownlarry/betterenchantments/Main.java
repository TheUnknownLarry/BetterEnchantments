package de.theunknownlarry.betterenchantments;

import de.theunknownlarry.betterenchantments.init.BlockInit;
import de.theunknownlarry.betterenchantments.init.EnchantmentInit;
import de.theunknownlarry.betterenchantments.init.ItemInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;

@Mod(Main.MODID)
public class Main
{
	public static final String MODID = "betterenchantments";
    public static final CreativeModeTab TAB_BETTERENCHANTMENTS = new CreativeModeTab(MODID) {
        @Override
        public @NotNull ItemStack makeIcon()
        {
            return ItemInit.EMPTY_ENCHANTED_BOOK.get().getDefaultInstance();
        }
    };
    public Main() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::setup);
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        EnchantmentInit.ENCHANTMENTS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }
}
