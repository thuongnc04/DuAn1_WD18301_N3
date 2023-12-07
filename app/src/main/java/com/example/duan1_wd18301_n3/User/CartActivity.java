package com.example.duan1_wd18301_n3.User;

import java.util.ArrayList;
import java.util.List;

public class CartActivity {
    public static void removeByProductId(int id) {
        for (Item item : listItem) {
            if (item.product_id == id) {
                listItem.remove(item);
                return;
            }
        }
    }

    public static void setQuantityByProductId(int id, int quantity) {
        for (int i = 0; i < listItem.size(); i++) {
            if (listItem.get(i).product_id == id) {
                listItem.get(i).setQuantity(quantity);
                return;
            }
        }
    }

    public static class Item {
        private int product_id;
        private int quantity;

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Item(int product_id, int quantity) {
            this.product_id = product_id;
            this.quantity = quantity;
        }
    }

    public static List<Item> listItem;

    // Them vao gio hang
    public static boolean addItem(int product_id, int quantity) {
        if (listItem == null) {
            listItem = new ArrayList<>();
        }

        for (Item item : listItem) {
            if (item.product_id == product_id) {
                item.quantity += quantity;
                return true;
            }
        }


        // Nếu chưa có sản phẩm thì thêm mới
        Item item = new Item(product_id, quantity);
        listItem.add(item);
        return true;
//        return false; // Trả về false để chỉ ra rằng không tìm thấy đối tượng
    }

    // Lấy ra thông tin sản phẩm
    public static Item getItem(int product_id) {
        for (Item item: listItem) {
            if (item.product_id == product_id) {
                return item;
            }
        }
        return null;
    }

    // Xoá sản phẩm khỏi giỏ hàng (Khi số lượng sản phẩm bằng 0 hoặc khi ấn thanh toán)
    public static boolean deleteItem(int product_id) {
        for (Item item: listItem) {
            if (item.product_id == product_id) {
                listItem.remove(item);
                return true;
            }
        }
        return false;
    }

    public static int sum() {
        int s = 0;
        for (Item item : listItem) {
            s += item.quantity;
        }
        return s;
    }

    public static int quantity_product() {
        return listItem.size();
    }
}
