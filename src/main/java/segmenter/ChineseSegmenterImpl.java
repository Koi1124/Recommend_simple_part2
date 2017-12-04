package segmenter;


import javafx.util.Pair;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import tf_idf.TF_IDF;
import tf_idf.TF_IDFImpl;
import vo.StockInfo;

import javax.swing.text.html.ListView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChineseSegmenterImpl implements ChineseSegmenter {

    //private TF_IDF tf_idf;
    /**
     * this func will get chinese word from a list of stocks. You need analysis stocks' answer and get answer word.
     * And implement this interface in the class : ChineseSegmenterImpl
     * Example: 我今天特别开心 result : 我 今天 特别 开心
     *
     * @param stocks stocks info
     * @return chinese word
     * @see ChineseSegmenterImpl
     */
    @Override
    public List<String> getWordsFromInput(StockInfo[] stocks,int id) {
        //TODO: write your code here
        Set<String> expectedNature = new HashSet<String>() {{
            add("n");add("v");add("vd");add("vn");add("vf");
            add("vx");add("vi");add("vl");add("vg");
            add("nt");add("nz");add("nw");add("nl");
            add("ng");add("userDefine");add("wh");
        }};
        List<String> words =new ArrayList<String>();

        String s = stocks[id].getALLINFO();
        Result result = ToAnalysis.parse(s);
        List<Term> terms = result.getTerms();
        for(int j=0;j<terms.size();j++) {
            String natureStr = terms.get(j).getNatureStr();
            if (expectedNature.contains(natureStr)) {
                words.add(terms.get(j).getName());
            }
        }

        return words;
    }

    @Override
    public List<String> getWordsFromImport(String s) {
        Set<String> expectedNature = new HashSet<String>() {{
            add("n");add("v");add("vd");add("vn");add("vf");
            add("vx");add("vi");add("vl");add("vg");
            add("nt");add("nz");add("nw");add("nl");
            add("ng");add("userDefine");add("wh");
        }};
        List<String> words = new ArrayList<>();
        Result result = ToAnalysis.parse(s);
        List<Term> terms = result.getTerms();
        for(int i=0;i<terms.size();i++) {
            String natureStr = terms.get(i).getNatureStr();
            if(expectedNature.contains(natureStr)) {
                words.add(terms.get(i).getName());
            }
        }

        return words;
    }
}
