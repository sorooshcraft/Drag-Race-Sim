package ui.actions;

import model.*;
import ui.CarGameGUI;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class CreateCarAction extends AbstractAction {
    private final CarGameGUI gui;
    private final JTextField carNameField = new JTextField();
    private final JTextField chassisName = new JTextField();
    private final JTextField chassisWeight = new JTextField();
    private final JTextField chassisDrag = new JTextField();
    private final JTextField chassisTire = new JTextField();
    private final JTextField engineName = new JTextField();
    private final JTextField engineHp = new JTextField();
    private final JTextField engineWeight = new JTextField();
    private final JTextField engineRedline = new JTextField();
    private final JTextField engineCylinders = new JTextField();
    private final JTextField transName = new JTextField();
    private final JTextField transWeight = new JTextField();
    private final JTextField transGears = new JTextField();
    private final JTextField transShift = new JTextField();
    private final JTextField transEff = new JTextField();

    public CreateCarAction(CarGameGUI gui) {
        super("Create Custom Car");
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object[] message = {
                "Car Name:", carNameField, "--- Chassis ---", "Name:", chassisName,
                "Weight (lbs):", chassisWeight, "Drag Coeff:", chassisDrag,
                "Max Tire Width (mm):", chassisTire, "--- Engine ---", "Name:", engineName,
                "Base HP:", engineHp, "Weight (lbs):", engineWeight, "Redline:", engineRedline,
                "Cylinders:", engineCylinders, "--- Transmission ---", "Name:", transName,
                "Weight (lbs):", transWeight, "Gear Count:", transGears,
                "Shift Time (ms):", transShift, "Efficiency (0.0-1.0):", transEff
        };

        int option = JOptionPane.showConfirmDialog(gui, message,
                "New Car Wizard", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            handleCarCreation();
        }
    }

    private void handleCarCreation() {
        try {
            Chassis c = new Chassis(chassisName.getText(), Integer.parseInt(chassisWeight.getText()),
                    Double.parseDouble(chassisDrag.getText()), Integer.parseInt(chassisTire.getText()));
            Engine e = new Engine(engineName.getText(), Integer.parseInt(engineHp.getText()),
                    Integer.parseInt(engineWeight.getText()), Integer.parseInt(engineRedline.getText()),
                    Integer.parseInt(engineCylinders.getText()));
            Transmission t = new Transmission(transName.getText(), Integer.parseInt(transWeight.getText()),
                    Integer.parseInt(transGears.getText()), Integer.parseInt(transShift.getText()),
                    Double.parseDouble(transEff.getText()));

            gui.getGarage().addCar(new Car(carNameField.getText(), c, e, t));
            gui.refreshList();
            gui.getDetailsArea().setText("");
            gui.printToConsole("Car '" + carNameField.getText() + "' created successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(gui,
                    "Invalid input. Check numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}