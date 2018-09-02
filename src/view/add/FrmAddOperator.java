package view.add;

import model.BeanCategory;
import model.BeanOperator;
import util.BaseException;
import util.PetManageSystemUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAddOperator extends JDialog implements ActionListener{

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private Button btnOk = new Button("确认");
    private Button btnCancel = new Button("取消");
    private JTextField editName = new JTextField();
    private JLabel labelName = new JLabel("名称:");
    private JLabel labelPassword = new JLabel("密码:");
    private JPasswordField editPassword = new JPasswordField();
    private JLabel labelConfirmPassword = new JLabel("确认密码:");
    private JPasswordField editConfirmPassword = new JPasswordField();


    public FrmAddOperator(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        editConfirmPassword.setColumns(20);
        editPassword.setColumns(20);
        editName.setColumns(20);

        workPane.add(labelName);
        workPane.add(editName);
        workPane.add(labelPassword);
        workPane.add(editPassword);
        workPane.add(labelConfirmPassword);
        workPane.add(editConfirmPassword);

        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(270, 225);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

        this.validate();
        this.btnOk.addActionListener(this);
        this.btnCancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnCancel) {
            this.setVisible(false);
            return;
        }
        else if(e.getSource()==this.btnOk){
            if(editPassword.getText().length()<=6){
                JOptionPane.showMessageDialog(null,"密码长度低于6","错误",JOptionPane.ERROR_MESSAGE);
                return;
            } else if(!editPassword.getText().equals(editConfirmPassword.getText())){
                JOptionPane.showMessageDialog(null,"两次密码输入不一致","错误",JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                try{
                    BeanOperator operator = new BeanOperator();
                    operator.setOpName(editName.getText());
                    operator.setOpPwd(editPassword.getPassword().toString());
                    operator.setOpLevel(1);
                    PetManageSystemUtil.operatorController.addOperator(operator);
                    this.setVisible(false);
                } catch (BaseException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
    }
}
