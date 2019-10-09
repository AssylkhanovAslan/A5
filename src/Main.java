import static helper.Helper.println;

public class Main {

    public static void main(String[] args) {
        System.out.println("Check");
        DocumentStats documentStats = new DocumentStats();
        try {
            documentStats.processFile("sonnets.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        double check = documentStats.probability("it", "let");
        println("%.18f", check);
    }

}
