package view.add;

import model.*;
import util.BaseException;
import util.PetManageSystemUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.List;

public class FrmAddCategory extends JDialog implements ActionListener{

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private Button btnOk = new Button("确认");
    private Button btnCancel = new Button("取消");
    private JLabel labelName = new JLabel("名称:");
    private TextField editName = new TextField();
    private JLabel labelDetail = new JLabel("详细:");
    private JTextField editDetail = new JTextField();


    public FrmAddCategory(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        editDetail.setColumns(20);
        editName.setColumns(20);

        workPane.add(labelName);
        workPane.add(editName);
        workPane.add(labelDetail);
        workPane.add(editDetail);

        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(600, 100);

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
            try{
                BeanCategory cate = new BeanCategory();
                cate.setCateName(editName.getText());
                cate.setCateDetail(editDetail.getText());
                PetManageSystemUtil.categoryController.add(cate);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

    }
}
