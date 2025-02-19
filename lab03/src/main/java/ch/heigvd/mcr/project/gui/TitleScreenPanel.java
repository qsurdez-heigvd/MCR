package ch.heigvd.mcr.project.gui;

import ch.heigvd.mcr.project.Simulation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * The panel that is displayed when the application starts
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class TitleScreenPanel extends JPanel {
    private final Image backgroundImage;
    private final Image image;

    private final SettingsSelectorPanel settingsSelectorPanel;
    /**
     * Constructor
     * @param jFrame the JFrame of the application
     * @param simulationPanel the simulation panel to display the simulation
     * @param controlPanel the control panel to display the buttons to change the mediator and the colorblind mode
     * @param image the image to display
     * @param backgroundImage the background image to display
     */
    public TitleScreenPanel(JFrame jFrame, JPanel simulationPanel, JPanel controlPanel, Image image, Image backgroundImage) {
        this.image = image;
        this.backgroundImage = backgroundImage;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = getTitleLabel();

        add(Box.createVerticalStrut(30));
        add(titleLabel);
        add(Box.createVerticalStrut(80));

        // Create the gatherer animation
        JLabel gathererLabel = new JLabel();
        try {
            Image gathererImage1 = ImageIO.read(Objects.requireNonNull(TitleScreenPanel.class.getResource("/assets/gatherer.png"))).getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            Image gathererImage2 = ImageIO.read(Objects.requireNonNull(TitleScreenPanel.class.getResource("/assets/gatherer2.png"))).getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            Image gathererImage3 = ImageIO.read(Objects.requireNonNull(TitleScreenPanel.class.getResource("/assets/gatherer3.png"))).getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon gathererIcon1 = new ImageIcon(gathererImage1);
            ImageIcon gathererIcon2 = new ImageIcon(gathererImage2);
            ImageIcon gathererIcon3 = new ImageIcon(gathererImage3);
            gathererLabel.setIcon(gathererIcon1);

            new Timer(200, e -> {
                if (gathererLabel.getIcon().equals(gathererIcon1)) {
                    gathererLabel.setIcon(gathererIcon2);
                } else if (gathererLabel.getIcon().equals(gathererIcon2)) {
                    gathererLabel.setIcon(gathererIcon3);
                } else {
                    gathererLabel.setIcon(gathererIcon1);
                }
            }).start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        gathererLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(gathererLabel);
        add(Box.createVerticalStrut(80));

        // Create the settings selector panel
        settingsSelectorPanel = new SettingsSelectorPanel();
        add(settingsSelectorPanel);
        add(Box.createVerticalStrut(50));

        // Create the start button
        JButton startButton = getStartButton(jFrame, simulationPanel, controlPanel);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(startButton);
    }

    /**
     * Create the title label with the text "Ant Simulation"
     * @return the title label
     */
    private JLabel getTitleLabel() {
        JLabel titleLabel = new JLabel("Ant Simulation") {
            @Override
            protected void paintComponent(Graphics g) {
                Color originalColor = g.getColor();
                g.setColor(new Color(248, 239, 228));
                g.setFont(getFont());
                FontMetrics fm = g.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                // Draw the text with a shadow
                for (int i = -2; i <= 2; i++) {
                    for (int j = -2; j <= 2; j++) {
                        g.drawString(getText(), x + i, y + j);
                    }
                }
                g.setColor(new Color(54, 36, 23));
                g.drawString(getText(), x, y);
                g.setColor(originalColor);
            }
        };
        titleLabel.setFont(new Font("Serif", Font.BOLD, 70));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return titleLabel;
    }

    /**
     * Create the start button that starts the simulation
     * @param jFrame the JFrame of the application
     * @param simulationPanel the simulation panel to display the simulation
     * @param controlPanel the control panel to display the buttons to change the mediator and the colorblind mode
     * @return the start button
     */
    private JButton getStartButton(JFrame jFrame, JPanel simulationPanel, JPanel controlPanel) {
        JButton startButton = new JButton("Start Simulation");
        startButton.setFocusable(false);
        startButton.setBackground(new Color(54, 36, 23));
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 30));
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.addActionListener(e -> {
            jFrame.remove(this);
            jFrame.add(simulationPanel, BorderLayout.CENTER);
            jFrame.add(controlPanel, BorderLayout.SOUTH);
            jFrame.validate();
            jFrame.repaint();

            Simulation.getInstance().setNbGatherer(settingsSelectorPanel.getGathererCount());
            Simulation.getInstance().setNbScouts(settingsSelectorPanel.getScoutCount());
            Simulation.getInstance().setNbSoldiers(settingsSelectorPanel.getSoldierCount());
            // Initialize the mediator after setting the numbers
            Simulation.getInstance().initMediator();
            Simulation.getInstance().run();
            new InformationFrame().display();
        });
        return startButton;
    }

    /**
     * Paint the component
     * @param g the Graphics object on which to paint
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
        g.drawImage(image, 0, 0, null);
        setVisible(true);
    }
}