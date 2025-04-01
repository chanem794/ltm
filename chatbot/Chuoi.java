package chatbot;

public class Chuoi {
    public static String daoChuoi(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    public static String vietHoa(String str) {
        return str.toUpperCase();
    }

    public static String vietThuong(String str) {
        return str.toLowerCase();
    }

    public static String daoChuHoaThuong(String str) {
        StringBuilder swapped = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) swapped.append(Character.toLowerCase(c));
            else if (Character.isLowerCase(c)) swapped.append(Character.toUpperCase(c));
            else swapped.append(c);
        }
        return swapped.toString();
    }

    public static int demSoTu(String str) {
        return str.trim().split("\\s+").length;
    }

    public static String layNguyenAm(String str) {
        return str.replaceAll("[^AEIOUaeiou]", "");
    }
}
