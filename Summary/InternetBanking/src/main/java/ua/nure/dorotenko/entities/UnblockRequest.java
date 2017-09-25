package ua.nure.dorotenko.entities;

import java.util.Date;

public class UnblockRequest {
    private long id;
    private long accountId;
    private Date datetime;
    private boolean isSatisfied;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "UnblockRequest{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", datetime=" + datetime +
                ", isSatisfied=" + isSatisfied +
                '}';
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public boolean isSatisfied() {
        return isSatisfied;
    }

    public void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }

}
