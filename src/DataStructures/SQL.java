package DataStructures;


import Classes.Admin;
import Classes.Book;
import Classes.Borrowed;
import Classes.Datas;
import Classes.User;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;

public class SQL {
    //Veri tabanına bitap kaydetmek
    public void saveBook(Book book) throws SQLException {
        Statement st;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            st = c.createStatement();
            String query = "INSERT INTO books (NumberPage,piece,availablePiece,BookName,author,YearOfPublication,Publisher) VALUES ('"+book.getNumberPage()+"','"+book.getPiece()+"','"+book.getAvailablePiece()+"','"+book.getBookName()+"','"+book.getAuthor()+"','"+book.getYearOfPublication()+"','"+book.getPublisher()+"')";
            int sonuc = st.executeUpdate(query);
            System.out.println(sonuc);
        }
        st.close();
    }
    
    //Veri tabanından kitaplar çekilir.
    //Çekilen kitapları bir sıraya alınır. ve bu sıra döndürülür.
    public Queue getBooksFromSQL() throws SQLException{
        Queue<Book> queue;
        Statement st;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from books");
            queue = new LinkedList<>();
            while(rs.next()){
                Book book = new Book(rs.getInt("NumberPage"), rs.getString("BookName"), rs.getString("author"), rs.getDate("YearOfPublication").toLocalDate(), rs.getString("Publisher"), rs.getInt("piece"), rs.getInt("availablePiece"));
                book.setİdFromSQL();
                queue.add(book);
            }
        }
        st.close();
        return queue;
    }
    //Veri tabanında kitap bilgileri güncellenir.
    public void UpdateBookİnfo(Book  book) throws SQLException{
        String sqlUpdate = "UPDATE books SET NumberPage = '"+book.getNumberPage()+"',piece = '"+book.getPiece()+"',availablePiece = '"+book.getAvailablePiece()+"', BookName= '"+book.getBookName()+"',author = '"+book.getAuthor()+"',YearOfPublication = '"+book.getYearOfPublication()+"', Publisher = '"+book.getPublisher()+"' WHERE id = '"+book.getId()+"'";
        PreparedStatement pstmt;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            pstmt = c.prepareStatement(sqlUpdate);
            int sonuc = pstmt.executeUpdate();
            System.out.println(sonuc);
        }
        pstmt.close();
    }
    //Veri tabanından kitap silinir
    public void deleteBook(Book book) throws SQLException{
        Statement st;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            st = c.createStatement();
            String query = "DELETE FROM books WHERE id = '"+book.getId()+"'";
            int sonuc = st.executeUpdate(query);
            System.out.println(sonuc);
        }
        st.close();
    }
    //Veri tabanına ödünç alınmış kitap kaydetmek
    public void saveBorrowedBook(Borrowed  book) throws SQLException{
        Statement st;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            st = c.createStatement();
            String query = "INSERT INTO borrowedbooks (NumberPage,piece,availablePiece,BookName,author,YearOfPublication,Publisher,Status,MemberName,DateOfIssue,DeliveryDate) VALUES ('"+book.getNumberPage()+"','"+book.getPiece()+"','"+book.getAvailablePiece()+"','"+book.getBookName()+"','"+book.getAuthor()+"','"+book.getYearOfPublication()+"','"+book.getPublisher()+"','"+book.getStatus()+"','"+book.getMemberName()+"','"+book.getDateOfIssue()+"','"+book.getDeliveryDate()+"')";
            int sonuc = st.executeUpdate(query);
            System.out.println(sonuc);
        }
        st.close();
    }
    //Veri tabanından ödünç alınmış kitaplar çekilir.
    
    public void getBorrowedBooksFromSQL(User user) throws SQLException{
        Statement st;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from borrowedbooks");
            while(rs.next()){
                Borrowed book = new Borrowed(rs.getString("MemberName"), rs.getString("Status"), rs.getDate("DateOfIssue").toLocalDate(), rs.getDate("DeliveryDate").toLocalDate(), rs.getInt("NumberPage"), rs.getString("BookName"), rs.getString("author"), rs.getDate("YearOfPublication").toLocalDate(), rs.getString("Publisher"), rs.getInt("piece"), rs.getInt("availablePiece"));
                book.setİdFromSQL();
                if(book.getMemberName().equals(user.getNickname())){
                    user.addBook(book);
                }
            }
        }
        st.close();
    }
    //Veri tabanında ödünç alınmış kitap bilgileri güncellenir.
    public void UpdateBorrowedBookİnfo(Borrowed  book) throws SQLException{
        String sqlUpdate = "UPDATE borrowedbooks SET NumberPage = '"+book.getNumberPage()+"',piece = '"+book.getPiece()+"',availablePiece = '"+book.getAvailablePiece()+"', BookName= '"+book.getBookName()+"',author = '"+book.getAuthor()+"',YearOfPublication = '"+book.getYearOfPublication()+"', Publisher = '"+book.getPublisher()+"' WHERE id = '"+book.getId()+"'";
        PreparedStatement pstmt;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            pstmt = c.prepareStatement(sqlUpdate);
            int sonuc = pstmt.executeUpdate();
            System.out.println(sonuc);
        }
        pstmt.close();
    }
    //Veri tabanından ödünç alınmış kitap silinir
    public void deleteBorrowedBook(Borrowed book) throws SQLException{
        Statement st;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            st = c.createStatement();
            String query = "DELETE FROM borrowedbooks WHERE id = '"+book.getId()+"'";
            int sonuc = st.executeUpdate(query);
            System.out.println(sonuc);
        }
        st.close();
    }
    //Veri tabanına kullanıcı kaydetmek
    public void saveUser(User user) throws SQLException {
        Statement st;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            st = c.createStatement();
            String query = "INSERT INTO users (Nickname, Password, Name, Surname, Mail, TelNo, BirthDate) VALUES ('"+user.getNickname()+"','"+user.getPassword()+"','"+user.getName()+"','"+user.getSurname()+"','"+user.getMail()+"','"+user.getTelNo()+"','"+user.getBirthDate()+"')";
            int sonuc = st.executeUpdate(query);
            System.out.println(sonuc);
        }
        st.close();
    }
    //Veri tabanından üyeler çekilir
    //Çekilen üyeler bir yığına alınır. ve bu sıra döndürülür.

    public MyStackMember getUsersFromSQL() throws SQLException{
        Statement st;
        MyStackMember myStackMember;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from users");
            myStackMember = new MyStackMember();
            while(rs.next()){
                User user = new User(rs.getString("Nickname"), rs.getString("Password"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Mail"), rs.getString("TelNo"), rs.getDate("BirthDate").toLocalDate() );
                user.setİdFromSQL();
                getBorrowedBooksFromSQL(user);
                myStackMember.addElement(user);
            }
        }
        st.close();
        return myStackMember;
    }
    //Veri tabanından üye silinir
    public void deleteUser(User user) throws SQLException{
        Statement st;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            st = c.createStatement();
            String query = "DELETE FROM users WHERE id = '"+user.getId()+"'";
            int sonuc = st.executeUpdate(query);
            System.out.println(sonuc);
        }
        st.close();
    }
    //Veri tabanında üye bilgileri güncellenir.
    public void UpdateUserİnfo(User user) throws SQLException{
            String sqlUpdate = "UPDATE users SET Nickname = '"+user.getNickname()+"',Name = '"+user.getName()+"',Surname = '"+user.getSurname()+"', Mail= '"+user.getMail()+"',TelNo = '"+user.getTelNo()+"',BirthDate = '"+user.getBirthDate()+"' WHERE kullanici_id = '"+user.getId()+"'";
            PreparedStatement pstmt;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) { 
            pstmt = conn.prepareStatement(sqlUpdate);
            int sonuc = pstmt.executeUpdate();
            System.out.println(sonuc);
        }
            pstmt.close();
    }
    //Veri tabanına admin kaydetmek
    public void saveAdmin(Admin admin) throws SQLException {
        Statement st;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            st = c.createStatement();
            String query = "INSERT INTO admins (Nickname, Password, Name, Surname, Mail, TelNo, BirthDate) VALUES ('"+admin.getNickname()+"','"+admin.getPassword()+"','"+admin.getName()+"','"+admin.getSurname()+"','"+admin.getMail()+"','"+admin.getTelNo()+"','"+admin.getBirthDate()+"')";
            int sonuc = st.executeUpdate(query);
            System.out.println(sonuc);
        }
        st.close();
    }
    //Veri tabanından adminler çekilir
    //Çekilen Adminler bir yığına alınır. ve bu sıra döndürülür.

    public MyStackMember getAdminsFromSQL() throws SQLException{
        Statement st;
        MyStackMember myStackMember;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from admins");
            myStackMember = new MyStackMember();
            while(rs.next()){
                Admin admin = new Admin(rs.getString("Nickname"), rs.getString("Password"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Mail"), rs.getString("TelNo"), rs.getDate("BirthDate").toLocalDate() );
                myStackMember.addElement(admin);
            }
        }
        st.close();
        return myStackMember;
    }
    
    
    
    
}
