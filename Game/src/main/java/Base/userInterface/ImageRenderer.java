package Base.userInterface;

import Base.objects.abstracts.AbstractFigur;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ImageRenderer extends DefaultTableCellRenderer {

    private JLabel lbl = new JLabel();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        AbstractFigur currentObject = (AbstractFigur) value;
        lbl.setText(null);
        lbl.setIcon(currentObject.getImage());
        return lbl;
    }

}