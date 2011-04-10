package me.Joeyy.Armor;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.inventory.ItemStack;

public class ArmorListener extends EntityListener {

	Logger log = Logger.getLogger("Minecraft");

	public static Armor plugin;

	public ArmorListener(Armor instance) {
		plugin = instance;
	}

	public void onEntityDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();

		if (!(entity instanceof Player))
			return;

		Player player = (Player) entity;

		switch (event.getCause()) {
		case FALL:
			fall(event, player);
			break;
		case LAVA:
			lava(event, player);
			break;
		case CONTACT:
			contact(event, player);
			break;
		}

	/**	if (!(event instanceof EntityDamageByEntityEvent))
			return;

		if (event instanceof EntityDamageByEntityEvent) {

			switch (event.getCause()) {
			case ENTITY_ATTACK:

				if (itemSet("LEATHER", player)) {
					event.setDamage(plugin
							.getConfiguration()
							.getInt("Chance-Of-Missed-Attacks-While-Wearing-Leather",
									5));
				}
				if (itemSet("IRON", player)) {
					((EntityDamageByEntityEvent) event).getDamager();
					event.setDamage(plugin.getConfiguration().getInt(
							"Damage-Of-Zombie-While-Wearing-Iron", 2));
				}
				if (itemSet("GOLD", player)) {
					((EntityDamageByEntityEvent) event).getDamager();
					event.getDamage();
					event.setDamage(plugin.getConfiguration().getInt(
							"Damage-Of-Zombie-While-Wearing-Gold", 2));
				}
				if (itemSet("DIAMOND", player)) {
					((EntityDamageByEntityEvent) event).getDamager();
					event.getDamage();
					event.setDamage(plugin.getConfiguration().getInt(
							"Damage-Of-Zombie-While-Wearing-Diamond", 1));
				}
			}
		} **/
	}

	/**
	 * @param event
	 * @param player
	 */
	private void contact(EntityDamageEvent event, Player player) {
		if (plugin.getConfiguration().getBoolean("Hurt-On-Catcus-Contact-Prevention", true)) {

				switch (player.getInventory().getHelmet().getType()) {
				case IRON_HELMET:
				case CHAINMAIL_HELMET:
				case GOLD_HELMET:
				case DIAMOND_HELMET:
					break;
				default:
					return;
				}

				switch (player.getInventory().getChestplate().getType()) {
				case IRON_CHESTPLATE:
				case CHAINMAIL_CHESTPLATE:
				case GOLD_CHESTPLATE:
				case DIAMOND_CHESTPLATE:
					break;
				default:
					return;
				}

				switch (player.getInventory().getLeggings().getType()) {
				case IRON_LEGGINGS:
				case CHAINMAIL_LEGGINGS:
				case GOLD_LEGGINGS:
				case DIAMOND_LEGGINGS:
					break;
				default:
					return;
				}

				switch (player.getInventory().getBoots().getType()) {
				case IRON_BOOTS:
				case CHAINMAIL_BOOTS:
				case GOLD_BOOTS:
				case DIAMOND_BOOTS:
					break;
				default:
					return;
				}

				event.setCancelled(true);
		}
	}

	/**
	 * @param event
	 * @param player
	 */
	private void lava(EntityDamageEvent event, Player player) {
		if (itemSet("LEATHER", player)) {
			event.setDamage(plugin.getConfiguration().getInt(
					"Set-Lava-Damage-Using-Ironarmor", 4));
		}
		
		if (itemSet("IRON", player)) {
			event.setDamage(plugin.getConfiguration().getInt(
					"Set-Lava-Damage-Using-Ironarmor", 3));
		}
		
		if (itemSet("GOLD", player)) {
			event.setDamage(plugin.getConfiguration().getInt(
					"Set-Lava-Damage-Using-Ironarmor", 3));
		}

		if (itemSet("CHAINMAIL", player)) {
			event.setDamage(plugin.getConfiguration().getInt(
					"Set-Lava-Damage-Using-Goldnarmor", 2));
		}
		if (itemSet("DIAMOND", player)) {
			event.setDamage(plugin.getConfiguration().getInt(
					"Set-Lava-Damage-Using-Diamondarmor", 1));
		}
	}

	/**
	 * @param event
	 * @param player
	 */
	private void fall(EntityDamageEvent event, Player player) {
		if (event.getDamage() <= plugin.getConfiguration().getInt(
				"Leather-Boots-Fall-Damage-Prevention", 1)
				&& player.getInventory().getBoots().getType() == Material.LEATHER_BOOTS) {
			event.getDamage();
			event.setCancelled(true);
		}

		if (event.getDamage() <= plugin.getConfiguration().getInt(
				"Iron-Boots-Fall-Damage-Prevention", 2)
				&& player.getInventory().getBoots().getType() == Material.IRON_BOOTS) {
			event.getDamage();
			event.setCancelled(true);
		}
		
		if (event.getDamage() <= plugin.getConfiguration().getInt(
				"Chain-Boots-Fall-Damage-Prevention", 2)
				&& player.getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS) {
			event.getDamage();
			event.setCancelled(true);
		}

		if (event.getDamage() <= plugin.getConfiguration().getInt(
				"Gold-Boots-Fall-Damage-Prevention", 3)
				&& player.getInventory().getBoots().getType() == Material.GOLD_BOOTS) {
			event.getDamage();
			event.setCancelled(true);
		}

		if (event.getDamage() <= plugin.getConfiguration().getInt(
				"Diamond-Boots-Fall-Damage-Prevention", 4)
				&& player.getInventory().getBoots().getType() == Material.DIAMOND_BOOTS) {
			event.getDamage();
			event.setCancelled(true);
		}
	}

	public boolean itemSet(String type, Player player) {
		String[] parts = { "BOOTS", "LEGGINGS", "HELMET", "CHESTPLATE" };
		for (String p : parts) {
			ItemStack[] i = player.getInventory().getArmorContents();
			for (int j = 0; j < i.length; j++) {
				if (i[j].getType().toString().equalsIgnoreCase(type + "_" + p)) {
					return true;
				}
			}
		}
		return false;
	}
}