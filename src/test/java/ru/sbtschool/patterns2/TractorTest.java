package ru.sbtschool.patterns2;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static ru.sbtschool.patterns2.Tractor.CMD_FORWARD;
import static ru.sbtschool.patterns2.Tractor.CMD_TURN;

public class TractorTest {

    private Tractor tractor;

    @Before
    public void setUp() {
        tractor = new Tractor(
                Bounds.builder()
                        .left( -5 )
                        .right( 5 )
                        .top( 5 )
                        .bottom( -5 )
                        .build()
        );
    }

    @Test
    public void testMoveForwards() {
        tractor.moveForwards();

        assertEquals( 1, tractor.getPositionY() );
    }

    @Test
    public void testTurnClockwise() {
        tractor.turnClockwise();

        assertEquals( Orientation.EAST, tractor.getOrientation() );
    }

    @Test
    public void testPath() {
        Stream.of(
                CMD_FORWARD, CMD_TURN, CMD_TURN, CMD_FORWARD,
                CMD_FORWARD, CMD_TURN, CMD_TURN, CMD_FORWARD,
                CMD_FORWARD, CMD_TURN, CMD_FORWARD
        ).forEach(
                cmd -> tractor.move( cmd )
        );

        assertEquals( "X not equal", 1, tractor.getPositionX() );
        assertEquals( "Y not equal", 1, tractor.getPositionY() );
        assertEquals( "Orientation not equal", Orientation.EAST, tractor.getOrientation() );
    }

    @Test( expected = TractorInDitchException.class )
    public void testIntersectBounds() {
        Stream.of(
                CMD_FORWARD, CMD_FORWARD, CMD_FORWARD, CMD_FORWARD,
                CMD_FORWARD
        ).forEach(
                cmd -> tractor.move( cmd )
        );

        fail( "Error wasn't send" );
    }
}
