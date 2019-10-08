public class Main {

    public static void main(String[] args) {
        System.out.println("Check");
        DocumentStats documentStats = new DocumentStats();
        try {
            documentStats.processFile("data.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
