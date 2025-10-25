package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CardPhieuDatPhong_GUI extends JPanel {
    
    public CardPhieuDatPhong_GUI(String maPhieu, String tenKH, String sdt, String checkIn, String checkOut) {
        setPreferredSize(new Dimension(200, 160)); // ⚡ Kích thước vừa phải, cân 6 card mỗi hàng
        setBackground(new Color(102, 187, 106)); // xanh lá nhạt (đã đặt)
        setLayout(null);
        setBorder(new LineBorder(new Color(30, 100, 60), 1, true)); // viền bo góc nhẹ

        // --- Mã phiếu ---
        JLabel lblMa = new JLabel(maPhieu, SwingConstants.CENTER);
        lblMa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblMa.setForeground(Color.BLACK);
        lblMa.setBounds(0, 5, 200, 20);
        add(lblMa);

        // --- Đường kẻ ngang ---
        JSeparator line = new JSeparator();
        line.setBounds(10, 28, 180, 1);
        add(line);

        // --- Nội dung chi tiết ---
        JTextArea txt = new JTextArea(
                "Tên KH: " + tenKH + "\n" +
                "SĐT: " + sdt + "\n" +
                "Check-in: " + checkIn + "\n" +
                "Check-out: " + checkOut
        );
        txt.setBounds(10, 35, 180, 110);
        txt.setBackground(new Color(102, 187, 106));
        txt.setForeground(Color.BLACK);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txt.setEditable(false);
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        add(txt);
    }
}
