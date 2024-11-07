//package project;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class MailboxManager {
    private Message[] mailbox = new Message[0];

    // Gửi tin nhắn tới hộp thư
    public void sendMessage(Message message) {
        mailbox = Arrays.copyOf(mailbox, mailbox.length + 1);
        mailbox[mailbox.length - 1] = message;
        saveToFile();
        System.out.println("Da gui tin nhan toi khach hang: " + message);
    }

    // Xem tất cả tin nhắn
    public void displayMessages() {
        if (mailbox.length == 0) {
            System.out.println("Hop thu trong.");
        } else {
            for (Message message : mailbox) {
                System.out.println(message);
            }
        }
    }

    // Tìm kiếm tin nhắn theo ID hoặc tên khách hàng
    public void searchMessage(String keyword) {
        boolean found = false;
        for (Message message : mailbox) {
            if (String.valueOf(message.getId()).equals(keyword) || message.getCustomerName().equalsIgnoreCase(keyword)) {
                System.out.println("Tim thay: " + message);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay tin nhan: " + keyword);
        }
    }

    // Xem chi tiết tin nhắn và đánh dấu là đã đọc
    public void viewMessage(int id) {
        for (Message message : mailbox) {
            if (message.getId() == id) {
                System.out.println("Chi tiết tin nhắn:");
                System.out.println("Người gửi: " + message.getCustomerName());
                System.out.println("Chủ đề: " + message.getSubject());
                System.out.println("Nội dung: " + message.getContent());
                message.markAsRead();  // Đánh dấu là đã đọc
                saveToFile();
                return;
            }
        }
        System.out.println("Không tìm thấy tin nhắn với ID: " + id);
    }

    // Lưu hộp thư vào file
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mailbox.dat"))) {
            oos.writeObject(mailbox);
            System.out.println("Dữ liệu đã được lưu vào file.");
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    // Đọc dữ liệu hộp thư từ file
    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("mailbox.dat"))) {
            mailbox = (Message[]) ois.readObject();
            System.out.println("Dữ liệu đã được tải từ file.");
        } catch (FileNotFoundException e) {
            System.out.println("File không tồn tại, sẽ tạo file mới khi lưu.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }

    // Hàm main với menu điều khiển
    public static void main(String[] args) {
        MailboxManager manager = new MailboxManager();
        manager.loadFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU QUẢN LÝ HỘP THƯ ===");
            System.out.println("1. Gửi tin nhắn");
            System.out.println("2. Xem tất cả tin nhắn");
            System.out.println("3. Tìm kiếm tin nhắn");
            System.out.println("4. Xem chi tiết tin nhắn và đánh dấu đã đọc");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập ID tin nhắn: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhập tên khách hàng: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Nhập chủ đề tin nhắn: ");
                    String subject = scanner.nextLine();
                    System.out.print("Nhập nội dung tin nhắn: ");
                    String content = scanner.nextLine();
                    manager.sendMessage(new Message(id, customerName, subject, content));
                    break;

                case 2:
                    manager.displayMessages();
                    break;

                case 3:
                    System.out.print("Nhập ID hoặc tên khách hàng để tìm kiếm tin nhắn: ");
                    String keyword = scanner.nextLine();
                    manager.searchMessage(keyword);
                    break;

                case 4:
                    System.out.print("Nhập ID tin nhắn cần xem chi tiết: ");
                    int viewId = scanner.nextInt();
                    manager.viewMessage(viewId);
                    break;

                case 0:
                    System.out.println("Thoát chương trình.");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
