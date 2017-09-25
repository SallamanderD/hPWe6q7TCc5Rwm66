package ua.nure.dorotenko.entities;


import java.util.Date;

public class CardPayment {
    private long id;
    private long senderCardId;
    private long receiverCardId;
    private double sum;
    private boolean prepared;
    private Date datetime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSenderCardId() {
        return senderCardId;
    }

    public void setSenderCardId(long senderCardId) {
        this.senderCardId = senderCardId;
    }

    public long getReceiverCardId() {
        return receiverCardId;
    }

    public void setReceiverCardId(long receiverCardId) {
        this.receiverCardId = receiverCardId;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isPrepared() {
        return prepared;
    }

    public void setPrepared(boolean prepared) {
        this.prepared = prepared;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "CardPayment{" +
                "id=" + id +
                ", senderCardId=" + senderCardId +
                ", receiverCardId=" + receiverCardId +
                ", sum=" + sum +
                ", prepared=" + prepared +
                ", datetime=" + datetime +
                '}';
    }
}
