package swing.demo.jlistviewmodule;

import java.awt.Image;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class JListView {
    private HashSet<Integer> mHashSet;
    private JScrollPane mJScrollPanel;
    private Vector<FileEntity> mPicIconList;
    private ImageListModel mListModel;
    private JList<FileEntity> mListView;
    private int mCellLength = 100;
    private OnBottomListener mBottomCallback;
    private OnDoubleClickListener mDoubleListener;

    public JListView(JScrollPane scrollPanel) {
        mHashSet = new HashSet();
        mPicIconList = new Vector<>();
        mListModel = new ImageListModel();
        mListView = new JList<FileEntity>();
        mJScrollPanel = scrollPanel;
        setListener();
    }

    public void addData(final List<FileEntity> list) {
        mPicIconList.clear();
        for (int i = 0; i < list.size(); i++) {
            final int finalI = i;
            String type = list.get(finalI).getType();
            FileEntity originFileEntity = list.get(finalI);
            FileEntity newFileEntity = new FileEntity();
            newFileEntity.setName(originFileEntity.getName());
            newFileEntity.setType(originFileEntity.getType());
            if (type.equals("photo")) {
                ImageIcon icon = new ImageIcon(new ImageIcon("JListViewModule/src/main/java/resource/listview_photo.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                newFileEntity.setFinalImageIcon(icon);
                mPicIconList.add(newFileEntity);
            } else if (type.equals("folder")) {
                ImageIcon icon = new ImageIcon(new ImageIcon("JListViewModule/src/main/java/resource/listview_folder.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                newFileEntity.setFinalImageIcon(icon);
                mPicIconList.add(newFileEntity);
            }
        }

        for (int i = 0; i < mPicIconList.size(); i++) {
            FileEntity fileEntity = mPicIconList.get(i);
            mListModel.addElement(fileEntity);
        }

        mListView.setModel(mListModel);
        mListView.setCellRenderer(new ImageCellRender());
        mListView.setFixedCellHeight(mCellLength);
        mListView.setFixedCellWidth(mCellLength);
        //此处可控制多列或单列
        mListView.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        mListView.setVisibleRowCount(-1);
    }

    public void setListener(){
        mListView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = mListView.getSelectedIndex();

                    if (mDoubleListener != null) {
                        mDoubleListener.doubleClick(index);
                    }
                }
            }
        });
        mJScrollPanel.setViewportView(mListView);
        mJScrollPanel.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int index = mListView.getLastVisibleIndex();
                if (!mHashSet.contains(index)) {
                    mHashSet.add(index);
                    if (index == mListModel.getSize() - 1) {
                        if (mBottomCallback != null) {
                            mBottomCallback.goBottom(index);
                        }
                    }
                }
            }
        });
    }

    public void setOnScrollBottomListener(OnBottomListener callback) {
        mBottomCallback = callback;
    }

    interface OnBottomListener {
        void goBottom(int index);
    }

    public void setOnDoubleClickListener(OnDoubleClickListener callback) {
        mDoubleListener = callback;
    }

    interface OnDoubleClickListener {
        void doubleClick(int index);
    }
}
