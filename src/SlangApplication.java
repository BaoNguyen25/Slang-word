import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SlangApplication extends JFrame {

    public SlangApplication() {
    }

    public void init() {

        this.setTitle("Slang Words Application");
        this.setSize(400, 600);
        this.setLayout(new BorderLayout());

        // create header
        JLabel header = new JLabel("MENU", JLabel.CENTER);
        Font font  = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        header.setFont(font);
        header.setForeground(Color.orange);
        JPanel jPanel_header = new JPanel();
        jPanel_header.setBackground(new Color(81,80,106));
        jPanel_header.setLayout(new BorderLayout());
        jPanel_header.add(header, BorderLayout.CENTER);

        // create list of features
        JPanelGradient jPanel_features = addButton();

        this.add(jPanel_header, BorderLayout.NORTH);
        this.add(jPanel_features, BorderLayout.CENTER);

        this.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public JPanelGradient addButton() {
        JPanelGradient buttonPanel = new JPanelGradient();

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));

        buttonPanel.add(createButton("View Slang Words List"));
        buttonPanel.add(Box.createVerticalGlue());

        buttonPanel.add(createButton("Find Slang By Word"));
        buttonPanel.add(Box.createVerticalGlue());

        buttonPanel.add(createButton("Find Slang By Definition"));
        buttonPanel.add(Box.createVerticalGlue());

        buttonPanel.add(createButton("View Search History"));
        buttonPanel.add(Box.createVerticalGlue());

        buttonPanel.add(createButton("Add Slang Word"));
        buttonPanel.add(Box.createVerticalGlue());

        buttonPanel.add(createButton("Edit Slang Word"));
        buttonPanel.add(Box.createVerticalGlue());

        buttonPanel.add(createButton("Delete Slang Word"));
        buttonPanel.add(Box.createVerticalGlue());

        buttonPanel.add(createButton("Reset Slang Words List"));
        buttonPanel.add(Box.createVerticalGlue());

        buttonPanel.add(createButton("Random Slang Word"));
        buttonPanel.add(Box.createVerticalGlue());

        buttonPanel.add(createButton("Game"));
        buttonPanel.add(Box.createVerticalGlue());

        return buttonPanel;
    }

    public JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);
        Font font = new Font(Font.SERIF, Font.BOLD, 15);
        button.setForeground(Color.RED);
        button.setFont(font);
        button.setBackground(Color.WHITE);
        button.setFocusable(false);

        return button;
    }
}
