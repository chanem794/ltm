package chatbot;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 2025)) {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            // Tạo và khởi động luồng để nhận tin nhắn từ server
            Thread receiveThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = input.readUTF()) != null) {
                        // In ra tin nhắn nhận được từ server
                        System.out.println("Server: " + message);
                    }
                } catch (IOException e) {
                    // Xử lý khi server ngắt kết nối
                    System.out.println("Server đã ngắt kết nối.");
                }
            });
            int chon;
            do {
                // Nhận menu từ Server từng dòng
                String line;
                while (!(line = input.readUTF()).equals("END")) {
                    System.out.println("Server: " + line);
                }
                
                chon = sc.nextInt();
                output.writeInt(chon);
                sc.nextLine(); // Xử lý dòng trống sau nextInt
                
                if (chon == 0) break;
                
                // Nhận yêu cầu nhập dữ liệu từ Server
                while (!(line = input.readUTF()).equals("END")) {
                    System.out.println("Server: " + line);
                }
                
                // Nhập dữ liệu từ Client và gửi lên Server
                String duLieuNhap = sc.nextLine();
                output.writeUTF(duLieuNhap);
                System.out.println("Client: " + duLieuNhap);
                
                // Nhận kết quả từ Server
                while (!(line = input.readUTF()).equals("END")) {
                    System.out.println("Server: " + line);
                }
            } while (chon != 0);
            
            input.close();
            output.close();
        } catch (IOException e) {
            System.out.println("Lỗi Client: " + e.getMessage());
        }
    }
}
