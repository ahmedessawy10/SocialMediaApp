import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PostsScene {

    public static Scene createPostsScene() {
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));

        // Example: Display images for posts
        ImageView imageView1 = new ImageView(new Image("image1.jpg")); // Replace with actual image paths
        ImageView imageView2 = new ImageView(new Image("image2.jpg"));
        ImageView imageView3 = new ImageView(new Image("image3.jpg"));

        content.getChildren().addAll(imageView1, imageView2, imageView3);

        return new Scene(content, 600, 400);
    }
}