package me.Joeyy.Armor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
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

}