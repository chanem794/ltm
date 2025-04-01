

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class chatServer {
    
    public static void main(String[] args) {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "2025"));
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server dang chay va cho ket noi...");
            
            
            while (true) {
                Socket socket = serverSocket.accept();

                // Lấy địa chỉ IP của client từ socket
                String clientIp = socket.getInetAddress().getHostAddress();
                System.out.println("Client voi IP " + clientIp + " da ket noi.");

                // Lấy hostname của client từ socket
                String clientHostname = socket.getInetAddress().getHostName();
                System.out.println("Client " + clientHostname + " da ket noi.");

                

                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // Nhận hostname của server từ client
            String serverHostnameFromClient = in.readLine();
            System.out.println("Client bao ket noi tu server: " + serverHostnameFromClient);
            
            String input;
            do {
                out.println("[Server] " + menu());  // Hiển thị menu sau mỗi lần thực hiện xong chức năng
                input = in.readLine();

                switch (input) {
                    case "0":
                        out.println("[Server] Thoat chuong trinh.");
                        break;
                    case "1":
                        out.println("[Server] Nhap 1 so: ");
                        handleSingleNumber(in, out);
                        break;
                    case "2":
                        out.println("[Server] Nhap 1 so nguyen duong: ");
                        handleSinglePositiveNumber(in, out);
                        break;
                    case "3":
                        out.println("[Server] Nhap hai so nguyen duong: ");
                        handleTwoPositiveNumbers(in, out);
                        break;
                    case "4":
                        out.println("[Server] Nhap chuoi: ");
                        handleString(in, out);
                        break;
                    case "5":
                        out.println("[Server] Nhap chuoi: ");
                        handleStringProcessing(in, out);
                        break;
                    case "6":
                        out.println("[Server] Nhap chuoi: ");
                        handleCharacterFrequency(in, out);
                        break;
                    default:
                        out.println("[Server] Lua chon khong hop le, vui long thu lai!");
                        break;
                }
            } while (!input.equals("0"));

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    private void handleSingleNumber(BufferedReader in, PrintWriter out) throws IOException {
        try {
            int so = Integer.parseInt(in.readLine());
            out.println("---------------------------------------");
            out.println("So nguyen to: " + So.laSoNguyenTo(so));
            out.println("So chinh phuong: " + So.laSoChinhPhuong(so));
            out.println("So hoan hao: " + So.laSoHoanHao(so));
            out.println("So Armstrong: " + So.laSoArmstrong(so));
            out.println("---------------------------------------");
        } catch (NumberFormatException e) {
            out.println("Loi: Vui long nhap so hop le!");
        }
    }

    private void handleSinglePositiveNumber(BufferedReader in, PrintWriter out) throws IOException {
        try {
            int m = Integer.parseInt(in.readLine());
            out.println("---------------------------------------");
            out.println("Tong chu so: " + So.tongChuSo(m));
            out.println("Tich chu so: " + So.tichChuSo(m));
            out.println("---------------------------------------");
        } catch (NumberFormatException e) {
            out.println("Loi: Vui long nhap so nguyen duong hop le!");
        }
    }

    private void handleTwoPositiveNumbers(BufferedReader in, PrintWriter out) throws IOException {
        try {
            out.println("[Server] Nhap so thu nhat: ");
            int a = Integer.parseInt(in.readLine());
            out.println("[Server] Nhap so thu hai: ");
            int b = Integer.parseInt(in.readLine());
            out.println("---------------------------------------");
            out.println("Uoc chung lon nhat: " + So.ucln(a, b));
            out.println("Boi chung nho nhat: " + So.bcnn(a, b));
            out.println("---------------------------------------");
        } catch (NumberFormatException e) {
            out.println("Loi: Vui long nhap so nguyen duong hop le!");
        }
    }

    private void handleString(BufferedReader in, PrintWriter out) throws IOException {
        String chuoi = in.readLine();
        out.println("---------------------------------------");
        out.println("[Server] Chuoi dao: " + Chuoi.daoChuoi(chuoi));
        out.println("---------------------------------------");
    }

    private void handleStringProcessing(BufferedReader in, PrintWriter out) throws IOException {
        String str = in.readLine();
        int luaChon;
        do {
            out.println("---------------------------------------");
            out.println("1. In ra chuoi dao nguoc cua chuoi da cho");
            out.println("2. Doi chuoi da cho sang chu hoa");
            out.println("3. Doi chuoi da cho sang chu thuong");
            out.println("4. Doi chuoi da cho sang vua chu hoa vua chu thuong");
            out.println("5. Dem so tu co trong chuoi da cho");
            out.println("6. In ra cac nguyen am co trong chuoi da cho");
            out.println("0. Quay lai");
            out.println("[Server] Chon chuc nang: ");
            luaChon = Integer.parseInt(in.readLine());
            out.println("---------------------------------------");
            switch (luaChon) {
                case 1:
                    out.println("Chuoi dao nguoc: " + Chuoi.daoChuoi(str));
                    break;
                case 2:
                    out.println("Chu hoa: " + Chuoi.vietHoa(str));
                    break;
                case 3:
                    out.println("Chu thuong: " + Chuoi.vietThuong(str));
                    break;
                case 4:
                    out.println("Doi hoa-thuong: " + Chuoi.daoChuHoaThuong(str));
                    break;
                case 5:
                    out.println("So tu: " + Chuoi.demSoTu(str));
                    break;
                case 6:
                    out.println("Nguyen am: " + Chuoi.layNguyenAm(str));
                    break;
                case 0:
                    out.println("Quay lai menu chinh.");
                    break;
                default:
                    out.println("Lua chon khong hop le, vui long thu lai!");
                    break;
            }
        } while (luaChon != 0);
    }

    private void handleCharacterFrequency(BufferedReader in, PrintWriter out) throws IOException {
        String vanBan = in.readLine();
        int chon6;
        do {
            out.println("---------------------------------------");
            out.println("1. In tung tu tren moi dong");
            out.println("2. Bang tan so ky tu");
            out.println("0. Quay lai");
            out.println("[Server] Chon chuc nang: ");
            chon6 = Integer.parseInt(in.readLine());
            out.println("---------------------------------------");
            switch (chon6) {
                case 1:
                    out.println("Tung tu tren moi dong:");
                    for(char kyTu : vanBan.toCharArray()) {
                        out.println(kyTu);
                    }
                    break;
                case 2:
                    out.println("Bang tan so ky tu:");
                    Map<Character, Integer> tanSo = new HashMap<>();
                    for (char c : vanBan.toCharArray()) {
                        tanSo.put(c, tanSo.getOrDefault(c, 0) + 1);
                    }
                    tanSo.forEach((k, v) -> out.println(k + ": " + v));
                    break;
                case 0:
                    out.println("Quay lai menu chinh.");
                    break;
                default:
                    out.println("Lua chon khong hop le, vui long thu lai!");
                    break;
            }
        } while (chon6 != 0);
    }

    private static String menu() {
        return """
                =======================================
                |    CHUONG TRINH BAI TAP JAVA        |
                =======================================
                1. Kiem tra so
                2. Tong va tich cac chu so
                3. Uoc chung lon nhat va boi chung nho nhat
                4. Dao chuoi
                5. Xu ly chuoi ky tu
                6. Tan so xuat hien ky tu trong chuoi
                0. Thoat
                =======================================
                [Server] Chon bai tap (1-6, 0 de thoat):
                """;
    }
}
