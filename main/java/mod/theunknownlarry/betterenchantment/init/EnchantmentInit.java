package mod.theunknownlarry.betterenchantment.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mod.theunknownlarry.betterenchantment.Main;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentAbsorption;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentBadLuck;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentBlindness;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentFireResistance;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentGlowing;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentHaste;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentHealthBoost;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentHunger;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentInstantDamage;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentInstantHealth;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentInvisibility;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentJumpBoost;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentLevitation;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentLuck;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentMiningFatigue;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentNausea;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentNightVision;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentPoison;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentRegeneration;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentResistance;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentSaturation;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentSlowness;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentSpeed;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentStrength;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentWaterBreathing;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentWeakness;
import mod.theunknownlarry.betterenchantment.enchantment.EnchantmentWither;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

@Mod.EventBusSubscriber(modid=Main.MODID)
public class EnchantmentInit {
	
	public static final List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();
	
	public static final Enchantment SPEED = new EnchantmentSpeed();
	public static final Enchantment SLOWNESS = new EnchantmentSlowness();
	public static final Enchantment HASTE = new EnchantmentHaste();
	public static final Enchantment MINING_FATIGUE = new EnchantmentMiningFatigue();
	public static final Enchantment STRENGTH = new EnchantmentStrength();
	public static final Enchantment INSTANT_HEALTH = new EnchantmentInstantHealth();
	public static final Enchantment INSTANT_DAMAGE = new EnchantmentInstantDamage();
	public static final Enchantment JUMP_BOOST = new EnchantmentJumpBoost();
	public static final Enchantment NAUSEA = new EnchantmentNausea();
	public static final Enchantment REGENERATION = new EnchantmentRegeneration();
	public static final Enchantment RESISTANCE = new EnchantmentResistance();
	public static final Enchantment FIRE_RESISTANCE = new EnchantmentFireResistance();
	public static final Enchantment WATER_BREATHING = new EnchantmentWaterBreathing();
	public static final Enchantment INVISIBILITY = new EnchantmentInvisibility();
	public static final Enchantment BLINDNESS = new EnchantmentBlindness();
	public static final Enchantment NIGHT_VISION = new EnchantmentNightVision();
	public static final Enchantment HUNGER = new EnchantmentHunger();
	public static final Enchantment WEAKNESS = new EnchantmentWeakness();
	public static final Enchantment POISON = new EnchantmentPoison();
	public static final Enchantment WITHER = new EnchantmentWither();
	public static final Enchantment HEALTH_BOOST = new EnchantmentHealthBoost();
	public static final Enchantment ABSORPTION = new EnchantmentAbsorption();
	public static final Enchantment SATURATION = new EnchantmentSaturation();
	public static final Enchantment GLOWING = new EnchantmentGlowing();
	public static final Enchantment LEVITATION = new EnchantmentLevitation();
	public static final Enchantment LUCK = new EnchantmentLuck();
	public static final Enchantment BAD_LUCK = new EnchantmentBadLuck();
	
	@SubscribeEvent
	public static void effectFunction(PlayerTickEvent event) {
		
		if(event.player.hasItemInSlot(EntityEquipmentSlot.HEAD)) {
					
			ItemStack item = event.player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
			
			if(item.isItemEnchanted()) {
				
				Map<Enchantment, Integer> enchs = EnchantmentHelper.getEnchantments(item);
				
				for (Entry<Enchantment, Integer> entry : enchs.entrySet()) {
					
		            if(entry.getKey().getName().contentEquals("enchantment.Bad Luck")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(27), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Fire Resistance")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(12), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Glowing")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(24), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Instant Damage")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(7), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Instant Health")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(6), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Invisibility")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(14), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Luck")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(26), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Nausea")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Night Vision")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Resistance")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Water Breathing")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(13), 2, entry.getValue()-1, false, false));
		            	
		            }
		            
		    	}
				
			}
			
		}
		
		if(event.player.hasItemInSlot(EntityEquipmentSlot.CHEST)) {
			
			ItemStack item = event.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
			
			if(item.isItemEnchanted()) {
				
				Map<Enchantment, Integer> enchs = EnchantmentHelper.getEnchantments(item);
				
				for (Entry<Enchantment, Integer> entry : enchs.entrySet()) {
					
		            if(entry.getKey().getName().contentEquals("enchantment.Absorption")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(22), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Bad Luck")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(27), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Fire Resistance")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(12), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Glowing")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(24), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Haste")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(3), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Health Boost")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(21), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Hunger")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(17), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Instant Damage")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(7), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Instant Health")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(6), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Invisibility")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(14), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Mining Fatigue")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(4), 2, entry.getValue()+1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Poison")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(19), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Regeneration")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Resistance")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Saturation")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(23), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Strength")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Weakness")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(18), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Wither")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(20), 2, entry.getValue()-1, false, false));
		            	
		            }
		            
		    	}
				
			}
			
		}
		
		if(event.player.hasItemInSlot(EntityEquipmentSlot.LEGS)) {
			
			ItemStack item = event.player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
			
			if(item.isItemEnchanted()) {
				
				Map<Enchantment, Integer> enchs = EnchantmentHelper.getEnchantments(item);
				
				for (Entry<Enchantment, Integer> entry : enchs.entrySet()) {
					
		            if(entry.getKey().getName().contentEquals("enchantment.Bad Luck")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(27), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Fire_Resistance")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(12), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Glowing")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(24), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Instant Damage")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(7), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Instant Health")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(6), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Invisibility")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(14), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Resistance")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 2, entry.getValue()-1, false, false));
		            	
		            }
		            
		    	}
				
			}
			
}
		
		if(event.player.hasItemInSlot(EntityEquipmentSlot.FEET)) {
			
			ItemStack item = event.player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
			
			if(item.isItemEnchanted()) {
				
				Map<Enchantment, Integer> enchs = EnchantmentHelper.getEnchantments(item);
				
				for (Entry<Enchantment, Integer> entry : enchs.entrySet()) {
					
		            if(entry.getKey().getName().contentEquals("enchantment.Bad Luck")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(27), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Slowness")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Speed")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Fire Resistance")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(12), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Glowing")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(24), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Instant Damage")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(7), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Instant Health")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(6), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Invisibility")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(14), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Jump Boost")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(8), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Levitation")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(25), 2, entry.getValue()-1, false, false));
		            	
		            }
					
		            if(entry.getKey().getName().contentEquals("enchantment.Resistance")) {
		            	
		            	event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 2, entry.getValue()-1, false, false));
		            	
		            }
		            
		    	}
				
			}
			
		}
		
	}

}
