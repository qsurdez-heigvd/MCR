package ch.heig.mcr.labo;

import ch.heig.mcr.labo.displayer.DisplayWindow;
import ch.heig.mcr.labo.factories.AbstractFactory;
import ch.heig.mcr.labo.factories.ConcreteFactoryEmpty;
import ch.heig.mcr.labo.factories.ConcreteFactoryFilled;
import ch.heig.mcr.labo.shape.Bounceable;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

/**
 * Main class for the bouncers application. It creates a window and manages the bouncers.
 * It will create the different instances of the singletons and manages the loop for the animation
 *
 * @author REDACTED
 * @author Quentin Surdez
 * @version 1.0
 */
public class Bouncers {

    private LinkedList<Bounceable> bouncers = new LinkedList<>();
    private Random random = new Random();

    /**
     * Constructor of the Bouncers class. It will add a key listener to the window.
     */
    public Bouncers() {
        DisplayWindow.getInstance().addKeyListener(new Key());
    }

    /**
     * Method to run the application. It will set the title of the window and start the timer for the animation.
     */
    public void run() {
        DisplayWindow.getInstance().setTitle("Bouncers");
        Timer timer = new Timer(10, e -> {
            for (Bounceable bouncer : bouncers) {
                bouncer.draw();
                bouncer.move();
            }
            DisplayWindow.getInstance().repaint();
        });
        timer.start();
    }


    /**
     * Main method of the application. It will create an instance of the Bouncers class and run the application.
     *
     * @param args arguments of the application
     */
    public static void main(String ... args) {
        SwingUtilities.invokeLater(new Bouncers()::run);
    }


    /**
     * Create 10 circles and 10 squares. The shapes will be full or empty, depending on the factory passed as parameter.
     *
     * @param af the factory used to create the shapes
     */
    private void createShapes(AbstractFactory af) {
        for (int i = 0; i < 10; i++) {
            bouncers.add(af.createCircle(random));
            bouncers.add(af.createSquare(random));
        }
    }

    /**
     * Key listener for the application. It will listen to the key events and act accordingly.
     */
    private class Key extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_E) {
                // effacer l'affichage
                bouncers.clear();
            } else if (keyCode == KeyEvent.VK_B) {
                // générer 10 cercles et carrés avec bordure
                Bouncers.this.createShapes(ConcreteFactoryEmpty.getInstance());
            } else if (keyCode == KeyEvent.VK_F) {
                // générer 10 cercles et carrés pleins
                Bouncers.this.createShapes(ConcreteFactoryFilled.getInstance());
            } else if (keyCode == KeyEvent.VK_Q) {
                // quitter l'application
                System.exit(0);
            }
        }
    }
}
