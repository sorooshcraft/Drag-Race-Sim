package ui.actions;

import ui.CarGameGUI;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class InstantRaceAction extends AbstractAction {
    private final CarGameGUI gui;

    public InstantRaceAction(CarGameGUI gui) {
        super("Instant Race");
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gui.handleRaceSetup(false);
    }
}