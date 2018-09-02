package ui;


import model.*;
import util.BaseException;
import util.PetManageSystemUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

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


    List<JMenuItem> itemList = new ArrayList<JMenuItem>();


    public void addListener(java.util.List<JMenuItem> list){
        for(JMenuItem j : list){
            j.addActionListener(this);
        }
    }

    //�鿴ԤԼ
    private Object tblAppointmentTitles[] = BeanAppointment.tableTitles;
    private Object tblAppointmentData[][];
    private List<BeanAppointment> allAppointment = null;
    DefaultTableModel tabAppointmentModel=new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            if (column == 1) {
                return true;
            } else {
                return false;
            }
        }
    };
    private JTable dataTableAppointment = new JTable(tabAppointmentModel);

    private void reloadAppointmentTable(){
        try {
            allAppointment = PetManageSystemUtil.appointmentController.loadAll();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "????",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblAppointmentData =  new Object[allAppointment.size()][tblAppointmentTitles.length];
        for(int i=0;i<allAppointment.size();i++){
            for(int j=0;j<tblAppointmentTitles.length;j++){
                if(j==1){
                    int id = Integer.parseInt(allAppointment.get(i).getCell(1)) ;
                    BeanMyUser tmpUser = PetManageSystemUtil.userController.findUserById(id);
                    tblAppointmentData[i][j] = tmpUser.getUserName();
                }else if(j==2){
                    int id = Integer.parseInt(allAppointment.get(i).getCell(2)) ;
                    BeanPet tmpPet = PetManageSystemUtil.petController.findPetById(id);
                    tblAppointmentData[i][j] = tmpPet.getPetNikename();
                }else if(j==3){
                    int id = Integer.parseInt(allAppointment.get(i).getCell(3)) ;
                    BeanService tmpService = PetManageSystemUtil.serviceController.findServiceById(id);
                    tblAppointmentData[i][j] = tmpService.getServName();
                }else
                    tblAppointmentData[i][j]=allAppointment.get(i).getCell(j);
            }
        }
        tabAppointmentModel.setDataVector(tblAppointmentData,tblAppointmentTitles);
        this.dataTableAppointment.validate();
        this.dataTableAppointment.repaint();
    }
    //�鿴ԤԼ

    //ɾ��ԤԼ
    private BeanAppointment curAppointment = null;

    //ɾ��ԤԼ

    // �鿴���
    private Object tblCategoryTitles[] = BeanCategory.tableTitles;
    private Object tblCategoryData[][];
    private List<BeanCategory> allCategory = null;
    DefaultTableModel tabCategoryModel=new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            if (column == 1) {
                return true;
            } else {
                return false;
            }
        }
    };
    private JTable dataTableCategory = new JTable(tabCategoryModel);

    private void reloadCategoryTable(){
        try {
            allCategory = PetManageSystemUtil.categoryController.loadAll();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "????",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblCategoryData =  new Object[allCategory.size()][tblCategoryTitles.length];
        for(int i=0;i<allCategory.size();i++){
            for(int j=0;j<tblCategoryTitles.length;j++)
                tblCategoryData[i][j]=allCategory.get(i).getCell(j);
        }
        tabCategoryModel.setDataVector(tblCategoryData,tblCategoryTitles);
        this.dataTableCategory.validate();
        this.dataTableCategory.repaint();
    }
    //�鿴���



    public FrmMain(){

        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("�������ϵͳ");
        dlgLogin=new FrmLogin(this,"��½",true);
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
            this.reloadAppointmentTable();
            this.getContentPane().add(new JScrollPane(this.dataTableAppointment), BorderLayout.WEST);
            this.setVisible(true);
        } else if(e.getSource() == add_app){
            FrmAddAppointment dlg = new FrmAddAppointment(this,"���ԤԼ",true);
            dlg.setVisible(true);
            this.reloadAppointmentTable();
        } else if (e.getSource() == del_app){
            int i = this.dataTableAppointment.getSelectedRow();
            if(i<0){
                JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�����Ķ���", "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
            int id = Integer.parseInt((String) tblAppointmentData[i][0]);
            int option = JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫɾ��ԤԼ"+id, "ȡ��", JOptionPane.YES_NO_OPTION);
            if(option == 0 ){
                PetManageSystemUtil.appointmentController.delAppointment(id);
                this.reloadAppointmentTable();
            } else
                return;
        } else if(e.getSource() == mod_app){
            int i = this.dataTableAppointment.getSelectedRow();
            if(i<0){
                JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�����Ķ���", "����",JOptionPane.ERROR_MESSAGE);
                return;
            }
            int id = Integer.parseInt((String) tblAppointmentData[i][0]);
            FrmModAppointment dlg = new FrmModAppointment(this,"�޸�ԤԼ",true, id);
            dlg.setVisible(true);
            this.reloadAppointmentTable();
        } else if (e.getSource() == view_cate){
            this.getContentPane().removeAll();
            this.reloadCategoryTable();
            this.getContentPane().add(new JScrollPane(this.dataTableCategory), BorderLayout.CENTER);
            this.setVisible(true);
        }
    }

}
