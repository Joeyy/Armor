package me.Joeyy.Armor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Armor extends JavaPlugin {
	ArmorListener ArmorListener = new ArmorListener(this);
	PlayerListener ArmorMove = new PlayerListener();
	public static final Logger log = Logger.getLogger("Minecraft");
	public final static String premessage = ChatColor.RED + "[Armor] "
	+ ChatColor.YELLOW;


	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, ArmorListener,
				Event.Priority.Normal, this);
		this.loadConfigFile();
		addCommands();

		log.info("Armor version "+ getDescription().getVersion() + " is enabled");
	}

	public void onDisable() {
		log.info("Armor version "+ getDescription().getVersion() + " is disabled");

	}

	public void loadConfigFile() {
		// load config file, creating it first if it doesn't exist
		File configFile = new File(this.getDataFolder(), "config.properties");

		if (!configFile.canRead())
			try {
				FileOutputStream myFile;
				PrintStream myStream;
				configFile.getParentFile().mkdirs();
				myFile = new FileOutputStream(configFile);
				myStream = new PrintStream(myFile);
				myStream.println("##Control falldamage for each shoe##");
				myStream.println("\n");
				myStream.println("Leather-Boots-Fall-Damage-Prevention: 1\n");
				myStream.println("Iron-Boots-Fall-Damage-Prevention: 2\n");
				myStream.println("Gold-Boots-Fall-Damage-Prevention: 3\n");
				myStream.println("Diamond-Boots-Fall-Damage-Prevention: 4\n");
				myStream.println("\n");
				myStream.println("##Control lavadamage for each armor set##");
				myStream.println("\n");
				myStream.println("Set-Lava-Damage-Using-Ironarmor: 3\n");
				myStream.println("Set-Lava-Damage-Using-Goldarmor: 2\n");
				myStream.println("Set-Lava-Damage-Using-Diamondarmor: 1\n");
				myStream.println("\n");
				myStream.println("##Hurt on hitting cactus with armor (true/false)##");
				myStream.println("\n");
				myStream.println("Hurt-On-Catcus-Contact-Prevention: true\n");
				myStream.println("\n");
				myStream.println("##Gold boots giving light (true/false)##");
				myStream.println("\n");
				myStream.println("Gold-Boots-Give-Light: true\n");
				myStream.println("\n");
				myStream.println("##Control monster damage##");
				myStream.println("\n");
				myStream.println("Damage-Of-Zombie-While-Wearing-Leather: 3\n");
				myStream.println("\n");
				myStream.println("Damage-Of-Zombie-While-Wearing-Iron: 2\n");
				myStream.println("\n");
				myStream.println("Damage-Of-Zombie-While-Wearing-Gold: 2\n");
				myStream.println("\n");
				myStream.println("Damage-Of-Zombie-While-Wearing-Diamond: 1\n");
				myStream.close();
			} catch (Exception e) {
				System.out
						.println("ERROR! Armor: could not create configuration file");
			}
	}
	
	private void addCommands() {
		getCommand("equiparmor").setExecutor(new GiveArmor(this));
	}
	
	public boolean isPlayer(CommandSender sender) {
		if (sender instanceof Player)
			return true;

		return false;
	}
	
	public Player playerMatch(String name) {
		if (this.getServer().getOnlinePlayers().length < 1) {
			return null;
		}

		Player[] online = this.getServer().getOnlinePlayers();
		Player lastPlayer = null;

		for (Player player : online) {
			String playerName = player.getName();
			String playerDisplayName = player.getDisplayName();

			if (playerName.equalsIgnoreCase(name)) {
				lastPlayer = player;
				break;
			} else if (playerDisplayName.equalsIgnoreCase(name)) {
				lastPlayer = player;
				break;
			}

			if (playerName.toLowerCase().indexOf(name.toLowerCase()) != -1) {
				if (lastPlayer != null) {
					return null;
				}

				lastPlayer = player;
			} else if (playerDisplayName.toLowerCase().indexOf(
					name.toLowerCase()) != -1) {
				if (lastPlayer != null) {
					return null;
				}

				lastPlayer = player;
			}
		}

		return lastPlayer;
	}
	
	public String[] itemList() {
		ArrayList<String> itemlist = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					getDataFolder() + File.separator + "item.txt"));
			String str;
			while ((str = in.readLine()) != null) {
				itemlist.add(str);
			}
			in.close();
		} catch (IOException e) {
			log.info("[Armor] Could not get item list.");
		}

		return itemlist.toArray(new String[] {});
	}

}