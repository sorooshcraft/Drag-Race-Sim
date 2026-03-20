package ui.actions;

import model.Car;
import ui.CarGameGUI;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Action class to handle the removal of a selected car from the garage
@ExcludeFromJacocoGeneratedReport
public class RemoveCarAction extends AbstractAction {
    private final CarGameGUI gui;

    // EFFECTS: initializes the action with a name and stores the GUI reference
    public RemoveCarAction(CarGameGUI gui) {
        super("Remove Car");
        this.gui = gui;
    }

    // MODIFIES: gui
    // EFFECTS: prompts for confirmation and removes the selected car from the garage; 
    //          refreshes the GUI list and console.
    @Override
    public void actionPerformed(ActionEvent evt) {
        int index = gui.getCarList().getSelectedIndex();
        if (index < 0) {
            JOptionPane.showMessageDialog(gui, "Please select a car from the garage to remove!");
            return;
        }

        Car c = gui.getGarage().getCar(index);
        
        int response = JOptionPane.showConfirmDialog(gui,
                "Are you sure you want to scrap " + c.getName() + "?",
                "Confirm Removal",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            gui.getGarage().removeCar(c);
            gui.refreshList();
            gui.getDetailsArea().setText("");
            gui.printToConsole("Scrapped car: " + c.getName());
        }
    }
}