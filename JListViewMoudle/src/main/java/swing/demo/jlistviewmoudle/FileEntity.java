package swing.demo.jlistviewmoudle;

import javax.swing.ImageIcon;

public class FileEntity {
    private String name;
    private String type;
    private ImageIcon finalImageIcon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ImageIcon getFinalImageIcon() {
        return finalImageIcon;
    }

    public void setFinalImageIcon(ImageIcon finalImageIcon) {
        this.finalImageIcon = finalImageIcon;
    }
}
