package view;


import model.*;
import util.BaseException;
import util.PetManageSystemUtil;
import view.add.FrmAddAppointment;
import view.add.FrmAddCategory;
import view.add.FrmAddOperator;
import view.add.FrmAddOrder;
import view.mod.FrmModAppointment;
import view.mod.FrmModCategory;
import view.mod.FrmModOperator;
import view.mod.FrmModOrder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
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


    //category
    private JMenuItem view_cate = new JMenuItem("查看分类");
    private JMenuItem add_cate = new JMenuItem("添加分类");

    //order
    private JMenuItem view_order = new JMenuItem("查看订单");
    private JMenuItem add_order = new JMenuItem("添加订单");


    //user
    private JMenuItem view_user = new JMenuItem("查看用户");
    private JMenuItem add_user = new JMenuItem("添加用户");


    //pet
    private JMenuItem view_pet = new JMenuItem("查看宠物");
    private JMenuItem add_pet = new JMenuItem("添加宠物");


    //product
    private JMenuItem view_prod = new JMenuItem("查看产品");
    private JMenuItem add_prod = new JMenuItem("添加产品");


    //service
    private JMenuItem view_serv = new JMenuItem("查看服务");
    private JMenuItem add_serv = new JMenuItem("添加服务");


    //operator
    private JMenuItem view_oper = new JMenuItem("查看管理员");
    private JMenuItem add_oper = new JMenuItem("添加管理员");


    //popup menu
    private JPopupMenu app_popup =  null;
    private JPopupMenu vate_popup = null;


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

    //查看预约
    private Object tblAppointmentTitles[] = BeanAppointment.tableTitles;
    private Object tblAppointmentData[][];
    private List<BeanAppointment> allAppointment = null;
    DefaultTableModel tabAppointmentModel=new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
             return false;
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
    //查看预约



    // 查看类别
    private Object tblCategoryTitles[] = BeanCategory.tableTitles;
    private Object tblCategoryData[][];
    private List<BeanCategory> allCategory = null;
    DefaultTableModel tabCategoryModel=new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
                return false;
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
    //查看类别

    //查看订单
    private Object tblOrderTitles[] = BeanMyOrder.tableTitles;
    private Object tblOrderData[][];
    private List<BeanMyOrder> allOrder = null;
    DefaultTableModel tabOrderModel=new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
                return false;
        }
    };
    private JTable dataTableOrder = new JTable(tabOrderModel);

    private void reloadOrderTable(){

        allOrder = PetManageSystemUtil.orderController.loadAll();
        tblOrderData =  new Object[allOrder.size()][tblOrderTitles.length];
        for(int i=0;i<allOrder.size();i++){
            for(int j=0;j<tblOrderTitles.length;j++)
                tblOrderData[i][j]=allOrder.get(i).getCell(j);
        }
        tabOrderModel.setDataVector(tblOrderData,tblOrderTitles);
        this.dataTableOrder.validate();
        this.dataTableOrder.repaint();
    }
    //查看订单

    //查看用户
    private Object tblUserTitles[] = BeanMyUser.tableTitles;
    private Object tblUserData[][];
    private List<BeanMyUser> allUser = null;
    DefaultTableModel tabUserModel=new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
                return false;
        }
    };
    private JTable dataTableUser = new JTable(tabUserModel);

    private void reloadUserTable(){

        allUser = PetManageSystemUtil.userController.loadAll();
        tblUserData =  new Object[allUser.size()][tblUserTitles.length];
        for(int i=0;i<allUser.size();i++){
            for(int j=0;j<tblUserTitles.length;j++)
                tblUserData[i][j]=allUser.get(i).getCell(j);
        }
        tabUserModel.setDataVector(tblUserData,tblUserTitles);
        this.dataTableUser.validate();
        this.dataTableUser.repaint();
    }
    //查看用户

    //查看操作人员
    private Object tblOperatorTitles[] = BeanOperator.tableTitles;
    private Object tblOperatorData[][];
    private List<BeanOperator> allOperator = null;
    DefaultTableModel tabOperatorModel=new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
                return false;
        }
    };
    private JTable dataTableOperator = new JTable(tabOperatorModel);

    private void reloadOperatorTable(){

        allOperator = PetManageSystemUtil.operatorController.loadAll();
        tblOperatorData =  new Object[allOperator.size()][tblOperatorTitles.length];
        for(int i=0;i<allOperator.size();i++){
            for(int j=0;j<tblOperatorTitles.length;j++)
                tblOperatorData[i][j]=allOperator.get(i).getCell(j);
        }
        tabOperatorModel.setDataVector(tblOperatorData,tblOperatorTitles);
        this.dataTableOperator.validate();
        this.dataTableOperator.repaint();
    }
    //查看操作人员

    //查看宠物
    private Object tblPetTitles[] = BeanPet.tableTitles;
    private Object tblPetData[][];
    private List<BeanPet> allPet = null;
    DefaultTableModel tabPetModel=new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
                return false;
        }
    };
    private JTable dataTablePet = new JTable(tabPetModel);

    private void reloadPetTable(){

        allPet = PetManageSystemUtil.petController.loadAll();
        tblPetData =  new Object[allPet.size()][tblPetTitles.length];
        for(int i=0;i<allPet.size();i++){
            for(int j=0;j<tblPetTitles.length;j++)
                tblPetData[i][j]=allPet.get(i).getCell(j);
        }
        tabPetModel.setDataVector(tblPetData,tblPetTitles);
        this.dataTablePet.validate();
        this.dataTablePet.repaint();
    }
    //查看宠物

    //查看产品
    private Object tblProductTitles[] = BeanProduct.tableTitles;
    private Object tblProductData[][];
    private List<BeanProduct> allProduct = null;
    DefaultTableModel tabProductModel=new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
                return false;
        }
    };
    private JTable dataTableProduct = new JTable(tabProductModel);

    private void reloadProductTable(){

        allProduct = PetManageSystemUtil.productController.loadAll();
        tblProductData =  new Object[allProduct.size()][tblProductTitles.length];
        for(int i=0;i<allProduct.size();i++){
            for(int j=0;j<tblProductTitles.length;j++)
                tblProductData[i][j]=allProduct.get(i).getCell(j);
        }
        tabProductModel.setDataVector(tblProductData,tblProductTitles);
        this.dataTableProduct.validate();
        this.dataTableProduct.repaint();
    }
    //查看产品

    //查看服务
    private Object tblServiceTitles[] = BeanService.tableTitles;
    private Object tblServiceData[][];
    private List<BeanService> allService = null;
    DefaultTableModel tabServiceModel=new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
                return false;
        }
    };
    private JTable dataTableService = new JTable(tabServiceModel);

    private void reloadServiceTable(){

        allService = PetManageSystemUtil.serviceController.loadAll();
        tblServiceData =  new Object[allService.size()][tblServiceTitles.length];
        for(int i=0;i<allService.size();i++){
            for(int j=0;j<tblServiceTitles.length;j++)
                tblServiceData[i][j]=allService.get(i).getCell(j);
        }
        tabServiceModel.setDataVector(tblServiceData,tblServiceTitles);
        this.dataTableService.validate();
        this.dataTableService.repaint();
    }
    //查看服务



    public FrmMain(){

        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("宠物服务系统");
        dlgLogin=new FrmLogin(this,"登陆",true);
        dlgLogin.setVisible(false);

        this.Appointment.add(add_app);
        this.Appointment.add(view_app);

        itemList.add(add_app);
        itemList.add(view_app);


        this.Category.add(add_cate);
        this.Category.add(view_cate);

        itemList.add(add_cate);
        itemList.add(view_cate);


        this.Order.add(add_order);
        this.Order.add(view_order);

        itemList.add(add_order);
        itemList.add(view_order);

        this.User.add(add_user);
        this.User.add(view_user);

        itemList.add(add_user);
        itemList.add(view_user);

        this.Operator.add(add_oper);
        this.Operator.add(view_oper);

        itemList.add(add_oper);
        itemList.add(view_oper);

        this.Pet.add(add_pet);
        this.Pet.add(view_pet);

        itemList.add(add_pet);
        itemList.add(view_pet);

        this.Product.add(add_prod);
        this.Product.add(view_prod);

        itemList.add(add_prod);
        itemList.add(view_prod);

        this.Service.add(add_serv);
        this.Service.add(view_serv);

        itemList.add(add_serv);
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

        //预约右键菜单
        dataTableAppointment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    //通过点击位置找到点击为表格中的行
                    int focusedRowIndex = dataTableAppointment.rowAtPoint(e.getPoint());
                    if (focusedRowIndex == -1) {
                        JOptionPane.showMessageDialog(null, "未选中任何对象", "错误",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int id = Integer.parseInt((String) tblAppointmentData[focusedRowIndex][0]);

                    //将表格所选项设为当前右键点击的行
                    dataTableAppointment.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
                    //弹出菜单
                    JMenuItem finishItem = new JMenuItem();
                    finishItem.setText("  完成预约  ");
                    finishItem.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            BeanAppointment beanAppointment = PetManageSystemUtil.appointmentController.findAppointmentById(id);
                            PetManageSystemUtil.appointmentController.finishAppointment(beanAppointment);
                            reloadAppointmentTable();
                        }
                    });

                    JMenuItem delMenItem = new JMenuItem();
                    delMenItem.setText("  删除预约  ");
                    delMenItem.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            int option = JOptionPane.showConfirmDialog(null, "是否要删除预约"+id, "取消", JOptionPane.YES_NO_OPTION);
                            if(option == 0 ){
                                PetManageSystemUtil.appointmentController.delAppointment(id);
                                reloadAppointmentTable();
                            } else
                                return;
                        }
                    });

                    JMenuItem modMenItem = new JMenuItem();
                    modMenItem.setText("  修改预约  ");
                    modMenItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            FrmModAppointment dlg = new FrmModAppointment(FrmMain.this,"修改预约",true, id);
                            dlg.setVisible(true);
                            reloadAppointmentTable();
                        }
                    });
                    app_popup = new JPopupMenu();
                    app_popup.add(finishItem);
                    app_popup.add(delMenItem);
                    app_popup.add(modMenItem);
                    app_popup.show(dataTableAppointment, e.getX(), e.getY());
                }
            }
        });

        //分类右键菜单
        dataTableCategory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    //通过点击位置找到点击为表格中的行
                    int focusedRowIndex = dataTableCategory.rowAtPoint(e.getPoint());
                    if (focusedRowIndex == -1) {
                        JOptionPane.showMessageDialog(null, "未选中任何对象", "错误",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int id = Integer.parseInt((String) tblCategoryData[focusedRowIndex][0]);

                    //将表格所选项设为当前右键点击的行
                    dataTableCategory.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
                    //弹出菜单

                    JMenuItem delMenItem = new JMenuItem();
                    delMenItem.setText("  删除分类  ");
                    delMenItem.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            int option = JOptionPane.showConfirmDialog(null, "是否要删除分类"+id+"?", "取消", JOptionPane.YES_NO_OPTION);
                            if(option == 0 ){
                                try{
                                    PetManageSystemUtil.categoryController.delCategory(id);
                                }catch (Exception e){
                                    JOptionPane.showMessageDialog(null, "目前不可删除, 有产品或服务属于此类别", "错误",JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                reloadCategoryTable();
                            } else
                                return;
                        }
                    });

                    JMenuItem modMenItem = new JMenuItem();
                    modMenItem.setText("  修改分类  ");
                    modMenItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            FrmModCategory dlg = new FrmModCategory(FrmMain.this,"修改分类",true, id);
                            dlg.setVisible(true);
                            reloadCategoryTable();
                        }
                    });

                    app_popup = new JPopupMenu();
                    app_popup.add(delMenItem);
                    app_popup.add(modMenItem);
                    app_popup.show(dataTableCategory, e.getX(), e.getY());
                }
            }
        });

        //操作员右键菜单
        dataTableOperator.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    //通过点击位置找到点击为表格中的行
                    int focusedRowIndex = dataTableOperator.rowAtPoint(e.getPoint());
                    if (focusedRowIndex == -1) {
                        JOptionPane.showMessageDialog(null, "未选中任何对象", "错误",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int id = Integer.parseInt((String) tblOperatorData[focusedRowIndex][0]);

                    //将表格所选项设为当前右键点击的行
                    dataTableOperator.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
                    //弹出菜单

                    JMenuItem delMenItem = new JMenuItem();
                    delMenItem.setText("  删除管理员  ");
                    delMenItem.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            int option = JOptionPane.showConfirmDialog(null, "是否要删除管理员"+id+"?", "取消", JOptionPane.YES_NO_OPTION);
                            if(option == 0 ){
                                try{
//                                    if(BeanOperator.currentLoginUser.getOpId() == id){
//                                        JOptionPane.showMessageDialog(null, "目前不可删除, 此管理员正处于活跃状态", "错误",JOptionPane.ERROR_MESSAGE);
//                                        return;
//                                    }else{
                                        PetManageSystemUtil.operatorController.delOperator(id);
//                                    }
                                }catch (Exception e){
                                    JOptionPane.showMessageDialog(null, "目前不可删除, 此管理员正处于活跃状态", "错误",JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                reloadOperatorTable();
                            } else
                                return;
                        }
                    });

                    JMenuItem modMenItem = new JMenuItem();
                    modMenItem.setText("  修改管理员  ");
                    modMenItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            FrmModOperator dlg = new FrmModOperator(FrmMain.this,"修改管理员",true, id);
                            dlg.setVisible(true);
                            reloadCategoryTable();
                            view_oper.doClick();
                        }
                    });

                    JMenuItem levUpMenItem = new JMenuItem();
                    levUpMenItem.setText("  升级  ");
                    levUpMenItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try{
                                PetManageSystemUtil.operatorController.levOperator(PetManageSystemUtil.operatorController.findOperatorById(id),1);
                                reloadOperatorTable();
                            }catch (BaseException e1){
                                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    });

                    JMenuItem levDoMenItem = new JMenuItem();
                    levDoMenItem.setText("  降级  ");
                    levDoMenItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try{
                                PetManageSystemUtil.operatorController.levOperator(PetManageSystemUtil.operatorController.findOperatorById(id),-1);
                                reloadOperatorTable();
                            }catch (BaseException e1){
                                JOptionPane.showMessageDialog(null, "最低为1级", "错误",JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    });

                    app_popup = new JPopupMenu();
                    app_popup.add(delMenItem);
                    app_popup.add(modMenItem);
                    app_popup.add(levDoMenItem);
                    app_popup.add(levUpMenItem);
                    app_popup.show(dataTableOperator, e.getX(), e.getY());
                }
            }
        });

        //订单右键菜单
        dataTableOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    //通过点击位置找到点击为表格中的行
                    int focusedRowIndex = dataTableOrder.rowAtPoint(e.getPoint());
                    if (focusedRowIndex == -1) {
                        JOptionPane.showMessageDialog(null, "未选中任何对象", "错误",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int id = Integer.parseInt((String) tblOrderData[focusedRowIndex][0]);

                    //将表格所选项设为当前右键点击的行
                    dataTableOrder.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
                    //弹出菜单

                    JMenuItem delMenItem = new JMenuItem();
                    delMenItem.setText("  删除订单  ");
                    delMenItem.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            int option = JOptionPane.showConfirmDialog(null, "是否要删除订单"+id+"?", "取消", JOptionPane.YES_NO_OPTION);
                            if(option == 0 ){
                                try{
                                    PetManageSystemUtil.orderController.delOrder(id);
                                }catch (Exception e){
                                    JOptionPane.showMessageDialog(null, "目前不可删除", "错误",JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                reloadOrderTable();
                            } else
                                return;
                        }
                    });

                    JMenuItem modMenItem = new JMenuItem();
                    modMenItem.setText("  修改订单  ");
                    modMenItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            FrmModOrder dlg = new FrmModOrder(FrmMain.this,"修改订单",true, id);
                            dlg.setVisible(true);
                            reloadOrderTable();
                        }
                    });

                    app_popup = new JPopupMenu();
                    app_popup.add(delMenItem);
                    app_popup.add(modMenItem);
                    app_popup.show(dataTableOrder, e.getX(), e.getY());
                }
            }
        });



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
            this.getContentPane().add(new JScrollPane(this.dataTableAppointment), BorderLayout.CENTER);
            this.setVisible(true);
        } else if(e.getSource() == add_app){
            FrmAddAppointment dlg = new FrmAddAppointment(this,"添加预约",true);
            dlg.setVisible(true);
            view_app.doClick();
        }
//        else if (e.getSource() == del_app){
//            int i = this.dataTableAppointment.getSelectedRow();
//            if(i<0){
//                JOptionPane.showMessageDialog(null, "请选择要操作的对象", "错误",JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            int id = Integer.parseInt((String) tblAppointmentData[i][0]);
//            int option = JOptionPane.showConfirmDialog(null, "是否要删除预约"+id, "取消", JOptionPane.YES_NO_OPTION);
//            if(option == 0 ){
//                PetManageSystemUtil.appointmentController.delAppointment(id);
//                this.reloadAppointmentTable();
//            } else
//                return;
//        }
//        else if(e.getSource() == mod_app){
//            int i = this.dataTableAppointment.getSelectedRow();
//            if(i<0){
//                JOptionPane.showMessageDialog(null, "请选择要操作的对象", "错误",JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            int id = Integer.parseInt((String) tblAppointmentData[i][0]);
//            FrmModAppointment dlg = new FrmModAppointment(this,"修改预约",true, id);
//            dlg.setVisible(true);
//            this.reloadAppointmentTable();
//        }
        else if (e.getSource() == view_cate){
            this.getContentPane().removeAll();
            this.reloadCategoryTable();
            this.getContentPane().add(new JScrollPane(this.dataTableCategory), BorderLayout.CENTER);
            this.setVisible(true);
        } else if (e.getSource() == add_cate) {
            this.getContentPane().removeAll();
            FrmAddCategory dlg = new FrmAddCategory(this,"添加分类",true);
            dlg.setVisible(true);
            view_cate.doClick();
        } else if (e.getSource() == view_order){
            this.getContentPane().removeAll();
            this.reloadOrderTable();
            this.getContentPane().add(new JScrollPane(this.dataTableOrder), BorderLayout.CENTER);
            this.setVisible(true);
        } else if (e.getSource() == add_order){
            this.getContentPane().removeAll();
            FrmAddOrder dlg = new FrmAddOrder(this, "添加订单", true);
            dlg.setVisible(true);
        } else if (e.getSource() == view_user) {
            this.getContentPane().removeAll();
            this.reloadUserTable();
            this.getContentPane().add(new JScrollPane(this.dataTableUser), BorderLayout.CENTER);
            this.setVisible(true);
        } else if (e.getSource() == view_oper) {
            this.getContentPane().removeAll();
            this.reloadOperatorTable();
            this.getContentPane().add(new JScrollPane(this.dataTableOperator), BorderLayout.CENTER);
            this.setVisible(true);
        } else if(e.getSource() == add_oper){
            this.getContentPane().removeAll();
            FrmAddOperator dlg = new FrmAddOperator(this,"添加管理员",true);
            dlg.setVisible(true);
            reloadOperatorTable();
            view_oper.doClick();
        } else if (e.getSource() == view_pet) {
            this.getContentPane().removeAll();
            this.reloadPetTable();
            this.getContentPane().add(new JScrollPane(this.dataTablePet), BorderLayout.CENTER);
            this.setVisible(true);
        } else if (e.getSource() == view_prod) {
            this.getContentPane().removeAll();
            this.reloadProductTable();
            this.getContentPane().add(new JScrollPane(this.dataTableProduct), BorderLayout.CENTER);
            this.setVisible(true);
        } else if (e.getSource() == view_serv) {
            this.getContentPane().removeAll();
            this.reloadServiceTable();
            this.getContentPane().add(new JScrollPane(this.dataTableService), BorderLayout.CENTER);
            this.setVisible(true);
        }
    }

}
