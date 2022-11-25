import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SearchHistoryFrame extends JFrame implements ActionListener {
    private SlangWordList list;
    private JButton backButton;
    private JButton clearButton;
    private JTable table;
    private String[][] results;
    private DefaultTableModel model;
    private HandleFiles hf = new HandleFiles();
    public SearchHistoryFrame() {
        list = new SlangWordList();
        Container container = this.getContentPane();
        container.setBackground(new Color(81,80,106));

        //Title
        JLabel header = new JLabel();
        header.setText("Search History");
        header.setForeground(Color.ORANGE);
        header.setFont(new Font("Gill Sans MT", Font.PLAIN, 35));
        header.setAlignmentX(CENTER_ALIGNMENT);

        // Table
        JPanel panelTable = new JPanel();
        panelTable.setBackground(Color.black);
        String column[] = {"SLANG", "MEANING", "TIME"};
        results = hf.readHistory();
        table = new JTable(new DefaultTableModel(column, 0));
        model = (DefaultTableModel) table.getModel();
        table.setRowHeight(50);

        // add history to table;
        for(int i = 0; i < results.length; i++) {
            String s[] = results[i];
            model.addRow(s);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getModel().addTableModelListener(table);

        // Scroll
        JScrollPane sp = new JScrollPane(table);
        panelTable.setLayout(new GridLayout(1, 1));
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

        clearButton = new JButton("Clear history");
        clearButton.addActionListener(this);
        clearButton.setFocusable(false);
        clearButton.setAlignmentX(CENTER_ALIGNMENT);
        clearButton.setForeground(Color.BLUE);
        clearButton.setFont(font);
        clearButton.setBackground(Color.WHITE);

        bottomPanel.add(clearButton);
        bottomPanel.add(backButton);

        // Add elements to container
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(header);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(panelTable);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
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
        if(e.getSource() == clearButton) {
            this.clearTable();
            try {
                hf.cleanFile("history.txt");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == backButton) {
            this.dispose();
            new SlangApplication();
        }
    }

    void clearTable() {
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
}
