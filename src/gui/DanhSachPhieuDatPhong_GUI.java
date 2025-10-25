package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DanhSachPhieuDatPhong_GUI extends JPanel implements ActionListener, MouseListener {
    private JPanel pnlCards;
    private JTextField txtSDT, txtMaPhong;
    private JButton btsdt, btmp;
    public DanhSachPhieuDatPhong_GUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // --- Thanh tìm kiếm phía trên ---
        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.setBackground(new Color(240, 240, 240));
        pnlTop.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // --- Phần bên trái: tìm kiếm ---
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        pnlSearch.setBackground(new Color(240, 240, 240));

        pnlSearch.add(new JLabel("Số điện thoại:"));
        txtSDT = new JTextField(10);
        pnlSearch.add(txtSDT);
        btsdt = new JButton("Tìm");
        pnlSearch.add(btsdt);

        pnlSearch.add(new JLabel("Mã phòng:"));
        txtMaPhong = new JTextField(10);
        pnlSearch.add(txtMaPhong);
        btmp= new JButton("Tìm");
        pnlSearch.add(btmp);

        pnlTop.add(pnlSearch, BorderLayout.WEST);

        // --- Phần bên phải: chú thích trạng thái ---
        JPanel pnlLegend = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        pnlLegend.setBackground(new Color(240, 240, 240));

        pnlLegend.add(createLegend(Color.RED, "Tới ngày nhận phòng"));
        pnlLegend.add(createLegend(new Color(102, 187, 106), "Đã đặt"));
        pnlLegend.add(createLegend(new Color(255, 215, 0), "Đang ở"));
        pnlLegend.add(createLegend(new Color(100, 149, 237), "Tới ngày trả phòng"));


        pnlTop.add(pnlLegend, BorderLayout.EAST);

        add(pnlTop, BorderLayout.NORTH);

        // --- Panel chứa danh sách phiếu ---
        pnlCards = new JPanel();
        pnlCards.setLayout(new GridLayout(0, 6, 15, 15)); // ✅ 6 phiếu mỗi hàng
        pnlCards.setBackground(Color.WHITE);
        pnlCards.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        // --- Scroll pane ---
        JScrollPane scroll = new JScrollPane(pnlCards);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);

        // --- Thêm vài card mẫu ---
        for (int i = 1; i <= 30; i++) {
            pnlCards.add(new CardPhieuDatPhong_GUI(
                    "PDP20092025" + String.format("%03d", i),
                    "Nguyễn Văn A " + i,
                    "090" + (1000000 + i),
                    "14:00 20/09/2025",
                    "12:00 22/09/2025"
            ));
        }
    }

    private JPanel createLegend(Color color, String text) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JLabel box = new JLabel();
        box.setPreferredSize(new Dimension(20, 20));
        box.setOpaque(true);
        box.setBackground(color);
        p.add(box);

        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        p.add(lbl);

        p.setBackground(new Color(240, 240, 240));
        return p;
    }

    @Override public void actionPerformed(ActionEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    // --- Hàm main chạy thử ---
    public static void main(String[] args) {
        JFrame frame = new JFrame("Danh sách phiếu đặt phòng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // full màn hình
        frame.add(new DanhSachPhieuDatPhong_GUI());
        frame.setVisible(true);
    }
}
