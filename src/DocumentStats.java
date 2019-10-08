import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DocumentStats implements BigramCalculator {

    private int wordCount;
    private Map<String, Integer> singleWordProbability;
    private Map<String, Map<String, Integer>> followingWordProbability;

    public DocumentStats() {
        wordCount = 0;
        singleWordProbability = new HashMap<>();
        followingWordProbability = new HashMap<>();
    }

    @Override
    public void processFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new IOException("File does not exist");
        }
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        int ch;
        StringBuilder wordBuilder = new StringBuilder();
        do {
            ch = reader.read();
            if ((ch > 64 && ch < 91) || (ch > 96 && ch < 122)) {
                wordBuilder.append((char)ch);
            } else {
                if (wordBuilder.length() != 0) {
                    println(wordBuilder.toString());
                    wordBuilder.setLength(0);
                }
            }
        } while (ch != -1);
    }

    @Override
    public int getWordCount() {
        return wordCount;
    }

    @Override
    public double probability(String w0) {
        Integer singleWordCount = singleWordProbability.get(w0);
        if (singleWordCount == null) {
            return 0;
        }
        return singleWordCount / (double) wordCount;
    }

    @Override
    public double probability(String w1, String w0) {
        return 0;
    }

    private void print(String msg) {
        System.out.print(msg);
    }

    private void print(String format, Object... args) {
        System.out.print(String.format(format, args));
    }

    private void println(String msg) {
        System.out.println(msg);
    }

    private void println(String format, Object... args) {
        System.out.println(String.format(format, args));
    }
}
