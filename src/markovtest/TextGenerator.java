/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markovtest;

import java.util.ArrayList;
import java.util.Arrays;
import markov.SentenceBuilder;

/**
 *
 * @author Andy
 */
public class TextGenerator {

    SentenceBuilder<String> builder;

    public static void main(String[] args) {
        TextGenerator texgen = new TextGenerator();
        texgen.addString("Parmenides argued that it is impossible to doubt that thinking actually occurs. But thinking must have an object, therefore something beyond thinking really exists. Parmenides deduced that what really exists must have certain properties—for example, that it cannot come into existence or cease to exist, that it is a coherent whole, that it remains the same eternally (in fact, exists altogether outside time). This is known as the third man argument. Plato (427–347 BC) combined rationalism with a form of realism. The philosopher's work is to consider being, and the essence (ousia) of things. But the characteristic of essences is that they are universal. The nature of a man, a triangle, a tree, applies to all men, all triangles, all trees. Plato argued that these essences are mind-independent \"forms\", that humans (but particularly philosophers) can come to know by reason, and by ignoring the distractions of sense-perception. Modern rationalism begins with Descartes. Reflection on the nature of perceptual experience, as well as scientific discoveries in physiology and optics, led Descartes (and also Locke) to the view that we are directly aware of ideas, rather than objects. This view gave rise to three questions:");
        for (int i = 0; i < 2; i++) {
            System.out.println(texgen.generateSentence());
        }
    }

    public TextGenerator() {
        builder = new SentenceBuilder<String>();
    }

    public String generateSentence() {
        ArrayList<String> words = builder.generateSentence();
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word);
        }
        return generateSentence();
    }

    public void addString(String s) {
        ArrayList<ArrayList<String>> sentences = deliminate(s);
        for (ArrayList<String> sentence : sentences) {
            builder.addSentence(sentence);
        }
    }

    //returns a list of sentences where a sentence is a list of words
    public static ArrayList<ArrayList<String>> deliminate(String in) {
        String[] sentences = in.split("[\\.\\?\\!]");
        ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();
        for (String sentence : sentences) {
            if (!sentence.equals("")) {
                String[] words = sentence.split("[\\s\\W]");
                ArrayList<String> wordsOut = new ArrayList<String>();
                for(String word : words) {
                    if(!word.equals("")) {
                        wordsOut.add(word);
                    }
                }
                out.add(wordsOut);
            }
        }
        return out;
    }
}
