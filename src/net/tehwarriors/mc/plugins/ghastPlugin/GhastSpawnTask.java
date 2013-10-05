package net.tehwarriors.mc.plugins.ghastPlugin;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.entity.Wither;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GhastSpawnTask implements Runnable {
	private GhastSpawn parent;        
	public GhastSpawnTask(GhastSpawn parent)
    {
        this.parent = parent;
    }
	int i = 0;
	Random rng = new Random();
	double x;
	double y;
	double z;
	public void run()
	{
		World world = Bukkit.getServer().getWorld(this.parent.getConfig().getString("world"));
		long worldTime = world.getTime();
		i = 0;
		if(Bukkit.getOnlinePlayers().length > 0)
		{
			if (worldTime > 16000 && worldTime < 21999)
			{	
			Player players[] = Bukkit.getServer().getOnlinePlayers();
				while(i < Bukkit.getOnlinePlayers().length)
				{
					x = players[i].getLocation().getX();
					y = players[i].getLocation().getY();
					z = players[i].getLocation().getZ();
					World world2 = players[i].getWorld();
					world2.setTime(worldTime);
					Location loc = new Location(world2, x+5, y+15, z+5);
					Location loc2 = new Location(world2, x+10, y+3, z+10);
					if (i < Bukkit.getOnlinePlayers().length)
					{
						Skeleton skeleton = (Skeleton) world2.spawnEntity(loc2, EntityType.SKELETON);
						skeleton.setCustomName("TOSOG'S WITHER SKELETON");
						skeleton.setSkeletonType(SkeletonType.WITHER);
						if(rng.nextInt(2) == 1)
						{
							skeleton.getEquipment().setItemInHand(new ItemStack(Material.BOW));
							skeleton.setSkeletonType(SkeletonType.WITHER);
							skeleton.setFireTicks(6);
						}
						else
						{
							skeleton.getEquipment().setItemInHand(new ItemStack(Material.IRON_SWORD));
						}
						skeleton.setMaxHealth(40);
						skeleton.setHealth(40);				
						skeleton.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 10, 1));
					}
					if(rng.nextInt(15) == 0)
					{
						players[i].getWorld().spawnEntity(loc, EntityType.WITHER);
					}
					i++;
				}
			}
			if (worldTime > 21999)
			{
				if(Bukkit.getServer().getWorld(this.parent.getConfig().getString("mineWorld")) != null)
				{
					World world2 = Bukkit.getServer().getWorld(this.parent.getConfig().getString("mineWorld"));
					for(Entity en : world2.getEntitiesByClasses(Wither.class, Skeleton.class))
					{
						en.remove();
					}
				}
				for(Entity en2 : world.getEntitiesByClasses(Wither.class, Skeleton.class))
				{
					en2.remove();
				}
			}
		}
	}
}