package view.mod;

import model.BeanCategory;
import model.BeanMyOrder;
import model.BeanMyUser;
import model.BeanProduct;
import util.PetManageSystemUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.List;

public class FrmModOrder extends JDialog implements ActionListener{
    //用户 产品 数量 价格
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private Button btnOk = new Button("确认");
    private Button btnCancel = new Button("取消");
    private JLabel labelUser = new JLabel("用户: ");
    private JLabel labelProduct = new JLabel("产品:");
    private JLabel labelNum  = new JLabel("数量:");
    private JLabel labelPrice = new JLabel("价格:  ");
    private JComboBox<BeanMyUser> userBox = new JComboBox<BeanMyUser>();
    private JComboBox<BeanProduct> productBox = new JComboBox<BeanProduct>();
    private JFormattedTextField num = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JLabel price = new JLabel("请选择产品和数量");

    private BeanProduct objProduct = null;
    private BeanMyUser objUser = null;
    private BeanMyOrder objOrder = null;
    int intPrice;
    int intNum;

    public FrmModOrder(JFrame f, String s, boolean b, int id){
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        num.setColumns(5);

        objOrder = PetManageSystemUtil.orderController.findOrderById(id);
        //users
        java.util.List<BeanMyUser> users =  PetManageSystemUtil.userController.loadAll();
        for(BeanMyUser user : users){
            userBox.addItem(user);
        }

        objUser = (BeanMyUser)userBox.getSelectedItem();
        userBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                objUser = (BeanMyUser)userBox.getSelectedItem();
            }
        });



        //products
        List<BeanProduct> products = PetManageSystemUtil.productController.loadAll();
        for (BeanProduct product : products) {
            productBox.addItem(product);
        }

        objProduct = (BeanProduct)productBox.getSelectedItem();

        productBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                objProduct = (BeanProduct)productBox.getSelectedItem();
            }
        });


        num.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                intNum = Integer.parseInt(num.getValue().toString());
                intPrice = intNum * objProduct.getProdPrice();
                price.setText(String.valueOf(intPrice)+"元");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        workPane.add(labelUser);
        workPane.add(userBox);
        workPane.add(labelProduct);
        workPane.add(productBox);
        workPane.add(labelNum);
        workPane.add(num);
        workPane.add(labelPrice);
        workPane.add(price);

        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(380, 125);

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
            objOrder.setOrderNum(intNum);
            objOrder.setOrderPrice(objProduct.getProdPrice() * intNum);
            objOrder.setOrderProd(objProduct.getProdId());
            objOrder.setOrderState("订单创建完成");
            objOrder.setOrderUser(objUser.getUserId());
            PetManageSystemUtil.update(objOrder);
            this.setVisible(false);
        }

    }
}
