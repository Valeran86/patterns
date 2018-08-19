package ru.sbtschool.patterns2.commands;

import ru.sbtschool.patterns2.Tractor;

public class TurnCommand extends Command {
    public TurnCommand( Tractor tractor ) {
        super( tractor );
    }

    @Override
    public void execute( ) {
        tractor.turnClockwise();
    }
}
