package model;

import java.io.Serializable;

public class Client implements Serializable {
    private String name;
    private String address;
    private String idMachine;

    public Client () {
    }

    public Client(String name, String address, String idMachine) {
        this.name = name;
        this.address = address;
        this.idMachine = idMachine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(String idMachine) {
        this.idMachine = idMachine;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name +
                ", address='" + address +
                ", idMachine='" + idMachine +
                '}';
    }
}
