//package project;

import java.util.Scanner;

public class StatisticsManager {
    private ProductManager productManager;
    private OrderManager orderManager;

    public StatisticsManager(ProductManager productManager, OrderManager orderManager) {
        this.productManager = productManager;
        this.orderManager = orderManager;
    }

    // Thống kê sản phẩm theo trạng thái (còn hàng hoặc hết hàng)
    public void countOrderByStatus() {
        int inStockCount = 0;
        int outOfStockCount = 0;

        for (Order order : orderManager.getOrders()) {
            if (order.getQuantity() > 0) {
                inStockCount++;
            } else {
                outOfStockCount++;
            }
        }

        System.out.println("Thong ke san pham theo trang thai");
        System.out.println("Con hang: " + inStockCount);
        System.out.println("Het hang: " + outOfStockCount);
    }

 
    public void totalQuantitySold() {
        int totalSold = 0;
        for (Order order : orderManager.getOrders()) {
            totalSold += order.getQuantity(); 
        }

        System.out.println("Tong so san pham da ban: " + totalSold);
    }

    // Thống kê người dùng có nhiều đơn hàng nhất
    public void userWithMostOrders() {
        int maxOrders = 0;
        User topUser = null;

        for (User user : orderManager.getUser()) { 
            int orderCount = 0;
            for (Order order : orderManager.getOrders()) {
                if (order.getId() == user.getId()) { 
                    orderCount++;
                }
            }
            if (orderCount > maxOrders) {
                maxOrders = orderCount;
                topUser = user;
            }
        }

        if (topUser != null) {
            System.out.println("Nguoi dung co nhieu don hang nhat: " + topUser.getName() + " voi " + maxOrders + " don hang.");
        } else {
            System.out.println("Khong tim thay nguoi dung nao.");
        }
    }

    // Hàm main để chọn các chức năng thống kê
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        OrderManager orderManager = new OrderManager();

        productManager.loadFromFile();
        orderManager.loadFromFile();
        
        StatisticsManager statisticsManager = new StatisticsManager(productManager, orderManager);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU THONG KE ===");
            System.out.println("1. Thong ke san pham theo trang thai");
            System.out.println("2. Thong ke so luong theo san pham da ban");
            System.out.println("3. Thong ke nguoi dung co nhieu don hang nhat");
            System.out.println("0. thoat");
            System.out.print("--Chon chuc nang: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    statisticsManager.countOrderByStatus();
                    break;
                case 2:
                    statisticsManager.totalQuantitySold();
                    break;
                case 3:
                    statisticsManager.userWithMostOrders();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
}
