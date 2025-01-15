package View;

import Controller.UserOperation;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SocialMediaApp extends Application {

    private String username = "Bob Smith";
    private String bio = "Hello! I'm Bob. Welcome to my profile.";
    private BorderPane mainLayout;
    UserOperation operation = new UserOperation() ; 

    @Override
    public void start(Stage primaryStage) {
        VBox welcomeScene = createWelcomeScene(primaryStage);

        primaryStage.setTitle("Social Media Platform");
        primaryStage.setScene(new Scene(welcomeScene, 900, 600));
        primaryStage.show();
    }

    private VBox createWelcomeScene(Stage primaryStage) {
        VBox welcomeBox = new VBox(30);
        welcomeBox.setPadding(new Insets(50));
        welcomeBox.setAlignment(Pos.CENTER);
        welcomeBox.setStyle("-fx-background-color: linear-gradient(to bottom, #3b5998, #8b9dc3);");

        Label titleLabel = new Label("Welcome to Social Media Platform");
        titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 36));
        titleLabel.setTextFill(Color.WHITE);

        Button loginButton = new Button("Login");
        styleButton(loginButton);
        loginButton.setOnAction(e -> primaryStage.setScene(new Scene(createLoginScene(primaryStage), 900, 600)));

        Button registerButton = new Button("Register");
        styleButton(registerButton);
        registerButton.setOnAction(e -> primaryStage.setScene(new Scene(createRegistrationScene(primaryStage), 900, 600)));

        welcomeBox.getChildren().addAll(titleLabel, loginButton, registerButton);
        return welcomeBox;
    }

    private VBox createRegistrationScene(Stage primaryStage)  {
        VBox registrationBox = new VBox(20);
        registrationBox.setPadding(new Insets(30));
        registrationBox.setAlignment(Pos.CENTER);
        registrationBox.setStyle("-fx-background-color: #dfe3ee;");

        Label titleLabel = new Label("Register");
        titleLabel.setFont(Font.font("Verdana", 30));
        titleLabel.setTextFill(Color.BLUE);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField countryField = new TextField();
        countryField.setPromptText("Country");

        TextField cityField = new TextField();
        cityField.setPromptText("City");

        TextField streetField = new TextField();
        streetField.setPromptText("Street");

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() ||
                emailField.getText().isEmpty() || countryField.getText().isEmpty() ||
                cityField.getText().isEmpty() || streetField.getText().isEmpty()) {
                errorLabel.setText("All fields are required.");
            } else {
                try {
                    //                primaryStage.setScene(new Scene(createLoginScene(primaryStage), 900, 600));
                    if(operation.registration(
                            usernameField.getText() , 
                            emailField.getText() , 
                            passwordField.getText() ,
                            streetField.getText() , 
                            cityField.getText(),
                            countryField.getText() )){
                     primaryStage.setScene(new Scene(createLoginScene(primaryStage), 900, 600));   
                    }
                    else {
                        Text errorText = new Text() ; 
                        errorText.setText("that mail you set it before");
                        registrationBox.getChildren().add(errorText) ; 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SocialMediaApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(new Scene(createWelcomeScene(primaryStage), 900, 600)));

        registrationBox.getChildren().addAll(titleLabel, usernameField, passwordField, emailField, countryField, cityField, streetField, registerButton, backButton, errorLabel);
        return registrationBox;
    }

    private VBox createLoginScene(Stage primaryStage) {
        VBox loginBox = new VBox(20);
        loginBox.setPadding(new Insets(30));
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setStyle("-fx-background-color: #dfe3ee;");

        Label titleLabel = new Label("Login");
        titleLabel.setFont(Font.font("Verdana", 30));
        titleLabel.setTextFill(Color.BLUE);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            mainLayout = createMainLayout(createProfileScene());
            primaryStage.setScene(new Scene(mainLayout, 900, 600));
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(new Scene(createWelcomeScene(primaryStage), 900, 600)));

        loginBox.getChildren().addAll(titleLabel, usernameField, passwordField, loginButton, backButton, errorLabel);
        return loginBox;
    }

    private BorderPane createMainLayout(VBox content) {
        BorderPane layout = new BorderPane();
        layout.setLeft(createSidebar());
        layout.setCenter(content);
        return layout;
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(10));
        sidebar.setStyle("-fx-background-color: #3b5998;");
        sidebar.setAlignment(Pos.TOP_CENTER);

        Button profileButton = createSidebarButton("Profile", "https://cdn-icons-png.flaticon.com/512/149/149071.png",
                () -> mainLayout.setCenter(createProfileScene()));

        Button feedsButton = createSidebarButton("Feeds", "https://cdn-icons-png.flaticon.com/512/565/565294.png",
                () -> mainLayout.setCenter(createPostsScene()));

        Button friendsButton = createSidebarButton("Friends", "https://cdn-icons-png.flaticon.com/512/3135/3135715.png",
                () -> mainLayout.setCenter(createFriendsScene()));

        Button logoutButton = createSidebarButton("Logout", "https://cdn-icons-png.flaticon.com/512/1828/1828479.png",
                () -> System.exit(0));

        sidebar.getChildren().addAll(profileButton, feedsButton, friendsButton, logoutButton);
        return sidebar;
    }

    private Button createSidebarButton(String text, String iconUrl, Runnable action) {
        Image iconImage = new Image(iconUrl);
        ImageView icon = new ImageView(iconImage);
        icon.setFitWidth(20);
        icon.setFitHeight(20);
        Button button = new Button(text, icon);
        button.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #3b5998; -fx-font-size: 14px;");
        button.setOnAction(e -> action.run());
        return button;
    }

private VBox createProfileScene() {
    VBox profileBox = new VBox(20);
    profileBox.setPadding(new Insets(20));
    profileBox.setStyle("-fx-background-color: #dfe3ee;");

    // Add Profile Picture
    Image profileImage = new Image("https://example.com/your-profile-image.jpg");  // Replace with your actual image URL or file path
    ImageView profileImageView = new ImageView(profileImage);
    profileImageView.setFitWidth(100);  // Set image width
    profileImageView.setFitHeight(100); // Set image height
    profileImageView.setStyle("-fx-background-radius: 50%; -fx-border-radius: 50%; -fx-border-color: #B0C4DE;");

    // Title for Profile
    Label title = new Label("My Profile");
    title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #3b5998;");

    // Bio
    Label bioLabel = new Label(bio);
    bioLabel.setWrapText(true);

    // Posts List
    VBox postsList = new VBox(10);
    postsList.setPadding(new Insets(10));

    postsList.getChildren().addAll(
            createPostWidget("User 1", "This is my first post!", "10 likes", "5 comments"),
            createPostWidget("User 2", "What a beautiful day!", "20 likes", "8 comments")
    );

    profileBox.getChildren().addAll(profileImageView, title, bioLabel, postsList);
    return profileBox;
}

    private VBox createPostWidget(String username, String content, String likes, String comments) {
        VBox postBox = new VBox(10);
        postBox.setPadding(new Insets(10));
        postBox.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #B0C4DE; -fx-border-radius: 5;");

        Label usernameLabel = new Label(username);
        usernameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label contentLabel = new Label(content);
        contentLabel.setWrapText(true);

        HBox statsBox = new HBox(10);
        statsBox.setAlignment(Pos.CENTER_LEFT);
        statsBox.getChildren().addAll(new Label(likes), new Label(comments));

        postBox.getChildren().addAll(usernameLabel, contentLabel, statsBox);
        return postBox;
    }

    private VBox createFriendsScene() {
        VBox friendsBox = new VBox(20);
        friendsBox.setPadding(new Insets(30));
        friendsBox.setAlignment(Pos.CENTER);
        friendsBox.setStyle("-fx-background-color: #dfe3ee;");

        Label titleLabel = new Label("Friends");
        titleLabel.setFont(Font.font("Verdana", 30));
        titleLabel.setTextFill(Color.BLUE);

        Label friendsListLabel = new Label("Friend 1, Friend 2, Friend 3, ...");

        friendsBox.getChildren().addAll(titleLabel, friendsListLabel);
        return friendsBox;
    }

    private VBox createPostsScene() {
        VBox postsBox = new VBox(10);
        postsBox.setPadding(new Insets(20));
        postsBox.setStyle("-fx-background-color: #dfe3ee;");

        Label title = new Label("Feeds");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #3b5998;");

        ScrollPane scrollPane = new ScrollPane();
        VBox postsList = new VBox(10);
        postsList.setPadding(new Insets(10));

        postsList.getChildren().addAll(
                createPostWithLikeButton("Bob", "Hello!", "10 likes", "5 comments"),
                createPostWithLikeButton("Alice", "This is our social media platform project...", "15 likes", "7 comments")
        );

        scrollPane.setContent(postsList);
        postsBox.getChildren().addAll(title, scrollPane);
        return postsBox;
    }

    private VBox createPostWithLikeButton(String username, String content, String likes, String comments) {
        VBox postBox = new VBox(10);
        postBox.setPadding(new Insets(10));
        postBox.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #B0C4DE; -fx-border-radius: 5;");

        Label usernameLabel = new Label(username);
        usernameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label contentLabel = new Label(content);
        contentLabel.setWrapText(true);

        HBox statsBox = new HBox(10);
        statsBox.setAlignment(Pos.CENTER_LEFT);
        Label likeCountLabel = new Label(likes);
        Button likeButton = new Button("Like");
        likeButton.setOnAction(e -> likeCountLabel.setText(Integer.toString(Integer.parseInt(likes.split(" ")[0]) + 1) + " likes"));

        statsBox.getChildren().addAll(likeCountLabel, new Label(comments), likeButton);

        postBox.getChildren().addAll(usernameLabel, contentLabel, statsBox);
        return postBox;
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #3b5998; -fx-text-fill: white; -fx-font-size: 16px; -fx-border-radius: 5;");
        button.setPrefWidth(150);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
