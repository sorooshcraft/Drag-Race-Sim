package ui;

import model.*;
import java.util.Scanner;

// Car Configurator & Drag Strip Simulator Application
public class CarGameApp {
    private Garage garage;
    private Scanner input;

    // EFFECTS: runs the game application
    public CarGameApp() {
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGame() {
        boolean keepRunning = true;
        String command = null;

        init();

        while (keepRunning) {
            displayMenu();
            command = input.next();
            command = command.trim().toLowerCase();

            if (command.equals("q")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye! Thanks for racing.");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            doCreateCar();
        } else if (command.equals("v")) {
            doViewGarage();
        } else if (command.equals("m")) {
            doModifyCar();
        } else if (command.equals("r")) {
            doRaceCar();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the garage and scanner
    private void init() {
        garage = new Garage();
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n--- DRAG STRIP SIMULATOR ---");
        System.out.println("Select from:");
        System.out.println("\tc -> create a new car");
        System.out.println("\tv -> view garage");
        System.out.println("\tm -> modify a car");
        System.out.println("\tr -> race a car (Simulation)");
        System.out.println("\tq -> quit");
        System.out.print("Enter command: ");
    }

    // MODIFIES: this
    // EFFECTS: creates a new car with default "Stock" parts and adds it to garage
    private void doCreateCar() {
        System.out.print("Enter a name for your new car: ");
        String name = input.next();

        Chassis chassis = new Chassis("Stock Chassis", 3000, 0.35, 255);
        Engine engine = new Engine("V6 Stock", 250, 400, 6500, 6);
        Transmission trans = new Transmission("5-Speed Auto", 150, 5, 300, 0.85);

        Car newCar = new Car(name, chassis, engine, trans);
        garage.addCar(newCar);

        System.out.println("Car '" + name + "' created successfully with stock parts!");
    }

    // EFFECTS: prints a list of all cars in the garage with their index
    private void doViewGarage() {
        System.out.println("\n--- MY GARAGE ---");
        if (garage.getCarCount() == 0) {
            System.out.println("Your garage is empty.");
        } else {
            for (int i = 0; i < garage.getCarCount(); i++) {
                Car c = garage.getCar(i);
                System.out.println(i + " -> " + c.getName() 
                        + " [HP: " + c.calculateHorsepower() 
                        + ", Weight: " + c.calculateWeight() + " lbs]");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: handles the interaction for modifying a car
    private void doModifyCar() {
        Car c = selectCar();
        if (c == null) {
            return;
        }

        System.out.println("Modifying " + c.getName());
        System.out.println("1 -> Add Turbo (+150 HP, +40lbs)");
        System.out.println("2 -> Add Drag Slicks (Better Grip, +0lbs)");
        System.out.print("Choice: ");
        
        int choice = input.nextInt();
        processModChoice(c, choice);
    }

    // MODIFIES: c
    // EFFECTS: applies modification based on user choice
    private void processModChoice(Car c, int choice) {
        if (choice == 1) {
            c.addMod(new EngineMod("Turbo Kit", 2000, 40, 150));
            System.out.println("Turbo added!");
        } else if (choice == 2) {
            c.addMod(new TireMod("Mickey Thompson Slicks", 600, 0, 15));
            System.out.println("Slicks added!");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // EFFECTS: handles the interaction for racing a car
    private void doRaceCar() {
        Car c = selectCar();
        if (c == null) {
            return;
        }

        System.out.println("\n--- RACING " + c.getName() + " ---");
        System.out.println("Warming up tires...\nStaging...\nGO!");
        
        double time = c.calculateQuarterMileTime();
        printRaceResults(time);
    }

    // EFFECTS: prints the result of the race based on the time
    private void printRaceResults(double time) {
        System.out.printf("1/4 Mile Time: %.3f seconds\n", time);
        
        if (time < 11.0) {
            System.out.println("Wow! That's a fast car!");
        } else if (time > 15.0) {
            System.out.println("A bit slow. Maybe add a turbo?");
        } else {
            System.out.println("Nice run.");
        }
    }

    // EFFECTS: Helper method to prompt user to select a valid car from the garage.
    //          Returns the selected Car, or null if invalid/empty.
    private Car selectCar() {
        if (garage.getCarCount() == 0) {
            System.out.println("No cars available.");
            return null;
        }

        doViewGarage();
        System.out.print("Enter ID of car: ");
        int id = input.nextInt();

        if (id >= 0 && id < garage.getCarCount()) {
            return garage.getCar(id);
        } else {
            System.out.println("Invalid car ID.");
            return null;
        }
    }
}