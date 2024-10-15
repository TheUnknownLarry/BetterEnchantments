package de.theunknownlarry.betterenchantments.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import de.theunknownlarry.betterenchantments.init.EnchantmentInit;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.StuckInBodyLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = StuckInBodyLayer.class)
public class StuckInBodyLayerMixin<T extends LivingEntity, M extends PlayerModel<T>>
{
	@Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At("HEAD"), cancellable = true)
	public void render(
		final PoseStack p_117586_,
		final MultiBufferSource p_117587_,
		final int p_117588_,
		final T p_117589_,
		final float p_117590_,
		final float p_117591_,
		final float p_117592_,
		final float p_117593_,
		final float p_117594_,
		final float p_117595_,
		final CallbackInfo ci) {
		if(EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.INVISIBILITY.get(), p_117589_) >= 1) {
			ci.cancel();
		}
	}
}
