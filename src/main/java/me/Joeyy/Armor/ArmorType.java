package me.Joeyy.Armor;

import org.bukkit.Material;

/**
 * @author Ashton
 *
 */
public enum ArmorType {
	LEATHER(	Material.LEATHER_HELMET,
				Material.LEATHER_CHESTPLATE,
				Material.LEATHER_LEGGINGS,
				Material.LEATHER_BOOTS),
			
	CHAINMAIL(	Material.CHAINMAIL_HELMET,
				Material.CHAINMAIL_CHESTPLATE,
				Material.CHAINMAIL_LEGGINGS,
				Material.CHAINMAIL_BOOTS),
			
	IRON(	Material.IRON_HELMET,
			Material.IRON_CHESTPLATE,
			Material.IRON_LEGGINGS,
			Material.IRON_BOOTS),
			 
	DIAMOND(	Material.DIAMOND_HELMET,
				Material.DIAMOND_CHESTPLATE,
				Material.DIAMOND_LEGGINGS,
				Material.DIAMOND_BOOTS),
			
	GOLD(	Material.GOLD_HELMET,
			Material.GOLD_CHESTPLATE,
			Material.GOLD_LEGGINGS,
			Material.GOLD_BOOTS);
	
	public final Material helmet;
	public final Material chestplate;
	public final Material leggings;
	public final Material boots;
	
	private ArmorType(Material h, Material c, Material l, Material b) {
		this.helmet = h; this.chestplate = c;
		this.leggings = l; this.boots = b;
	}

	/**
	 * @return the helmet
	 */
	public Material getHelmet() {
		return helmet;
	}

	/**
	 * @return the chestplate
	 */
	public Material getChestplate() {
		return chestplate;
	}

	/**
	 * @return the leggings
	 */
	public Material getLeggings() {
		return leggings;
	}

	/**
	 * @return the boots
	 */
	public Material getBoots() {
		return boots;
	}
	
	public static String getTypeNames() {
		return ""+ LEATHER +" "+ CHAINMAIL +" "+ IRON +" "+ DIAMOND +" "+ GOLD;
	}
}
