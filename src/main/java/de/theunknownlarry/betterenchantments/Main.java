package de.theunknownlarry.betterenchantments;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import de.theunknownlarry.betterenchantments.init.BlockInit;
import de.theunknownlarry.betterenchantments.init.EnchantmentInit;
import de.theunknownlarry.betterenchantments.init.ItemInit;
import de.theunknownlarry.betterenchantments.init.LootModifiersInit;
import de.theunknownlarry.betterenchantments.init.MenuInit;
import de.theunknownlarry.betterenchantments.init.RecipeInit;
import de.theunknownlarry.betterenchantments.screens.EnchantmentDeterminerScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ItemEnchantmentArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.server.command.ConfigCommand;
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
    public static final RecipeBookType ENCHANTMENT_DETERMINER_RECIPE_BOOK_TYPE = RecipeBookType.create("ENCHANTMENT_DETERMINER");

    public Main() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::setup);
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        EnchantmentInit.ENCHANTMENTS.register(bus);
        MenuInit.MENUS.register(bus);
        LootModifiersInit.LOOT_MODIFIER_SERIALIZERS.register(bus);
        RecipeInit.RECIPE_SERIALIZERS.register(bus);
        RecipeInit.RECIPE_TYPES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setup(final FMLCommonSetupEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(MenuInit.ENCHANTMENT_DETERMINER.get(), EnchantmentDeterminerScreen::new);
        }
    }

    @Mod.EventBusSubscriber(modid = MODID)
    public class ModEvents {
        @SubscribeEvent
        public void onCommandsRegister(RegisterCommandsEvent event) {
            new JsonCommand(event.getDispatcher());

            ConfigCommand.register(event.getDispatcher());
        }
    }

    public static class JsonCommand
    {
        private static final DynamicCommandExceptionType ERROR_NOT_LIVING_ENTITY = new DynamicCommandExceptionType((p_137029_) -> {
            return Component.translatable("commands.enchant.failed.entity", p_137029_);
        });
        private static final DynamicCommandExceptionType ERROR_NO_ITEM = new DynamicCommandExceptionType((p_137027_) -> {
            return Component.translatable("commands.enchant.failed.itemless", p_137027_);
        });

        public JsonCommand(CommandDispatcher<CommandSourceStack> commandDispatcher)
        {
            commandDispatcher.register(Commands.literal("json").executes(this::execute));
        }

        private int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
        {
            final ServerPlayer player = context.getSource().getPlayer();
            if(player == null) {
                throw ERROR_NOT_LIVING_ENTITY.create(context.getSource());
            }
            ItemStack itemstack = player.getMainHandItem();
            if (!itemstack.isEmpty()) {
                context.getSource().sendSuccess(Component.literal(GsonHelper.convertToString(Ingredient.of(itemstack).toJson(), "")), false);
                return 1;
            }
            else {
                throw ERROR_NO_ITEM.create(player.getName().getString());
            }
        }
    }
}
