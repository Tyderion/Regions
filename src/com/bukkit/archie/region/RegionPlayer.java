package com.bukkit.archie.region;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

import Poly.Point;
import Poly.PointList;
import Poly.PolyList;
import Poly.Polygon;
import Poly.Polyline;

public class RegionPlayer extends PlayerListener {

    private final Regions plugin;
    private final PolyList list;
    private PointList plist;
    private boolean line=false;
    private boolean region=false;
    private String color="FFAA00";
    private double opacity=0.5;
    
    private final String HEXPATTERN="([a-f]|[A-F]|[0-9]){6}?$";
    
    private final Pattern hex = Pattern.compile(HEXPATTERN);
    public RegionPlayer( final Regions plugin )
    {
        this.plugin = plugin;
        this.list = new PolyList(plugin);
    }

    @Override
    public void onPlayerCommand( PlayerChatEvent event )
    {
    	
    	if ( event.isCancelled() )
    		return;
    	String[] args = event.getMessage().split( " ", 2 );
    	boolean b = false; 
    	if (line || region) {
    		if ( args[ 0 ].equalsIgnoreCase( "/setcolor" ) ) {
    			
    			Matcher m = hex.matcher(args[1]);
    			if (m.matches()){
    				color=args[1];
    				event.getPlayer().sendMessage("Color set");
    			} else {
        		event.getPlayer().sendMessage("Please use a 6-Digite Hex-Number without the preceeding #");
    			}
        		b = true;
        	}	
    		
    		if ( args[ 0 ].equalsIgnoreCase( "/setopacity" ) ) {
    			double d = 0;
    			boolean doub=false;
    			try {
    		         d = Double.valueOf(args[1].trim()).doubleValue();
    		         doub=true;
    		      } catch (NumberFormatException nfe) {
    		    	  event.getPlayer().sendMessage("NumberFormatException: " + nfe.getMessage());
    		      }
    		      if (doub && d<=1 && d>=0){
    		    	  this.opacity=d;
    		    	  event.getPlayer().sendMessage("opacity set to:" + d);
    		      } else event.getPlayer().sendMessage("Opacity Values should be between 0 and 1");
    		}

        		b = true;
        		
    	if ( args[ 0 ].equalsIgnoreCase( "/apoint" ) ) {
    		Point p = new Point( event.getPlayer().getLocation());
    		
    		plist.add(p);
    		event.getPlayer().sendMessage("Point added: ("+p.getX()+", "+p.getY()+", "+p.getZ()+")");
    		b = true;
    	}
    	if ( args[ 0 ].equalsIgnoreCase( "/endpoint" ) ) {

			Point p = new Point( event.getPlayer().getLocation());
			if (line){
				plist.add(p);
				Polyline line = new Polyline(plist,color,opacity);
				list.add(line);
				event.getPlayer().sendMessage("Point added: ("+p.getX()+", "+p.getY()+", "+p.getZ()+")");
				event.getPlayer().sendMessage( "Polyline ended" );
				event.getPlayer().sendMessage( line.getPoly());
				this.line=false;
			}
			if (region){
				plist.add(p);
				Polygon gon = new Polygon(plist,color,opacity);
				list.add(gon);
				event.getPlayer().sendMessage("Point added: ("+p.getX()+", "+p.getY()+", "+p.getZ()+")");
				event.getPlayer().sendMessage( "Region (Polygon) ended" );
				event.getPlayer().sendMessage( gon.getPoly());
				this.region=false;
			}

			b = true;
    	}
    	} else {
    	
    	if ( args[ 0 ].equalsIgnoreCase( "/linestart" ) ) {
    		line=true;
    		Point p = new Point( event.getPlayer().getLocation());
    		plist = new PointList(p);
    		event.getPlayer().sendMessage("Line started \n Point added: ("+p.getX()+", "+p.getY()+", "+p.getZ()+")");
    		b = true;
    	} else if ( args[ 0 ].equalsIgnoreCase( "/regionstart" ) ) {
    		region=true;
    		Point p = new Point( event.getPlayer().getLocation());
    		event.getPlayer().sendMessage("Region started \n Point added: ("+p.getX()+", "+p.getY()+", "+p.getZ()+")");
    		plist = new PointList(p);
    		b = true;
    	} else if ( args[ 0 ].equalsIgnoreCase( "/savepolys" ) ) {
    		event.getPlayer().sendMessage("trying to save your polygons");
    		this.list.writeData();
    		
    		b = true;
    	}
    	}
    	
    	event.setCancelled( b );
    }

}