package de.theunknownlarry.betterenchantments.init;

import java.util.Objects;
import java.util.function.Supplier;

import de.theunknownlarry.betterenchantments.Main;
import de.theunknownlarry.betterenchantments.enchantments.AbsorptionEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.BadLuckEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.BadOmenEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.BlindnessEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.DarknessEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.DolphinsGraceEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.FireResistanceEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.GlowingEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.HasteEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.HealthBoostEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.HeroOfTheVillageEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.HungerEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.InstantDamageEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.InstantHealthEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.InvisibilityEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.JumpBoostEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.LevitationEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.LuckEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.MiningFatigueEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.NauseaEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.NightVisionEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.PoisonEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.RegenerationEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.ResistanceEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.SaturationEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.SlowFallingEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.SlownessEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.SpeedEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.StrengthEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.WaterBreathingEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.WeaknessEnchantment;
import de.theunknownlarry.betterenchantments.enchantments.WitherEnchantment;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EnchantmentInit {

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Main.MODID);

	public static final RegistryObject<Enchantment> SPEED = register("speed", SpeedEnchantment::new);	//1
	public static final RegistryObject<Enchantment> SLOWNESS = register("slowness", SlownessEnchantment::new);	//2
	public static final RegistryObject<Enchantment> HASTE = register("haste", HasteEnchantment::new);	//3
	public static final RegistryObject<Enchantment> MINING_FATIGUE = register("mining_fatigue", MiningFatigueEnchantment::new);	//4
	public static final RegistryObject<Enchantment> STRENGTH = register("strength", StrengthEnchantment::new);	//5
	public static final RegistryObject<Enchantment> INSTANT_HEALTH = register("instant_health", InstantHealthEnchantment::new);	//6
	public static final RegistryObject<Enchantment> INSTANT_DAMAGE = register("instant_damage", InstantDamageEnchantment::new);	//7
	public static final RegistryObject<Enchantment> JUMP_BOOST = register("jump_boost", JumpBoostEnchantment::new);	//8
	public static final RegistryObject<Enchantment> NAUSEA = register("nausea", NauseaEnchantment::new);	//9
	public static final RegistryObject<Enchantment> REGENERATION = register("regeneration", RegenerationEnchantment::new);	//10
	public static final RegistryObject<Enchantment> RESISTANCE = register("resistance", ResistanceEnchantment::new);	//11
	public static final RegistryObject<Enchantment> FIRE_RESISTANCE = register("fire_resistance", FireResistanceEnchantment::new);	//12
	public static final RegistryObject<Enchantment> WATER_BREATHING = register("water_breathing", WaterBreathingEnchantment::new);	//13
	public static final RegistryObject<Enchantment> INVISIBILITY = register("invisibility", InvisibilityEnchantment::new);	//14
	public static final RegistryObject<Enchantment> BLINDNESS = register("blindness", BlindnessEnchantment::new);	//15
	public static final RegistryObject<Enchantment> NIGHT_VISION = register("night_vision", NightVisionEnchantment::new);	//16
	public static final RegistryObject<Enchantment> HUNGER = register("hunger", HungerEnchantment::new);	//17
	public static final RegistryObject<Enchantment> WEAKNESS = register("weakness", WeaknessEnchantment::new);	//18
	public static final RegistryObject<Enchantment> POISON = register("poison", PoisonEnchantment::new);	//19
	public static final RegistryObject<Enchantment> WITHER = register("wither", WitherEnchantment::new);	//20
	public static final RegistryObject<Enchantment> HEALTH_BOOST = register("health_boost", HealthBoostEnchantment::new);	//21
	public static final RegistryObject<Enchantment> ABSORPTION = register("absorption", AbsorptionEnchantment::new);	//22
	public static final RegistryObject<Enchantment> SATURATION = register("saturation", SaturationEnchantment::new);	//23
	public static final RegistryObject<Enchantment> GLOWING = register("glowing", GlowingEnchantment::new);	//24
	public static final RegistryObject<Enchantment> LEVITATION = register("levitation", LevitationEnchantment::new);	//25
	public static final RegistryObject<Enchantment> LUCK = register("luck", LuckEnchantment::new);	//26
	public static final RegistryObject<Enchantment> BAD_LUCK = register("bad_luck", BadLuckEnchantment::new);	//27
	public static final RegistryObject<Enchantment> SLOW_FALLING = register("slow_falling", SlowFallingEnchantment::new);	//28
	public static final RegistryObject<Enchantment> DOLPHINS_GRACE = register("dolphins_grace", DolphinsGraceEnchantment::new);	//30
	public static final RegistryObject<Enchantment> BAD_OMEN = register("bad_omen", BadOmenEnchantment::new);	//31
	public static final RegistryObject<Enchantment> HERO_OF_THE_VILLAGE = register("hero_of_the_village", HeroOfTheVillageEnchantment::new);	//32
	public static final RegistryObject<Enchantment> DARKNESS = register("darkness", DarknessEnchantment::new);	//33

	private static RegistryObject<Enchantment> register(String name, Supplier<Enchantment> supplier)
	{
		return ENCHANTMENTS.register(name, supplier);
	}

	@SubscribeEvent
	public static void effectFunction(LivingEquipmentChangeEvent event) {

		if (!event.getSlot().getType().equals(EquipmentSlot.Type.ARMOR)) {
			return;
		}

		final ItemStack from = event.getFrom();
		final ItemStack to = event.getTo();
		if (from.sameItemStackIgnoreDurability(to)) {
			return;
		}
		if (!from.isEnchanted() && !to.isEnchanted()) {
			return;
		}

		final LivingEntity entity = event.getEntity();
		manageEffect(entity, EnchantmentInit.SPEED,1);
		manageEffect(entity, EnchantmentInit.SLOWNESS,2);
		manageEffect(entity, EnchantmentInit.HASTE,3);
		manageEffect(entity, EnchantmentInit.MINING_FATIGUE,4);
		manageEffect(entity, EnchantmentInit.STRENGTH,5);
		manageEffect(entity, EnchantmentInit.INSTANT_HEALTH,6);
		manageEffect(entity, EnchantmentInit.INSTANT_DAMAGE,7);
		manageEffect(entity, EnchantmentInit.JUMP_BOOST,8);
		manageEffect(entity, EnchantmentInit.NAUSEA,9);
		manageEffect(entity, EnchantmentInit.REGENERATION,10);
		manageEffect(entity, EnchantmentInit.RESISTANCE,11);
		manageEffect(entity, EnchantmentInit.FIRE_RESISTANCE,12);
		manageEffect(entity, EnchantmentInit.WATER_BREATHING,13);
		manageEffect(entity, EnchantmentInit.INVISIBILITY,14);
		manageEffect(entity, EnchantmentInit.BLINDNESS,15);
		manageEffect(entity, EnchantmentInit.NIGHT_VISION,16);
		manageEffect(entity, EnchantmentInit.HUNGER,17);
		manageEffect(entity, EnchantmentInit.WEAKNESS,18);
		manageEffect(entity, EnchantmentInit.POISON,19);
		manageEffect(entity, EnchantmentInit.WITHER,20);
		manageEffect(entity, EnchantmentInit.HEALTH_BOOST,21);
		manageEffect(entity, EnchantmentInit.ABSORPTION,22);
		manageEffect(entity, EnchantmentInit.SATURATION,23);
		manageEffect(entity, EnchantmentInit.GLOWING,24);
		manageEffect(entity, EnchantmentInit.LEVITATION,25);
		manageEffect(entity, EnchantmentInit.LUCK,26);
		manageEffect(entity, EnchantmentInit.BAD_LUCK,27);
		manageEffect(entity, EnchantmentInit.SLOW_FALLING,28);
		manageEffect(entity, EnchantmentInit.DOLPHINS_GRACE,30);
		manageEffect(entity, EnchantmentInit.BAD_OMEN,31);
		manageEffect(entity, EnchantmentInit.HERO_OF_THE_VILLAGE,32);
		manageEffect(entity, EnchantmentInit.DARKNESS,33);
	}

	private static void manageEffect(final LivingEntity entity, final RegistryObject<Enchantment> enchantment, final int id)
	{
		int level = EnchantmentHelper.getEnchantmentLevel(enchantment.get(), entity);
		final MobEffect effect = Objects.requireNonNull(MobEffect.byId(id));
		entity.removeEffect(effect);
		if(level > 0) {
			entity.addEffect(new MobEffectInstance(effect, 1000000, level-1, false, false,true));
		}
//		TODO TH: add better handling
//		if(level == 0) {
//			entity.removeEffect(effect);
//		}else{
//			final int amp = level - 1;
//			final MobEffectInstance p21165 = new MobEffectInstance(effect, 1000000, amp, false, false,true);
//			if(entity.hasEffect(effect)) {
//				final MobEffectInstance entityEffect = entity.getEffect(effect);
//				final int amplifier = entityEffect.getAmplifier();
//				if(amplifier >= amp) {
//					return;
//				}
//				entity.removeEffect(effect);
//			}
//			entity.addEffect(p21165);
//		}
	}
}
