package chatbot;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int chon;

        do {
            System.out.println("=======================================");
            System.out.println("|    CHUONG TRINH BAI TAP JAVA        |");
            System.out.println("=======================================");
            System.out.println("1. Kiem tra so");
            System.out.println("2. Tong va tich cac chu so");
            System.out.println("3. Uoc chung lon nhat va boi chung nho nhat");
            System.out.println("4. Dao chuoi");
            System.out.println("5. Xu ly chuoi ky tu");
            System.out.println("6. Tan so xuat hien ky tu trong chuoi");
            System.out.println("0. Thoat");
            System.out.println("=======================================");
            System.out.print("Chon bai tap (1-6, 0 de thoat): ");

            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1:
                    System.out.print("Nhap 1 so: ");
                    int so = sc.nextInt();
                    System.out.println("---------------------------------------");
                    System.out.println("So nguyen to: " + So.laSoNguyenTo(so));
                    System.out.println("So chinh phuong: " + So.laSoChinhPhuong(so));
                    System.out.println("So hoan hao: " + So.laSoHoanHao(so));
                    System.out.println("So Armstrong: " + So.laSoArmstrong(so));
                    System.out.println("---------------------------------------");
                    break;
                case 2:
                    System.out.print("Nhap 1 so nguyen duong: ");
                    int m = sc.nextInt();
                    System.out.println("---------------------------------------");
                    System.out.println("Tong chu so: " + So.tongChuSo(m));
                    System.out.println("Tich chu so: " + So.tichChuSo(m));
                    System.out.println("---------------------------------------");
                    break;
                case 3:
                    System.out.print("Nhap hai so nguyen duong: ");
                    int a = sc.nextInt();
                    int b = sc.nextInt();
                    System.out.println("---------------------------------------");
                    System.out.println("Uoc chung lon nhat: " + So.ucln(a, b));
                    System.out.println("Boi chung nho nhat: " + So.bcnn(a, b));
                    System.out.println("---------------------------------------");
                    break;
                case 4:
                    System.out.print("Nhap chuoi: ");
                    String chuoi = sc.nextLine();
                    System.out.println("---------------------------------------");
                    System.out.println("Chuoi dao: " + Chuoi.daoChuoi(chuoi));
                    System.out.println("---------------------------------------");
                    break;
                case 5:
                    System.out.print("Nhap chuoi: ");
                    String str = sc.nextLine();
                    int luaChon;
                    do {
                        System.out.println("---------------------------------------");
                        System.out.println("1. In ra chuoi dao nguoc cua chuoi da cho");
                        System.out.println("2. Doi chuoi da cho sang chu hoa");
                        System.out.println("3. Doi chuoi da cho sang chu thuong");
                        System.out.println("4. Doi chuoi da cho sang vua chu hoa vua chu thuong");
                        System.out.println("5. Dem so tu co trong chuoi da cho");
                        System.out.println("6. In ra cac nguyen am co trong chuoi da cho");
                        System.out.println("0. Quay lai");
                        System.out.print("Chon chuc nang: ");
                        luaChon = sc.nextInt();
                        sc.nextLine();
                        System.out.println("---------------------------------------");
                        switch (luaChon) {
                            case 1:
                                System.out.println("Chuoi: " + str);
                                break;
                            case 2:
                                System.out.println("Chu hoa: " + Chuoi.vietHoa(str));
                                break;
                            case 3:
                                System.out.println("Chu thuong: " + Chuoi.vietThuong(str));
                                break;
                            case 4:
                                System.out.println("Doi hoa-thuong: " + Chuoi.daoChuHoaThuong(str));
                                break;
                            case 5:
                                System.out.println("So tu: " + Chuoi.demSoTu(str));
                                break;
                            case 6:
                                System.out.println("Nguyen am: " + Chuoi.layNguyenAm(str));
                                break;
                        }
                    } while (luaChon != 0);
                    break;
                case 6:
                    System.out.print("Nhap chuoi: ");
                    String vanBan = sc.nextLine();
                    int chon6;
                    do {
                        System.out.println("---------------------------------------");
                        System.out.println("1. In tung tu tren moi dong");
                        System.out.println("2. Bang tan so ky tu");
                        System.out.println("0. Quay lai");
                        System.out.print("Chon chuc nang: ");
                        chon6 = sc.nextInt();
                        sc.nextLine();
                        System.out.println("---------------------------------------");
                        switch (chon6) {
                            case 1:
                                System.out.println("Tung ky tu tren moi dong:");
                                for (char kyTu : vanBan.toCharArray()) {
                                    System.out.println(kyTu);
                                }
                                break;
                            case 2:
                                System.out.println("Bang tan so ky tu:");
                                Map<Character, Integer> tanSo = new HashMap<>();
                                for (char c : vanBan.toCharArray()) {
                                    tanSo.put(c, tanSo.getOrDefault(c, 0) + 1);
                                }
                                tanSo.forEach((k, v) -> System.out.println(k + ": " + v));
                                break;
                        }
                    } while (chon6 != 0);
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long chon lai!");
            }
        } while (chon != 0);
        sc.close();
    }
}
