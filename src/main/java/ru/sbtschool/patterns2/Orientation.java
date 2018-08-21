package ru.sbtschool.patterns2;

import java.awt.*;

public enum Orientation {
	NORTH {
		public Point move(Point position, int[] field) {
			position.y++;
			if (position.y >= field[1])
				throw new TractorInDitchException();
			return position;
		}
		public Orientation turnClockwise() {
			return EAST;
		}
	},
	WEST {
		public Point move(Point position, int[] field) {
			position.x--;
			if (position.x <= -field[0])
				throw new TractorInDitchException();
			return position;
		}
		public Orientation turnClockwise() {
			return NORTH;
		}
	},
	SOUTH {
		public Point move(Point position, int[] field) {
			position.y--;
			if (position.y <= -field[1])
				throw new TractorInDitchException();
			return position;
		}
		public Orientation turnClockwise() {
			return WEST;
		}
	},
	EAST {
		public Point move(Point position, int[] field) {
			position.x++;
			if (position.x >= field[0])
				throw new TractorInDitchException();
			return position;
		}
		public Orientation turnClockwise() {
			return SOUTH;
		}
	};
	public abstract Point move(Point position, int[] field);
	public abstract Orientation turnClockwise();
}
