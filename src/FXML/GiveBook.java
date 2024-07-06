/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package FXML;

import Classes.Book;
import Classes.Borrowed;
import Classes.Datas;
import Classes.User;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
 *
 * @author dursun
 */

public class GiveBook extends Application implements Initializable{
    
    SQL sql = new SQL();
    User user = new User();
    Datas datas = new Datas();
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GiveBookFXML.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private Button BtnSrch;

    @FXML
    private TableColumn<Borrowed, String> ColumnAuthor;

    @FXML
    private TableColumn<Borrowed, String> ColumnBookName;

    @FXML
    private TableColumn<Borrowed, LocalDate> ColumnDateOfIssue;

    @FXML
    private TableColumn<Borrowed, Integer> ColumnId;

    @FXML
    private TableColumn<Borrowed, Integer> ColumnPageNumber;

    @FXML
    private TableColumn<Borrowed, String> ColumnPublishDate;

    @FXML
    private TableColumn<Borrowed, String> ColumnPublisher;

    @FXML
    private TableColumn<Borrowed, LocalDate> DateOfIssueDeliveryDate;

    @FXML
    private ImageView Exit;

    @FXML
    private Rectangle Giveit;

    @FXML
    private TableView<Borrowed> TblVwBooks;

    @FXML
    private TextField txfldSearch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ColumnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        ColumnBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        ColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));;
        ColumnPageNumber.setCellValueFactory(new PropertyValueFactory<>("NumberPage"));;
        ColumnPublisher.setCellValueFactory(new PropertyValueFactory<>("Publisher"));;
        ColumnPublishDate.setCellValueFactory(new PropertyValueFactory<>("YearOfPublication"));;
        ColumnDateOfIssue.setCellValueFactory(new PropertyValueFactory<>("DateOfIssue"));
        DateOfIssueDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("DeliveryDate"));
    }
    
    ObservableList<Borrowed> books;
    @FXML
    void ClickBtnSearch(MouseEvent event) {
        books= TblVwBooks.getItems();
        TblVwBooks.getItems().clear();
        printLinkedList();
        TblVwBooks.setItems(books);
    }
    
    public void printLinkedList()
    {
        DataStructures.Node temp = user.getBooks().head;       
        while(temp != null)
        {
            books.add(temp.data);
            temp = temp.next;
        }
    }
    
    @FXML
    void setUser(User user) {
        this.user = user;
    }
    
    @FXML
    void ClickExit(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MemberMainFXML.fxml"));
        Parent root = loader.load();

        MemberMainScreen memberMainScreen= loader.getController();
        memberMainScreen.setMemberMainScreen(user);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();     
    }

    @FXML
    void ClickGiveit(MouseEvent event) throws SQLException {
        if(TblVwBooks.getSelectionModel().getSelectedItem() != null){
            Book book = datas.getBooks().search(TblVwBooks.getSelectionModel().getSelectedItem().getBookName()).key;
            datas.getBooks().search(book.getBookName()).key.setAvailablePiece(datas.getBooks().search(book.getBookName()).key.getAvailablePiece() + 1);
            sql.UpdateBookİnfo(book);
            user.getBooks().deleteNode(TblVwBooks.getSelectionModel().getSelectedItem());
            sql.deleteBorrowedBook(TblVwBooks.getSelectionModel().getSelectedItem());
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Kitap geri verildi.",ButtonType.CANCEL);
            alert.showAndWait();
        }
        ClickBtnSearch(event);    
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
