package swing.demo.jlistviewmodule;


import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class ImageListModel extends AbstractListModel<FileEntity> {

    private List<FileEntity> fileEntities = new ArrayList<FileEntity>();

    public void addElement(FileEntity file){
        this.fileEntities.add(file);
    }

    public int getSize() {
        return fileEntities.size();
    }

    public FileEntity getElementAt(int index) {
        return fileEntities.get(index);
    }

    public void clear(){
        fileEntities.clear();
    }

}