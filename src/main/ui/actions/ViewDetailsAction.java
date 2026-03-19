package ui.actions;

import model.*;
import ui.CarGameGUI;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class ViewDetailsAction extends AbstractAction {
    private final CarGameGUI gui;

    public ViewDetailsAction(CarGameGUI gui) {
        super("View Details");
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        int index = gui.getCarList().getSelectedIndex();
        if (index >= 0) {
            displayDetails(gui.getGarage().getCar(index));
        } else {
            JOptionPane.showMessageDialog(gui, "Please select a car!");
        }
    }

    private void displayDetails(Car c) {
        gui.getDetailsArea().setText("");
        gui.printToConsole("=========================================");
        gui.printToConsole("   SPEC SHEET: " + c.getName().toUpperCase());
        gui.printToConsole("=========================================");
        printBaseComponents(c);
        printModifications(c);
        printSummary(c);
        gui.getDetailsArea().setCaretPosition(0);
    }

    private void printBaseComponents(Car c) {
        gui.printToConsole("--- BASE COMPONENTS ---");
        Chassis ch = c.getChassis();
        gui.printToConsole(String.format(" [CHASSIS] %s\n   Wt: %d lbs | Drag: %.2f | Tire: %d mm",
                ch.getName(), ch.getWeight(), ch.getDragCoefficient(), ch.getMaxTireWidth()));

        Engine e = c.getEngine();
        gui.printToConsole(String.format(" [ENGINE]  %s\n   Base HP: %d | Wt: %d lbs | RPM: %d",
                e.getName(), e.getBaseHorsepower(), e.getWeight(), e.getRedline()));

        Transmission t = c.getTransmission();
        gui.printToConsole(String.format(" [TRANS]   %s\n   Wt: %d lbs | Gears: %d | Eff: %.2f",
                t.getName(), t.getWeight(), t.getGearCount(), t.getEfficiency()));
    }

    private void printModifications(Car c) {
        gui.printToConsole("-----------------------------------------");
        gui.printToConsole("--- INSTALLED MODIFICATIONS ---");
        if (c.getMods().isEmpty()) {
            gui.printToConsole(" (Stock - No mods installed)");
        } else {
            for (Modification m : c.getMods()) {
                gui.printToConsole(String.format(" + %-15s | Cost: $%4d", m.getName(), m.getCost()));
            }
        }
    }

    private void printSummary(Car c) {
        int totalModCost = 0;
        for (Modification m : c.getMods()) {
            totalModCost += m.getCost();
        }

        gui.printToConsole("=========================================");
        gui.printToConsole("--- TOTAL AGGREGATE SUMMARY ---");
        gui.printToConsole(" [TOTAL HP]       " + c.calculateHorsepower());
        gui.printToConsole(" [TOTAL WEIGHT]   " + c.calculateWeight() + " lbs");
        gui.printToConsole(" [TOTAL GRIP]     " + String.format("%.2f", c.calculateTotalGrip()));
        gui.printToConsole(" [TOTAL MOD COST] $" + totalModCost);
        gui.printToConsole("=========================================");
    }
}