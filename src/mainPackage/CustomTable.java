package mainPackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class CustomTable extends JPanel {

	private JTable table;
    private DefaultTableModel model;
    private Consumer<String> removeAction; 
    private Consumer<Students> modifyAction; 
    /**
     * Creates a custom table with specified column names and data.
     *
     * @param columnNames The names of the columns.
     * @param data        The data to populate the table.
     * @param actionColumnIndices The indices of the columns that will contain clickable labels.
     * @param removeAction The function to handle remove actions.
     * @param modifyAction The function to handle modify actions.
     */
    public CustomTable(String[] columnNames, Object[][] data, int[] actionColumnIndices, 
                       Consumer<String> removeAction, Consumer<Students> modifyAction) {
        this.removeAction = removeAction;
        this.modifyAction = modifyAction;
        setLayout(new BorderLayout());

      
        model = new DefaultTableModel(data, columnNames) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 5198845814440282461L;
			

			@Override
            public boolean isCellEditable(int row, int column) {
                // Make only the action columns editable
                for (int index : actionColumnIndices) {
                    if (column == index) {
                        return true;
                    }
                }
                return false;
            }
        };
        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBackground(new Color(0x31363F));
        table.getTableHeader().setForeground(Color.white);
        JTableHeader tableHeader = table.getTableHeader();
        Font headerFont = tableHeader.getFont();
        tableHeader.setFont(headerFont.deriveFont(Font.BOLD));
        table.updateUI();
        
        // Set custom renderer and editor for the action columns
        for (int index : actionColumnIndices) {
            table.getColumnModel().getColumn(index).setCellRenderer(new LabelRenderer());
            table.getColumnModel().getColumn(index).setCellEditor(new LabelEditor());
        }
        
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800,400));
        scrollPane.setAutoscrolls(true);
       
       
        
      
        add(scrollPane, BorderLayout.CENTER);
    }

	/**
     * Custom cell renderer to display a JLabel.
     */
    private static class LabelRenderer extends JLabel implements TableCellRenderer {
        public LabelRenderer() {
            setOpaque(true); // Make the label opaque to ensure background color is visible
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString()); // Set the label text
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground()); 
            setForeground(isSelected ? table.getSelectionForeground() : table.getForeground()); // Set text color
            return this;
        }
    }

    /**
     * Custom cell editor to handle mouse events on the JLabel.
     */
    private class LabelEditor extends AbstractCellEditor implements TableCellEditor {
        private JLabel label;

        public LabelEditor() {
            label = new JLabel();
            label.setOpaque(true);

            // Add a MouseListener to the label
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = table.getSelectedRow();
                    int column = table.getSelectedColumn();

                    // "Remove" action
                    if (column == 5) { 
                        String cne = (String) table.getValueAt(row, 0); // Get the CNE from the first column
                        if (removeAction != null) {
                            removeAction.accept(cne); 
                        }
                    }

                    //Modify action
                    if (column == 6) { 
                        String cne = (String) table.getValueAt(row, 0); 
                        String firstName = (String) table.getValueAt(row, 1); 
                        String lastName = (String) table.getValueAt(row, 2); 
                        Object dateObj = table.getValueAt(row, 3); 
                        String email = (String) table.getValueAt(row, 4); 

                       
                        Date date =new Date();
                        if (dateObj instanceof Date) {
                           
                            date = (Date) dateObj;       } 
                        // Create a Student object
                        Students student = new Students(cne, firstName, lastName, date, email);

                        if (modifyAction != null) {
                            modifyAction.accept(student); // Call the modify action function
                        }
                    }

                    fireEditingStopped(); // Stop editing after the click
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label.setText((value == null) ? "" : value.toString()); // Set the label text
            label.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground()); // Set background color
            label.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground()); // Set text color
            return label;
        }

        @Override
        public Object getCellEditorValue() {
            return label.getText(); // Return the cell value
        }
    }
}