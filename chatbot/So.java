package chatbot;

public class So {
    public static boolean laSoNguyenTo(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static boolean laSoChinhPhuong(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }

    public static boolean laSoHoanHao(int num) {
        int sum = 1;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i != num / i) sum += num / i;
            }
        }
        return sum == num && num != 1;
    }

    public static boolean laSoArmstrong(int num) {
        int original = num, sum = 0, digits = String.valueOf(num).length();
        while (num > 0) {
            sum += Math.pow(num % 10, digits);
            num /= 10;
        }
        return sum == original;
    }
    
    public static int tongChuSo(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static int tichChuSo(int num) {
        int product = 1;
        while (num > 0) {
            product *= num % 10;
            num /= 10;
        }
        return product;
    }

    public static int ucln(int a, int b) {
        return b == 0 ? a : ucln(b, a % b);
    }

    public static int bcnn(int a, int b) {
        return a * (b / ucln(a, b));
    }
}
