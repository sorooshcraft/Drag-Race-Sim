package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.actions.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CarGameGUI extends JFrame {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 700;
    private static final String JSON_STORE = "./data/garage.json";

    private Garage garage;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private DefaultListModel<String> listModel;
    private JList<String> carList;
    private JTextArea detailsArea;

    private JButton btnCreate;
    private JButton btnModify;
    private JButton btnView;

    private JMenu fileMenu;
    private JMenu actionMenu;

    public CarGameGUI() {
        super("Drag Strip Simulator");
        garage = new Garage();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        listModel = new DefaultListModel<>();

        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setupWindowListener();
        addMenu();
        addListPanel();
        addCenterPanel();
        addButtonPanel();

        centreOnScreen();
        setVisible(true);
        promptLoadOnStartup();
    }

    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem(new LoadAction(this)));
        fileMenu.add(new JMenuItem(new SaveAction(this)));
        menuBar.add(fileMenu);

        actionMenu = new JMenu("Actions");
        actionMenu.add(new JMenuItem(new CreateCarAction(this)));
        actionMenu.add(new JMenuItem(new ModifyCarAction(this)));
        actionMenu.add(new JMenuItem(new ViewDetailsAction(this)));
        menuBar.add(actionMenu);
        setJMenuBar(menuBar);
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
        btnModify = new JButton(new ModifyCarAction(this));
        btnView = new JButton(new ViewDetailsAction(this));

        buttonPanel.add(btnCreate);
        buttonPanel.add(btnModify);
        buttonPanel.add(btnView);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    private void promptLoadOnStartup() {
        int response = JOptionPane.showConfirmDialog(this, "Do you want to load your garage from file?", "Load Garage",
                JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            new LoadAction(this).actionPerformed(null);
        }
    }

    private void setupWindowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(CarGameGUI.this,
                        "Do you want to save your garage before quitting?", "Save Garage", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    new SaveAction(CarGameGUI.this).actionPerformed(null);
                }
                System.exit(0);
            }
        });
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

    // Getters
    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public JList<String> getCarList() {
        return carList;
    }

    public JTextArea getDetailsArea() {
        return detailsArea;
    }

    public JsonWriter getJsonWriter() {
        return jsonWriter;
    }

    public JsonReader getJsonReader() {
        return jsonReader;
    }

    public String getJsonStore() {
        return JSON_STORE;
    }

    // Stub for ModAction
    public Object getVisualPanel() {
        return new JPanel();
    }
}