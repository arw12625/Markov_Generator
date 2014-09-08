/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Andy
 */
public class MarkovChain<T> {

    private HashMap<T, HashMap<T, Integer>> counts;
    private HashMap<T, HashMap<T, Double>> probabilities;

    public MarkovChain() {
        counts = new HashMap<T, HashMap<T, Integer>>();
        probabilities = new HashMap<T, HashMap<T, Double>>();
    }

    public void addAll(ArrayList<T> sentence) {
        T last = null;
        for (T t : sentence) {
            addCount(last, t);
            //System.out.println(last + " " + counts.get(last));
            updateProbability(last);
            last = t;
        }
    }

    public Set<Entry<T, Double>> getEntrySet(T token) {
        return probabilities.get(token).entrySet();
    }

    private void addCount(T first, T second) {
        if (first != null && second != null) {
            HashMap<T, Integer> row = counts.get(first);
            //System.out.println("ROW " + row);
            if (row == null) {
                counts.put(first, new HashMap<T, Integer>());
                probabilities.put(first, new HashMap<T, Double>());
                row = counts.get(first);
            }
            Integer count = row.get(second);
            row.put(second, count == null ? 1 : count + 1);
        }
    }

    private void updateProbability(T first) {
        if (first != null) {
            HashMap<T, Integer> countRow = counts.get(first);
            HashMap<T, Double> probRow = new HashMap<T, Double>();
            int sum = 0;
            Set<Entry<T, Integer>> set = countRow.entrySet();
            for (Entry<T, Integer> ent : set) {
                sum += ent.getValue();
            }
            for (Entry<T, Integer> ent : set) {
                probRow.put(ent.getKey(), new Double(ent.getValue()) / sum);
            }
            probabilities.put(first, probRow);
        }

    }

    public double getProbability(T first, T second) {
        return probabilities.get(first).get(second);
    }
}
