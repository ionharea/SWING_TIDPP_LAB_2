import java.util.List;

import javax.swing.*;

class Mediator {

    private JList list, mirror;    // lists
    private JTextField tName;        // name field
    private JButton bAdd;            // add button
    private JButton bRemove;        // remove button

    private StateManager stateManager = new StateManager(this);

    // registration methods

    void registerList(JList list) {
        this.list = list;
    }

    void registerMirror(JList mirror) {
        this.mirror = mirror;
    }

    void registerName(JTextField tName) {
        this.tName = tName;
    }

    void registerAdd(JButton bAdd) {
        this.bAdd = bAdd;
    }

    void registerRemove(JButton bRemove) {
        this.bRemove = bRemove;
    }

    // list methods

    void addList() {
        final String text = tName.getText();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null, "Name is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        bAdd.setEnabled(false);

        new ListWorker(text).execute();
    }

    void removeList() {
        int index = list.getSelectedIndex();
        if (index == -1) {
            final String text = tName.getText();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null, "No item selected!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                ((DefaultListModel) list.getModel()).removeElement(text);
            }
        } else
            ((DefaultListModel) list.getModel()).remove(index);
    }

    // methods called on events

    void add() {
        stateManager.add();
    }

    void remove() {
        stateManager.remove();
    }

    void list() {
        stateManager.setListState();
    }

    void message() {
        stateManager.setMessageState();
    }


    // swing worker

    private class ListWorker extends SwingWorker<Void, Integer> {

        private String text;

        ListWorker(String text) {
            this.text = text;
        }

        @Override
        protected Void doInBackground() {
            int i = 3;
            while (i-- > 0)
                try {
                    Thread.sleep(1000);
                    publish(i);
                } catch (InterruptedException e) {
                }

            return null;
        }

        @Override
        protected void process(List<Integer> list) {
            for (int i : list)
                System.out.println("Processed: " + i);
        }

        @Override
        public void done() {
            ((DefaultListModel) list.getModel()).addElement(text);
            bAdd.setEnabled(true);
        }

    }
}
