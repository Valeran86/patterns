package ru.sbtschool.patterns2.commands;

import ru.sbtschool.patterns2.Tractor;

public abstract class Command {

    public final Tractor tractor;

    Command( Tractor tractor ) {
        this.tractor = tractor;
    }

    public abstract void execute( );

}
