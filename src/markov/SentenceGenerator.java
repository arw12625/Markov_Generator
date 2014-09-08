/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markov;

import java.util.ArrayList;

/**
 *
 * @author Andy
 */
public class SentenceGenerator<T> extends MarkovGenerator<T> {

    T beginToken;
    T endToken;

    public SentenceGenerator(MarkovChain<T> chain, T begin, T end) {
        super(chain);
        this.beginToken = begin;
        this.endToken = end;
    }

    public ArrayList<T> generateSentence() {
        setEntryToken(beginToken);
        ArrayList<T> sentence = new ArrayList<T>();
        T currentToken = null;
        while (!(currentToken = getNext()).equals(endToken)) {
            sentence.add(currentToken);
        }
        return sentence;
    }
}
