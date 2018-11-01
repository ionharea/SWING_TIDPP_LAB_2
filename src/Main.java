import javax.swing.*;

import java.awt.*;

public class Main extends JPanel {

    private DefaultListModel<String> model;                                        // list model
    private JList<String> list;
    private JList mirror;                                // lists
    private JTextField tName = new JTextField(10);        // name field
    private JButton bAdd = new JButton("Add");        // add button
    private JButton bRemove = new JButton("Remove");    // remove button
    private JRadioButton rList = new JRadioButton("List");        // list radio button
    private JRadioButton rMessage = new JRadioButton("Message");    // list radio button

    private Mediator med = new Mediator();

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

        // init radios
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(rList);
        radioGroup.add(rMessage);
        rList.setSelected(true);

        // main panel
        JPanel top = new JPanel(new FlowLayout());
        JPanel middle = new JPanel(new GridLayout(1, 0)); // 1 row, any number of columns
        middle.setName("middle");
        JPanel bottom = new JPanel(new FlowLayout());
        bottom.setName("bottom");
        this.setLayout(new BorderLayout());
        this.add(top, BorderLayout.NORTH);
        this.add(middle, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);

        // top panel: two radios
        top.add(rList);
        top.add(rMessage);

        // middle panel: the two lists (scrollable)
        JScrollPane jsp = new JScrollPane(list);
        jsp.setName("scroll");

        JScrollPane jsp2 = new JScrollPane(mirror);
        jsp.setName("scroll2");

        middle.add(jsp);
        middle.add(jsp2);

        // bottom panel: name field, add button, remove button
        bottom.add(tName);
        bottom.add(bAdd);
        bottom.add(bRemove);

        // mediator init
        med.registerList(list);
        med.registerMirror(mirror);
        med.registerName(tName);
        med.registerAdd(bAdd);
        med.registerRemove(bRemove);

        bAdd.addActionListener(e -> {
            med.add();
            String text = tName.getText();
//            if (text.isEmpty()) {
//                JOptionPane.showMessageDialog(
//                        null, "Name is empty!", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
            model.addElement(text);
        });

        bRemove.addActionListener(e -> {
            med.remove();

            int index = list.getSelectedIndex();
//            if (index == -1) {
//                JOptionPane.showMessageDialog(
//                        null, "No item selected!", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }

            model.remove(index);

        });

        rList.addActionListener(e -> med.list());

        rMessage.addActionListener(e -> med.message());

    }

    private static void buildGUI() {
        JFrame frame = new JFrame("Laborator 2/3 TIDPP"); // title
        frame.setContentPane(new Main()); // content
        frame.setSize(300, 300); // width / height
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit application when window is closed
        frame.setVisible(true); // show it!
    }


    public static void main(String[] args) {
        // run on EDT (event dispatch thread), not on main thread!
        SwingUtilities.invokeLater(
                () -> buildGUI());
    }

}
