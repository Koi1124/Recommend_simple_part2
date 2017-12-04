package util;

import javafx.util.Pair;



public interface StockSorter {

    Pair<String,Double>[] sort(Pair<String,Double>[] pairs);

    Pair<String,Double>[] sort(Pair<String,Double>[] pairs,boolean order);

}
