package ui;

import model.BeanOperator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmMain extends JFrame implements ActionListener {

    private FrmLogin dlgLogin=null;

    public FrmMain(){
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("宠物服务系统");
        dlgLogin=new FrmLogin(this,"登陆",true);
        dlgLogin.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
