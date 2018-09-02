package view.mod;

import model.BeanAppointment;
import model.BeanMyUser;
import model.BeanPet;
import model.BeanService;
import util.BaseException;
import util.PetManageSystemUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class FrmModAppointment extends JDialog implements ActionListener{

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private Button btnOk = new Button("确认");
    private Button btnCancel = new Button("取消");
    private JLabel labelUser = new JLabel("用户:");
    private JLabel labelPet = new JLabel("宠物:");
    private JLabel labelService = new JLabel("服务:");
    private JLabel labelPrice = new JLabel("价格:");
    private JLabel price = new JLabel("未指定");
    private JComboBox userBox = new JComboBox();
    private JComboBox petBox = new JComboBox();
    private JComboBox serviceBox = new JComboBox();

    private BeanMyUser objUser = null;
    private BeanService objService = null;
    private BeanPet objPet = null;
    private BeanAppointment objAppointmnet = null;


    public FrmModAppointment(JFrame f, String s, boolean b, int id) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        objAppointmnet = PetManageSystemUtil.appointmentController.findAppointmentById(id);

        // users
        List<BeanMyUser> users =  PetManageSystemUtil.userController.loadAll();
        for(BeanMyUser user : users){
            userBox.addItem(user.getUserName());
        }

        String user = userBox.getSelectedItem().toString();


        try{
            objUser = PetManageSystemUtil.userController.findUserByName(user);
        }catch (BaseException e){
            e.printStackTrace();
        }

        userBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ItemEvent.SELECTED == e.getStateChange()){
                    petBox.removeAllItems();
                    String user = (String)userBox.getSelectedItem();
                    try{
                        objUser = PetManageSystemUtil.userController.findUserByName(user);
                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                    //pets
                    List<BeanPet> pets = PetManageSystemUtil.petController.loadAll(objUser.getUserId());
                    for (BeanPet pet : pets){
                        petBox.addItem(pet.getPetNikename());
                    }
                }
            }
        });

        List<BeanPet> pets = PetManageSystemUtil.petController.loadAll(objUser.getUserId());

        for (BeanPet pet : pets){
            petBox.addItem(pet.getPetNikename());
        }

        String pet = petBox.getSelectedItem().toString();
        objPet = PetManageSystemUtil.petController.findPetByName(pet);

        petBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(ItemEvent.SELECTED == e.getStateChange()){
                    String pet = petBox.getSelectedItem().toString();
                    objPet = PetManageSystemUtil.petController.findPetByName(pet);
                }
            }
        });



        //services
        List<BeanService> services = PetManageSystemUtil.serviceController.loadAll();
        for (BeanService service: services){
            serviceBox.addItem(service.getServName());
        }

        String service = serviceBox.getSelectedItem().toString();
        objService =  PetManageSystemUtil.serviceController.findServiceByName(service);
        price.setText(objService.getServPrice().toString() + "元");
        serviceBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(ItemEvent.SELECTED == e.getStateChange()){
                    objService = PetManageSystemUtil.serviceController.findServiceByName(serviceBox.getSelectedItem().toString());
                    price.setText(objService.getServPrice().toString()+ "元");
                }
            }
        });

        workPane.add(labelUser);
        workPane.add(userBox);
        workPane.add(labelPet);
        workPane.add(petBox);
        workPane.add(labelService);
        workPane.add(serviceBox);
        workPane.add(labelPrice);
        workPane.add(price);

        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(515, 100);

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
        }else if(e.getSource() == this.btnOk){
            objAppointmnet.setUserId(objUser.getUserId());
            objAppointmnet.setPetId(objPet.getPetId());
            objAppointmnet.setAppServ(objService.getServId());
            if(objAppointmnet.getAppState().equals("Finished")){
                JOptionPane.showMessageDialog(null, "此预约已经完成,不可修改", "错误",JOptionPane.ERROR_MESSAGE);
            }else{
                PetManageSystemUtil.update(objAppointmnet);
            }
            this.setVisible(false);
            return;
        }
    }
}
