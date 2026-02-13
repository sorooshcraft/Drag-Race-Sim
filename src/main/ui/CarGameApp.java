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
    // EFFECTS: initializes the garage and scanner with correct delimiter
    private void init() {
        garage = new Garage();
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n--- DRAG STRIP SIMULATOR ---");
        System.out.println("Select from:");
        System.out.println("\tc -> create a custom car");
        System.out.println("\tv -> view garage");
        System.out.println("\td -> view car details");
        System.out.println("\tm -> modify a car");
        System.out.println("\tr -> race a car");
        System.out.println("\tq -> quit");
        System.out.print("Enter command: ");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            doCreateCar();
        } else if (command.equals("v")) {
            doViewGarage();
        } else if (command.equals("d")) {
            doViewCarDetails();
        } else if (command.equals("m")) {
            doModifyCar();
        } else if (command.equals("r")) {
            doRaceCar();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new car by prompting user for all components
    private void doCreateCar() {
        System.out.println("\n--- NEW CAR WIZARD ---");
        System.out.print("Enter Car Name: ");
        String name = input.next();

        Chassis c = createCustomChassis();
        Engine e = createCustomEngine();
        Transmission t = createCustomTransmission();

        garage.addCar(new Car(name, c, e, t));
        System.out.println("Car '" + name + "' created successfully!");
    }

    // EFFECTS: prompts user for Chassis specs and returns object
    private Chassis createCustomChassis() {
        System.out.println("- Chassis Specs -");
        System.out.print("Name: "); 
        String name = input.next();
        System.out.print("Weight (lbs): "); 
        int w = input.nextInt();
        System.out.print("Drag Coeff (e.g. 0.35): "); 
        double drag = input.nextDouble();
        System.out.print("Max Tire Width (mm): "); 
        int tire = input.nextInt();
        return new Chassis(name, w, drag, tire);
    }

    // EFFECTS: prompts user for Engine specs and returns object
    private Engine createCustomEngine() {
        System.out.println("- Engine Specs -");
        System.out.print("Name: "); 
        String name = input.next();
        System.out.print("Base HP: "); 
        int hp = input.nextInt();
        System.out.print("Weight (lbs): "); 
        int w = input.nextInt();
        System.out.print("Redline: "); 
        int red = input.nextInt();
        System.out.print("Cylinders: "); 
        int cyl = input.nextInt();
        return new Engine(name, hp, w, red, cyl);
    }

    // EFFECTS: prompts user for Transmission specs and returns object
    private Transmission createCustomTransmission() {
        System.out.println("- Transmission Specs -");
        System.out.print("Name: "); 
        String name = input.next();
        System.out.print("Weight (lbs): "); 
        int w = input.nextInt();
        System.out.print("Gear Count: "); 
        int gears = input.nextInt();
        System.out.print("Shift Time (ms): "); 
        int shift = input.nextInt();
        System.out.print("Efficiency (0.0-1.0): "); 
        double eff = input.nextDouble();
        return new Transmission(name, w, gears, shift, eff);
    }

    // EFFECTS: prints a list of all cars in the garage
    private void doViewGarage() {
        System.out.println("\n--- MY GARAGE ---");
        if (garage.getCarCount() == 0) {
            System.out.println("Your garage is empty.");
        } else {
            for (int i = 0; i < garage.getCarCount(); i++) {
                Car c = garage.getCar(i);
                System.out.println(i + " -> " + c.getName() 
                        + " [HP: " + c.calculateHorsepower() 
                        + ", Weight: " + c.calculateWeight() + "]");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: selects a car and adds a custom modification
    private void doModifyCar() {
        Car c = selectCar();
        if (c == null) {
            return;
        }

        System.out.println("Modifying " + c.getName());
        System.out.println("1 -> Custom Engine Mod (Turbo, Intake, etc)");
        System.out.println("2 -> Custom Tire Mod (Slicks, Radials, etc)");
        System.out.println("3 -> Custom Body Panel Mod (Carbon hood, Spoiler, etc)");
        System.out.print("Choice: ");
        int choice = input.nextInt();

        if (choice == 1) {
            addCustomEngineMod(c);
        } else if (choice == 2) {
            addCustomTireMod(c);
        } else if (choice == 3) {
            addCustomBodyPanelMod(c);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // MODIFIES: c
    // EFFECTS: prompts user for engine mod specs and adds to car
    private void addCustomEngineMod(Car c) {
        System.out.print("Mod Name: "); 
        String name = input.next();
        System.out.print("Cost: "); 
        int cost = input.nextInt();
        System.out.print("Weight Added: "); 
        int w = input.nextInt();
        System.out.print("HP Gain: "); 
        int hp = input.nextInt();
        
        c.addMod(new EngineMod(name, cost, w, hp));
        System.out.println("Engine mod added!");
    }

    // MODIFIES: c
    // EFFECTS: prompts user for tire mod specs and adds to car
    private void addCustomTireMod(Car c) {
        System.out.print("Tire Name: "); 
        String name = input.next();
        System.out.print("Cost: "); 
        int cost = input.nextInt();
        System.out.print("Weight Change: "); 
        int w = input.nextInt();
        System.out.print("Grip Multiplier (e.g. 1.5): "); 
        double grip = input.nextDouble();

        c.addMod(new TireMod(name, cost, w, grip));
        System.out.println("Tires installed!");
    }


    // MODIFIES: c
    // EFFECTS: prompts user for tire mod specs and adds to car
    private void addCustomBodyPanelMod(Car c) {
        System.out.print("Body Part Name: "); 
        String name = input.next();
        System.out.print("Cost: "); 
        int cost = input.nextInt();
        System.out.print("Weight Change: "); 
        int w = input.nextInt();
        System.out.print("Grip Multiplier (e.g. 1.5): "); 
        double aeroGrip = input.nextDouble();

        c.addMod(new BodyPanelMod(name, cost, w, aeroGrip));
        System.out.println("Body part installed!");
    }

// EFFECTS: determines if race is solo or head-to-head
    private void doRaceCar() {
        if (garage.getCarCount() < 2) {
            raceSingleCar();
            return;
        }

        System.out.println("1 -> Solo Run");
        System.out.println("2 -> Head-to-Head Race");
        System.out.print("Choice: ");
        int choice = input.nextInt();

        if (choice == 2) {
            raceTwoCars();
        } else {
            raceSingleCar();
        }
    }

    // EFFECTS: runs a solo time trial simulation
    private void raceSingleCar() {
        Car c = selectCar();
        if (c == null) {
            return;
        }

        System.out.println("\n--- RACING " + c.getName() + " ---");
        System.out.println("Warming up tires... Staging... GO!");
        
        double time = c.calculateQuarterMileTime();
        
        System.out.printf("1/4 Mile Time: %.3f seconds\n", time);
    }

    // EFFECTS: selects two cars and runs a race
    private void raceTwoCars() {
        System.out.println("\n--- SELECT LANE 1 ---");
        Car c1 = selectCar();
        if (c1 == null) {
            return;
        }

        System.out.println("\n--- SELECT LANE 2 ---");
        Car c2 = selectCar();
        if (c2 == null) {
            return;
        }

        printHeadToHeadResults(c1, c2);
    }

    // EFFECTS: calculates times and prints the winner
    private void printHeadToHeadResults(Car c1, Car c2) {
        double t1 = c1.calculateQuarterMileTime();
        double t2 = c2.calculateQuarterMileTime();

        System.out.println("\n--- RACE RESULTS ---");
        System.out.printf("%s: %.3f s\n", c1.getName(), t1);
        System.out.printf("%s: %.3f s\n", c2.getName(), t2);

        if (t1 < t2) {
            System.out.println("WINNER: " + c1.getName());
        } else if (t2 < t1) {
            System.out.println("WINNER: " + c2.getName());
        } else {
            System.out.println("It's a TIE!");
        }
    }

    // EFFECTS: Helper to select car. Returns car or null.
    private Car selectCar() {
        if (garage.getCarCount() == 0) {
            System.out.println("No cars available.");
            return null;
        }
        doViewGarage();
        System.out.print("\nEnter ID of car: ");
        int id = input.nextInt();

        if (id >= 0 && id < garage.getCarCount()) {
            return garage.getCar(id);
        } else {
            System.out.println("Invalid car ID.");
            return null;
        }
    }

    // EFFECTS: selects a car and prints full detailed specifications
    private void doViewCarDetails() {
        Car c = selectCar();
        if (c == null) {
            return;
        }

        System.out.println("\n==================================");
        System.out.println("   DETAILS: " + c.getName().toUpperCase());
        System.out.println("==================================");
        
        printComponentStats(c);
        printModsList(c);
    }

    // EFFECTS: prints the details of the base components
    private void printComponentStats(Car c) {
        Engine e = c.getEngine();
        Chassis ch = c.getChassis();
        Transmission t = c.getTransmission();

        System.out.println(" BASE COMPONENTS");
        System.out.println(" [ENGINE]  " + e.getName() 
                + " (" + e.getCylinders() + " cyl, " + e.getBaseHorsepower() + " base hp)");
        System.out.println(" [CHASSIS] " + ch.getName() 
                + " (Drag: " + ch.getDragCoefficient() + ")");
        System.out.println(" [TRANS]   " + t.getName() 
                + " (" + t.getGearCount() + " Speed)");
        System.out.println("----------------------------------");
    }

    // EFFECTS: prints the list of installed modifications
    private void printModsList(Car c) {
        System.out.println(" INSTALLED MODIFICATIONS");
        if (c.getMods().isEmpty()) {
            System.out.println(" (Stock - No mods installed)");
        } else {
            for (Modification m : c.getMods()) {
                System.out.println(" + " + m.getName() 
                        + " [Cost: " + m.getCost() + " | Wt: " + m.getWeightChange() + "]");
            }
        }
        System.out.println("==================================");
    }
}