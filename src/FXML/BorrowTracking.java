/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package FXML;

import Classes.Admin;
import Classes.Borrowed;
import Classes.Datas;
import Classes.Member;
import Classes.User;
import DataStructures.HashTable01;
import DataStructures.SQL;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author dursun
 */
public class BorrowTracking extends Application implements Initializable {
    
    Admin admin = new Admin();
    Datas datas = new Datas();
    SQL sql = new SQL();
    
    public void setAdmin(Admin admin) {
        this.admin = admin;
    } 
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BorrowTrackingFXML.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

   @FXML
    private Button BtnGiveBook;

    @FXML
    private Button BtnListBooks;

    @FXML
    private ImageView Exit;

    @FXML
    private TableView<Borrowed> TblVwLoanedOut;

    @FXML
    private Button btnExtendDate;

    @FXML
    private TableColumn<Borrowed, String> ColumnBookName;

    @FXML
    private TableColumn<Borrowed, LocalDate> ColumnDateOfIssue;

    @FXML
    private TableColumn<Borrowed, Integer> ColumnId;

    @FXML
    private TableColumn<Borrowed, String> ColumnMemberName;

    @FXML
    private TableColumn<Borrowed, String> ColumnStatu;

    @FXML
    private TableColumn<Borrowed, LocalDate> DateOfIssueDeliveryDate;
    
    ObservableList<Borrowed> books;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ColumnBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        ColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));;
        ColumnMemberName.setCellValueFactory(new PropertyValueFactory<>("MemberName"));
        ColumnStatu.setCellValueFactory(new PropertyValueFactory<>("Status"));
        ColumnDateOfIssue.setCellValueFactory(new PropertyValueFactory<>("DateOfIssue"));
        DateOfIssueDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("DeliveryDate"));
    }
    
    
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
    void ClickExtendDate(MouseEvent event) throws SQLException {
        Borrowed borrowed = TblVwLoanedOut.getSelectionModel().getSelectedItem();
        if(borrowed != null){           
            borrowed.setDeliveryDate(borrowed.getDeliveryDate().plusDays(7));     
            sql.UpdateBorrowedBookİnfo(borrowed);
            ClickListBooks(event);
        }
    }

    @FXML
    void ClickListBooks(MouseEvent event) {
        books= TblVwLoanedOut.getItems();
        TblVwLoanedOut.getItems().clear();
        GetAllBook();
        TblVwLoanedOut.setItems(books);
    }
    
    public void  GetAllBook(){
        for (int i = 0; i < 2000; i++) {
            if(datas.getUsers().getMember(i) != null && datas.getUsers().getMember(i).getObject() instanceof User){
                User user1 = (User)datas.getUsers().getMember(i).getObject();
                DataStructures.Node temp = user1.getBooks().head;  
                while(temp != null)
                {
                    temp.data.getStatus();
                    books.add(temp.data);
                    temp = temp.next;
                }
            }
        }
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
