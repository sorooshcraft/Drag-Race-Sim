package ui;

import model.*;
import ui.actions.*;
import javax.swing.*;
import java.awt.*;

public class CarGameGUI extends JFrame {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 700;

    private Garage garage;
    private DefaultListModel<String> listModel;
    private JList<String> carList;
    private JTextArea detailsArea;

    private JButton btnCreate;
    private JButton btnView;

    public CarGameGUI() {
        super("CPSC 210: Drag Strip Simulator");
        garage = new Garage();
        listModel = new DefaultListModel<>();

        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addListPanel();
        addCenterPanel();
        addButtonPanel();

        centreOnScreen();
        setVisible(true);
    }

    private void addListPanel() {
        carList = new JList<>(listModel);
        carList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(carList);
        listScrollPane.setPreferredSize(new Dimension(220, 0));
        listScrollPane.setBorder(BorderFactory.createTitledBorder("My Garage"));
        add(listScrollPane, BorderLayout.WEST);
    }

    private void addCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout());
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane detailsScroll = new JScrollPane(detailsArea);
        detailsScroll.setBorder(BorderFactory.createTitledBorder("Console Output"));
        centerPanel.add(detailsScroll, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnCreate = new JButton(new CreateCarAction(this));
        btnView = new JButton(new ViewDetailsAction(this));
        buttonPanel.add(btnCreate);
        buttonPanel.add(btnView);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    public void refreshList() {
        listModel.clear();
        for (int i = 0; i < garage.getCarCount(); i++) {
            listModel.addElement(garage.getCar(i).getName());
        }
    }

    public void printToConsole(String text) {
        detailsArea.setText(detailsArea.getText() + text + "\n");
        detailsArea.setCaretPosition(detailsArea.getDocument().getLength());
    }

    // Getters needed by actions
    public Garage getGarage() {
        return garage;
    }

    public JList<String> getCarList() {
        return carList;
    }

    public JTextArea getDetailsArea() {
        return detailsArea;
    }
}