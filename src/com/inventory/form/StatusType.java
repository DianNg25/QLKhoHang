package com.inventory.form;

public enum StatusType {
    DA_XOA("Đã xóa"),
        BINH_THUONG("Hoạt động");

        private final String text;

        StatusType(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
}
