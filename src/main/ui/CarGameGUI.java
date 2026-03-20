package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.actions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Vector;

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
    private VisualPanel visualPanel;

    private JButton btnCreate;
    private JButton btnModify;
    private JButton btnInstant;
    private JButton btnSimulate;
    private JButton btnView;
    private JButton btnEndRace;
    private JMenu fileMenu;
    private JMenu actionMenu;

    public CarGameGUI() {
        super("Drag Strip Simulator");
        initializeFields();
        initializeGraphics();
        promptLoadOnStartup();
    }

    private void initializeFields() {
        garage = new Garage();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        listModel = new DefaultListModel<>();
    }

    private void initializeGraphics() {
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
        actionMenu.add(new JMenuItem(new InstantRaceAction(this)));
        actionMenu.add(new JMenuItem(new SimulateRaceAction(this)));
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

        visualPanel = new VisualPanel();
        visualPanel.setPreferredSize(new Dimension(WIDTH, 250));
        centerPanel.add(visualPanel, BorderLayout.NORTH);

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
        btnInstant = new JButton(new InstantRaceAction(this));
        btnSimulate = new JButton(new SimulateRaceAction(this));
        btnView = new JButton(new ViewDetailsAction(this));
        btnEndRace = new JButton(new EndRaceAction(this));

        btnEndRace.setEnabled(false);

        buttonPanel.add(btnCreate);
        buttonPanel.add(btnModify);
        buttonPanel.add(btnInstant);
        buttonPanel.add(btnSimulate);
        buttonPanel.add(btnView);
        buttonPanel.add(btnEndRace);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void toggleUIState(boolean isRacing) {
        btnCreate.setEnabled(!isRacing);
        btnModify.setEnabled(!isRacing);
        btnInstant.setEnabled(!isRacing);
        btnSimulate.setEnabled(!isRacing);
        btnView.setEnabled(!isRacing);
        carList.setEnabled(!isRacing);
        fileMenu.setEnabled(!isRacing);
        actionMenu.setEnabled(!isRacing);
        btnEndRace.setEnabled(isRacing);
    }

    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    private void promptLoadOnStartup() {
        int response = JOptionPane.showConfirmDialog(this,
                "Do you want to load your garage from file?",
                "Load Garage", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            new LoadAction(this).actionPerformed(null);
        }
    }

    private void setupWindowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(CarGameGUI.this,
                        "Do you want to save your garage before quitting?",
                        "Save Garage", JOptionPane.YES_NO_OPTION);
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

    // Helper methods for race actions
    public String[] getAvailableImages() {
        File dir = new File("./data/");
        Vector<String> options = new Vector<>();
        options.add("None (Fallback)");
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File f : files) {
                    addIfImageFile(f, options);
                }
            }
        }
        return options.toArray(new String[0]);
    }

    private void addIfImageFile(File f, Vector<String> options) {
        String name = f.getName().toLowerCase();
        boolean isImage = name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg");
        boolean isNotSplash = !name.startsWith("splash");
        if (isImage && isNotSplash) {
            options.add(f.getName());
        }
    }

    private void printPreRaceText() {
        printToConsole("Burnout...");
        printToConsole("Staging...");
        printToConsole("Ready...");
        printToConsole("Set...");
        printToConsole("GO!");
    }

    public void handleRaceSetup(boolean isSimulated) {
        if (garage.getCarCount() == 0) {
            JOptionPane.showMessageDialog(this, "Garage is empty. Build a car first!");
            return;
        } else if (garage.getCarCount() == 1) {
            executeSoloRace(garage.getCar(0), isSimulated);
            return;
        }

        String[] options = { "Solo Run", "Head-to-Head Race" };
        int choice = JOptionPane.showOptionDialog(this, "Choose Race Type", "Race",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            int index = carList.getSelectedIndex();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Select a car!");
            } else {
                executeSoloRace(garage.getCar(index), isSimulated);
            }
        } else if (choice == 1) {
            executeHeadToHead(isSimulated);
        }
    }

    private void executeSoloRace(Car c, boolean isSimulated) {
        String img = "None (Fallback)";
        if (isSimulated) {
            String[] imgs = getAvailableImages();
            img = (String) JOptionPane.showInputDialog(this, "Select Image for " + c.getName(),
                    "Car Image", JOptionPane.QUESTION_MESSAGE, null, imgs, imgs[0]);
            if (img == null) {
                return;
            }
        }

        detailsArea.setText("");
        double time = c.calculateQuarterMileTime();
        printPreRaceText();

        if (isSimulated) {
            toggleUIState(true);
            printToConsole("--- SIMULATING " + c.getName() + " ---");
            visualPanel.animateRace(c, img, time, null, null, 0, () -> {
                printToConsole(String.format("1/4 Mile Time: %.3f seconds", time));
                toggleUIState(false);
            });
        } else {
            printToConsole("--- INSTANT RACE: " + c.getName() + " ---");
            printToConsole(String.format("1/4 Mile Time: %.3f seconds", time));
        }
    }

    private void executeHeadToHead(boolean isSimulated) {
        Vector<String> names = new Vector<>();
        for (Car c : garage.getCars()) {
            names.add(c.getName());
        }

        JComboBox<String> l1 = new JComboBox<>(names);
        JComboBox<String> l2 = new JComboBox<>(names);
        JComboBox<String> img1 = new JComboBox<>(getAvailableImages());
        JComboBox<String> img2 = new JComboBox<>(getAvailableImages());

        Object[] msg = isSimulated
                ? new Object[] { "Select Lane 1:", l1, "Image 1:", img1, "Select Lane 2:", l2, "Image 2:", img2 }
                : new Object[] { "Select Lane 1:", l1, "Select Lane 2:", l2 };

        int res = JOptionPane.showConfirmDialog(this, msg, "Race Setup", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            String i1 = isSimulated ? (String) img1.getSelectedItem() : "None (Fallback)";
            String i2 = isSimulated ? (String) img2.getSelectedItem() : "None (Fallback)";
            setupHeadToHeadRace(l1.getSelectedIndex(), i1, l2.getSelectedIndex(), i2, isSimulated);
        }
    }

    private void setupHeadToHeadRace(int idx1, String img1, int idx2, String img2, boolean isSimulated) {
        Car c1 = garage.getCar(idx1);
        Car c2 = garage.getCar(idx2);
        double t1 = c1.calculateQuarterMileTime();
        double t2 = c2.calculateQuarterMileTime();

        detailsArea.setText("");
        printPreRaceText();

        if (isSimulated) {
            toggleUIState(true);
            printToConsole("--- SIMULATING " + c1.getName() + " VS " + c2.getName() + " ---");
            visualPanel.animateRace(c1, img1, t1, c2, img2, t2, () -> finalizeRace(c1, t1, c2, t2));
        } else {
            printRaceResults(c1, t1, c2, t2);
        }
    }

    private void finalizeRace(Car c1, double t1, Car c2, double t2) {
        printRaceResults(c1, t1, c2, t2);
        toggleUIState(false);
    }

    private void printRaceResults(Car c1, double t1, Car c2, double t2) {
        printToConsole("\n--- RACE RESULTS ---");
        printToConsole(String.format("%s: %.3f s", c1.getName(), t1));
        printToConsole(String.format("%s: %.3f s", c2.getName(), t2));

        if (t1 < t2) {
            printToConsole("WINNER: " + c1.getName().toUpperCase() + "!!!");
        } else if (t2 < t1) {
            printToConsole("WINNER: " + c2.getName().toUpperCase() + "!!!");
        } else {
            printToConsole("It's a DEAD TIE!");
        }
    }

    // Getters for actions
    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public JList<String> getCarList() {
        return carList;
    }

    public JTextArea getDetailsArea() {
        return detailsArea;
    }

    public VisualPanel getVisualPanel() {
        return visualPanel;
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

    public JButton getBtnEndRace() {
        return btnEndRace;
    }
}