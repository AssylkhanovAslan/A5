import java.io.IOException;

public interface BigramCalculator {

	/**
	 * Processes a document with the given filename, storing information
	 * about word occurances to be used in calculating probabilities.  If
	 * this method is called multiple times, the collected information is 
	 * added to the data that has already been stored 
	 * 
	 * @param filename the name of the document to be processed
	 * 
	 * @throws IOException if the given document does not exist in the 
	 * current directory
	 */
    public void processFile(String filename) throws IOException;

    /**
     * After one or more documents have been processed, this gives the total 
     * word count of the documents.  If no documents have been processed, 
     * however, the value -1 should be returned
     * 
     * @return the number of words in the processed file(s)
     */
    public int getWordCount();
    
    /**
     * After one or more documents have been processed, this gives the probability 
     * that a word chosen at random in the document(s) is the given word w0.  If 
     * no documents have been processed, however, the value -1.0 should be returned
     * 
     * @return the probability that a word in the document(s) is w0
     */
    public double probability(String w0);
    
    /**
     * After one or more documents have been processed, this gives the probability 
     * that a word chosen at random in the document(s) which follows a given word
     * w0 will be the word w1.  If no documents have been processed, however, the 
     * value -1.0 should be returned
     * 
     * @return the probability that the next word following a w0 in the 
     * document(s) will be w1
     */
    public double probability(String w1, String w0);
        
}
