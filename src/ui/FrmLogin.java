package ui;

import util.BaseException;
import util.PetManageSystemUtil;
import model.BeanOperator;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;


public class FrmLogin extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnLogin = new JButton("µÇÂ¼");
    private JButton btnCancel = new JButton("ÍË³ö");
    private JButton btnRegister = new JButton("×¢²á");
    java.net.URL base = this.getClass().getResource("");


    private JLabel labelUser = new JLabel("ÓÃ»§Ãû");
    private JLabel labelPwd = new JLabel("ÃÜ   Âë");
    private JTextField edtUserId = new JTextField(21);
    private JPasswordField edtPwd = new JPasswordField(21);


    public FrmLogin(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnRegister);
        toolBar.add(btnLogin);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);


        workPane.add(labelUser);
        workPane.add(edtUserId);
        workPane.add(labelPwd);
        workPane.add(edtPwd);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(330, 140);
        // ÆÁÄ»¾ÓÖÐÏÔÊ¾
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        btnLogin.addActionListener(this);
        btnCancel.addActionListener(this);
        this.btnRegister.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        System.out.println(base);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnLogin) {
            String userid=this.edtUserId.getText();
            String pwd=new String(this.edtPwd.getPassword());
            try {
                BeanOperator.currentLoginUser = PetManageSystemUtil.operatorController.login(userid,pwd);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
                return;
            }
//			this.setVisible(false);

        } else if (e.getSource() == this.btnCancel) {
            System.exit(0);
        } else if(e.getSource()==this.btnRegister){
            FrmRegister dlg=new FrmRegister(this,"×¢²á",true);
            double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
            dlg.setLocation((int) (width - this.getWidth()) / 2,
                    (int) (height - this.getHeight()) / 2);
            dlg.setVisible(true);
        }
    }

}