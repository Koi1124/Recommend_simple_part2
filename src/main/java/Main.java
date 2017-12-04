import java.awt.Color;
import java.io.IOException;
import java.util.*;

import com.github.davidmoten.guavamini.Lists;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import wordcloud.WordCloudBuilder;

public class Main extends Application{

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homework_3.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("简易搜索");
        primaryStage.setScene(new Scene(root,800,500));
        Controller controller = fxmlLoader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();
    }



    public static void main(String[] args) throws IOException {
        launch(args);
        String[] words = new String[]{
                "词频","java","考试","小明","毕业"
        };
        List<String> s = Lists.newArrayList(words);
        Color[] colors = new Color[10];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = new Color(
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128);
        }

        WordCloudBuilder.buildWordCouldByWords(200,200,4,20,10,s,new Color(-1),"data.png",colors);
    }

}





