package com.example.sop.enums;

public enum OrderStatusEnum {
    PENDING(0),
    COMPLETED(1),
    CANCELED(2);
    private int numOrderStatus;

    OrderStatusEnum(int numOrderStatus) {
        this.numOrderStatus = numOrderStatus;
    }

    public int getNumOrderStatus() {
        return numOrderStatus;
    }

    public void setNumOrderStatus(int numOrderStatus) {
        this.numOrderStatus = numOrderStatus;
    }
}
