package ui.actions;

import model.*;
import ui.CarGameGUI;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Action class to handle adding aftermarket modifications to an existing car
@ExcludeFromJacocoGeneratedReport
public class ModifyCarAction extends AbstractAction {
    private final CarGameGUI gui;

    // EFFECTS: initializes the action with a name and stores the GUI reference
    public ModifyCarAction(CarGameGUI gui) {
        super("Modify Car");
        this.gui = gui;
    }

    // MODIFIES: gui
    // EFFECTS: prompts user to select a mod type for the currently selected car in the list
    @Override
    public void actionPerformed(ActionEvent evt) {
        int index = gui.getCarList().getSelectedIndex();
        if (index < 0) {
            JOptionPane.showMessageDialog(gui, "Please select a car from the garage first!");
            return;
        }
        Car c = gui.getGarage().getCar(index);

        String[] modTypes = { "Engine Mod", "Tire Mod", "Body Panel Mod" };
        String modChoice = (String) JOptionPane.showInputDialog(gui, "Select Mod Type:",
                "Modify " + c.getName(), JOptionPane.QUESTION_MESSAGE, null, modTypes, modTypes[0]);

        if (modChoice != null) {
            promptForModStats(c, modChoice);
        }
    }

    // MODIFIES: this, car
    // EFFECTS: shows a dialog to enter specific statistics for the chosen modification type
    private void promptForModStats(Car car, String type) {
        JTextField modName = new JTextField();
        JTextField modCost = new JTextField();
        JTextField modWeight = new JTextField();
        JTextField modSpec = new JTextField();
        String specLabel = type.equals("Engine Mod") ? "HP Gain:"
                : type.equals("Tire Mod") ? "Grip Multiplier:" : "Aero Grip:";

        Object[] message = { "Mod Name:", modName, "Cost:", modCost,
                "Weight Change (lbs):", modWeight, specLabel, modSpec };

        int option = JOptionPane.showConfirmDialog(gui, message,
                "Mod Specifications", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            applyMod(car, type, modName.getText(), modCost.getText(), modWeight.getText(), modSpec.getText());
        }
    }

    // REQUIRES: type is one of "Engine Mod", "Tire Mod", "Body Panel Mod"
    // MODIFIES: c, gui
    // EFFECTS: instantiates the modification and adds it to the car; updates the GUI console
    private void applyMod(Car c, String type, String name, String cost, String weight, String spec) {
        try {
            int icost = Integer.parseInt(cost);
            int iweight = Integer.parseInt(weight);
            if (type.equals("Engine Mod")) {
                c.addMod(new EngineMod(name, icost, iweight, Integer.parseInt(spec)));
            } else if (type.equals("Tire Mod")) {
                c.addMod(new TireMod(name, icost, iweight, Double.parseDouble(spec)));
            } else {
                c.addMod(new BodyPanelMod(name, icost, iweight, Double.parseDouble(spec)));
            }
            gui.getDetailsArea().setText("");
            gui.printToConsole("Successfully installed " + name + "!");
            gui.getVisualPanel().repaint();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(gui, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}