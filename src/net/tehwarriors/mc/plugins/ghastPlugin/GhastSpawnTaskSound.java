package net.tehwarriors.mc.plugins.ghastPlugin;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class GhastSpawnTaskSound implements Runnable {
	public static GhastSpawn plugin;
	private GhastSpawn parent;        
	public GhastSpawnTaskSound(GhastSpawn parent)
    {
        this.parent = parent;
    }
	public void run()
	{
		World world = Bukkit.getServer().getWorld(this.parent.getConfig().getString("world"));
		long worldTime = world.getTime();
		if(worldTime > 16000 && worldTime < 21999)
		{
			for(Player player : Bukkit.getOnlinePlayers())
			{
				Random rng = new Random();
				if(rng.nextInt(3) == 0)
				{
					player.playSound(player.getLocation(), Sound.GHAST_SCREAM2, 10, 1);
				}
				if(rng.nextInt(3) == 1)
				{
					player.playSound(player.getLocation(), Sound.GHAST_MOAN, 10, 1);
				}
				else
				{
					player.playSound(player.getLocation(), Sound.GHAST_SCREAM, 10, 1);
				}
			}
		}
	}
}