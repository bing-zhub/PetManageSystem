package view.mod;

import model.BeanOperator;
import util.PetManageSystemUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmModOperator extends JDialog implements ActionListener{

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private Button btnOk = new Button("确认");
    private Button btnCancel = new Button("取消");
    private JLabel labelName = new JLabel("名称:");
    private JTextField editName = new JTextField();
    private JLabel labelPassword = new JLabel("密码:");
    private JPasswordField editPassword = new JPasswordField();
    private JLabel labelConfirmPassword = new JLabel("确认密码:");
    private JPasswordField editConfirmPassword = new JPasswordField();

    BeanOperator objOperator = null;

    public FrmModOperator(JFrame f, String s, boolean b, int id) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        objOperator = PetManageSystemUtil.operatorController.findOperatorById(id);

        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(270, 225);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

        this.validate();
        this.btnOk.addActionListener(this);
        this.btnCancel.addActionListener(this);

        editPassword.setColumns(20);
        editConfirmPassword.setColumns(20);
        editName.setColumns(20);

        workPane.add(labelName);
        workPane.add(editName);
        workPane.add(labelPassword);
        workPane.add(editPassword);
        workPane.add(labelConfirmPassword);
        workPane.add(editConfirmPassword);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnCancel) {
            this.setVisible(false);
            return;
        }else if(e.getSource() == this.btnOk){
            if(!editConfirmPassword.getText().equals(editPassword.getText())){
                JOptionPane.showMessageDialog(null,"两次密码输入不一致","错误",JOptionPane.ERROR_MESSAGE);
            }
            objOperator.setOpPwd(editPassword.getText());
            objOperator.setOpName(editName.getText());
            PetManageSystemUtil.update(objOperator);
            this.setVisible(false);
            return;
        }
    }
}
