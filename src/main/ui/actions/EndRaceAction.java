package ui.actions;

import ui.CarGameGUI;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Action class to handle early termination/abortion of a running race animation
@ExcludeFromJacocoGeneratedReport
public class EndRaceAction extends AbstractAction {
    private final CarGameGUI gui;

    // EFFECTS: initializes the action with a name and stores the GUI reference
    public EndRaceAction(CarGameGUI gui) {
        super("End Race");
        this.gui = gui;
    }

    // MODIFIES: gui
    // EFFECTS: stops the visual animation, prints an abort message, and re-enables UI buttons
    @Override
    public void actionPerformed(ActionEvent evt) {
        gui.getVisualPanel().stopRaceEarly();
        gui.printToConsole("--- RACE ABORTED ---");
        gui.toggleUIState(false);
    }
}