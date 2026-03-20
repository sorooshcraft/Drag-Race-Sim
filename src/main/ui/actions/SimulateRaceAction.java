package ui.actions;

import ui.CarGameGUI;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class SimulateRaceAction extends AbstractAction {
    private final CarGameGUI gui;

    public SimulateRaceAction(CarGameGUI gui) {
        super("Simulate Race");
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gui.handleRaceSetup(true);
    }
}