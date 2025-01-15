import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class FriendsScene {

    public static Scene createFriendsScene() {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        content.getChildren().add(new Label("Friends content here"));
        return new Scene(content, 600, 400);
    }
}