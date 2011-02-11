package Poly;


public class Polyline extends Poly {
	
	
	
	public Polyline(PointList list, String col, double op) {
		super(list, col, op);
	}
	
	public String getPoly(){
		this.makestrings();
		return this.part1+"false"+this.part2;
	}
}
