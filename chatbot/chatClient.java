package chatbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;   
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class chatClient {
    public static void main(String[] args) {
        String serverAddress = "192.168.2.151"; // Thay đổi địa chỉ IP của server nếu cần
        int serverPort = 2025;

        try (Socket socket = new Socket(serverAddress, serverPort);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner sc = new Scanner(System.in)) {

                // Lấy địa chỉ IP của máy client
            String clientIp = InetAddress.getLocalHost().getHostAddress();
            // Lấy hostname của server từ serverAddress
            String serverHostname = InetAddress.getByName(serverAddress).getHostName();
            // Hiển thị thông báo đã kết nối với server
            System.out.println("Đã kết nối với server: " + serverHostname);
            // Gửi địa chỉ IP của client và hostname của server cho server
            out.println("Client IP: " + clientIp + " | Ket noi tu server: " + serverHostname);
            String serverResponse;
            while ((serverResponse = in.readLine()) != null) {
                System.out.println(serverResponse);
                if (serverResponse.contains("Thoat chuong trinh.")) {
                    break;
                }

                if (serverResponse.contains("[Server] Nhap lua chon") || serverResponse.contains("Nhap 1 so") || 
                    serverResponse.contains("Nhap 1 so nguyen duong") || serverResponse.contains("Nhap so thu nhat") || 
                    serverResponse.contains("Nhap so thu hai") || serverResponse.contains("Nhap chuoi") || 
                    serverResponse.contains("Chon chuc nang") || serverResponse.contains("Chon bai tap")) {
                    String userInput = sc.nextLine();
                    out.println(userInput);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}