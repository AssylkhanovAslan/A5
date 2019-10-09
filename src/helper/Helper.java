package helper;

public class Helper {

    public static void print(String msg) {
        System.out.print(msg);
    }

    public static void print(String format, Object... args) {
        System.out.print(String.format(format, args));
    }

    public static void println(String msg) {
        System.out.println(msg);
    }

    public static void println(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

}
