import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import segmenter.ChineseSegmenter;
import segmenter.ChineseSegmenterImpl;
import tf_idf.TF_IDF;
import tf_idf.TF_IDFImpl;
import util.FileHandler;
import util.FileHandlerImpl;
import vo.StockInfo;
import wordcloud.WordCloud;
import wordcloud.WordCloudController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class Controller implements Initializable {
    //private FileHandler fileHandler = new FileHandlerImpl();

    private ChineseSegmenter chineseSegmenter = new ChineseSegmenterImpl();

    private TF_IDF tf_idf = new TF_IDFImpl();

    private Stage stage;
    @FXML
    private TextField keyWordsTextField;
    @FXML
    private ListView<String> resultListView;

    private String input;

    private StockInfo[] stockInfos;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setStage(Stage stage) {
        this.stage=stage;
    }

    public void newStage() throws Exception{
        FXMLLoader cloudfxmlLoader = new FXMLLoader(getClass().getResource("wordCloud.fxml"));
        Parent cloudRoot = cloudfxmlLoader.load();
        Stage secondStage = new Stage();
        WordCloudController controller = cloudfxmlLoader.getController();
        controller.init();
        secondStage.setTitle("词云");
        secondStage.setScene(new Scene(cloudRoot, 300,400));
        secondStage.show();
    }

    public void inputClickedAction(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT","*.txt"));
        fileChooser.setTitle("选择文件");
        fileChooser.setInitialDirectory(new File("D:\\IDEA\\homework_3\\src\\main\\resources"));
        File file =fileChooser.showOpenDialog(stage);
        if(file!=null) {
            FileHandler fileHandler = new FileHandlerImpl();
            stockInfos = fileHandler.getStockInfoFromFile(file);
        }
    }
    public void inputAction() {

    }
    public void searchClickedAction() {
        Controller controller = new Controller();
        input = keyWordsTextField.getText();
        HashMap<Integer,List<String>> keyWordsMap = controller.seachDeal(stockInfos);
        Pair<Integer,Integer>[] pairs = new Pair[keyWordsMap.size()];

        for(Map.Entry<Integer,List<String>> key:keyWordsMap.entrySet()) {
            int degree = 0;
            List<String> keyWords = key.getValue();
            for(int i=0;i<keyWords.size();i++) {
                if(input.contains(keyWords.get(i))) {
                    degree+=i;
                }
            }
            Pair<Integer,Integer> pair = new Pair<>(key.getKey(),degree);
            pairs[key.getKey()]=pair;
        }
        for(int i=0;i<pairs.length-1;i++) {
            for(int j=i+1;j<pairs.length;j++) {
                if(pairs[j].getValue()<pairs[i].getValue()) {
                    Pair temp = pairs[i];
                    pairs[i]=pairs[j];
                    pairs[j]=temp;
                }
            }
        }


        String[] result = new String[10];
        for(int i=0;i<10;i++) {
            result[i]=stockInfos[pairs[i+1].getKey()].getResult();
        }

        ObservableList<String> strList = FXCollections.observableArrayList(result);
        resultListView.setItems(strList);

        resultListView.setOnMouseClicked(e -> {
            try {
                String information = resultListView.getSelectionModel().getSelectedItem();
                int index=0;
                for(int i=0;i<10;i++) {
                    if(information.equals(result[i])) {
                        index = pairs[i+1].getKey();
                    }
                }
                showCloud(index,stockInfos);
                newStage();
            }catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public void showCloud(int index,StockInfo[] stockInfos) throws IOException{
        WordCloud.buildWordCloud(index, stockInfos);
    }
    public HashMap<Integer,List<String>> seachDeal(StockInfo[] stockInfos) {
        HashMap<Integer,List<String>> infoMap = new HashMap<>();
        for(int i=0;i<stockInfos.length;i++) {
            List<String> words = chineseSegmenter.getWordsFromInput(stockInfos,i);
            infoMap.put(i,words);
        }

        HashMap<Integer,List<String>> sortedinfoMap = new HashMap<>();
        for(Map.Entry<Integer,List<String>> info:infoMap.entrySet()) {
            List<String> sortedWords = tf_idf.getResult(info.getValue(),stockInfos);
            sortedinfoMap.put(info.getKey(),sortedWords);
        }

        return sortedinfoMap;
    }
}
