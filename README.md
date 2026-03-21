# Car Configurator & Drag Strip Simulator

### A Term Project Proposal

This application is a **non-interactive drag race simulator**. It allows users to build virtual cars from a library of components and then test their creations on a simulated drag strip.

The application has two primary functions:
*   **Configuration:** Users can create and save multiple car configurations in a virtual "Garage". Building a car involves selecting different parts, such as engines parts, tires, and transmissions, each with unique performance characteristics that influence the car's final race times.
*   **Simulation:** Users can select one or two cars from their Garage to race on a simulated quarter-mile drag strip. The race runs automatically based on the cars' calculated physics. Upon completion, the application provides a detailed time slip with the final time and speed.

This tool is designed for car enthusiasts and anyone interested in the technical side of vehicle tuning. It provides a lightweight platform to explore how different component combinations affect straight-line performance without the complexities of a real-time driving game.

This project interests me because it combines the two things that interest me the most; cars and programming. Additonally, I have always wanted to make some sort of simulation, granted this will be a very low-fideltiy simulation.

### User Stories

*   As a user, I want to be able to create a new car, give it a name, and add it to my garage.
*   As a user, I want to be able to view a list of all the cars currently stored in my garage.
*   As a user, I want to be able to select a specific car from my garage and modify its components, such as changing its engine parts or tires.
*   As a user, I want to be able to remove a car from my garage.
*   As a user, I want to select one or two cars from my garage and simulate a drag race to see the final race times and results.
*   As a user, I want to be able to save my garage file.
*   As a user, I want to see a visual animation of the race occurring on the screen.
*   As a user, when I select the quit option from the application menu, I want to be reminded to save my garage to file and have the option to do so or not.
*   As a user, when I start the application, I want to be given the option to load my garage from file.

# Instructions for End User

- You can view the panel that displays the Cars (Xs) that have already been added to the Garage (Y) by looking at the "My Garage" list on the left side of the screen.
- You can generate the first required action related to the user story "adding multiple Cars to a Garage" by clicking the button labeled "Create Custom Car" or selecting it from the "Actions" menu.
- You can generate the second required action related to the user story "adding multiple Cars to a Garage" by selecting a car from the list and clicking the button labeled "Remove Car" to scrap it from the garage.
- You can locate my visual component by looking at the center panel of the application; it displays a splash screen image on startup and shows a full race animation with a countdown and moving car graphics when you click the "Simulate Race" button.
- You can save the state of my application by going to the "File" menu and selecting "Save Garage," or by clicking "Yes" when the application prompts you to save before quitting.
- You can reload the state of my application by going to the "File" menu and selecting "Load Garage," or by clicking "Yes" when the application asks if you want to load your data during startup.