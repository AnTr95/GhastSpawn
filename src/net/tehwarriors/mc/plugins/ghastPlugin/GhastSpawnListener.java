package net.tehwarriors.mc.plugins.ghastPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GhastSpawnListener implements Listener {

	private GhastSpawn parent;        
	public GhastSpawnListener(GhastSpawn parent)
    {
        this.parent = parent;
    }
	@EventHandler(priority = EventPriority.MONITOR)
	public void onEntitySpawn(CreatureSpawnEvent creatureSpawnEvent)
	{
		World world = Bukkit.getServer().getWorld(this.parent.getConfig().getString("world"));
		World world2 = Bukkit.getServer().getWorld(this.parent.getConfig().getString("mineWorld"));
		LivingEntity entity = creatureSpawnEvent.getEntity();
		if(entity.getWorld() == world || entity.getWorld() == world2)
		{
			if(creatureSpawnEvent.getEntityType() == EntityType.ZOMBIE)
			{
				entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 8000, 1));
				entity.setMaxHealth(20);
				entity.setHealth(20);
				entity.setCustomName("ZOMBIE ON ATHENE X-TREME");
			}
			if(creatureSpawnEvent.getEntityType() == EntityType.CREEPER)
			{
				entity.setCustomName("TOSOG'S CREEPER");
			}
			if(creatureSpawnEvent.getEntityType() == EntityType.SPIDER)
			{
				entity.setCustomName("TOSOG'S SPIDER");
			}
			if(creatureSpawnEvent.getEntityType() == EntityType.ENDERMAN)
			{
				entity.setCustomName("TOSOG'S ENDERMAN");
			}
			if(creatureSpawnEvent.getEntityType() == EntityType.WITHER)
			{
				entity.setCustomName("TOSOG'S WITHER");
			}
			if(creatureSpawnEvent.getEntityType() == EntityType.SKELETON)
			{
				entity.setCustomName("TOSOG'S PHANTOM SCOUT");
			}
		}
	}
	@EventHandler
	public void onAttack(EntityDamageEvent entityDamageEvent)
	{
		if(entityDamageEvent.getEntityType() == EntityType.SKELETON)
		{
			LivingEntity ent = (LivingEntity) entityDamageEvent.getEntity();
			if(ent.getCustomName() != "TOSOG'S WITHER SKELETON")
			{
				ent.damage(50);
			}
		}
		
	}
	@EventHandler
	public void onEntityDeath(EntityDeathEvent entityDeathEvent)
	{
		if(entityDeathEvent.getEntityType() == EntityType.SKELETON)
		{
			entityDeathEvent.getDrops().clear();
			entityDeathEvent.setDroppedExp(0);
		}
	}
	@EventHandler
	public void onEntityDamage(EntityDamageEvent entityDamage)
	{
		if(entityDamage.getEntityType() == EntityType.ZOMBIE)
		{
			entityDamage.setDamage(4.5);
		}
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent playerJoin)
	{
		Player player = playerJoin.getPlayer();
		player.removePotionEffect(PotionEffectType.WITHER);
	}
	@EventHandler
	public void onQuitEvent(PlayerQuitEvent playerQuitEvent)
	{
		Player player = playerQuitEvent.getPlayer();
		World world = Bukkit.getServer().getWorld(this.parent.getConfig().getString("world"));
		World world2 = Bukkit.getServer().getWorld(this.parent.getConfig().getString("mineWorld"));
		if(player.getWorld() == world2)
		{
			Double x = parent.getConfig().getDouble("spawnX");
			Double y = parent.getConfig().getDouble("spawnY");
			Double z = parent.getConfig().getDouble("spawnZ");
			Location spawn = new Location(world, x, y ,z);		
			player.teleport(spawn);
		}
	}
}