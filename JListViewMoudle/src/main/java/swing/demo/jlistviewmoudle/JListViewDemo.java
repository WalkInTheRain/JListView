package swing.demo.jlistviewmoudle;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class JListViewDemo extends JFrame {

    public JListViewDemo() {
        final JScrollPane scrollPane = new JScrollPane();
        final JListView listView = new JListView(scrollPane);
        final List<FileEntity> data = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            FileEntity newFileEntity = new FileEntity();
            newFileEntity.setName("文件-" + (i + 1));
            newFileEntity.setType(i > 20 ? "photo" : "folder");
            data.add(newFileEntity);
        }
        listView.addData(data);
        listView.setOnScrollBottomListener(new JListView.OnBottomListener() {
            @Override
            public void goBottom(int index) {
                System.out.println("callback-------bottom " + index);
                listView.addData(data);
            }
        });
        listView.setOnDoubleClickListener(new JListView.OnDoubleClickListener() {
            @Override
            public void doubleClick(int index) {
                System.out.println("callback-------doubleclick " + index);
            }
        });
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(950, 500);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new JListViewDemo();
    }
}
