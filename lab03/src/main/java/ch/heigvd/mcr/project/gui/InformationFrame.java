package ch.heigvd.mcr.project.gui;

import ch.heigvd.mcr.project.Simulation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * A frame that displays the number of scouts, soldiers, gatherers and predators currently in the simulation
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class InformationFrame extends JFrame {
    private final JLabel scoutsLabel;
    private final JLabel soldiersLabel;
    private final JLabel gatherersLabel;
    private final JLabel predatorsLabel;

    /**
     * Constructor
     */
    public InformationFrame() {
        setTitle("Simulation Information");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        try{
            Image iconImage = ImageIO.read(Objects.requireNonNull(SimulationDisplayer.class.getResource("/assets/icon.png")));
            setIconImage(iconImage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.scoutsLabel = createAndSetupLabel();
        this.soldiersLabel = createAndSetupLabel();
        this.gatherersLabel = createAndSetupLabel();
        this.predatorsLabel = createAndSetupLabel();

        JPanel panel = new JPanel();
        panel.setBackground(new Color(54, 36, 23));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(scoutsLabel);
        panel.add(soldiersLabel);
        panel.add(gatherersLabel);
        panel.add(predatorsLabel);

        add(panel);
        new Timer(1500, e -> updateLabels()).start();
    }

    /**
     * Create and set up a label
     * @return the label
     */
    private JLabel createAndSetupLabel() {
        JLabel label = new JLabel();
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    /**
     * Update the labels with the current number of scouts, soldiers, gatherers and predators
     */
    private void updateLabels() {
        this.scoutsLabel.setText("Number of scouts: " + Simulation.getInstance().getNumberOfScouts());
        this.soldiersLabel.setText("Number of soldiers: " + Simulation.getInstance().getNumberOfSoldiers());
        this.gatherersLabel.setText("Number of gatherers: " + Simulation.getInstance().getNumberOfGatherers());
        this.predatorsLabel.setText("Number of predators: " + Simulation.getInstance().getNumberOfPredators());
        pack();
    }

    /**
     * Display the frame
     */
    public void display() {
        updateLabels();
        pack();
        setVisible(true);
    }
}