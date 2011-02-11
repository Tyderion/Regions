package Poly;


public class Polygon extends Poly {

	
	
	public Polygon(PointList list, String col, double op) {
		super(list, col, op);
	}

	@Override
	public String getPoly() {
		this.makestrings();
		return this.part1+"true"+this.part2;
	}
}
