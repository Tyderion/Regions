package Poly;

import org.bukkit.Location;

public class Point {
	private int x;
	private int y;
	private int z;
	
	
	public Point( Location loc)
	{
		x = loc.getBlockX();
		y = loc.getBlockY();
		z = loc.getBlockZ();
	}
	
	public Point( String s)
	{
		String[] args = s.split( " ", 3 );
		x = Integer.parseInt( args[ 0 ] );
		y = Integer.parseInt( args[ 1 ] );
		z = Integer.parseInt( args[ 2 ] );
	}
	
	
	public String saveString()
	{
		return "xyz " + Integer.toString( getX() ) + " " + Integer.toString( getY() ) + " " + Integer.toString( getZ() ).replaceAll( "§", "##" );
	}
	
	
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getZ()
	{
		return z;
	}
}
