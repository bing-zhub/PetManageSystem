package ui;


import com.sun.deploy.panel.JavaPanel;
import model.BeanOperator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmMain extends JFrame implements ActionListener {


    // menubar
    private JMenuBar jMenuBar = new JMenuBar();
    private JMenu Appointment = new JMenu("ԤԼ����");
    private JMenu Category = new JMenu("������");
    private JMenu Order = new JMenu("��������");
    private JMenu User = new JMenu("�û�����");
    private JMenu Pet = new JMenu("�������");
    private JMenu Product = new JMenu("��Ʒ����");
    private JMenu Service = new JMenu("�������");
    private JMenu Operator = new JMenu("����Ա����");

    //menu item
    //appiontment
    private JMenuItem view_app = new JMenuItem("�鿴ԤԼ");
    private JMenuItem add_app = new JMenuItem("���ԤԼ");
    private JMenuItem del_app = new JMenuItem("ɾ��ԤԼ");
    private JMenuItem mod_app = new JMenuItem("�޸�ԤԼ");

    //category
    private JMenuItem view_cate = new JMenuItem("�鿴����");
    private JMenuItem add_cate = new JMenuItem("��ӷ���");
    private JMenuItem del_cate = new JMenuItem("ɾ������");
    private JMenuItem mod_cate = new JMenuItem("�޸ķ���");

    //order
    private JMenuItem view_order = new JMenuItem("�鿴����");
    private JMenuItem add_order = new JMenuItem("��Ӷ���");
    private JMenuItem del_order = new JMenuItem("ɾ������");
    private JMenuItem mod_order = new JMenuItem("�޸Ķ���");

    //user
    private JMenuItem view_user = new JMenuItem("�鿴�û�");
    private JMenuItem add_user = new JMenuItem("����û�");
    private JMenuItem del_user = new JMenuItem("ɾ���û�");
    private JMenuItem mod_user = new JMenuItem("�޸��û�");

    //pet
    private JMenuItem view_pet = new JMenuItem("�鿴����");
    private JMenuItem add_pet = new JMenuItem("��ӳ���");
    private JMenuItem del_pet = new JMenuItem("ɾ������");
    private JMenuItem mod_pet = new JMenuItem("�޸ĳ���");

    //product
    private JMenuItem view_prod = new JMenuItem("�鿴��Ʒ");
    private JMenuItem add_prod = new JMenuItem("��Ӳ�Ʒ");
    private JMenuItem del_prod = new JMenuItem("ɾ����Ʒ");
    private JMenuItem mod_prod = new JMenuItem("�޸Ĳ�Ʒ");

    //service
    private JMenuItem view_serv = new JMenuItem("�鿴����");
    private JMenuItem add_serv = new JMenuItem("��ӷ���");
    private JMenuItem del_serv = new JMenuItem("ɾ������");
    private JMenuItem mod_serv = new JMenuItem("�޸ķ���");

    //operator
    private JMenuItem view_oper = new JMenuItem("�鿴����Ա");
    private JMenuItem add_oper = new JMenuItem("��ӹ���Ա");
    private JMenuItem del_oper = new JMenuItem("ɾ������Ա");
    private JMenuItem mod_oper = new JMenuItem("�޸Ĺ���Ա");


    //status bar
    private JPanel statusBar = new JPanel();


    //login and register page
    private FrmLogin dlgLogin=null;

    public FrmMain(){

        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("�������ϵͳ");
        dlgLogin=new FrmLogin(this,"��½",true);
        dlgLogin.setVisible(false);

        this.Appointment.add(add_app);
        this.add_app.addActionListener(this);
        this.Appointment.add(del_app);
        this.del_app.addActionListener(this);
        this.Appointment.add(mod_app);
        this.mod_app.addActionListener(this);
        this.Appointment.add(view_app);
        this.view_app.addActionListener(this);

        this.Category.add(add_cate);
        this.add_cate.addActionListener(this);
        this.Category.add(del_cate);
        this.del_cate.addActionListener(this);
        this.Category.add(mod_cate);
        this.mod_cate.addActionListener(this);
        this.Category.add(view_cate);
        this.view_cate.addActionListener(this);

        this.Order.add(add_order);
        this.add_order.addActionListener(this);
        this.Order.add(del_order);
        this.del_order.addActionListener(this);
        this.Order.add(mod_order);
        this.mod_order.addActionListener(this);
        this.Order.add(view_order);
        this.view_order.addActionListener(this);

        this.User.add(add_user);
        this.add_user.addActionListener(this);
        this.User.add(del_user);
        this.del_user.addActionListener(this);
        this.User.add(mod_user);
        this.mod_user.addActionListener(this);
        this.User.add(view_user);
        this.view_user.addActionListener(this);

        this.Operator.add(add_oper);
        this.add_oper.addActionListener(this);
        this.Operator.add(del_oper);
        this.del_oper.addActionListener(this);
        this.Operator.add(mod_oper);
        this.mod_oper.addActionListener(this);
        this.Operator.add(view_oper);
        this.view_oper.addActionListener(this);

        this.Pet.add(add_pet);
        this.add_pet.addActionListener(this);
        this.Pet.add(del_pet);
        this.del_pet.addActionListener(this);
        this.Pet.add(mod_pet);
        this.mod_pet.addActionListener(this);
        this.Pet.add(view_pet);
        this.view_pet.addActionListener(this);

        this.Product.add(add_prod);
        this.add_prod.addActionListener(this);
        this.Product.add(del_prod);
        this.del_prod.addActionListener(this);
        this.Product.add(mod_prod);
        this.mod_prod.addActionListener(this);
        this.Product.add(view_prod);
        this.view_prod.addActionListener(this);

        this.Service.add(add_serv);
        this.add_serv.addActionListener(this);
        this.Service.add(del_serv);
        this.del_serv.addActionListener(this);
        this.Service.add(mod_serv);
        this.mod_serv.addActionListener(this);
        this.Service.add(view_serv);
        this.view_serv.addActionListener(this);


        jMenuBar.add(Appointment);
        jMenuBar.add(Category);
        jMenuBar.add(Order);
        jMenuBar.add(User);
        jMenuBar.add(Pet);
        jMenuBar.add(Product);
        jMenuBar.add(Service);
        jMenuBar.add(Operator);
        this.setJMenuBar(jMenuBar);

        this.getContentPane().add(new JLabel(new ImageIcon("img/pet_icon.png")));
        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
//        JLabel label=new JLabel("����! "+ BeanOperator.currentLoginUser.getOpName());
        JLabel label=new JLabel("����! test!");
        statusBar.add(label);
        this.getContentPane().add(statusBar,BorderLayout.SOUTH);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view_app){
            this.getContentPane().removeAll();
            this.getContentPane().add(new JLabel("hello!!"));
            this.setVisible(true);
        }
    }

}
