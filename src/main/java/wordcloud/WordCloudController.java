package wordcloud;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class WordCloudController implements Initializable {

    @FXML
    private ImageView wordCloudImage;
    private Image image = new Image(new File("D:/IDEA/homework_3/data.png").toURI().toString());

    public void initialize(URL location, ResourceBundle resources) {

    }
    public void init() {
        wordCloudImage.setImage(image);
    }

    public void saveClickedAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("保存词云");
        fileChooser.setInitialFileName("data.png");
        fileChooser.setInitialDirectory(new File("D:\\IDEA\\homework_3\\"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG","*.png"));
        File file = fileChooser.showSaveDialog(null);
        if(file!=null) {
            try{
                ImageIO.write(SwingFXUtils.fromFXImage(image,null),"png",file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
