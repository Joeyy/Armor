package me.Joeyy.Armor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EquipArmorCommand implements CommandExecutor {
	Armor plugin;

	public EquipArmorCommand(Armor instance) {
		this.plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		Player target = null;

		// CommandSender could be server console.
		if (args.length < 1)
			return false;
		
		if (plugin.isPlayer(sender)) {
			if (!Armor.Permissions.has((Player) sender, "Armor.equiparmor")) {
				sender.sendMessage(ChatColor.YELLOW
						+ "You to dont have proper permissions for that command.");
				return true;
			}
			// default
			target = (Player) sender;
		}

		// check for both player and console
		if (args.length >= 2) {
			Player p = plugin.getServer().getPlayer(args[1]);
			if (p == null || !p.isOnline()) {
				sender.sendMessage(ChatColor.YELLOW
						+ "Player "+ args[1] + " is not found.");
				return true;
			}
				target = p;
		}
		
		// send console an error message
		if (target == null) {
			sender.sendMessage(ChatColor.YELLOW
					+ "No player specified");
			return true;
		}
		
		// find armor type
		ArmorType set;
		try {
			set = ArmorType.valueOf(args[0].toUpperCase());
		} catch (IllegalArgumentException e) {
			set = null;
		}
		
		if (set == null) {
			// maybe send list of valid armor types
			sender.sendMessage(ChatColor.YELLOW
					+ "Invalid armor type. Types: "
					+ ArmorType.getTypeNames());
			return true;
		}
		
		// now give target the armor
		Armor.givePlayerArmor(target, set);
		return true;
	}
}
