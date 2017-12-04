package util;

import javafx.util.Pair;

public class StockSorterImpl implements StockSorter {

    @Override
    public Pair<String,Double>[] sort(Pair<String,Double>[] pairs) {
        //TODO: write your code here
        Pair<String,Double> insert;
        int position;
        for (int i=1;i<pairs.length;i++) {
            insert = pairs[i];
            position=i-1;
            while (position>=0&&insert.getValue()>pairs[position].getValue()) {
                pairs[position+1]=pairs[position];
                position--;
            }
            pairs[position+1]=insert;
        }
        return pairs;
    }

    @Override
    public Pair<String, Double>[] sort(Pair<String, Double>[] pairs, boolean order) {
        //TODO: write your code here
        Pair<String,Double> insert;
        int position;
        if (order) {
            for (int i=1;i<pairs.length;i++) {
                insert = pairs[i];
                position = i - 1;
                while (position >= 0 && insert.getValue() <= pairs[i].getValue()) {
                    pairs[position + 1] = pairs[position];
                    position--;
                }
                pairs[position + 1] = insert;
            }
        }
        else {
            for (int i=1;i<pairs.length;i++) {
                insert = pairs[i];
                position=i-1;
                while (position>=0&&insert.getValue()>=pairs[i].getValue()) {
                    pairs[position+1]=pairs[position];
                    position--;
                }
                pairs[position+1]=insert;
            }
        }
        return pairs;
    }
}
