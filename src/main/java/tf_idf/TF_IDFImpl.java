package tf_idf;

import javafx.util.Pair;
import util.StockSorterImpl;
import util.StockSorter;
import vo.StockInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TF_IDFImpl implements TF_IDF {
    /**
     * this func you need to calculate words frequency , and sort by frequency.
     * you maybe need to use the sorter written by yourself in example 1
     *
     * @param words the word after segment
     * @return a sorted words
     * @see StockSorter,Pair
     */
    //@Override
    public List<String> getResult(List<String> words, StockInfo[] stockInfos) {
        //TODO: write your code here
        //TF
        HashMap<String, Double> tfMap = new HashMap<String, Double>();
        int freq=0;
        for (int i=0;i<words.size();i++) {
            freq=0;
            for (int j=0;j<words.size();j++) {
                if (words.get(i).equals(words.get(j))) {
                    freq++;
                }
            }
            tfMap.put(words.get(i),(new Double(freq))/words.size());
        }
        //IDF
        HashMap<String,Double> tdfMap = new HashMap<String, Double>();
        int time=0;
        for (int i=0;i<words.size();i++) {
            time=0;
            for (int j=0;j<stockInfos.length;j++) {
                String s = stockInfos[j].getALLINFO();
                if (s.contains(words.get(i))) {
                    time++;
                }
            }
            tdfMap.put(words.get(i),(Math.log(stockInfos.length/new Double(++time))));
        }
        //TF-IDF
        Pair<String,Double>[] pairs = new Pair[tfMap.size()];
        int index=0;
        for (Map.Entry<String,Double> tf:tdfMap.entrySet()) {
            for (Map.Entry<String,Double> idf:tfMap.entrySet()) {
                if (tf.getKey()==idf.getKey()) {
                    double tfidf = idf.getValue()*tf.getValue();
                    Pair<String,Double> pair = new Pair<String, Double>(idf.getKey(),tfidf);
                    pairs[index]=pair;
                    index++;
                }
            }
        }
        //排序
        pairs = new StockSorterImpl().sort(pairs);

        List<String> keyWords = new ArrayList<>();

        for(int i=0;i<pairs.length;i++) {
            keyWords.add(pairs[i].getKey());
        }



        return keyWords;
    }
}
