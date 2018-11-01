import javax.swing.*;
import javax.swing.event.*;

public class ReverseListModel extends AbstractListModel implements ListDataListener {

    private ListModel innerModel;

    ReverseListModel(ListModel innerModel) {
        this.innerModel = innerModel;
        innerModel.addListDataListener(this);
    }

    @Override
    public Object getElementAt(int index) {
        int newIndex = getSize() - 1 - index;
        return innerModel.getElementAt(newIndex);
    }

    @Override
    public int getSize() {
        return innerModel.getSize();
    }

    @Override
    public void contentsChanged(ListDataEvent e) {
        fireContentsChanged(e.getSource(), e.getIndex0(), e.getIndex1());
    }

    @Override
    public void intervalAdded(ListDataEvent e) {
        fireIntervalAdded(e.getSource(), e.getIndex0(), e.getIndex1());
    }

    @Override
    public void intervalRemoved(ListDataEvent e) {
        fireIntervalRemoved(e.getSource(), e.getIndex0(), e.getIndex1());
    }

}
