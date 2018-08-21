package ru.sbtschool.patterns2;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * @author Ben
 *
 */
public class TractorTest extends TestCase {

	public void testShouldMoveForward(){
		Tractor tractor = new Tractor();
		tractor.move(TractorCommands.Move);
		assertEquals(0, tractor.getPositionX());
		assertEquals(1, tractor.getPositionY());
	}

	public void testShouldTurn(){
		Tractor tractor = new Tractor();
		tractor.move(TractorCommands.TurnClockwise);
		assertEquals(Orientation.EAST, tractor.getOrientation());
		tractor.move(TractorCommands.TurnClockwise);
		assertEquals(Orientation.SOUTH, tractor.getOrientation());
		tractor.move(TractorCommands.TurnClockwise);
		assertEquals(Orientation.WEST, tractor.getOrientation());
		tractor.move(TractorCommands.TurnClockwise);
		assertEquals(Orientation.NORTH, tractor.getOrientation());
	}

	public void testShouldTurnAndMoveInTheRightDirection(){
		Tractor tractor = new Tractor();
		tractor.move(TractorCommands.TurnClockwise);
		tractor.move(TractorCommands.Move);		
		assertEquals(1, tractor.getPositionX());
		assertEquals(0, tractor.getPositionY());
		tractor.move(TractorCommands.TurnClockwise);
		tractor.move(TractorCommands.Move);		
		assertEquals(1, tractor.getPositionX());
		assertEquals(-1, tractor.getPositionY());
		tractor.move(TractorCommands.TurnClockwise);
		tractor.move(TractorCommands.Move);		
		assertEquals(0, tractor.getPositionX());
		assertEquals(-1, tractor.getPositionY());
		tractor.move(TractorCommands.TurnClockwise);
		tractor.move(TractorCommands.Move);		
		assertEquals(0, tractor.getPositionX());
		assertEquals(0, tractor.getPositionY());		
	}
	
	public void testShouldThrowExceptionIfFallsOffPlateau(){
		Tractor tractor = new Tractor();
		tractor.move(TractorCommands.Move);
		tractor.move(TractorCommands.Move);
		tractor.move(TractorCommands.Move);
		tractor.move(TractorCommands.Move);
		tractor.move(TractorCommands.Move);
		try{
			tractor.move(TractorCommands.Move);
			fail("Tractor was expected to fall off the plateau");
		}catch(TractorInDitchException expected){
		}
	}
	public void testPath() {
		Tractor tractor = new Tractor();
		Stream.of(
				TractorCommands.Move, TractorCommands.TurnClockwise, TractorCommands.TurnClockwise, TractorCommands.Move,
				TractorCommands.Move, TractorCommands.TurnClockwise, TractorCommands.TurnClockwise, TractorCommands.Move,
				TractorCommands.Move, TractorCommands.TurnClockwise, TractorCommands.Move
		).forEach(
				cmd -> tractor.move( cmd )
		);

		assertEquals( "X not equal", 1, tractor.getPositionX() );
		assertEquals( "Y not equal", 1, tractor.getPositionY() );
		assertEquals( "Orientation not equal", Orientation.EAST, tractor.getOrientation() );
	}
	@Test( expected = TractorInDitchException.class )
	public void testIntersectBounds() {
		Tractor tractor = new Tractor();
		Stream.of(
				TractorCommands.Move, TractorCommands.Move, TractorCommands.Move, TractorCommands.Move,
				TractorCommands.Move
		).forEach(
				cmd -> tractor.move( cmd )
		);

		fail( "Error wasn't send" );
	}
}
