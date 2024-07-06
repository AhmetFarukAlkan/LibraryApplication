package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


public class Borrowed extends Book{
    private String MemberName, Status;
    private LocalDate DateOfIssue, DeliveryDate;
    private Book MainBook;

    //Constructor
    public Borrowed() {
        DateOfIssue = LocalDate.now();
        DeliveryDate = DateOfIssue.plusDays(7);
    }
    //Constructor
    public Borrowed(String MemberName, String Status, LocalDate DateOfIssue, LocalDate DeliveryDate, int NumberPage, String BookName, String author, LocalDate YearOfPublication, String Publisher, int piece, int availablePiece) {
        super(NumberPage, BookName, author, YearOfPublication, Publisher, piece, availablePiece);
        this.MemberName = MemberName;
        this.Status = Status;
        this.DateOfIssue = DateOfIssue;
        this.DeliveryDate = DeliveryDate;
    }
    
    //sql den kitapların id numarası döndürülür.
    @Override
    public void setİdFromSQL() throws SQLException{
        int id = -1;
        Statement st;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from borrowedbooks");
            while(rs.next()){
                Borrowed book = new Borrowed(rs.getString("MemberName"), rs.getString("Status"), rs.getDate("DateOfIssue").toLocalDate(), rs.getDate("DeliveryDate").toLocalDate(), rs.getInt("NumberPage"), rs.getString("BookName"), rs.getString("author"), rs.getDate("YearOfPublication").toLocalDate(), rs.getString("Publisher"), rs.getInt("piece"), rs.getInt("availablePiece"));
                if(book.getBookName().equals(getBookName())&&book.getAuthor().equals(getAuthor())&& book.getNumberPage()==getNumberPage() && book.getPublisher().equals(getPublisher())&& book.getYearOfPublication().equals(getYearOfPublication())&& book.getMemberName().equals(getMemberName())&& book.getStatus().equals(getStatus())&& book.getDateOfIssue().equals(book.getDateOfIssue())&&book.getDeliveryDate().equals(book.getDeliveryDate())){
                    setId(Integer.valueOf(rs.getString("id")));
                }
            }
        }
        st.close();   
    }
    
    //encapsulation
    public void extendTime(){
        setDeliveryDate(getDeliveryDate().plusDays(7));
    }
    
    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String MemberName) {
        this.MemberName = MemberName;
    }

    public String getStatus() {
        int value = LocalDate.now().compareTo(DeliveryDate);
        if (value > 0)
            setStatus("Süresi Dolmuştur");
        else{
            setStatus("Süresi Vardır");
        }
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public LocalDate getDateOfIssue() {
        return DateOfIssue;
    }

    public void setDateOfIssue(LocalDate DateOfIssue) {
        this.DateOfIssue = DateOfIssue;
    }

    public LocalDate getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(LocalDate DeliveryDate) {
        this.DeliveryDate = DeliveryDate;
    }

    public Book getMainBook() {
        return MainBook;
    }

    public void setMainBook(Book MainBook) {
        this.MainBook = MainBook;
    }    
    
}
