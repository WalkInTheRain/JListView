package swing.demo.jlistviewmodule;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

public class ImageCellRender extends JButton implements ListCellRenderer {
    public ImageCellRender() {
    }

    public void setBackgroundPng(final FileEntity fileEntity, final int index) {
        setBorderPainted(false);
        setText(fileEntity.getName());
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setIcon(fileEntity.getFinalImageIcon());
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof FileEntity) {
            FileEntity fileEntity = (FileEntity) value;
            try {
                setBackgroundPng(fileEntity, index);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }
}