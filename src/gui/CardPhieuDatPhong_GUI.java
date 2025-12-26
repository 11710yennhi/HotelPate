package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CardPhieuDatPhong_GUI extends JPanel {

    public CardPhieuDatPhong_GUI(String maPhieu, String maKH, String tenKH, String sdt, int x) {
        // ✅ Kích thước gọn hơn — vừa 6 card mỗi hàng, không dài
        setPreferredSize(new Dimension(170, 115));
        if(x==1) {
        	 setBackground(Color.RED);
        }else if(x==2) {
        	setBackground(new Color(102, 187, 106));
        }else if(x==4) {
        	setBackground(new Color(100, 149, 237));
        }else if(x==3) {
        	setBackground(new Color(255, 215, 0));
        }
       // setBackground(new Color(102, 187, 106)); // xanh lá nhạt
        setLayout(null);
        setBorder(new LineBorder(new Color(30, 100, 60), 1, true)); // viền bo nhẹ

        // --- Mã phiếu ---
        JLabel lblMa = new JLabel(maPhieu, SwingConstants.CENTER);
        lblMa.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMa.setForeground(Color.BLACK);
        lblMa.setBounds(0, 5, 170, 18);
        add(lblMa);

        // --- Đường kẻ ngang ---
        JSeparator line = new JSeparator();
        line.setBounds(10, 25, 150, 1);
        add(line);

        // --- Nội dung chi tiết ---
        JTextArea txt = new JTextArea(
                "Mã KH: " + maKH + "\n" +
                "Họ Tên: " + tenKH+ "\n" +
                "SDT: " + sdt + "\n" 
                
        );
        txt.setBounds(10, 30, 150, 70);
        txt.setBackground(getBackground());
        txt.setForeground(Color.BLACK);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        txt.setEditable(false);
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        add(txt);
    }
}
