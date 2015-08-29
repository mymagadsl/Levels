package com.thexfactor117.levels.helpers;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * @author TheXFactor117
 *
 */
public class AbilityHelper 
{
	/**
	 * Draws between the given abilities, picking one to be equipped to the weapon.
	 * Default percentages are 50-50 for either Fire or Frost.
	 * @param stack
	 */
	public static void drawLevelTwoMeleeAbility(ItemStack stack)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt != null)
		{
			Random rand = new Random();
			int var = rand.nextInt(1);
			if (var == 0) nbt.setBoolean("FIRE", true);
			if (var == 1) nbt.setBoolean("FROST", true);
		}
	}
	
	/**
	 * Draws between the given abilities, picking one to be equipped to the weapon.
	 * Default percentages are 33-33-33 for Poison, Strength, and nothing respectively.
	 * @param stack
	 */
	public static void drawLevelThreeMeleeAbility(ItemStack stack)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt != null)
		{
			Random rand = new Random();
			int var = rand.nextInt(2);
			if (var == 0) nbt.setBoolean("POISON", true);
			if (var == 1) nbt.setBoolean("STRENGTH", true);
		}
	}
	
	/**
	 * Draws between the given abilities and parameters. Percentages are more complex here,
	 * due to the several scenarios taken into account.
	 * @param stack
	 */
	public static void drawLevelFourMeleeAbility(ItemStack stack)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt != null)
		{
			if (nbt.getBoolean("FIRE") == true)
			{
				/*
				 * If level two yielded nothing, add two abilities (the opposite of what the first ability was,
				 * and a random chance between Poison and Strength.
				 * 
				 * If level two did yield an ability, 50-50 draw between opposite of first ability and other
				 * second ability.
				 */
				if (nbt.getBoolean("POISON") == false && nbt.getBoolean("STRENGTH") == false)
				{
					nbt.setBoolean("FROST", true);
					
					Random rand = new Random();
					int var = rand.nextInt(1);
					if (var == 0) nbt.setBoolean("POISON", true);
					if (var == 1) nbt.setBoolean("STRENGTH", true);
				}
				else
				{
					if (nbt.getBoolean("POISON") == true)
					{
						Random rand = new Random();
						int var = rand.nextInt(1);
						if (var == 0) nbt.setBoolean("FROST", true);
						if (var == 1) nbt.setBoolean("STRENGTH", true);
					}
					else
					{
						Random rand = new Random();
						int var = rand.nextInt(1);
						if (var == 0) nbt.setBoolean("FROST", true);
						if (var == 1) nbt.setBoolean("POISON", true);
					}
				}
			}
			
			if (nbt.getBoolean("FROST") == true)
			{
				/*
				 * If level two yielded nothing, add two abilities (the opposite of what the first ability was,
				 * and a random chance between Poison and Strength.
				 * 
				 * If level two did yield an ability, 50-50 draw between opposite of first ability and other
				 * second ability.
				 */
				if (nbt.getBoolean("POISON") == false && nbt.getBoolean("STRENGTH") == false)
				{
					nbt.setBoolean("FIRE", true);
					
					Random rand = new Random();
					int var = rand.nextInt(1);
					if (var == 0) nbt.setBoolean("POISON", true);
					if (var == 1) nbt.setBoolean("STRENGTH", true);
				}
				else
				{
					if (nbt.getBoolean("POISON") == true)
					{
						Random rand = new Random();
						int var = rand.nextInt(1);
						if (var == 0) nbt.setBoolean("FIRE", true);
						if (var == 1) nbt.setBoolean("STRENGTH", true);
					}
					else
					{
						Random rand = new Random();
						int var = rand.nextInt(1);
						if (var == 0) nbt.setBoolean("FIRE", true);
						if (var == 1) nbt.setBoolean("POISON", true);
					}
				}
			}
		}
	}
	
	/**
	 * Draws between the given abilities, picking one to be equipped to the weapon.
	 * Default percentages are 50-50 for Ethereal and Void. 
	 * @param stack
	 */
	public static void drawLevelFiveMeleeAbility(ItemStack stack)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt != null)
		{
			Random rand = new Random();
			int var = rand.nextInt(1);
			if (var == 0) nbt.setBoolean("ETHEREAL", true);
			if (var == 1) nbt.setBoolean("VOID", true);
		}
	}
	
	/**
	 * Based on the previous ability obtained, a host of possibilities are
	 * available to be attained.
	 * @param stack
	 */
	public static void drawLevelSixMeleeAbility(ItemStack stack)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt != null)
		{
			if (nbt.getBoolean("ETHEREAL") == true)
			{
				Random rand = new Random();
				int var = rand.nextInt(4);
				
				if (var == 0)
				{
					nbt.setBoolean("FIRE", true);
					nbt.setBoolean("STRENGTH", true);
				}
				else if (var == 1)
				{
					nbt.setBoolean("FROST", true);
					nbt.setBoolean("POISON", true);
				}
				else if (var == 2)
				{
					nbt.setBoolean("FIRE", true);
					nbt.setBoolean("POISON", true);
					nbt.setBoolean("STRENGTH", true);
				}
				else if (var == 3)
				{
					nbt.setBoolean("FROST", true);
					nbt.setBoolean("STRENGTH", true);
				}
				else
				{
					nbt.setBoolean("VOID", true);
				}
			}
			
			if (nbt.getBoolean("VOID") == true)
			{
				Random rand = new Random();
				int var = rand.nextInt(4);
				
				if (var == 0)
				{
					nbt.setBoolean("FIRE", true);
					nbt.setBoolean("STRENGTH", true);
				}
				else if (var == 1)
				{
					nbt.setBoolean("FROST", true);
					nbt.setBoolean("POISON", true);
				}
				else if (var == 2)
				{
					nbt.setBoolean("FIRE", true);
					nbt.setBoolean("POISON", true);
					nbt.setBoolean("STRENGTH", true);
				}
				else if (var == 3)
				{
					nbt.setBoolean("FROST", true);
					nbt.setBoolean("STRENGTH", true);
				}
				else
				{
					nbt.setBoolean("ETHEREAL", true);
				}
			}
		}
	}
}