package project;

import java.awt.Point;

public class Dot {

	public final int SIZE;

	int x;
	int y;

	Point vector;

	Data data;

	Point circleVector;

	public Dot(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.SIZE = size;

	}

	public void setVector() {
		int x, y;
		if (Math.random() >= 0.5) {
			x = (int)Math.ceil(Math.random()*2);
		} else {
			x = -(int)Math.ceil(Math.random()*2);
		}

		if (Math.random() >= 0.5) {
			y = (int)Math.ceil(Math.random()*2);
		} else {
			y = -(int)Math.ceil(Math.random()*2);
		}
		vector = new Point(x, y);
	}
//	public void setCircle(float degree, int x , int y, int radius) {
//		int newX = x + (int)Math.cos(degree+0.1) * radius;
//		int newY = y + (int)Math.sin(degree+0.1) * radius;
//
//		circleVector = new Point(newX, newY);
//	}

	public static int distance(Dot a, Dot b) {
		int dx = b.x - a.x;
		int dy = b.y - a.y;
		double distant = Math.sqrt((double)(dx*dx) + (double)(dy*dy));

		return (int)distant;
	}

	public static float stroke(int dt, int radious) {
		 float strokeSize = (float)1 - (float)dt / (radious-1);//99
		return (float) strokeSize;
	}

	public void dotData (Data data) {
		this.data = data;

	}

}
