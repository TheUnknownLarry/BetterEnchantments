package de.theunknownlarry.betterenchantments.loot;


import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;
public class AddItemModifier extends LootModifier {
	public static final Supplier<Codec<AddItemModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst).and(
			inst.group(
				ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(m -> m.item),
				Codec.FLOAT.fieldOf("prob").forGetter(m -> m.prob)
			)
		).apply(inst, AddItemModifier::new)));
	private final Item item;
	private final Float prob;

	protected AddItemModifier(LootItemCondition[] conditionsIn, Item item, Float prob) {
		super(conditionsIn);
		this.item = item;
		this.prob = prob;
	}

	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if(context.getRandom().nextFloat() <= prob) {
			generatedLoot.add(new ItemStack(item));
		}
		return generatedLoot;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC.get();
	}
}
