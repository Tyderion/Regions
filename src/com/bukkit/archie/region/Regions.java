package com.bukkit.archie.region;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;


import Poly.Point;

public class Regions extends JavaPlugin {
	
	public static final String DATA_FILE = "polys.txt";
	public static final String CONFIG_FILE = "config.txt";
	public static Logger log = Logger.getLogger("Minecraft");
	public static PluginDescriptionFile description;
    private final RegionPlayer player = new RegionPlayer( this );

    public Regions( PluginLoader loader, Server server, PluginDescriptionFile pdf, File dir, File plugin, ClassLoader classLoader )
    {
        super( loader, server, pdf, dir, plugin, classLoader );
    }

    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvent( Event.Type.PLAYER_COMMAND, player,  Priority.Normal, this );
       
        log.info( "Regions version 0.1 loaded.");
    }

    @Override
    public void onDisable()
    {
    }
    
 
    public static String safeMessage( String message )
    {
    	String r = message;
    	boolean b = true;
    	while ( b ) {
    		b = false;
	    	while ( r.length() >= 2 && r.charAt( r.length() - 2 ) == '§' ) {
	    		r = r.substring( 0, r.length() - 2 );
	    		b = true;
	    	}
	    	while ( r.length() >= 1 && r.charAt( r.length() - 1 ) == '§' ) {
	    		r = r.substring( 0, r.length() - 1 );
	    		b = true;
	    	}
    	}
    	return r;
    }

    
    public Point getPoint( Player player)
    {
      	
    	Point mark = null;
    	mark = new Point( player.getLocation());
    	return mark;

    }
    
    
    
}