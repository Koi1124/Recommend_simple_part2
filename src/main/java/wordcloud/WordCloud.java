package wordcloud;

import segmenter.ChineseSegmenter;
import segmenter.ChineseSegmenterImpl;
import tf_idf.TF_IDF;
import tf_idf.TF_IDFImpl;
import vo.StockInfo;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordCloud {

    public static void buildWordCloud(int index, StockInfo[] stockInfos) throws IOException {
        Color[] colors = new Color[10];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = new Color(
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128);
        }

        ChineseSegmenter chineseSegmenter = new ChineseSegmenterImpl();
        TF_IDF tf_idf = new TF_IDFImpl();
        List<String> words = chineseSegmenter.getWordsFromInput(stockInfos,index);
        List<String> keyWords = tf_idf.getResult(words,stockInfos);
        List<String> ultimate = new ArrayList<>();

        if(keyWords.size()>20) {
            for(int i=0;i<20;i++) {
                ultimate.add(keyWords.get(i));
            }
        }
        else {
            for(int i=0;i<keyWords.size();i++) {
                ultimate.add(keyWords.get(i));
            }
        }

        WordCloudBuilder.buildWordCouldByWords(200,200,4,20,10,ultimate,new Color(-1),"D:/IDEA/homework_3/data.png",colors);
    }
}
