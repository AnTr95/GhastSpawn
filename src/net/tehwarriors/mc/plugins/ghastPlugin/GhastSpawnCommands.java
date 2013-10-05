package net.tehwarriors.mc.plugins.ghastPlugin;

//import org.bukkit.Bukkit;
//import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GhastSpawnCommands implements CommandExecutor {		
	private GhastSpawn parent;
	public GhastSpawnCommands(GhastSpawn parent)
	{
		this.parent = parent;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		Player player = (Player) sender;
		if(commandLabel.equalsIgnoreCase("gsreload"))
		{
			this.parent.reloadConfig();
			player.sendMessage("GhastSpawn was reloaded");
			return true;
		}
		/*if(commandLabel.equalsIgnoreCase("gstime"))
		{
			World world = Bukkit.getServer().getWorld(this.parent.getConfig().getString("world"));
			long worldTime = world.getTime();	
			int time = Integer.parseInt(args[1]) * 20;
			long aoN = time/6000;
			switch(args[0])
			{
			case "night":
				world.setTime(16000);
				aoN = aoN - 1;
				while(aoN > 0)
				{
					if(worldTime > 21998)
					{
						world.setTime(16000);
						aoN = aoN - 1;
						try 
						{
							wait(290000);
						} 
						catch (InterruptedException ie) 
						{
							parent.logToFile("An error was found during Thread sleep during night command");
							parent.logToFile("" + ie);
							aoN = 0;
						}
					}
				}
			break;
			/*case "day":
				worldTime = 4000;
				aoN = aoN - 1;
				while(aoN > 0)
				{
					if(worldTime > 10000)
					{
						world.setTime(4000);
						aoN = aoN - 1;
					}
					try 
					{
						Thread.sleep(290000);
					} 
					catch (InterruptedException ie) 
					{
						parent.logToFile("An error was found during Thread sleep during day command");
						parent.logToFile("" + ie);
						aoN = 0;
					}
				}
			break;
			default:
				player.sendMessage("Type /gs help for a list of commands");
			break;
			}
			return true;
		}*/
		return false;
	}
}
