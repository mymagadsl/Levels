package com.thexfactor117.levels.events;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.thexfactor117.levels.handlers.ConfigHandler;
import com.thexfactor117.levels.helpers.AbilityHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
public class EventLivingHurt 
{	
	/**
	 * Fired when an entity is about to be hurt.
	 * @param event
	 */
	@SubscribeEvent
	public void hitEntity(LivingHurtEvent event)
	{
		/*
		 * 
		 * WEAPONS
		 *
		 */
		if (event.source.getSourceOfDamage() instanceof EntityPlayer)
		{
			Random rand = new Random();
			EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
			EntityLivingBase enemy = event.entityLiving;
			ItemStack stack = player.inventory.getCurrentItem();
			
			if (stack != null)
			{
				if (stack.getItem() instanceof ItemSword)
				{
					NBTTagCompound nbt = stack.getTagCompound();
					
					if (nbt != null)
					{
						/*
						 * Experience
						 */
						if (nbt.getInteger("LEVEL") != 6)
						{
							if (nbt.getInteger("LEVEL") == 1 || nbt.getInteger("LEVEL") == 2 || nbt.getInteger("LEVEL") == 3)
							{
								nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 1);
							}
							else if (nbt.getInteger("LEVEL") == 4 || nbt.getInteger("LEVEL") == 5)
							{
								int var = rand.nextInt(4);
								if (var == 0)
								{
									nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 2);
								}
								else
								{
									nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 1);
								}
							}
						}
						
						/*
						 * Leveling system
						 */
						if (nbt.getInteger("LEVEL") == 1 && nbt.getInteger("EXPERIENCE") >= ConfigHandler.weaponMaxLevel2Exp)
						{
							nbt.setInteger("LEVEL", 2);
							AbilityHelper.getRandomizedMeleeAbilities(stack, nbt.getInteger("LEVEL"));
							Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Your weapon has leveled up!"));
						}
						
						if (nbt.getInteger("LEVEL") == 2 && nbt.getInteger("EXPERIENCE") >= ConfigHandler.weaponMaxLevel3Exp)
						{
							nbt.setInteger("LEVEL", 3);
							AbilityHelper.getRandomizedMeleeAbilities(stack, nbt.getInteger("LEVEL"));
							Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GREEN + "Your weapon has leveled up!"));
						}
						
						if (nbt.getInteger("LEVEL") == 3 && nbt.getInteger("EXPERIENCE") >= ConfigHandler.weaponMaxLevel4Exp)
						{
							nbt.setInteger("LEVEL", 4);
							AbilityHelper.getRandomizedMeleeAbilities(stack, nbt.getInteger("LEVEL"));
							Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Your weapon has leveled up!"));
						}
						
						if (nbt.getInteger("LEVEL") == 4 && nbt.getInteger("EXPERIENCE") >= ConfigHandler.weaponMaxLevel5Exp)
						{
							nbt.setInteger("LEVEL", 5);
							AbilityHelper.getRandomizedMeleeAbilities(stack, nbt.getInteger("LEVEL"));
							Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "Your weapon has leveled up!"));
						}
						
						if (nbt.getInteger("LEVEL") == 5 && nbt.getInteger("EXPERIENCE") >= ConfigHandler.weaponMaxLevel6Exp)
						{
							nbt.setInteger("LEVEL", 6);
							AbilityHelper.getRandomizedMeleeAbilities(stack, nbt.getInteger("LEVEL"));
							Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Your weapon has reached the max level!"));
						}
						
						/*
						 * Rarity
						 */						
						if (ConfigHandler.enableDevFeatures)
						{							
							if (nbt.getInteger("RARITY") != 1)
							{
								if (nbt.getInteger("RARITY") == 2)
								{
									event.ammount = event.ammount * 1.5F;
								}
								
								if (nbt.getInteger("RARITY") == 3)
								{
									event.ammount = event.ammount * 1.5F;
									
									int var = rand.nextInt(2);
									if (var == 0)
									{
										if (stack.getItemDamage() == stack.getMaxDamage())
										{
											stack.setItemDamage(stack.getItemDamage());
										}
										else
										{
											stack.setItemDamage(stack.getItemDamage() + 1);
										}
									}
								}
								
								if (nbt.getInteger("RARITY") == 4)
								{
									event.ammount = event.ammount * 2.0F;
									
									int var = rand.nextInt(10);
									if (var <= 5)
									{
										if (stack.getItemDamage() == stack.getMaxDamage())
										{
											stack.setItemDamage(stack.getItemDamage());
										}
										else
										{
											stack.setItemDamage(stack.getItemDamage() + 1);
										}
									}
									
									int var1 = rand.nextInt(20);
									if (var1 == 0)
									{
										if (stack.getItemDamage() == stack.getMaxDamage())
										{
											stack.setItemDamage(stack.getItemDamage());
										}
										else
										{
											stack.setItemDamage(stack.getItemDamage() + 20);
										}
									}
								}
								
								if (nbt.getInteger("RARITY") == 5)
								{
									event.ammount = event.ammount * 3.0F;
									
									int var = rand.nextInt(4);
									if (var != 0)
									{
										if (stack.getItemDamage() == stack.getMaxDamage())
										{
											stack.setItemDamage(stack.getItemDamage());
										}
										else
										{
											stack.setItemDamage(stack.getItemDamage() + 1);
										}
									}
									
									int var1 = rand.nextInt(10);
									if (var1 == 0)
									{
										if (stack.getItemDamage() == stack.getMaxDamage())
										{
											stack.setItemDamage(stack.getItemDamage());
										}
										else
										{
											stack.setItemDamage(stack.getItemDamage() + 20);
										}
									}
								}
							}
						}
						
						/*
						 * Abilities
						 */
						if (enemy != null)
						{
							if (nbt.getBoolean("FIRE")) enemy.setFire(4);
							if (nbt.getBoolean("FROST")) enemy.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20*4, 10));
							if (nbt.getBoolean("POISON")) enemy.addPotionEffect(new PotionEffect(Potion.poison.id, 20*4, 0));
							if (nbt.getBoolean("Strength")) 
							{
								int var = rand.nextInt(10);
								if (var == 0) player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20*10, 0));
							}
							
							if (nbt.getBoolean("ETHEREAL"))
							{
								float healthToBeAdded = 5F;
								
								int var1 = rand.nextInt(4);
								if (var1 == 0) 
								{				
									player.setHealth(player.getHealth() + healthToBeAdded);
									
									if (player.getHealth() > 20)
									{
										player.setHealth(20);
									}
								}
							}
							
							if (nbt.getBoolean("VOID"))
							{
								if (ConfigHandler.voidInstantKill)
								{
									int var2 = rand.nextInt(20);
									if (var2 == 0)
									{
										enemy.setHealth(0);
									}
								}
								else
								{
									int var2 = rand.nextInt(20);
									if (var2 == 0)
									{
										if (enemy.getHealth() < 30)
										{
											enemy.setHealth(0);
										}
										else
										{
											enemy.setHealth((float) (enemy.getHealth() - ConfigHandler.voidDamageAmount));
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		/*
		 * 
		 * ARMOR
		 * 
		 */
		if (event.entityLiving instanceof EntityPlayer)
		{
			Random rand = new Random();
			EntityLivingBase attacker = (EntityLivingBase) event.source.getEntity();
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			
			if (attacker instanceof EntityMob)
			{
				for (int i = 0; i < 4; i++)
				{
					if (player.getCurrentArmor(i) != null)
					{
						if (player.getCurrentArmor(i).getItem() instanceof ItemArmor && player.getCurrentArmor(i).getTagCompound() != null)
						{
							NBTTagCompound nbt = player.getCurrentArmor(i).getTagCompound();
							
							/*
							 * Experience
							 */
							if (nbt.getInteger("LEVEL") != 6)
							{
								if (nbt.getInteger("LEVEL") > 3)
								{
									int var = rand.nextInt(3);
									int var1 = rand.nextInt(3);
									if (var == 0)
									{
										nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 1 + var1);
									}
								}
								else
								{
									nbt.setInteger("EXPERIENCE", nbt.getInteger("EXPERIENCE") + 1);
								}
							}
							
							/*
							 * Leveling system
							 */
							if (nbt.getInteger("LEVEL") == 1 && nbt.getInteger("EXPERIENCE") >= ConfigHandler.armorMaxLevel2Exp)
							{
								nbt.setInteger("LEVEL", 2);
								AbilityHelper.getRandomizedArmorAbilities(player.getCurrentArmor(i), nbt.getInteger("LEVEL"));
								Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Your armor piece has leveled up!"));
							}
							
							if (nbt.getInteger("LEVEL") == 2 && nbt.getInteger("EXPERIENCE") >= ConfigHandler.armorMaxLevel3Exp)
							{
								nbt.setInteger("LEVEL", 3);
								AbilityHelper.getRandomizedArmorAbilities(player.getCurrentArmor(i), nbt.getInteger("LEVEL"));
								Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GREEN + "Your armor piece has leveled up!"));
							}
							
							if (nbt.getInteger("LEVEL") == 3 && nbt.getInteger("EXPERIENCE") >= ConfigHandler.armorMaxLevel4Exp)
							{
								nbt.setInteger("LEVEL", 4);
								AbilityHelper.getRandomizedArmorAbilities(player.getCurrentArmor(i), nbt.getInteger("LEVEL"));
								Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Your armor piece has leveled up!"));
							}
							
							if (nbt.getInteger("LEVEL") == 4 && nbt.getInteger("EXPERIENCE") >= ConfigHandler.armorMaxLevel5Exp)
							{
								nbt.setInteger("LEVEL", 5);
								AbilityHelper.getRandomizedArmorAbilities(player.getCurrentArmor(i), nbt.getInteger("LEVEL"));
								Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "Your armor piece has leveled up!"));
							}
							
							if (nbt.getInteger("LEVEL") == 5 && nbt.getInteger("EXPERIENCE") >= ConfigHandler.armorMaxLevel6Exp)
							{
								nbt.setInteger("LEVEL", 6);
								AbilityHelper.getRandomizedArmorAbilities(player.getCurrentArmor(i), nbt.getInteger("LEVEL"));
								Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Your armor piece has reached the max level!"));
							}
							
							/*
							 * Abilities
							 */
							if (nbt != null)
							{
								if (nbt.getBoolean("HARDENED"))
								{
									int var = rand.nextInt(5);
									if (var == 0)
									{
										attacker.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20*2, 10));
									}
								}
								
								if (nbt.getBoolean("POISONED"))
								{
									int var = rand.nextInt(5);
									if (var == 0)
									{
										attacker.addPotionEffect(new PotionEffect(Potion.poison.id, 20*10, 0));
									}
								}
								
								if (nbt.getBoolean("Strength"))
								{
									int var = rand.nextInt(10);
									if (var == 0)
									{
										player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20*10, 0));
									}
								}
								
								if (nbt.getBoolean("ETHEREAL"))
								{
									int var = rand.nextInt(20);
									if (var == 0)
									{
										player.setHealth(20.0F);
									}
								}
								
								if (nbt.getBoolean("VOID"))
								{
									int var = rand.nextInt(20);
									if (var == 0)
									{
										attacker.setHealth(0);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
