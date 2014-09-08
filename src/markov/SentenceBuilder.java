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
public class SentenceBuilder<T> {

    private MarkovChain<Token> chain;
    private SentenceGenerator<Token> generator;
    private Token begin, end;

    public SentenceBuilder() {
        chain = new MarkovChain<Token>();
        begin = new Token(SpecialToken.begin);
        end = new Token(SpecialToken.end);
        generator = new SentenceGenerator<Token>(chain, begin, end);
    }

    public void addSentence(ArrayList<T> words) {
        ArrayList<Token> mod = new ArrayList<Token>();
        mod.add(begin);
        for (T t : words) {
            mod.add(new Token(t));
        }
        mod.add(end);
        chain.addAll(mod);
    }

    public ArrayList<T> generateSentence() {
        ArrayList<Token> temp = generator.generateSentence();
        ArrayList<T> sentence = new ArrayList<T>();
        for (Token t : temp) {
            sentence.add(t.getParameter());
        }
        return sentence;
    }

    private class Token {

        private T t;
        private SpecialToken st;

        Token(T t) {
            this.t = t;
        }

        Token(SpecialToken st) {
            this.st = st;
        }

        public T getParameter() {
            return this.t;
        }

        public SpecialToken getSpecial() {
            return st;
        }

        @Override
        public String toString() {
            if (t != null) {
                return t.toString();
            } else {
                return st.toString();
            }
        }

        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((t==null)?0:t.hashCode())+((st==null)?0:st.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Token other = (Token) obj;
            if (this.t != other.t && (this.t == null || !this.t.equals(other.t))) {
                return false;
            }
            if (this.st != other.st) {
                return false;
            }
            return true;
        }
    }

    private enum SpecialToken {

        begin, end
    }
}
