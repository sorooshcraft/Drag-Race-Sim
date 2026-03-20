package ui.actions;

import ui.CarGameGUI;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Action class to trigger a race setup without visual animation
@ExcludeFromJacocoGeneratedReport
public class InstantRaceAction extends AbstractAction {
    private final CarGameGUI gui;

    // EFFECTS: initializes the action with a name and stores the GUI reference
    public InstantRaceAction(CarGameGUI gui) {
        super("Instant Race");
        this.gui = gui;
    }

    // EFFECTS: delegates to the GUI race setup method with simulation disabled
    @Override
    public void actionPerformed(ActionEvent evt) {
        gui.handleRaceSetup(false);
    }
}