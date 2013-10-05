package net.tehwarriors.mc.plugins.ghastPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java;

import org.bukkit.plugin.java.JavaPlugin;

public class GhastSpawn extends JavaPlugin {	
	
    public void logToFile(String message)   
    {
	    try
	    {
		    File dataFolder = getDataFolder();
		    if(!dataFolder.exists())
		    {
		    	dataFolder.mkdir();
		    } 
		    
		    File saveTo = new File(getDataFolder(), "gserrors.txt");
		    
		    if (!saveTo.exists())
		    {
		    	saveTo.createNewFile();
		    }    
		    
		    FileWriter fw = new FileWriter(saveTo, true);     
		    PrintWriter pw = new PrintWriter(fw);	     
		    pw.println(message);     
		    pw.flush();	     
		    pw.close();	     
	    } 
	    	catch (IOException e)
		    {    
	    		e.printStackTrace();
		    }
    }
	public void onEnable()
	{		
		this.saveDefaultConfig();
		getLogger().info("onEnable has been invoked.");
		getServer().getPluginManager().registerEvents(new GhastSpawnListener(this), this);
		long interval = this.getConfig().getLong("interval") * 20;
		long screamInterval = this.getConfig().getLong("screamInterval")*20;
		getCommand("gsreload").setExecutor(new GhastSpawnCommands(this));
		getCommand("gstime").setExecutor(new GhastSpawnCommands(this));
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new GhastSpawnTask(this), 0, interval);
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new GhastSpawnTaskSound(this), 0, screamInterval);
	}
	public void onDisable()
	{
		getLogger().info("onDisabled has been invoked.");
	}
}
