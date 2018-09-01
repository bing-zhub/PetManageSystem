package ui;


import com.sun.deploy.panel.JavaPanel;
import model.BeanOperator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class FrmMain extends JFrame implements ActionListener {


    // menubar
    private JMenuBar jMenuBar = new JMenuBar();
    private JMenu Appointment = new JMenu("预约管理");
    private JMenu Category = new JMenu("类别管理");
    private JMenu Order = new JMenu("订单管理");
    private JMenu User = new JMenu("用户管理");
    private JMenu Pet = new JMenu("宠物管理");
    private JMenu Product = new JMenu("产品管理");
    private JMenu Service = new JMenu("服务管理");
    private JMenu Operator = new JMenu("操作员管理");

    //menu item
    //appiontment
    private JMenuItem view_app = new JMenuItem("查看预约");
    private JMenuItem add_app = new JMenuItem("添加预约");
    private JMenuItem del_app = new JMenuItem("删除预约");
    private JMenuItem mod_app = new JMenuItem("修改预约");

    //category
    private JMenuItem view_cate = new JMenuItem("查看分类");
    private JMenuItem add_cate = new JMenuItem("添加分类");
    private JMenuItem del_cate = new JMenuItem("删除分类");
    private JMenuItem mod_cate = new JMenuItem("修改分类");

    //order
    private JMenuItem view_order = new JMenuItem("查看订单");
    private JMenuItem add_order = new JMenuItem("添加订单");
    private JMenuItem del_order = new JMenuItem("删除订单");
    private JMenuItem mod_order = new JMenuItem("修改订单");

    //user
    private JMenuItem view_user = new JMenuItem("查看用户");
    private JMenuItem add_user = new JMenuItem("添加用户");
    private JMenuItem del_user = new JMenuItem("删除用户");
    private JMenuItem mod_user = new JMenuItem("修改用户");

    //pet
    private JMenuItem view_pet = new JMenuItem("查看宠物");
    private JMenuItem add_pet = new JMenuItem("添加宠物");
    private JMenuItem del_pet = new JMenuItem("删除宠物");
    private JMenuItem mod_pet = new JMenuItem("修改宠物");

    //product
    private JMenuItem view_prod = new JMenuItem("查看产品");
    private JMenuItem add_prod = new JMenuItem("添加产品");
    private JMenuItem del_prod = new JMenuItem("删除产品");
    private JMenuItem mod_prod = new JMenuItem("修改产品");

    //service
    private JMenuItem view_serv = new JMenuItem("查看服务");
    private JMenuItem add_serv = new JMenuItem("添加服务");
    private JMenuItem del_serv = new JMenuItem("删除服务");
    private JMenuItem mod_serv = new JMenuItem("修改服务");

    //operator
    private JMenuItem view_oper = new JMenuItem("查看管理员");
    private JMenuItem add_oper = new JMenuItem("添加管理员");
    private JMenuItem del_oper = new JMenuItem("删除管理员");
    private JMenuItem mod_oper = new JMenuItem("修改管理员");


    //status bar
    private JPanel statusBar = new JPanel();


    //login and register page
    private FrmLogin dlgLogin=null;

    List<JMenuItem> itemList = new ArrayList<JMenuItem>();


    public void addListener(java.util.List<JMenuItem> list){
        for(JMenuItem j : list){
            j.addActionListener(this);
        }
    }

    public FrmMain(){

        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("宠物服务系统");
        dlgLogin=new FrmLogin(this,"登陆",true);
        dlgLogin.setVisible(false);

        this.Appointment.add(add_app);
        this.Appointment.add(del_app);
        this.Appointment.add(mod_app);
        this.Appointment.add(view_app);

        itemList.add(add_app);
        itemList.add(del_app);
        itemList.add(mod_app);
        itemList.add(view_app);


        this.Category.add(add_cate);
        this.Category.add(del_cate);
        this.Category.add(mod_cate);
        this.Category.add(view_cate);

        itemList.add(add_cate);
        itemList.add(del_cate);
        itemList.add(mod_cate);
        itemList.add(view_cate);


        this.Order.add(add_order);
        this.Order.add(del_order);
        this.Order.add(mod_order);
        this.Order.add(view_order);

        itemList.add(add_order);
        itemList.add(del_order);
        itemList.add(mod_order);
        itemList.add(view_order);

        this.User.add(add_user);
        this.User.add(del_user);
        this.User.add(mod_user);
        this.User.add(view_user);

        itemList.add(add_user);
        itemList.add(del_user);
        itemList.add(mod_user);
        itemList.add(view_user);

        this.Operator.add(add_oper);
        this.Operator.add(del_oper);
        this.Operator.add(mod_oper);
        this.Operator.add(view_oper);

        itemList.add(add_oper);
        itemList.add(del_oper);
        itemList.add(mod_oper);
        itemList.add(view_oper);

        this.Pet.add(add_pet);
        this.Pet.add(del_pet);
        this.Pet.add(mod_pet);
        this.Pet.add(view_pet);

        itemList.add(add_pet);
        itemList.add(del_pet);
        itemList.add(mod_pet);
        itemList.add(view_pet);

        this.Product.add(add_prod);
        this.Product.add(del_prod);
        this.Product.add(mod_prod);
        this.Product.add(view_prod);

        itemList.add(add_prod);
        itemList.add(del_prod);
        itemList.add(mod_prod);
        itemList.add(view_prod);

        this.Service.add(add_serv);
        this.Service.add(del_serv);
        this.Service.add(mod_serv);
        this.Service.add(view_serv);

        itemList.add(add_serv);
        itemList.add(del_serv);
        itemList.add(mod_serv);
        itemList.add(view_serv);

        addListener(itemList);

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
//        JLabel label=new JLabel("您好! "+ BeanOperator.currentLoginUser.getOpName());
        JLabel label=new JLabel("您好! test!");
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
