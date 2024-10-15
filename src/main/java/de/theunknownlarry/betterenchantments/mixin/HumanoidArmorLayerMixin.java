package de.theunknownlarry.betterenchantments.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import de.theunknownlarry.betterenchantments.init.EnchantmentInit;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = HumanoidArmorLayer.class)
public class HumanoidArmorLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>>
{
	@Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At("HEAD"), cancellable = true)
	public void render(
		final PoseStack p_117096_,
		final MultiBufferSource p_117097_,
		final int p_117098_,
		final T p_117099_,
		final float p_117100_,
		final float p_117101_,
		final float p_117102_,
		final float p_117103_,
		final float p_117104_,
		final float p_117105_,
		final CallbackInfo ci) {
		if (EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.INVISIBILITY.get(), p_117099_) >= 1) {
			ci.cancel();
		}
	}
}
