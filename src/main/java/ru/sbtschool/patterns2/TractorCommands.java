package ru.sbtschool.patterns2;

public enum TractorCommands {
    Move {
        public void execute (Tractor tractor) {
            tractor.orientation.move(tractor.position, tractor.field);
        }
    },
    TurnClockwise {
        public void execute (Tractor tractor) {
            tractor.orientation = tractor.orientation.turnClockwise();
        }
    };

    public abstract void execute(Tractor tractor);
}
