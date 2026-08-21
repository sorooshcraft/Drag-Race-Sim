# Car Configurator & Drag Strip Simulator

A desktop Java application that allows users to build custom vehicles from a component library and simulate quarter-mile drag races using calculated vehicle physics.

## Features
* **Configuration:** Create, edit, and save multiple car configurations in a virtual garage. Selecting different engine parts, tires, and transmissions adjusts power-to-weight ratios and traction metrics that dictate track performance.
* **Simulation:** Select vehicles from the garage to race on a simulated quarter-mile strip. The engine calculates acceleration curves and produces a detailed time slip with elapsed times and trap speeds.

## Technical Implementation
* **Language & Architecture:** Built with Java using Object-Oriented Programming (OOP) design patterns to decouple the UI, physics simulation model, and persistence layer.
* **Graphical Interface:** Built with Java Swing for dynamic component selection and race visualizer rendering.
* **Data Persistence:** Implemented custom JSON serialization to save and load garage configurations between sessions.
* **Testing:** Developed automated unit test suites with JUnit to verify physics calculations and boundary conditions.
