package Classes;

import java.sql.*;
import java.time.LocalDate;


public class Book {
    private int id, NumberPage, piece, availablePiece;
    private String BookName, author, Publisher;
    private LocalDate YearOfPublication;
    public Book() {
        this.id = 1;
    }

    public Book(int NumberPage, String BookName, String author, LocalDate YearOfPublication, String Publisher, int piece, int availablePiece) {
        this.NumberPage = NumberPage;
        this.BookName = BookName;
        this.author = author;
        this.YearOfPublication = YearOfPublication; 
        this.Publisher = Publisher;
        this.piece = piece;
        this.availablePiece = availablePiece;
    }

    public void setİdFromSQL() throws SQLException{
        int id = -1;
        Statement st;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from books");
            while(rs.next()){
                Book book = new Book(rs.getInt("NumberPage"), rs.getString("BookName"), rs.getString("author"), rs.getDate("YearOfPublication").toLocalDate(), rs.getString("Publisher"), rs.getInt("piece"), rs.getInt("availablePiece"));
                if(book.getBookName().equals(getBookName())&&book.getAuthor().equals(getAuthor())&& book.getNumberPage()==getNumberPage() && book.getPublisher().equals(getPublisher())&& book.getYearOfPublication().equals(getYearOfPublication())){
                    setId(Integer.valueOf(rs.getString("id")));
                }
            }
        }
        st.close();   
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String BookName) {
        this.BookName = BookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getYearOfPublication() {
        return YearOfPublication;
    }

    public void setYearOfPublication(LocalDate YearOfPublication) {
        this.YearOfPublication = YearOfPublication;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String Publisher) {
        this.Publisher = Publisher;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }    

    public int getAvailablePiece() {
        return availablePiece;
    }

    public void setAvailablePiece(int availablePiece) {
        this.availablePiece = availablePiece;
    }

    public int getNumberPage() {
        return NumberPage;
    }

    public void setNumberPage(int NumberPage) {
        this.NumberPage = NumberPage;
    }
}
