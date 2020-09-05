package com.revature.model;

import java.sql.Timestamp;

public class ErsReimbursements {

    private long reimbId;
    private double amount;
    private Timestamp submitted;
    private Timestamp resolved;
    private String description;
    private double receipt;
    private long authorId;
    private long resolverId;
    private long reimbStatusId;
    private long reimbTypeId;

    public long getReimbId() {
        return reimbId;
    }

    public void setReimbId(long reimbId) {
        this.reimbId = reimbId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getReceipt() {
        return receipt;
    }

    public void setReceipt(double receipt) {
        this.receipt = receipt;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getResolverId() {
        return resolverId;
    }

    public void setResolverId(long resolverId) {
        this.resolverId = resolverId;
    }

    public long getReimbStatusId() {
        return reimbStatusId;
    }

    public void setReimbStatusId(long reimbStatusId) {
        this.reimbStatusId = reimbStatusId;
    }

    public long getReimbTypeId() {
        return reimbTypeId;
    }

    public void setReimbTypeId(long reimbTypeId) {
        this.reimbTypeId = reimbTypeId;
    }

    @Override
    public String toString() {
        return "ErsReimbursements{" +
                "reimbId=" + reimbId +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", receipt=" + receipt +
                ", authorId=" + authorId +
                ", resolverId=" + resolverId +
                ", reimbStatusId=" + reimbStatusId +
                ", reimbTypeId=" + reimbTypeId +
                '}';
    }
}
