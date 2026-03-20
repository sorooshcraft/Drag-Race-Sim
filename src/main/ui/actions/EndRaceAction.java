package ui.actions;

import ui.CarGameGUI;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class EndRaceAction extends AbstractAction {
    private final CarGameGUI gui;

    public EndRaceAction(CarGameGUI gui) {
        super("End Race");
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gui.getVisualPanel().stopRaceEarly();
        gui.printToConsole("--- RACE ABORTED ---");
        gui.toggleUIState(false);
    }
}