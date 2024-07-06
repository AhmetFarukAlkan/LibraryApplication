package FXML;

import Classes.Admin;
import Classes.Book;
import Classes.Datas;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AddBook extends Application {
    
    Datas datas = new Datas();
    SQL sql = new SQL();
    Admin admin = new Admin();

    @FXML
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
 
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddBookFXML.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private ImageView Exit;

    @FXML
    private ImageView addBook;

    @FXML
    private TextField txtfldBookName;

    @FXML
    private TextField txtfldNameAuthor;

    @FXML
    private TextField txtfldNamePublisher;

    @FXML
    private TextField txtfldNumber;

    @FXML
    private TextField txtfldPageNumber;

    @FXML
    private DatePicker DtPicekrPublish;

    @FXML
    void ClickAddBook(MouseEvent event) throws SQLException {
        try{
            if(!txtfldBookName.getText().equals("") &&!txtfldNameAuthor.getText().equals("") &&!txtfldNamePublisher.getText().equals("") &&!txtfldNumber.getText().equals("") && !txtfldPageNumber.getText().equals("") && DtPicekrPublish.getValue() != null){
                Book book = new Book(Integer.valueOf(txtfldPageNumber.getText()), txtfldBookName.getText(), txtfldNameAuthor.getText(), DtPicekrPublish.getValue(), txtfldNamePublisher.getText(), Integer.valueOf(txtfldNumber.getText()), Integer.valueOf(txtfldNumber.getText()));                
                if(datas.getBooks().search(book.getBookName())!=null){
                    Book searcbook = datas.getBooks().search(book.getBookName()).key;
                    if(searcbook.getAuthor().equals(book.getAuthor()) && searcbook.getPublisher().equals(book.getPublisher()) && searcbook.getYearOfPublication().equals(book.getYearOfPublication())&& searcbook.getNumberPage() == book.getNumberPage()){
                        datas.getBooks().search(book.getBookName()).key.setPiece(searcbook.getPiece()+datas.getBooks().search(book.getBookName()).key.getPiece());
                        datas.getBooks().search(book.getBookName()).key.setAvailablePiece(searcbook.getAvailablePiece()+datas.getBooks().search(book.getBookName()).key.getAvailablePiece());                
                    }else{
                        sql.saveBook(book);
                        book.setİdFromSQL();
                        datas.addBook(book);
                        
                        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Kitap kütüphanye eklendi",ButtonType.CANCEL);
                        
                        alert.showAndWait();
                    }
                }
                else{
                    sql.saveBook(book);
                    book.setİdFromSQL();
                    datas.addBook(book);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,"Kitap kütüphanye eklendi",ButtonType.CANCEL);
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR,"Lütfen tüm alanları doldurunuz.",ButtonType.CANCEL);
                alert.showAndWait();
            }
        }
        catch(RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Lütfen bilgileri doğru doldurunuz.",ButtonType.CANCEL);
            alert.showAndWait();
        }

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

    public static void main(String[] args) {
        launch(args);
    }
    
}
