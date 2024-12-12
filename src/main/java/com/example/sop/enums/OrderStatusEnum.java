package com.example.sop.enums;

public enum OrderStatusEnum {
    PENDING(0),         // В ожидании обработки
    PROCESSING(1),      // Заказ в обработке
    PREPARED(2),        // Заказ подготовлен к доставке
    COMPLETED(3),       // Заказ доставлен
    CANCELED(4);        // Заказ отменён

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
