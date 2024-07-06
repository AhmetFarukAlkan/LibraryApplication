/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package FXML;

import Classes.Admin;
import Classes.Datas;
import Classes.User;
import DataStructures.SQL;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author dursun
 */
public class AddUser extends Application {

    Datas datas = new Datas();
    SQL sql = new SQL();
    Admin admin = new Admin();
    
    @FXML
    public void setAdmin(Admin admin) {
        this.admin = admin;
    } 
    
    @FXML
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddUserFXML.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private DatePicker BirthDate;

    @FXML
    private Button BtnExit;

    @FXML
    private Button BtnRegister;

    @FXML
    private TextField txtfldMail;

    @FXML
    private TextField txtfldName;

    @FXML
    private TextField txtfldNick;

    @FXML
    private PasswordField txtfldPswrd1;

    @FXML
    private PasswordField txtfldPswrd2;

    @FXML
    private TextField txtfldSurname;

    @FXML
    private TextField txtfldTelNo;

    @FXML
    void ClickExit(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminMainFXML.fxml"));
        Parent root = loader.load();

        AdminMainScreen adminMainScreen = loader.getController();
        adminMainScreen.setAdminMainScreen(this.admin);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ClickRegisterAdmin(MouseEvent event) throws SQLException {
        if(!txtfldMail.getText().equals("")&&!txtfldName.getText().equals("")&&!txtfldNick.getText().equals("")&&!txtfldPswrd1.getText().equals("")&&!txtfldPswrd2.getText().equals("")&&!txtfldSurname.getText().equals("")&&!txtfldTelNo.getText().equals("")&& BirthDate.valueProperty().getValue()!=null){
            if(txtfldPswrd1.getText().equals(txtfldPswrd2.getText())){
                if(datas.getUsers().search(txtfldNick.getText()) == -1){
                    Admin admin =new Admin(txtfldNick.getText(), txtfldPswrd1.getText(), txtfldName.getText(), txtfldSurname.getText(), txtfldMail.getText(), txtfldTelNo.getText(), BirthDate.getValue());                    
                    sql.saveAdmin(admin);
                    admin.setİdFromSQL();
                    datas.addUser(admin);  
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,"Admin kaydedildi.",ButtonType.CANCEL);
                    alert.showAndWait();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Girilen kullanıcı adı daha önce başkası tarafından alınmış.",ButtonType.CANCEL);
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR,"Girilen iki şifre farklı.",ButtonType.CANCEL);
                alert.showAndWait();  
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Tüm alanları doldurunuz.",ButtonType.CANCEL);
            alert.showAndWait();
        }
    }

    @FXML
    void ClickRegisterMember(MouseEvent event) throws SQLException, IOException, Exception {
        if(!txtfldMail.getText().equals("")&&!txtfldName.getText().equals("")&&!txtfldNick.getText().equals("")&&!txtfldPswrd1.getText().equals("")&&!txtfldPswrd2.getText().equals("")&&!txtfldSurname.getText().equals("")&&!txtfldTelNo.getText().equals("")&& BirthDate.valueProperty().getValue()!=null){
            if(txtfldPswrd1.getText().equals(txtfldPswrd2.getText())){
                if(datas.getUsers().search(txtfldNick.getText()) == -1){
                    User user =new User(txtfldNick.getText(), txtfldPswrd1.getText(), txtfldName.getText(), txtfldSurname.getText(), txtfldMail.getText(), txtfldTelNo.getText(), BirthDate.getValue());                    
                    sql.saveUser(user);
                    user.setİdFromSQL();
                    datas.addUser(user);  
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,"Kullanıcı kaydedildi.",ButtonType.CANCEL);
                    alert.showAndWait();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Girilen kullanıcı adı daha önce başkası tarafından alınmış.",ButtonType.CANCEL);
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR,"Girilen iki şifre farklı.",ButtonType.CANCEL);
                alert.showAndWait();  
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Tüm alanları doldurunuz.",ButtonType.CANCEL);
            alert.showAndWait();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
