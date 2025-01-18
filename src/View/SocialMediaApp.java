package view;

import Controller.UserOperation;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;

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

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
         
        Button loginButton = new Button("Login");
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(new Scene(createWelcomeScene(primaryStage), 900, 600)));

        loginBox.getChildren().addAll(titleLabel, emailField, passwordField, loginButton, backButton, errorLabel);
        loginButton.setOnAction(e -> {
             if(!emailField.getText().isEmpty() && !passwordField.getText().isEmpty()){
                  try { 
                      
                      if(operation.login(emailField.getText(), passwordField.getText())){
                        mainLayout = createMainLayout(createProfileScene());
                        primaryStage.setScene(new Scene(mainLayout, 900, 600));
                      }
                      else {
                        Text errorText = new Text("there's error on the email or the password") ; 
                        loginBox.getChildren().add(errorText) ; 
             }
                      
                  } catch (SQLException ex) {
                      Logger.getLogger(SocialMediaApp.class.getName()).log(Level.SEVERE, null, ex);
                  }
              
              }
             else {
                 Text requriedText = new Text("you didn't insert in some field") ; 
                  loginBox.getChildren().add(requriedText) ; 
             }
             
           
        });
        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                System.out.println("TextField is active (focused).");
            } else {
                System.out.println("TextField lost focus.");
            }
        });
//        Button backButton = new Button("Back");
        
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
        VBox profileBox = new VBox(15);
        profileBox.setPadding(new Insets(20));
        profileBox.setStyle("-fx-background-color: #dfe3ee;");

        // Create a single HBox for all header elements
        HBox headerBox = new HBox(15);
        headerBox.setAlignment(Pos.CENTER_LEFT);

        // Profile Picture
        Image profileImage = new Image("file:default-profile.png");
        ImageView profileImageView = new ImageView(profileImage);
        profileImageView.setFitWidth(80);
        profileImageView.setFitHeight(80);
        profileImageView.setStyle("-fx-background-radius: 50%; -fx-border-radius: 50%; -fx-border-color: #B0C4DE;");

        // User Info (username and bio)
        VBox userInfo = new VBox(5);
        Label usernameLabel = new Label("Username: " + username);
        usernameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        Label bioLabel = new Label(bio);
        bioLabel.setWrapText(true);
        bioLabel.setMaxWidth(200);
        bioLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");
        userInfo.getChildren().addAll(usernameLabel, bioLabel);

        // Buttons and Post Field
        HBox interactionBox = new HBox(10);
        interactionBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(interactionBox, Priority.ALWAYS);

        Button editProfileButton = new Button("Edit Profile");
        editProfileButton.setStyle("-fx-background-color: #3b5998; -fx-text-fill: white; -fx-font-size: 14px;");

        Button changePictureButton = new Button("Change Picture");
        changePictureButton.setStyle("-fx-background-color: #3b5998; -fx-text-fill: white; -fx-font-size: 14px;");

        TextField postTextField = new TextField();
        postTextField.setPromptText("What's on your mind?");
        postTextField.setStyle("-fx-font-size: 14px;");
        postTextField.setPrefWidth(300);
        HBox.setHgrow(postTextField, Priority.ALWAYS);

        Button addPostButton = new Button("Post");
        addPostButton.setStyle("-fx-background-color: #3b5998; -fx-text-fill: white; -fx-font-size: 14px;");

        interactionBox.getChildren().addAll(
            editProfileButton,
            changePictureButton,
            postTextField,
            addPostButton
        );

        // Add all elements to headerBox
        headerBox.getChildren().addAll(profileImageView, userInfo, interactionBox);

        // Posts List
        VBox postsList = new VBox(10);
        postsList.setPadding(new Insets(10));

        // ScrollPane for posts
        ScrollPane postsScrollPane = new ScrollPane(postsList);
        postsScrollPane.setFitToWidth(true);
        postsScrollPane.setPrefHeight(450); // Increased height for posts
        postsScrollPane.setStyle("-fx-background: transparent; -fx-border-color: transparent;");

        // Add Post Button Action
        addPostButton.setOnAction(e -> {
            String newPost = postTextField.getText();
            if (!newPost.isEmpty()) {
                postsList.getChildren().add(0, createPostWidget(username, newPost, "0 likes", "0 comments"));
                postTextField.clear();
            }
        });

        // Edit Profile Button Action
        editProfileButton.setOnAction(e -> {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Edit Profile");

            VBox dialogContent = new VBox(10);
            dialogContent.setPadding(new Insets(10));

            TextField usernameField = new TextField(username);
            usernameField.setPromptText("Enter new username");

            TextArea bioField = new TextArea(bio);
            bioField.setPromptText("Enter new bio");
            bioField.setWrapText(true);

            dialogContent.getChildren().addAll(
                new Label("Username:"), usernameField,
                new Label("Bio:"), bioField
            );

            dialog.getDialogPane().setContent(dialogContent);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            dialog.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    username = usernameField.getText();
                    bio = bioField.getText();
                    usernameLabel.setText("Username: " + username);
                    bioLabel.setText(bio);
                }
            });
        });

        // Change Picture Button Action
        changePictureButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Profile Picture");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );

            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                try {
                    Image newProfileImage = new Image(new FileInputStream(selectedFile));
                    profileImageView.setImage(newProfileImage);
                } catch (FileNotFoundException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Could not load the selected image file.");
                    alert.showAndWait();
                }
            }
        });

        // Add components to profileBox
        profileBox.getChildren().addAll(headerBox, postsScrollPane);

        return profileBox;
    }

    private VBox createPostWidget(String username, String content, String likes, String comments) {
        VBox postBox = new VBox(10);
        postBox.setStyle(" -fx-padding: 15px; -fx-border-radius: 5px;");

        // Post Content
        VBox postContent = new VBox(5);
        Label postUser = new Label(username);
        postUser.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #3b5998;");
        Label postText = new Label(content);
        postText.setWrapText(true);
        postText.setStyle(" -fx-text-fill: #3b5998;");
        postContent.getChildren().addAll(postUser, postText);

        // Comments section for new comments
        VBox commentsBox = new VBox(5);
        commentsBox.setStyle("-fx-padding-left: 5px;");

        // Counters section in one line with separator
        HBox countersSection = new HBox(15);
        countersSection.setAlignment(Pos.CENTER_LEFT);
        countersSection.setPadding(new Insets(5, 0, 5, 0));
        
        Label likesLabel = new Label(likes);
        likesLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");
        
        
        Label commentsCountLabel = new Label(comments);
        commentsCountLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");
        
        countersSection.getChildren().addAll(likesLabel, commentsCountLabel);

        // Interaction section (Like button, comment field, and comment button) in one line
        HBox interactionSection = new HBox(10);
        interactionSection.setAlignment(Pos.CENTER_LEFT);
        
        Button likeButton = new Button("Like");
        likeButton.setStyle("-fx-background-color: #8b9dc3; -fx-text-fill: white;");
        
        // Create a region to push the comment field and button to the right
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        TextField commentTextField = new TextField();
        commentTextField.setPromptText("Write a comment...");
        commentTextField.setStyle("-fx-font-size: 14px;");
        commentTextField.setPrefWidth(350);
        
        Button commentButton = new Button("Comment");
        commentButton.setStyle("-fx-background-color: #8b9dc3; -fx-text-fill: white;");
        
        interactionSection.getChildren().addAll(likeButton, spacer, commentTextField, commentButton);

        // Separator line
        Separator postSeparator = new Separator();
        postSeparator.setStyle("-fx-background-color: #dfe3ee;");

        // Like Button Action with toggle functionality
        final boolean[] isLiked = {false};
        likeButton.setOnAction(e -> {
            isLiked[0] = !isLiked[0];
            String currentLikes = likesLabel.getText().split(" ")[0];
            int likeCount = Integer.parseInt(currentLikes);
            
            if (isLiked[0]) {
                likeCount++;
                likeButton.setStyle("-fx-background-color: #3b5998; -fx-text-fill: white;");
            } else {
                likeCount--;
                likeButton.setStyle("-fx-background-color: #8b9dc3; -fx-text-fill: white;");
            }
            
            likesLabel.setText(likeCount + " likes");
        });

        // Comment Button Action
        commentButton.setOnAction(e -> {
            String comment = commentTextField.getText();
            if (!comment.isEmpty()) {
                Label newCommentLabel = new Label(username + ": " + comment);
                newCommentLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
                commentsBox.getChildren().add(newCommentLabel);
                commentTextField.clear();
                
                // Update comments counter
                String currentComments = commentsCountLabel.getText().split(" ")[0];
                int commentCount = Integer.parseInt(currentComments);
                commentCount++;
                commentsCountLabel.setText(commentCount + " comments");
            }
        });

        // Add all elements to the postBox
        postBox.getChildren().addAll(
            postContent,
            countersSection,
            commentsBox,
            interactionSection,
            postSeparator
        );
        
        return postBox;
    }

    private VBox createFriendsScene() {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        
        VBox friendsBox = new VBox(20);
        friendsBox.setPrefWidth(screenBounds.getWidth());
        friendsBox.setPrefHeight(screenBounds.getHeight());
        friendsBox.setPadding(new Insets(30));
        friendsBox.setAlignment(Pos.TOP_CENTER);
        friendsBox.setStyle("-fx-background-color: #f0f2f5;");

        Label titleLabel = new Label("Friends");
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));
        titleLabel.setTextFill(Color.valueOf("#1877f2"));

        // Friend List using ListView
        ListView<HBox> friendListView = new ListView<>();
        friendListView.setStyle("-fx-background-color: white; -fx-background-radius: 8; " +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");

        // Sample friends
        String[] friends = {"Alice", "Bob", "Charlie", "David"};
        for (String friend : friends) {
            HBox friendItem = new HBox(10);
            friendItem.setAlignment(Pos.CENTER_LEFT);
            friendItem.setPadding(new Insets(10));

            ImageView friendAvatar = new ImageView(new Image("file:default-profile.png"));
            friendAvatar.setFitWidth(40);
            friendAvatar.setFitHeight(40);
            friendAvatar.setStyle("-fx-background-radius: 50%;");

            Label friendName = new Label(friend);
            friendName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #1c1e21;");

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            Button addFriendButton = new Button("Add Friend");
            addFriendButton.setStyle("-fx-background-color: #42b72a; -fx-text-fill: white; -fx-font-size: 14px;");
            
            // Change button text and style on click
            addFriendButton.setOnAction(e -> {
                addFriendButton.setText("Pending");
                addFriendButton.setStyle("-fx-background-color: #cccccc; -fx-text-fill: #666666; -fx-font-size: 14px;");
                addFriendButton.setDisable(true); // Optionally disable the button
            });

            friendItem.getChildren().addAll(friendAvatar, friendName, spacer, addFriendButton);
            friendListView.getItems().add(friendItem);
        }

        // ScrollPane for friend list
        ScrollPane friendScrollPane = new ScrollPane(friendListView);
        friendScrollPane.setFitToWidth(true);
        friendScrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        friendsBox.getChildren().addAll(titleLabel, friendScrollPane);
        return friendsBox;
    }

    private VBox createPostsScene() {
        VBox postsBox = new VBox(10);
        postsBox.setPadding(new Insets(20));
        postsBox.setStyle("-fx-background-color: #dfe3ee;");

        Label title = new Label("Feeds");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #3b5998;");

        // Post Text Field and Add Post Button
        HBox postHBox = new HBox(10);
        postHBox.setStyle("-fx-padding: 5px;");

        TextField postTextField = new TextField();
        postTextField.setPromptText("What's on your mind?");
        postTextField.setStyle("-fx-font-size: 14px;");
        postTextField.setPrefWidth(500); // Make text field wider

        Button addPostButton = new Button("Post");
        addPostButton.setStyle("-fx-background-color: #3b5998; -fx-text-fill: white; -fx-font-size: 14px;");

        postHBox.getChildren().addAll(postTextField, addPostButton);

        // Posts List
        VBox postsList = new VBox(10);
        postsList.setPadding(new Insets(10));

        // Sample Posts using createPostWidget instead of createPostWithLikeButton
        postsList.getChildren().addAll(
            createPostWidget("Bob", "Hello everyone!", "10 likes", "5 comments"),
            createPostWidget("Alice", "This is our social media platform project...", "15 likes", "7 comments")
        );

        // ScrollPane for posts
        ScrollPane scrollPane = new ScrollPane(postsList);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400);
        scrollPane.setStyle("-fx-background: #dfe3ee; -fx-border-color: transparent;");

        // Add Post Button Action
        addPostButton.setOnAction(e -> {
            String newPost = postTextField.getText();
            if (!newPost.isEmpty()) {
                postsList.getChildren().add(0, createPostWidget(username, newPost, "0 likes", "0 comments"));
                postTextField.clear();
            }
        });

        postsBox.getChildren().addAll(title, postHBox, scrollPane);
        return postsBox;
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #3b5998; -fx-text-fill: white; -fx-font-size: 16px; -fx-border-radius: 5;");
        button.setPrefWidth(150);
    }

    private VBox createEnhancedPostWidget(String username, String content, String likes, String comments) {
        VBox postBox = new VBox(12);
        postBox.setStyle("-fx-background-color: white; " +
                         "-fx-padding: 20; " +
                         "-fx-background-radius: 12; " +
                         "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");

        // Post Header with user info
        HBox header = new HBox(12);
        header.setAlignment(Pos.CENTER_LEFT);

        // User Avatar
        ImageView userAvatar = new ImageView(new Image("file:default-profile.png"));
        userAvatar.setFitWidth(45);
        userAvatar.setFitHeight(45);
        userAvatar.setStyle("-fx-background-radius: 50%; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 4, 0, 0, 0);");

        // User Info
        VBox userInfo = new VBox(3);
        Label userName = new Label(username);
        userName.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #1877f2;");

        Label timeStamp = new Label("2 hours ago");
        timeStamp.setStyle("-fx-text-fill: #8e8e8e; -fx-font-size: 13px;");
        userInfo.getChildren().addAll(userName, timeStamp);

        header.getChildren().addAll(userAvatar, userInfo);

        // Post Content
        Label postContent = new Label(content);
        postContent.setWrapText(true);
        postContent.setStyle("-fx-font-size: 15px; -fx-padding: 15 0; -fx-text-fill: black;");

        // Interaction Stats
        HBox stats = new HBox(20);
        stats.setPadding(new Insets(10, 0, 10, 0));
        stats.setAlignment(Pos.CENTER_LEFT);
        
        // Like stats with icon
        HBox likeStats = new HBox(5);
        likeStats.setAlignment(Pos.CENTER_LEFT);
        Label likeIcon = new Label("👍");
        Label likesLabel = new Label(likes);
        likeStats.getChildren().addAll(likeIcon, likesLabel);
        
        // Comment stats with icon
        HBox commentStats = new HBox(5);
        commentStats.setAlignment(Pos.CENTER_LEFT);
        Label commentIcon = new Label("💬");
        Label commentsLabel = new Label(comments);
        commentStats.getChildren().addAll(commentIcon, commentsLabel);
        
        stats.getChildren().addAll(likeStats, commentStats);
        stats.setStyle("-fx-font-size: 14px; -fx-text-fill: #8e8e8e;");

        // Interaction Buttons
        HBox actions = new HBox(0);
        actions.setAlignment(Pos.CENTER);
        actions.setPrefWidth(Double.MAX_VALUE);
        actions.setPadding(new Insets(5, 0, 0, 0));

        // Create buttons with equal width
        Button likeButton = createInteractionButton("👍 Like");
        Button commentButton = createInteractionButton("💬 Comment");
        Button shareButton = createInteractionButton("↗ Share");

        actions.getChildren().addAll(likeButton, commentButton, shareButton);

        // Add all components to post box with spacing
        postBox.getChildren().addAll(header, postContent, stats, actions);
        return postBox;
    }

    // Helper method to create consistent interaction buttons
    private Button createInteractionButton(String text) {
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(36);
        HBox.setHgrow(button, Priority.ALWAYS);
        
        // Default style with updated colors
        button.setStyle("-fx-background-color: transparent; " +
                        "-fx-text-fill: #606770; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-cursor: hand; " +
                        "-fx-background-radius: 4;");
        
        // Hover effect with new color
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: #f2f3f5; " +
                           "-fx-text-fill: #1877f2; " +
                           "-fx-font-size: 14px; " +
                           "-fx-font-weight: bold; " +
                           "-fx-cursor: hand; " +
                           "-fx-background-radius: 4;");
        });
        
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color: transparent; " +
                           "-fx-text-fill: #606770; " +
                           "-fx-font-size: 14px; " +
                           "-fx-font-weight: bold; " +
                           "-fx-cursor: hand; " +
                           "-fx-background-radius: 4;");
        });
        
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
