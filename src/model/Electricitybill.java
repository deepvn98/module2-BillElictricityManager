package model;

import java.io.Serializable;

public class Electricitybill implements Serializable {
    private double oldNumber;
    private double newNumber;
    private double electricityPrice;
    private Client client;
    private double amountMoney;

    public Electricitybill() {

    }

    public Electricitybill(double oldNumber, double newNumber, double electricityPrice, Client client) {
        this.oldNumber = oldNumber;
        this.newNumber = newNumber;
        this.electricityPrice = electricityPrice;
        this.client = client;
    }

    public Electricitybill(double oldNumber, double newNumber, double electricityPrice) {
        this.oldNumber = oldNumber;
        this.newNumber = newNumber;
        this.electricityPrice = electricityPrice;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getOldNumber() {
        return oldNumber;
    }

    public void setOldNumber(double oldNumber) {
        this.oldNumber = oldNumber;
    }

    public double getNewNumber() {
        return newNumber;
    }

    public void setNewNumber(double newNumber) {
        this.newNumber = newNumber;
    }

    public double getElectricityPrice() {
        return electricityPrice;
    }

    public void setElectricityPrice(double electricityPrice) {
        this.electricityPrice = electricityPrice;
    }
    public double getAmountMoney(){
        return (newNumber-oldNumber)*electricityPrice;
    }

    @Override
    public String toString() {
        return "Electricitybill{" +
                "oldNumber=" + oldNumber +
                ", newNumber=" + newNumber +
                ", electricityPrice=" + electricityPrice +
                ", client=" + client +
                '}';
    }
}
