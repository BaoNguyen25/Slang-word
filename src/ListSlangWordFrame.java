import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListSlangWordFrame extends JFrame implements ActionListener, TableModelListener {
    private JButton backButton;
    private JTable table;
    private SlangWordList list;

    public ListSlangWordFrame() {
        list = new SlangWordList();
        Container container = this.getContentPane();
        container.setBackground(new Color(81,80,106));

        // Title
        JLabel header = new JLabel();
        header.setText("List Slang Words");
        header.setForeground(Color.ORANGE);
        header.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        header.setAlignmentX(CENTER_ALIGNMENT);

        // List Slang Words
        JPanelGradient panelTable = new JPanelGradient();
        String data[][] = list.getList();
        String column[] = { "NUMBER", "SLANG", "MEANING" };
        table = new JTable(data, column);
        table.setRowHeight(50);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getModel().addTableModelListener(this);

        JScrollPane sp = new JScrollPane(table);
        panelTable.setLayout(new BoxLayout(panelTable, BoxLayout.X_AXIS));
        panelTable.add(sp);

        // Button Back
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(81,80,106));
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        Font font = new Font(Font.SERIF, Font.BOLD, 15);
        backButton.setForeground(Color.RED);
        backButton.setFont(font);
        backButton.setBackground(Color.WHITE);
        bottomPanel.add(backButton);

        // Add elements to container
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(header);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(panelTable);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(bottomPanel);

        // Setting JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Slang Words Application");
        this.setVisible(true);
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            this.dispose();
            new SlangApplication();
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {

    }
}
