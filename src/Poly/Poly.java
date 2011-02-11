package Poly;

import java.util.ArrayList;


public abstract class Poly {
	 private ArrayList< Point > line=new ArrayList< Point >();
	private String color="FFAA00";
	private double opacity=0.5;
	String part1="";
	String part2="";
	
	
	public Poly(){}

	public Poly(PointList list, String col, double op) {
		line = list.getList();
		color=col;
		opacity=op;
	}
	
	public void makestrings(){
		StringBuilder out = new StringBuilder("{");
		out.append("\"color\":\"#"+color+"\", \"opacity\":"+opacity+", ")
		.append("\"closed\": ");
		part1=out.toString();
		out = new StringBuilder(", \"path\": [");
		for (Point p : line) {
			out.append("  {\"x\": "+p.getX()+", ")
			.append("\"y\": "+p.getY()+", ")
			.append("\"z\": "+p.getZ()+"},");
		}
		out.setLength(out.length()-1);
		out.append(" ]},");
		part2= out.toString();
	}
	
	public abstract String getPoly();

}
