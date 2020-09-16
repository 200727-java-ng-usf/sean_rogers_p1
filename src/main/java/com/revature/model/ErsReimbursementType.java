package com.revature.model;

/**
 * maps to ers_reimbursement_types
 */
public class ErsReimbursementType {

    private long reimbTypeId;
    private String reimbType;

    public long getReimbTypeId() {
        return reimbTypeId;
    }

    public void setReimbTypeId(long reimbTypeId) {
        this.reimbTypeId = reimbTypeId;
    }

    public String getReimbType() {
        return reimbType;
    }

    public void setReimbType(String reimbType) {
        this.reimbType = reimbType;
    }

    @Override
    public String toString() {
        return "ErsReimbursementTypes{" +
                "reimbTypeId=" + reimbTypeId +
                ", reimbType='" + reimbType + '\'' +
                '}';
    }
}
