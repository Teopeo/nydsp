package nydsp;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ComboBoxToolTipRenderer extends DefaultListCellRenderer {
	@Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
         JComponent component = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected,
                cellHasFocus);
         String tip = null;
         if (value instanceof ToolTipProvider) {
             ToolTipProvider ttp = (ToolTipProvider) value;
             tip = ttp.getToolTip();
         }
         list.setToolTipText(tip);
         return component;
    }
}