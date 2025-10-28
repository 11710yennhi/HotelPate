package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;

public class HoaDon_GUI extends JPanel implements ActionListener, MouseListener{
	private JTextField txtSDT, txtmaHD;
	private JTable table;
    private DefaultTableModel modelHD;
    private JButton btnTimKiem1, btnTimKiem2;
	public HoaDon_GUI() {
		setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // ===== TIÊU ĐỀ =====
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitle.setForeground(new Color(30, 60, 114));
        add(lblTitle, BorderLayout.NORTH);

        // ===== KHỞI TẠO GIAO DIỆN =====
        initForm();

        // ===== KẾT NỐI SQL & LOAD DỮ LIỆU =====
        try {
            ConnectDB.getInstance().connect();
            //loadKhuyenMaiToTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không thể kết nối CSDL: " + e.getMessage());
        }
	}
	
	private void initForm() {
		JPanel p = new JPanel(new BorderLayout(10, 10));
		TitledBorder title = new TitledBorder("Hóa đơn");
		title.setTitleFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
		p.setBorder(title);
		p.setBackground(Color.WHITE);
		add(p, BorderLayout.NORTH);

		// Panel cha dùng BoxLayout dọc
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.WHITE);

		//====== SDT ======
		JPanel panelSDT = new JPanel();
		panelSDT.setLayout(new BoxLayout(panelSDT, BoxLayout.X_AXIS));
		panelSDT.setBackground(Color.WHITE);

		panelSDT.add(new JLabel("SDT:  "));
		txtSDT = new JTextField();
		txtSDT.setPreferredSize(new Dimension(300, 30));
		panelSDT.add(txtSDT);
		
		btnTimKiem1 = new JButton("Tìm kiếm");
		btnTimKiem1.setPreferredSize(new Dimension(100, 30));
		panelSDT.add(btnTimKiem1);
		
		//====== MÃ HOÁ ĐƠN ======
		JPanel panelMaHD = new JPanel();
		panelMaHD.setLayout(new BoxLayout(panelMaHD, BoxLayout.X_AXIS));
		panelMaHD.setBackground(Color.WHITE);

		panelMaHD.add(new JLabel("Mã hoá đơn:  "));
		txtmaHD = new JTextField();
		txtmaHD.setPreferredSize(new Dimension(300, 30));
		panelMaHD.add(txtmaHD);
		
		btnTimKiem2 = new JButton("Tìm kiếm");
		btnTimKiem2.setPreferredSize(new Dimension(100, 30));
		panelMaHD.add(btnTimKiem2);
		
		//====== GOM 2 CÁI THÀNH 1 HÀNG ======
		JPanel row = new JPanel();
		row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
		row.setBackground(Color.WHITE);
		
		row.add(Box.createHorizontalStrut(10));
		row.add(panelSDT);
		row.add(Box.createHorizontalStrut(300)); // khoảng cách giữa 2 cụm
		row.add(panelMaHD);
		row.add(Box.createHorizontalStrut(500));
		
		// Thêm vào mainPanel
		mainPanel.add(row);

		// Thêm mainPanel vào panel chính
		p.add(mainPanel, BorderLayout.CENTER);


        // Bảng dữ liệu
        String[] cols = {"STT", "Mã hóa đơn", "SDT", "Trạng thái", "Tổng tiền"
        		, "Xem chi tiết"};
        modelHD = new DefaultTableModel(cols, 0);
        table = new JTable(modelHD);
        table.addMouseListener(this);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f1 = new JFrame("ccc");
            f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f1.setExtendedState(JFrame.MAXIMIZED_BOTH);
            f1.add(new HoaDon_GUI());
            f1.setVisible(true);
        });
    }
}
