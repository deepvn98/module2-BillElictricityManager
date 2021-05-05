package storage;

import model.Electricitybill;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BillManagerFile {
    private String name;
    private static BillManagerFile INSTANCE;

    public BillManagerFile(String name) {
        this.name = name;
    }
    public static BillManagerFile getINSTANCE(String name){
        if (INSTANCE == null){
            INSTANCE = new BillManagerFile(name);
        }return INSTANCE;
    }
//    Ghi File
    public void writeBillFile(List<Electricitybill> electricitybills) throws IOException {
        File file = new File("Bill.dat");
        if (!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(electricitybills);
        objectOutputStream.close();
        fileOutputStream.close();
    }
//    Đọc File trả về một danh sách bill
    public List<Electricitybill> readBillFile(String file) throws IOException, ClassNotFoundException {
        File file1 = new File(file);
        if (!file1.exists()){
            file1.createNewFile();
        }
        if (file1.length()>0){
            FileInputStream fileInputStream = new FileInputStream(file1);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object obj = objectInputStream.readObject();
            List<Electricitybill> electricitybills = (List<Electricitybill>) obj;
            objectInputStream.close();
            fileInputStream.close();
            return electricitybills;
        }else {
            return new ArrayList<>();
        }
    }
}
