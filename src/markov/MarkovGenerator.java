/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markov;

import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Andy
 */
public class MarkovGenerator<T> {
    
    private MarkovChain<T> chain;
    private T lastToken;
    
    public MarkovGenerator(MarkovChain chain) {
        this.chain = chain;
    }
    
    public T getNext() {
        Set<Entry<T, Double>> row = chain.getEntrySet(lastToken);
        double random = Math.random();
        for(Entry<T, Double> entry : row) {
            random -= entry.getValue();
            if(random <= 0) {
                lastToken = entry.getKey();
                return lastToken;
            }
        }
        return null;
    }
    
    public void setEntryToken(T entryToken) {
        lastToken = entryToken;
    }
}
