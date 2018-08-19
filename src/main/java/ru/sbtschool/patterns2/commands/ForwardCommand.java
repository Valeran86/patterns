package ru.sbtschool.patterns2.commands;


import ru.sbtschool.patterns2.Tractor;

public class ForwardCommand extends Command {

    public ForwardCommand( Tractor tractor ) {
        super( tractor );
    }

    @Override
    public void execute( ) {
        tractor.moveForwards();
    }
}
