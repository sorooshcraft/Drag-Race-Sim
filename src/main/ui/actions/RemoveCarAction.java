package ui.actions;

import model.Car;
import ui.CarGameGUI;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class RemoveCarAction extends AbstractAction {
    private final CarGameGUI gui;

    public RemoveCarAction(CarGameGUI gui) {
        super("Remove Car");
        this.gui = gui;
    }

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