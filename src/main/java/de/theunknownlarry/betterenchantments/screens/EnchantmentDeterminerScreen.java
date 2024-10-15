package de.theunknownlarry.betterenchantments.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import de.theunknownlarry.betterenchantments.Main;
import de.theunknownlarry.betterenchantments.menus.EnchantmentDeterminerMenu;
import de.theunknownlarry.betterenchantments.recipes.EnchantmentDeterminerRecipeBookComponent;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.RecipeBook;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.NotNull;

public class EnchantmentDeterminerScreen extends AbstractContainerScreen<EnchantmentDeterminerMenu> implements RecipeUpdateListener
{

    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/gui/enchantment_determiner.png");
    private static final ResourceLocation RECIPE_BUTTON_LOCATION = new ResourceLocation("textures/gui/recipe_button.png");
    private final EnchantmentDeterminerRecipeBookComponent recipeBookComponent = new EnchantmentDeterminerRecipeBookComponent();
    private boolean widthTooNarrow;

    public EnchantmentDeterminerScreen(EnchantmentDeterminerMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
        this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
        this.addRenderableWidget(new ImageButton(this.leftPos + 6, this.height / 2 - 57, 20, 18, 0, 0, 19, RECIPE_BUTTON_LOCATION, button -> {
            this.recipeBookComponent.toggleVisibility();
            this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
            ((ImageButton) button).setPosition(this.leftPos + 6, this.height / 2 - 57);
        }));
        this.addWidget(this.recipeBookComponent);
        this.setInitialFocus(this.recipeBookComponent);
        this.titleLabelX = 29;
        this.inventoryLabelY = 50;
    }

    @Override
    protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = this.leftPos;
        int y = (this.height - this.imageHeight) / 2;
        this.blit(pPoseStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void containerTick()
    {
        super.containerTick();
        this.recipeBookComponent.tick();
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
            this.recipeBookComponent.render(pPoseStack, mouseX, mouseY, delta);
            super.render(pPoseStack, mouseX, mouseY, delta);
        } else {
            this.recipeBookComponent.render(pPoseStack, mouseX, mouseY, delta);
            super.render(pPoseStack, mouseX, mouseY, delta);
            this.recipeBookComponent.renderGhostRecipe(pPoseStack, this.leftPos, this.topPos, false, delta);
        }
        renderTooltip(pPoseStack, mouseX, mouseY);
        this.recipeBookComponent.renderTooltip(pPoseStack, this.leftPos, this.topPos, mouseX, mouseY);
    }

    @Override
    protected boolean isHovering(int p_98462_, int p_98463_, int p_98464_, int p_98465_, double p_98466_, double p_98467_) {
        return (!this.widthTooNarrow || !this.recipeBookComponent.isVisible()) && super.isHovering(p_98462_, p_98463_, p_98464_, p_98465_, p_98466_, p_98467_);
    }

    @Override
    public boolean mouseClicked(double p_98452_, double p_98453_, int p_98454_) {
        if (this.recipeBookComponent.mouseClicked(p_98452_, p_98453_, p_98454_)) {
            this.setFocused(this.recipeBookComponent);
            return true;
        } else {
            return this.widthTooNarrow && this.recipeBookComponent.isVisible() ? true : super.mouseClicked(p_98452_, p_98453_, p_98454_);
        }
    }

    @Override
    protected boolean hasClickedOutside(double p_98456_, double p_98457_, int p_98458_, int p_98459_, int p_98460_) {
        boolean flag = p_98456_ < (double)p_98458_ || p_98457_ < (double)p_98459_ || p_98456_ >= (double)(p_98458_ + this.imageWidth) || p_98457_ >= (double)(p_98459_ + this.imageHeight);
        return this.recipeBookComponent.hasClickedOutside(p_98456_, p_98457_, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, p_98460_) && flag;
    }

    @Override
    protected void slotClicked(Slot p_98469_, int p_98470_, int p_98471_, ClickType p_98472_) {
        super.slotClicked(p_98469_, p_98470_, p_98471_, p_98472_);
        this.recipeBookComponent.slotClicked(p_98469_);
    }

    public void recipesUpdated() {
        this.recipeBookComponent.recipesUpdated();
    }

    @Override
    public void removed() {
        this.recipeBookComponent.removed();
        super.removed();
    }

    public @NotNull RecipeBookComponent getRecipeBookComponent() {
        return this.recipeBookComponent;
    }


}
