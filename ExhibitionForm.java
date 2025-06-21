package exhibition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExhibitionForm extends JFrame {
    private JTextField txtRegID, txtName, txtFaculty, txtProjectTitle, txtContact, txtEmail;
    private JLabel lblImage;
    private JButton btnBrowse, btnRegister, btnSearch, btnUpdate, btnDelete, btnClear, btnExit;

    public ExhibitionForm() {
        setTitle("Innovation & Tech Exhibition Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = { "Registration ID:", "Student Name:", "Faculty:", "Project Title:",
                            "Contact Number:", "Email Address:" };
        JTextField[] fields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1; 
            fields[i] = new JTextField(20);
            add(fields[i], gbc);
        }
        txtRegID = fields[0];
        txtName = fields[1];
        txtFaculty = fields[2];
        txtProjectTitle = fields[3];
        txtContact = fields[4];
        txtEmail = fields[5];

        gbc.gridx = 0; gbc.gridy = labels.length;
        add(new JLabel("Project Image:"), gbc);

        gbc.gridx = 1;
        btnBrowse = new JButton("Browse...");
        add(btnBrowse, gbc);

        gbc.gridx = 2;
        lblImage = new JLabel();
        lblImage.setPreferredSize(new Dimension(100, 100));
        lblImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(lblImage, gbc);

        btnBrowse.addActionListener(e -> onBrowseImage());

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        btnRegister = new JButton("Register");
        btnSearch   = new JButton("Search");
        btnUpdate   = new JButton("Update");
        btnDelete   = new JButton("Delete");
        btnClear    = new JButton("Clear");
        btnExit     = new JButton("Exit");

        panelButtons.add(btnRegister);
        panelButtons.add(btnSearch);
        panelButtons.add(btnUpdate);
        panelButtons.add(btnDelete);
        panelButtons.add(btnClear);
        panelButtons.add(btnExit);

        gbc.gridx = 0; gbc.gridy = labels.length + 1;
        gbc.gridwidth = 3;
        add(panelButtons, gbc);

        btnRegister.addActionListener(e -> JOptionPane.showMessageDialog(this, "Register clicked"));
        btnSearch.addActionListener(e -> JOptionPane.showMessageDialog(this, "Search clicked"));
        btnUpdate.addActionListener(e -> JOptionPane.showMessageDialog(this, "Update clicked"));
        btnDelete.addActionListener(e -> JOptionPane.showMessageDialog(this, "Delete clicked"));
        btnClear.addActionListener(e -> clearForm());
        btnExit.addActionListener(e -> System.exit(0));

        DBConnector.connect();

        pack();
        setLocationRelativeTo(null);
    }

    private void onBrowseImage() {
        JFileChooser chooser = new JFileChooser();
        int ch = chooser.showOpenDialog(this);
        if (ch == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            ImageIcon ico = new ImageIcon(new ImageIcon(path).getImage()
                                 .getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            lblImage.setIcon(ico);
            lblImage.setToolTipText(path);
        }
    }

    private void clearForm() {
        txtRegID.setText("");
        txtName.setText("");
        txtFaculty.setText("");
        txtProjectTitle.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        lblImage.setIcon(null);
        lblImage.setToolTipText(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ExhibitionForm().setVisible(true);
        });
    }
}