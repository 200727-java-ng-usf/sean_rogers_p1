package com.revature.model;

/**
 * maps to ers_reimbursement_statuses
 */
public class ErsReimbursementStatus {

    private long reimbStatusId;
    private String reimbStatus;

    public long getReimbStatusId() {
        return reimbStatusId;
    }

    public void setReimbStatusId(long reimbStatusId) {
        this.reimbStatusId = reimbStatusId;
    }

    public String getReimbStatus() {
        return reimbStatus;
    }

    public void setReimbStatus(String reimbStatus) {
        this.reimbStatus = reimbStatus;
    }

    @Override
    public String toString() {
        return "ErsReimbursementStatuses{" +
                "reimbStatusId=" + reimbStatusId +
                ", reimbStatus='" + reimbStatus + '\'' +
                '}';
    }
}
