//package project;

import java.io.Serializable;

public class Message implements Serializable {
    private int id;
    private String customerName;
    private String subject;
    private String content;
    private boolean isRead;

    public Message(int id, String customerName, String subject, String content) {
        this.id = id;
        this.customerName = customerName;
        this.subject = subject;
        this.content = content;
        this.isRead = false;  // Mặc định là chưa đọc khi tin nhắn mới được gửi
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public boolean isRead() {
        return isRead;
    }

    public void markAsRead() {
        this.isRead = true;
    }

    @Override
    public String toString() {
        return "Message{id=" + id + ", customerName='" + customerName + "', subject='" + subject + "', isRead=" + isRead + "}";
    }
}
