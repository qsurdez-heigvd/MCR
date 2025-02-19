package ch.heigvd.mcr.project.gui;

import javax.swing.*;
import java.awt.*;

/**
 * A panel that allows the user to enter the number of gatherers, scouts and soldiers he wants in the simulation
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class SettingsSelectorPanel extends JPanel {
    private final JTextField gathererField;
    private final JTextField scoutField;
    private final JTextField soldierField;

    /*
     * Constructor
     */
    public SettingsSelectorPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(new Color(54, 36, 23));

        scoutField = new JTextField("3", 5);
        gathererField = new JTextField("10", 5);
        soldierField = new JTextField("10", 5);

        addLabelAndField("Scouts:", scoutField);
        addLabelAndField("Gatherers:", gathererField);
        addLabelAndField("Soldiers:", soldierField);

        gathererField.setFont(new Font("Arial", Font.BOLD, 20));
        scoutField.setFont(new Font("Arial", Font.BOLD, 20));
        soldierField.setFont(new Font("Arial", Font.BOLD, 20));

        setMaximumSize(getPreferredSize());
    }

    /**
     * Get the number of gatherers entered by the user and ensure it is a valid number
     * @return the number of gatherers
     */
    public int getGathererCount() {
        return gathererField.getText().isEmpty() ? 0 : Integer.parseInt(gathererField.getText());
    }

    /**
     * Get the number of scouts entered by the user and ensure it is a valid number
     * @return the number of scouts
     */
    public int getScoutCount() {
        return scoutField.getText().isEmpty() ? 0 : Integer.parseInt(scoutField.getText());
    }

    /**
     * Get the number of soldiers entered by the user and ensure it is a valid number
     * @return the number of soldiers
     */
    public int getSoldierCount() {
        return soldierField.getText().isEmpty() ? 0 : Integer.parseInt(soldierField.getText());
    }

    /**
     * Add a label and a text field to the panel
     * @param labelText the text of the label
     * @param textField the text field to add
     */
    private void addLabelAndField(String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label);
        add(textField);
    }
}