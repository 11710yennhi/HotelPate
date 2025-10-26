package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.ChiPhiPhatSinh_DAO;
import entity.ChiPhiPhatSinh;

public class ChiPhiPhatSinh_GUI extends JPanel implements ActionListener, MouseListener {

    private JTextField txtMaCP, txtGia, txtTenCP;
    private JTable table;
    private JComboBox<String> cboLoai;
    private DefaultTableModel model;
    private JButton btnThem, btnSua, btnTim;
    private ChiPhiPhatSinh_DAO dao = new ChiPhiPhatSinh_DAO();

    public ChiPhiPhatSinh_GUI() {
        initialize();
        loadDataToTable();
    }

    private void initialize() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("CHI PHÍ PHÁT SINH", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitle.setForeground(new Color(30, 60, 114));
        add(lblTitle, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new BorderLayout(10, 10));
        infoPanel.setBorder(new TitledBorder("Thông tin chi phí phát sinh"));
        infoPanel.setBackground(Color.WHITE);
        add(infoPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        formPanel.setBackground(Color.WHITE);

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 30));
        JLabel lblMa = new JLabel("Mã chi phí:");
        txtMaCP = new JTextField(40);
        JLabel lblGia = new JLabel("Giá:                ");
        txtGia = new JTextField(40);
        row1.add(lblMa);
        row1.add(txtMaCP);
        row1.add(lblGia);
        row1.add(txtGia);
        row1.setBackground(Color.WHITE);
        formPanel.add(row1);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        JLabel lblTen = new JLabel("Tên chi phí:");
        txtTenCP = new JTextField(40);
        JLabel lblLoai = new JLabel("Loại chi phí:");
        cboLoai = new JComboBox<>(new String[]{"Dịch vụ", "Phạt"});
        cboLoai.setPreferredSize(new Dimension(400, 25));
        row2.add(lblTen);
        row2.add(txtTenCP);
        row2.add(lblLoai);
        row2.add(cboLoai);
        row2.setBackground(Color.WHITE);
        formPanel.add(row2);

        infoPanel.add(formPanel, BorderLayout.CENTER);

        // ==== Buttons ====
        JPanel buttonColumn = new JPanel();
        buttonColumn.setLayout(new BoxLayout(buttonColumn, BoxLayout.Y_AXIS));
        buttonColumn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 100));
        buttonColumn.setBackground(Color.WHITE);

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Lưu");
        btnTim = new JButton("Tìm kiếm");

        for (JButton btn : new JButton[]{btnThem, btnSua, btnTim}) {
            btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
            btn.setBackground(new Color(220, 230, 250));
            btn.setFocusPainted(false);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(120, 35));
            buttonColumn.add(btn);
            buttonColumn.add(Box.createVerticalStrut(10));
            btn.addActionListener(this);
        }

        infoPanel.add(buttonColumn, BorderLayout.EAST);

        // ==== Table ====
        JPanel tablePanel = new JPanel(new BorderLayout());
        JLabel lblDS = new JLabel("Danh sách chi phí phát sinh", SwingConstants.CENTER);
        lblDS.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDS.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tablePanel.add(lblDS, BorderLayout.NORTH);

        String[] columns = {"STT", "Mã CP", "Tên CP", "Loại", "Giá"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.addMouseListener(this);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.CENTER);
    }

    // Đọc dữ liệu từ DB lên bảng
    private void loadDataToTable() {
        model.setRowCount(0);
        List<ChiPhiPhatSinh> list = dao.getAllChiPhiPhatSinh();
        int stt = 1;
        for (ChiPhiPhatSinh cp : list) {
            model.addRow(new Object[]{
                stt++, cp.getMaChiPhiPhatSinh(), cp.getTenChiPhiPhatSinh(),
                cp.getLoaiChiPhiPhatSinh(), cp.getGia()
            });
        }
    }

    private ChiPhiPhatSinh getChiPhiFromForm() {
        String ma = txtMaCP.getText().trim();
        String ten = txtTenCP.getText().trim();
        String loai = cboLoai.getSelectedItem().toString();
        double gia = Double.parseDouble(txtGia.getText().trim());
        return new ChiPhiPhatSinh(ma, ten, loai, "", gia);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o == btnThem) {
            try {
                ChiPhiPhatSinh cp = getChiPhiFromForm();
                if (dao.insertChiPhi(cp)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    loadDataToTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi dữ liệu: " + ex.getMessage());
            }
        } else if (o == btnSua) {
            try {
                ChiPhiPhatSinh cp = getChiPhiFromForm();
                if (dao.updateChiPhi(cp)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                    loadDataToTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy mã để cập nhật!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            }
//        } else if (o == btnTim) {
//            String ma = txtMaCP.getText().trim();
//            if (ma.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã cần tìm!");
//                return;
//            }
//            ChiPhiPhatSinh cp = dao.getChiPhiTheoMa(ma);
//            if (cp != null) {
//                txtTenCP.setText(cp.getTenChiPhiPhatSinh());
//                txtGia.setText(String.valueOf(cp.getGia()));
//                cboLoai.setSelectedItem(cp.getLoaiChiPhiPhatSinh());
//            } else {
//                JOptionPane.showMessageDialog(this, "Không tìm thấy mã: " + ma);
//            }
//        }
        } else if (o == btnTim) {
                String ma = txtMaCP.getText().trim();
                if (ma.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập mã cần tìm!");
                    return;
                }

                ChiPhiPhatSinh cp = dao.getChiPhiTheoMa(ma);
                if (cp != null) {
                    // Hiển thị thông tin ra form
                    txtTenCP.setText(cp.getTenChiPhiPhatSinh());
                    txtGia.setText(String.valueOf(cp.getGia()));
                    cboLoai.setSelectedItem(cp.getLoaiChiPhiPhatSinh());

                    // Bôi đen hàng trong bảng
                    boolean found = false;
                    for (int i = 0; i < model.getRowCount(); i++) {
                        String maTable = model.getValueAt(i, 1).toString();
                        if (maTable.equalsIgnoreCase(ma)) {
                            table.setRowSelectionInterval(i, i);
                            table.scrollRectToVisible(table.getCellRect(i, 0, true)); // Cuộn đến hàng đó
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy mã trong bảng (dù có trong DB).");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy mã: " + ma);
                }
            }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        if (row != -1) {
            txtMaCP.setText(model.getValueAt(row, 1).toString());
            txtTenCP.setText(model.getValueAt(row, 2).toString());
            cboLoai.setSelectedItem(model.getValueAt(row, 3).toString());
            txtGia.setText(model.getValueAt(row, 4).toString());
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chi Phí Phát Sinh");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.add(new ChiPhiPhatSinh_GUI());
            frame.setVisible(true);
        });
    }
}
