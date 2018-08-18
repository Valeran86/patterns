package ru.sbtschool.patterns2;

public enum Orientation {
    NORTH {
        @Override
        public Orientation turn() {
            return EAST;
        }

        @Override
        public int[] move( int x, int y ) {
            return new int[]{ x, y + 1 };
        }
    }
    , WEST {
        @Override
        public Orientation turn() {
            return NORTH;
        }

        @Override
        public int[] move( int x, int y ) {
            return new int[]{ x - 1, y };
        }
    }
    , SOUTH {
        @Override
        public Orientation turn() {
            return WEST;
        }

        @Override
        public int[] move( int x, int y ) {
            return new int[]{ x, y - 1 };
        }
    }
    , EAST {
        @Override
        public Orientation turn() {
            return SOUTH;
        }

        @Override
        public int[] move( int x, int y ) {
            return new int[]{ x + 1, y };
        }
    }
    ;

    public abstract Orientation turn();

    public abstract int[] move( int x, int y );
}