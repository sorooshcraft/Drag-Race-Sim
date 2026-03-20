package ui.actions;

import ui.CarGameGUI;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Action class to trigger a race setup with visual animation enabled
@ExcludeFromJacocoGeneratedReport
public class SimulateRaceAction extends AbstractAction {
    private final CarGameGUI gui;

    // EFFECTS: initializes the action with a name and stores the GUI reference
    public SimulateRaceAction(CarGameGUI gui) {
        super("Simulate Race");
        this.gui = gui;
    }

    // EFFECTS: delegates to the GUI race setup method with simulation enabled
    @Override
    public void actionPerformed(ActionEvent evt) {
        gui.handleRaceSetup(true);
    }
}