package ch.heigvd.mcr.project.gui;

import ch.heigvd.mcr.project.Simulation;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the control panel of the simulation. It contains a drop-down list to select the mediator and a
 * button to switch the colorblind mode on and off.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class ControlPanel extends JPanel {
    private final JButton colorBlindModeButton;

    public ControlPanel() {
        this.setLayout(new GridLayout(1, 4)); // 1 row, 4 columns
        this.setBackground(new Color(146, 129, 122));

        // Drop-down list for mediator selection
        String[] mediators = {"Balanced", "Aggressive", "Explore"};
        JComboBox<String> mediatorList = new JComboBox<>(mediators);
        mediatorList.setFocusable(false);
        mediatorList.setBackground(new Color(54, 36, 23));
        mediatorList.setForeground(Color.WHITE);
        ((JLabel) mediatorList.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        mediatorList.addActionListener(e -> {
            String selectedMediator = (String) mediatorList.getSelectedItem();
            switch (selectedMediator) {
                case "Balanced":
                    Simulation.getInstance().setMediatorToBalanced();
                    break;
                case "Aggressive":
                    Simulation.getInstance().setMediatorToAggressive();
                    break;
                case "Explore":
                    Simulation.getInstance().setMediatorToExplore();
                    break;
                case null:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + selectedMediator);
            }
        });
        this.add(mediatorList);

        // Colorblind mode button
        colorBlindModeButton = new JButton("Colorblind Mode: " + (Simulation.getInstance().isColorblindMode() ? "On" : "Off"));
        colorBlindModeButton.setFocusable(false);
        colorBlindModeButton.addActionListener(e -> {
            Simulation.getInstance().switchColorBlindMode();
            colorBlindModeButton.setText("Colorblind Mode: " + (Simulation.getInstance().isColorblindMode() ? "On" : "Off"));
        });
        colorBlindModeButton.setBackground(Color.WHITE);
        colorBlindModeButton.setForeground(Color.BLACK);
        this.add(colorBlindModeButton);
    }
}