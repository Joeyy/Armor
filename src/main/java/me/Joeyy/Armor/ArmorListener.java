package me.Joeyy.Armor;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
//import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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
			if (event.getDamage() <= plugin.getConfiguration().getInt(
					"Leather-Boots-Fall-Damage-Prevention", 1)
					&& player.getInventory().getBoots().getType() == Material.LEATHER_BOOTS) {
				event.getDamage();
				log.info("Leather " + event.getDamage());
				event.setCancelled(true);
			}

			if (event.getDamage() <= plugin.getConfiguration().getInt(
					"Iron-Boots-Fall-Damage-Prevention", 2)
					&& player.getInventory().getBoots().getType() == Material.IRON_BOOTS) {
				event.getDamage();
				log.info("Iron " + event.getDamage());
				event.setCancelled(true);
			}

			if (event.getDamage() <= plugin.getConfiguration().getInt(
					"Gold-Boots-Fall-Damage-Prevention", 3)
					&& player.getInventory().getBoots().getType() == Material.GOLD_BOOTS) {
				event.getDamage();
				log.info("Gold " + event.getDamage());
				event.setCancelled(true);
			}

			if (event.getDamage() <= plugin.getConfiguration().getInt(
					"Diamond-Boots-Fall-Damage-Prevention", 4)
					&& player.getInventory().getBoots().getType() == Material.DIAMOND_BOOTS) {
				event.getDamage();
				log.info("Diamond " + event.getDamage());
				event.setCancelled(true);
			}
		}

		if (plugin.getConfiguration().getBoolean(
				"Hurt-On-Catcus-Contact-Prevention", true)) {

			switch (event.getCause()) {
			case CONTACT:
				switch (player.getInventory().getHelmet().getType()) {
				case IRON_HELMET:
				case GOLD_HELMET:
				case DIAMOND_HELMET:
					break;
				default:
					return;
				}

				switch (player.getInventory().getChestplate().getType()) {
				case IRON_CHESTPLATE:
				case GOLD_CHESTPLATE:
				case DIAMOND_CHESTPLATE:
					break;
				default:
					return;
				}

				switch (player.getInventory().getLeggings().getType()) {
				case IRON_LEGGINGS:
				case GOLD_LEGGINGS:
				case DIAMOND_LEGGINGS:
					break;
				default:
					return;
				}

				switch (player.getInventory().getBoots().getType()) {
				case IRON_BOOTS:
				case GOLD_BOOTS:
				case DIAMOND_BOOTS:
					break;
				default:
					return;
				}

				event.setCancelled(true);
				break;
			}
		}
		if (plugin.getConfiguration().getBoolean(
				"Hurt-On-Catcus-Contact-Prevention", false)) {
		}

		switch (event.getCause()) {
		case LAVA:
			if (itemSet("IRON", player)) {
				event.setDamage(plugin.getConfiguration().getInt(
						"Set-Lava-Damage-Using-Ironarmor", 3));
				System.out.println("Iron " + event.getDamage());
			}

			if (itemSet("GOLD", player)) {
				event.setDamage(plugin.getConfiguration().getInt(
						"Set-Lava-Damage-Using-Goldnarmor", 2));
				System.out.println("Gold " + event.getDamage());
			}
			if (itemSet("DIAMOND", player)) {
				event.setDamage(plugin.getConfiguration().getInt(
						"Set-Lava-Damage-Using-Diamondarmor", 1));
				System.out.println("Diamond " + event.getDamage());
			}
		}

		if (!(event instanceof EntityDamageByEntityEvent))
			return;

		if (event instanceof EntityDamageByEntityEvent) {

			/** ItemStack helmet = player.getInventory().getHelmet();
			ItemStack chestplate = player.getInventory().getChestplate();
			ItemStack leggings = player.getInventory().getLeggings();
			ItemStack boots = player.getInventory().getBoots(); **/

			switch (event.getCause()) {
			case ENTITY_ATTACK:

			/**	if (itemSet("LEATHER", player)) {
					event.setDamage(plugin.getConfiguration().getInt(
							"Damage-Of-Zombie-While-Wearing-Leather", 3));
					System.out.println("Health " + player.getHealth()
							+ " helmet " + helmet.getDurability()
							+ " chestplate " + chestplate.getDurability()
							+ " leggings " + leggings.getDurability()
							+ " boots " + boots.getDurability() + " Monster "
							+ ((EntityDamageByEntityEvent) event).getDamager());
				}
				if (itemSet("IRON", player)) {
					((EntityDamageByEntityEvent) event).getDamager();
					event.getDamage();
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
				} **/
			}  
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
