package nic.gst;

/**
 * Created by Aayush on 7/24/2017.
 */

public class GST {

    private int id;
    String percentage;
    private float amount, t1,t2, totalAmount;

    public GST(int id, String percentage, float amount, float t1,float t2, float totalAmount) {
        this.id = id;
        this.percentage = percentage;
        this.amount = amount;
        this.t1 = t1;
        this.t2=t2;
        this.totalAmount = totalAmount;
    }

    public GST(int id, String percentage, float amount, float t1, float totalAmount) {
        this.id = id;
        this.percentage = percentage;
        this.amount = amount;
        this.t1 = t1;
        this.totalAmount = totalAmount;
    }


    public int getId() {
        return id;
    }

    public String getPercentage() {
        return percentage;
    }

    public float getAmount() {
        return amount;
    }

    public float t1() {
        return t1;
    }

    public float t2() {
        return t2;
    }

    public float getTotalAmount() {
        return totalAmount;
    }
}
