package ru.sbtschool.patterns2;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Bounds {
    private int left;
    private int right;
    private int top;
    private int bottom;

    public boolean inBounds( int x, int y ) {
        return left < x && x < right
                &&
                bottom < y && y < top;
    }
}
