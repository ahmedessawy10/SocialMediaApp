import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane root;
    private VBox sidebar;
    private Scene homeScene;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        root = new BorderPane();
        sidebar = createSidebar();
        root.setLeft(sidebar);

        // Create initial Home scene
        homeScene = HomeScene.createHomeScene();
        primaryStage.setScene(homeScene);

        // Add click handlers to sidebar items
        sidebar.getChildren().forEach(node -> {
            if (node instanceof Label) {
                Label label = (Label) node;
                label.setOnMouseClicked(event -> {
                    switch (label.getText()) {
                        case "Home":
                            primaryStage.setScene(homeScene);
                            break;
                        case "Posts":
                            primaryStage.setScene(PostsScene.createPostsScene());
                            break;
                        case "Comments":
                            primaryStage.setScene(CommentsScene.createCommentsScene());
                            break;
                        case "Likes":
                            primaryStage.setScene(LikesScene.createLikesScene());
                            break;
                        case "Friends":
                            primaryStage.setScene(FriendsScene.createFriendsScene());
                            break;
                    }
                });
            }
        });

        primaryStage.setTitle("Social Media Platform");
        primaryStage.show();
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10));
        sidebar.getChildren().addAll(
                createClickableLabel("Home"),
                createClickableLabel("Posts"),
                createClickableLabel("Comments"),
                createClickableLabel("Likes"),
                createClickableLabel("Friends")
        );
        return sidebar;
    }

    private Label createClickableLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-cursor: hand;"); // Set cursor to hand for better UI feedback
        return label;
    }

    public static void main(String[] args) {
        launch(args);
    }
}