package de.theunknownlarry.betterenchantments.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import de.theunknownlarry.betterenchantments.init.EnchantmentInit;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ItemInHandLayer.class)
public class ItemInHandLayerMixin<T extends LivingEntity, M extends EntityModel<T> & ArmedModel>
{
	@Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At("HEAD"), cancellable = true)
	public void render(
		final PoseStack p_117204_,
		final MultiBufferSource p_117205_,
		final int p_117206_,
		final T p_117207_,
		final float p_117208_,
		final float p_117209_,
		final float p_117210_,
		final float p_117211_,
		final float p_117212_,
		final float p_117213_,
		final CallbackInfo ci) {
		if(EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.INVISIBILITY.get(), p_117207_) >= 1) {
			ci.cancel();
		}
	}
}
