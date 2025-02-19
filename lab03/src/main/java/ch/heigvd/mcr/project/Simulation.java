package ch.heigvd.mcr.project;

import ch.heigvd.mcr.project.gui.Displayer;
import ch.heigvd.mcr.project.gui.SimulationDisplayer;
import ch.heigvd.mcr.project.mediator.AggressiveMediator;
import ch.heigvd.mcr.project.mediator.ExploreMediator;
import ch.heigvd.mcr.project.mediator.BalancedMediator;
import ch.heigvd.mcr.project.mediator.Mediator;

import javax.swing.*;

/**
 * Class that represents the simulation of the game
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class Simulation {

    private boolean colorblindMode = true;
    private boolean done;
    private Mediator mediator;
    private int nbScouts;
    private int nbSoldiers;
    private int nbGatherer;

    /**
     * Constructor of the Simulation class
     */
    private Simulation(){
    }

    /**
     * Initialize the mediator
     */
    public void initMediator() {
        mediator = new BalancedMediator(nbSoldiers, nbGatherer, nbScouts);
    }

    /**
     * Check if the colorblind mode is activated
     * @return true if the colorblind mode is activated, false otherwise
     */
    public boolean isColorblindMode() {
        return colorblindMode;
    }

    /**
     * Switch the colorblind mode on and off
     */
    public void switchColorBlindMode(){
        colorblindMode = !colorblindMode;
    }

    /**
     * Set the number of scouts
     * @param nbScouts the number of scouts
     */
    public void setNbScouts(int nbScouts){
        this.nbScouts = nbScouts;
    }

    /**
     * Set the number of soldiers
     * @param nbSoldiers the number of soldiers
     */
    public void setNbSoldiers(int nbSoldiers){
        this.nbSoldiers = nbSoldiers;
    }

    /**
     * Set the number of gatherers
     * @param nbGatherer the number of gatherers
     */
    public void setNbGatherer(int nbGatherer){
        this.nbGatherer = nbGatherer;
    }

    /**
     * Set the done attribute to true
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * Get the number of scouts from the mediator.
     *
     * @return the number of scouts.
     */
    public int getNumberOfScouts() {
        return mediator.getNbScouts();
    }

    /**
     * Get the number of soldiers from the mediator.
     *
     * @return the number of soldiers.
     */
    public int getNumberOfSoldiers() {
        return mediator.getNbSoldiers();
    }

    /**
     * Get the number of gatherers from the mediator.
     *
     * @return the number of gatherers.
     */
    public int getNumberOfGatherers() {
        return mediator.getNbGatherers();
    }

    /**
     * Get the number of predators from the mediator.
     *
     * @return the number of predators.
     */
    public int getNumberOfPredators() {
        return mediator.getNbPredators();
    }

    /**
     * Run the simulation
     */
    public void run() {

        Timer timer = new Timer(10, e -> {
            if (done) {
                ((Timer)e.getSource()).stop();
            } else {
                mediator.nextTurn();
                mediator.display();
            }
        });
        timer.start();
    }

    /**
     * Method to set the mediator to aggressive
     */
    public void setMediatorToAggressive() {
        mediator = new AggressiveMediator(mediator);
    }

    /**
     * Method to set the mediator to bad weather
     */
    public void setMediatorToExplore() {
        mediator = new ExploreMediator(mediator);
    }

    /**
     * Method to set the mediator to balanced
     */
    public void setMediatorToBalanced() {
        mediator = new BalancedMediator(mediator);
    }

    /**
     * Get the unique instance of the Simulation type
     * @return the unique instance of the Simulation type
     */
    public static Simulation getInstance(){
        return Instance.instance;
    }

    /**
     * The inner class that holds the unique instance of the Simulation type
     */
    public static class Instance{
        static final Simulation instance = new Simulation();
    }

    /**
     * Main method of the program
     * @param args the arguments of the program
     */
    public static void main(String[] args) {
        // Initialize the simulation displayer
        Displayer displayer = SimulationDisplayer.getInstance();
        Simulation.getInstance().initMediator();
    }
}

