import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class HomeScene {

    public static Scene createHomeScene() {
        Label homeLabel = new Label("Home");
        TextField postTextField = new TextField();
        Button postButton = new Button("Post");
        ListView<String> postListView = new ListView<>();
        postListView.getItems().addAll(
                "User 3 first post",
                "User 3 second post",
                "User 3 third post"
        );

        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        content.getChildren().addAll(
                homeLabel,
                postTextField,
                postButton,
                postListView
        );

        return new Scene(content, 600, 400);
    }
}