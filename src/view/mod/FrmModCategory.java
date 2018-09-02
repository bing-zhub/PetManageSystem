package view.mod;

import model.*;
import util.BaseException;
import util.PetManageSystemUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class FrmModCategory extends JDialog implements ActionListener{

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private Button btnOk = new Button("ȷ��");
    private Button btnCancel = new Button("ȡ��");
    private JLabel labelName = new JLabel("����:");
    private JTextField editName = new JTextField();
    private JLabel labelDetail = new JLabel("��ϸ:");
    private JTextField editDetail = new JTextField();
    BeanCategory objCategory = null;

    public FrmModCategory(JFrame f, String s, boolean b, int id) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        objCategory = PetManageSystemUtil.categoryController.findCategoryById(id);

        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(300, 135);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

        this.validate();
        this.btnOk.addActionListener(this);
        this.btnCancel.addActionListener(this);

        editDetail.setColumns(20);
        editName.setColumns(20);

        workPane.add(labelName);
        workPane.add(editName);
        workPane.add(labelDetail);
        workPane.add(editDetail);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnCancel) {
            this.setVisible(false);
            return;
        }else if(e.getSource() == this.btnOk){
            objCategory.setCateDetail(editDetail.getText());
            objCategory.setCateName(editName.getText());
            PetManageSystemUtil.update(objCategory);
            this.setVisible(false);
            return;
        }
    }
}
