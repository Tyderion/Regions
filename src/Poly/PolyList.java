package Poly;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.bukkit.archie.region.Regions;


public class PolyList {
	private final Regions plugin;
	ArrayList<String> Linelist = new ArrayList<String>();
	ArrayList<String> Gonlist = new ArrayList<String>();
	
	public PolyList(final Regions plugin){
		this.plugin=plugin;
		try {
		    BufferedReader reader = new BufferedReader( new FileReader( plugin.getDataFolder() + File.separator + Regions.DATA_FILE ) );
		    boolean b = true;
		    String s = reader.readLine();
		    while ( b ) {
		    	if (s.contains("\"x\"")) {
		    		if (s.contains("false")) Linelist.add(s);
		    		else Gonlist.add(s);
		    	}
		    	s = reader.readLine();
		    	b = s != null;
		    }
		    reader.close();
		}
		catch ( IOException e ) {
		}
	};
	
	
	public void add(Polyline l) {
		Linelist.add(l.getPoly());
	}
	public void add(Polygon l) {
		Gonlist.add(l.getPoly());
	}
	
	public void add(String str){
		if (str.contains("false")) Linelist.add(str);
		else Gonlist.add(str);
		
		
	}
	
	public void writeData()
	{
    	try {
    		plugin.getDataFolder().mkdir();
    	    BufferedWriter writer = new BufferedWriter( new FileWriter( plugin.getDataFolder() + File.separator + Regions.DATA_FILE ) );
    	    System.out.println("writing data");
    	    writer.write("var regionData=[\n");
    	    for ( String p : Linelist )
    	    	writer.write( p + "\n" );
    	    for ( String p : Gonlist )
    	    	writer.write( p + "\n" );
    	    writer.write("\n   ];");
    	    writer.close();
    	}
    	catch ( IOException e ) {
    	}
	}
}
