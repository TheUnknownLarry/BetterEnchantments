package de.theunknownlarry.betterenchantments.blocks;

import de.theunknownlarry.betterenchantments.Main;
import de.theunknownlarry.betterenchantments.menus.EnchantmentDeterminerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class EnchantmentDeterminerBlock extends Block
{
	private static final Component CONTAINER_TITLE = Component.translatable("container." + Main.MODID + ".enchantment_determiner");

	public EnchantmentDeterminerBlock(final Properties properties)
	{
		super(properties);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (level.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			MenuConstructor menuConstructor = EnchantmentDeterminerMenu.getServerMenu(pos);
			MenuProvider provider =  new SimpleMenuProvider(menuConstructor, CONTAINER_TITLE);
			NetworkHooks.openScreen(((ServerPlayer) player), provider, pos);
			return InteractionResult.CONSUME;
		}
	}
}
