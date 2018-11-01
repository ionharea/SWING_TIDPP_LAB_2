import javax.swing.*;

import java.awt.*;

public class Main extends JPanel {

    private DefaultListModel<String> model;                 // list model
    private JList<String> list;
    private JList mirror;                                // lists
    private JTextField tName = new JTextField(10);        // name field
    private JButton bAdd = new JButton("Add");        // add button
    private JButton bRemove = new JButton("Remove");    // remove button

    private Main() {
        init();
    }

    private void init() {
        // initialize model
        model = new DefaultListModel<String>();
        model.addElement("Harea Ion");
        model.addElement("Tamazlicari Victor");
        model.addElement("Druta Mihai");

        tName.setName("tname");
        bAdd.setName("bAdd");
        bRemove.setName("bRemove");

        // initialize lists, based on the same model
        list = new JList<String>(model);
        list.setName("list");
        mirror = new JList(new ReverseListModel(model));


        // main panel
        JPanel top = new JPanel(new GridLayout(1, 0)); // 1 row, any number of columns
        JPanel bottom = new JPanel(new FlowLayout());
        this.setLayout(new BorderLayout());
        this.add(top, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);

        top.add(new JScrollPane(list));
        top.add(new JScrollPane(mirror));


        // bottom panel: name field, add button, remove button
        bottom.add(tName);
        bottom.add(bAdd);
        bottom.add(bRemove);

        bAdd.addActionListener(e -> {
            String text = tName.getText();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null, "The field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (model.contains(tName.getText())) {
                JOptionPane.showMessageDialog(
                        null, "Item already exist!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            model.addElement(text);
            tName.setText("");
        });

        bRemove.addActionListener(e -> {
            int index = list.getSelectedIndex();
            if (model.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null, "There is nothing to delete", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (index == -1) {
                JOptionPane.showMessageDialog(
                        null, "No item selected!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            model.remove(index);

        });
    }

    private static void buildGUI() {
        JFrame frame = new JFrame("Laborator 2"); // title
        frame.setContentPane(new Main()); // content
        frame.setSize(300, 300); // width / height
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit application when window is closed
        frame.setVisible(true); // show it!
    }


    public static void main(String[] args) {
        // run on EDT (event dispatch thread), not on main thread!
        SwingUtilities.invokeLater(
                () -> buildGUI());
    }

}
