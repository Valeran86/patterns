package ru.sbtschool.patterns2;

import java.awt.*;

public class Tractor {

	Point position = new Point(0,0);
	int[] field = new int[] { 5, 5 };
	Orientation orientation = Orientation.NORTH;

	public void move(TractorCommands command) {
		command.execute(this);
	}

	public int getPositionX() {
		return position.x;
	}

	public int getPositionY() {
		return position.y;
	}

	public Orientation getOrientation() {
		return orientation;
	}

}
