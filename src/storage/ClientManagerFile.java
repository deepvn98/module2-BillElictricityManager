package storage;

import model.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientManagerFile {
    private String name;
    private static ClientManagerFile InStance;

    public ClientManagerFile() {
    }

    public ClientManagerFile(String name) {
        this.name = name;
    }
    public static ClientManagerFile getInstance(String name){
        if (InStance== null){
            InStance = new ClientManagerFile(name);
        }return InStance;
    }
//    Ghi File
    public void writeFileClient(List<Client> clients) throws IOException {
        File file = new File("client.dat");
        if (!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fileOutputStream =new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(clients);
        objectOutputStream.close();
        fileOutputStream.close();
    }
//    Đọc file tạo ra một mảng Khách hàng
    public List<Client> readFileClient(String file) throws IOException, ClassNotFoundException {
        File file1 = new File(file);
        if (!file1.exists()){
            file1.createNewFile();
        }
        if (file1.length()>0){
            FileInputStream fileInputStream = new FileInputStream(file1);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object obj = objectInputStream.readObject();
            List<Client> clients = (List<Client>) obj;
            objectInputStream.close();
            fileInputStream.close();
            return clients;
        }else {
            return new ArrayList<Client>();
        }
    }
}
