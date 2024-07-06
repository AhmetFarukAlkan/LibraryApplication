package FXML;

import Classes.Admin;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminMainScreen extends Application{
    Admin admin = new Admin();

    @FXML
    void setAdminMainScreen(Admin admin) {
        this.admin = admin;
        LblWelcome.setText("Hoşgeldiniz "+admin.getNickname());    
    }
    
    @FXML
    private Label LblWelcome;
    
    @FXML
    void ClickAddBook(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddBookFXML.fxml"));
        Parent root = loader.load();

        AddBook addBook = loader.getController();
        addBook.setAdmin(this.admin);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ClickAddMember(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUserFXML.fxml"));
        Parent root = loader.load();

        AddUser addUser = loader.getController();
        addUser.setAdmin(this.admin);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ClickBorrowTracking(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BorrowTrackingFXML.fxml"));
        Parent root = loader.load();

        BorrowTracking borrowTracking = loader.getController();
        borrowTracking.setAdmin(this.admin);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ClickMemberSettings(MouseEvent event) throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MembersSettingsFXML.fxml"));
        Parent root = loader.load();

        MembersSettings  membersSettings = loader.getController();
        membersSettings.setAdmin(this.admin);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ClickShowBooks(MouseEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowBooksFXML.fxml"));
        Parent root = loader.load();

        ShowBooks showBooks = loader.getController();
        showBooks.setAdmin(this.admin);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void ClickExit(MouseEvent event) throws IOException, Exception {
        Parent tableMmbrScreen = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene tableLoginScene = new Scene(tableMmbrScreen);
     
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Login LoginScreen = new Login();
        LoginScreen.start(window);
        window.show();
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminMainFXML.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
