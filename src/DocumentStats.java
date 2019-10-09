import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static helper.Helper.println;


public class DocumentStats implements BigramCalculator {

    private int totalWordCount;
    private Map<String, Integer> singleWordProbability;
    private Map<String, Map<String, Integer>> pairedWordProbability;

    public DocumentStats() {
        totalWordCount = 0;
        singleWordProbability = new HashMap<>();
        pairedWordProbability = new HashMap<>();
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
        String prevWord = null;
        String currWord = null;
        boolean firstSentenceWordAppeared = false;
        do {
            ch = reader.read();
            if ((ch > 64 && ch < 91) || (ch > 96 && ch < 123)) {
                if (wordBuilder.length() == 0 && firstSentenceWordAppeared) {
                    prevWord = currWord;
                }
                wordBuilder.append((char)ch);
            } else {
                if (wordBuilder.length() != 0) {
                    //We have a word
                    totalWordCount++;
                    currWord = wordBuilder.toString().toLowerCase();
                    //Clear word builder
                    wordBuilder.setLength(0);


                    //Update/set value in the single words map
                    Integer currentWordCount = singleWordProbability.getOrDefault(currWord, 0);
                    currentWordCount = currentWordCount + 1;
                    singleWordProbability.put(currWord, currentWordCount);

                    if (firstSentenceWordAppeared) {
                        //println("The pair is = %s, %s", prevWord, currWord);
                        Map<String, Integer> followingWordsMap = pairedWordProbability.getOrDefault(prevWord, new HashMap<>());
                        Integer followingWordCount = followingWordsMap.getOrDefault(currWord, 0);
                        followingWordCount = followingWordCount + 1;
                        followingWordsMap.put(currWord, followingWordCount);
                        pairedWordProbability.put(prevWord, followingWordsMap);
                        //println(followingWordsMap.toString());
                    }

                    if (!firstSentenceWordAppeared) {
                        firstSentenceWordAppeared = true;
                    }
                }

                if (ch == 46) {
                    firstSentenceWordAppeared = false;
                }
            }
        } while (ch != -1);
    }

    @Override
    public int getWordCount() {
        return totalWordCount;
    }

    @Override
    public double probability(String w0) {
        w0 = w0.toLowerCase();

        Integer singleWordCount = singleWordProbability.get(w0);
        if (singleWordCount == null) {
            return 0;
        }
        return singleWordCount / (double) totalWordCount;
    }

    @Override
    public double probability(String w1, String w0) {
        w0 = w0.toLowerCase();
        w1 = w1.toLowerCase();

        Integer firstWordCount = singleWordProbability.get(w0);
        if (firstWordCount == null) {
            return 0;
        }
        Map<String, Integer> secondMap = pairedWordProbability.get(w0);
        if (secondMap == null) {
            return 0;
        }

        Integer secondWordCount = secondMap.getOrDefault(w1, 0);
        return secondWordCount / (double) firstWordCount;
    }
}
