package gui;

import javax.swing.*;
import java.awt.*;

public class NhanVien_testtt extends JPanel {
    public NhanVien_testtt() {
        setLayout(new BorderLayout());
        JLabel lbl = new JLabel("QUẢN LÝ NHÂN VIÊN", SwingConstants.CENTER);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 26));
        add(lbl, BorderLayout.NORTH);

        String[] columns = {"Mã NV", "Tên NV", "Chức vụ", "SĐT", "Trạng thái"};
        Object[][] data = {};
        JTable table = new JTable(data, columns);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}

