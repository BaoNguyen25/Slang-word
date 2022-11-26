import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SlangApplication extends JFrame implements ActionListener {
    private SlangWordList list;

    public SlangApplication() {
        list = new SlangWordList();
        this.setTitle("Slang Words Application");
        this.setSize(400, 600);
        this.setLayout(new BorderLayout());

        // create header
        JLabel header = new JLabel("MENU", JLabel.CENTER);
        Font font = new Font("Gill Sans MT", Font.PLAIN, 35);
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

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
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
        button.addActionListener(this);
        button.setFocusable(false);

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "View Slang Words List"){
            try {
                new ListSlangWordFrame();
                this.dispose();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getActionCommand() == "Find Slang By Word") {
            try {
                new FindWordFrame();
                this.dispose();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getActionCommand() == "Find Slang By Definition") {
            try {
                new FindDefinitionFrame();
                this.dispose();
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getActionCommand() == "View Search History") {
            try {
                new SearchHistoryFrame();
                this.dispose();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getActionCommand() == "Reset Slang Words List") {
            int n = JOptionPane.showConfirmDialog(this, "Do you really want to reset Slang Word List?", "Message",
                    JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                try {
                    list.resetOriginalDict();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(this, "Reset Successfully!");
            }
        }

        if(e.getActionCommand() == "Add Slang Word") {
            try {
                new AddSlangFrame();
                this.dispose();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getActionCommand() == "Delete Slang Word") {
            try {
                new DeleteSlangFrame();
                this.dispose();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getActionCommand() == "Edit Slang Word") {
            try {
                new EditSlangFrame();
                this.dispose();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getActionCommand() == "Random Slang Word") {
            try {
                new RandomSlangFrame();
                this.dispose();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getActionCommand() == "Game") {
            Object[] options = { "Random Slang", "Random Definition" };
            int n = JOptionPane.showOptionDialog(this,
                    "Choose a mode you want to play", "Message",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            if (n == 0) {
                new GameSlang();
                this.dispose();
            } else if (n == 1) {
                new GameDefinition();
                this.dispose();
            }
        }
    }
}
