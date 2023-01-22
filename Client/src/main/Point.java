package main;

import java.io.Serializable;

public class Point implements Serializable {
public int x;
public int y;
public Point(int x, int y) {
	
	this.x = x;
	this.y = y;
}
@Override
public String toString() {
	return "Point [x=" + x + ", y=" + y + "]";
}
@Override
public boolean equals(Object obj) {
	// TODO Auto-generated method stub
	if(this.x==((Point)obj).x&&this.y==((Point)obj).y) {
		return true;
	}else {
		return false;
	}
}
public double distance(Point b) {
	//
	return Math.sqrt(Math.pow(this.x-(b.x), 2)+Math.pow(this.y-(b.y), 2));
}
}
