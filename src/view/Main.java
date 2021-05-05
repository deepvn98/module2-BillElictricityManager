package view;

import controller.ElictricityManager;
import model.Client;
import model.Electricitybill;
import storage.BillManagerFile;
import storage.ClientManagerFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClientManagerFile clientManagerFile = ClientManagerFile.getInstance("Sáng");
        BillManagerFile billManagerFile = BillManagerFile.getINSTANCE("Sáng");
        List<Electricitybill> electricitybills = new ArrayList<>();
        try {
            electricitybills = billManagerFile.readBillFile("Bill.dat");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<Client> clients = new ArrayList<>();
        try {
            clients = clientManagerFile.readFileClient("client.dat");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ElictricityManager manager = new ElictricityManager(clients,electricitybills);
        menu(manager,clients,electricitybills);

    }
    public static void menu(ElictricityManager manager,List<Client> clients,List<Electricitybill>electricitybills){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhấn 1: Thêm khách hàng");
        System.out.println("Nhấn 2: Viết hoá đơn cho khách");
        System.out.println("Nhấn 3: Hiển thị thông tin khách hàng");
        System.out.println("Nhấn 4: Hiển thị bill chưa thanh toán tiền");
        System.out.println("Nhấn 5: Tính tiền cho khách hàng");
        System.out.println("Nhấn 6: Thoát menu");
        boolean check = true;
        int number = 0;
        do {
            System.out.print("Nhập vào lựa chọn của bạn: ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                {
                    System.out.print("Nhập vào số lượng khách hàng đăng ký:");
                    Scanner scanner1 = new Scanner(System.in);
                    number = scanner1.nextInt();
                    int arr[] = new int[number];
                    for (int i =0; i < arr.length;i++){
                        System.out.println("Khách hàng "+(i+1));
                        Client client = createNewClient();
                        try {
                            manager.addPersonInClients(client);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                case 2:
                {
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("Nhập id Đồng hồ điện:");
                    String id = scanner1.nextLine();
                   Client client = manager.getClientByIDMachine(id);
                   if (client!=null){
                       try {
                           manager.addCElictricityBillForClient(client);
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
                    break;
                }
                case 3:
                {
                    manager.showClient();
                    break;
                }
                case 4:
                {
                    if (electricitybills.size()>0){
                        manager.showBill();
                    }else {
                        System.out.println("hiện không còn hoá đơn nào cần thanh toán");
                    }

                    break;
                }
                case 5:
                {
                    System.out.println("nhập id đồng hồ điện của khách hàng:");
                    Scanner scanner1 = new Scanner(System.in);
                    String id = scanner1.nextLine();
                    Client client = manager.getClientByIDMachine(id);
                    if (client!=null){
                       if (electricitybills.size()>0){
                           try {
                               manager.getAmoutMoney(id);
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                       }else {
                           System.out.println("Hiện không có hoá đơn nào chưa thanh toán");
                       }
                    }else {
                        System.out.println("khách hàng không tồn tại");
                    }
                    break;
                }
                case 6:
                {
                    check = false;
                    break;
                }
            }
        }while (check);
    }
//    public void addClient(){
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Nhập tên: ");
//        String name = scanner.nextLine();
//        Scanner scanner1 = new Scanner(System.in);
//        System.out.print("Nhập địa chỉ: ");
//        String address = scanner1.nextLine();
//        Scanner scanner2 = new Scanner(System.in);
//        System.out.print("Nhập id máy: ");
//        String id = scanner2.nextLine();
//    }
    public static Client createNewClient(){
        System.out.print("Nhập Tên khách hàng: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.print("Nhập địa chỉ");
        Scanner scanner1 = new Scanner(System.in);
        String address = scanner1.nextLine();
        System.out.print("Nhập id máy ");
        Scanner scanner2 = new Scanner(System.in);
        String id = scanner2.nextLine();
        Client client = new Client(name,address,id);
        return client;
    }
    public static Electricitybill createNewElictricityBill(){
        System.out.print("Nhập Số điện cũ:");
        Scanner scanner = new Scanner(System.in);
        double oldNumber = scanner.nextDouble();
        System.out.print("Nhập Số điện mới:");
        Scanner scanner1 = new Scanner(System.in);
        double newNumber = scanner1.nextDouble();
        System.out.print("Nhập giá tiền điện:");
        Scanner scanner2 = new Scanner(System.in);
        double elictricityPrice = scanner2.nextDouble();
        Electricitybill electricitybill = new Electricitybill(oldNumber,newNumber,elictricityPrice);
        return electricitybill;
    }
}
