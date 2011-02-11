package Poly;

import java.util.ArrayList;


public class PointList {

	
	private ArrayList<Point> list = new ArrayList<Point>();
	
	public PointList(Point first) {
		list.add(first);
	}
	
	public void add(Point p){
		list.add(p);
	}
	
	public ArrayList<Point> getList(){
		return list;
	}
	
}
