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
        this.setTitle("�������ϵͳ");
        dlgLogin=new FrmLogin(this,"��½",true);
        dlgLogin.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
