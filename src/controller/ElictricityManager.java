package controller;

import model.Client;
import model.Electricitybill;
import storage.BillManagerFile;
import storage.ClientManagerFile;
import view.Main;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class ElictricityManager implements Serializable {
    private List<Client> clients;
    private List<Electricitybill>electricitybills;

    public ElictricityManager(List<Client> clients, List<Electricitybill> electricitybills) {
        this.clients = clients;
        this.electricitybills = electricitybills;
    }
//    Tạo hoá đơn cho khách hàng
    public void addCElictricityBillForClient(Client client) throws IOException {
        Electricitybill electricitybill =Main.createNewElictricityBill();
        electricitybill.setClient(client);
        electricitybills.add(electricitybill);
        BillManagerFile billManagerFile = BillManagerFile.getINSTANCE("Sáng");
        billManagerFile.writeBillFile(electricitybills);
    }


    public ElictricityManager() {
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Electricitybill> getElectricitybills() {
        return electricitybills;
    }

    public void setElectricitybills(List<Electricitybill> electricitybills) {
        this.electricitybills = electricitybills;
    }

//    Thêm khách hàng vào mảng khách hàng
    public void addPersonInClients(Client client) throws IOException {
        clients.add(client);
        ClientManagerFile clientManagerFile = ClientManagerFile.getInstance("Sáng");
        clientManagerFile.writeFileClient(clients);
    }
//    Tìm khách hàng theo mã máy công tơ điện
    public Client getClientByIDMachine(String id){
        Client client = null;
        for (int i =0;i< clients.size();i++){
            if (clients.get(i).getIdMachine().equalsIgnoreCase(id)){
                client = clients.get(i);
                return client;
            }
        }return null;
    }
//    Tính tiền điện khách phải trả tìm theo id đồng hồ điện
    public void getAmoutMoney(String id) throws IOException {
        for (int i =0; i< electricitybills.size();i++){
            if (electricitybills.get(i).getClient().getIdMachine().equalsIgnoreCase(id)){
                double money =electricitybills.get(i).getAmountMoney();
                electricitybills.remove(electricitybills.get(i));
                i--;
                BillManagerFile billManagerFile = BillManagerFile.getINSTANCE("Sáng");
                billManagerFile.writeBillFile(electricitybills);
                System.out.println("Số tiền khách phải thanh toán: "+ money);
            }
        }
    }
//    hiển thị khách hàng đáng đăng ký sử dụng điện
    public void showClient(){
        for (Client c:clients
             ) {
            System.out.println(c.toString());
        }
    }
//    Hiển thị hoá đơn thanh toán của khách
    public void showBill(){
        for (Electricitybill e:electricitybills
             ) {
            System.out.println(e.toString());
        }
    }

}
