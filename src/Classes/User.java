/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import DataStructures.BListe;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class User extends Member{
    //Kullanıcının ödünç aldığı kitaplar bağlı listede tutulur.
    BListe bListeBooks = new BListe();
    //Constructor 
    public User() {
    }
    //Constructor 
    public User(String Nickname, String Password, String Name, String Surname, String Mail, String TelNo, LocalDate BirthDate) {
        super(Nickname, Password, Name, Surname, Mail, TelNo, BirthDate);
    }
    //sql den kullanıcının id numarası döndürülür.
    public void setİdFromSQL() throws SQLException{
        int id = -1;
        Statement st;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from users");
            while(rs.next()){
                User user = new User(rs.getString("Nickname"), rs.getString("Password"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Mail"), rs.getString("TelNo"), rs.getDate("BirthDate").toLocalDate() );
                if(user.getNickname().equals(getNickname())){
                    setId(Integer.valueOf(rs.getString("id")));
                }
            }
        }
        st.close();   
    }
    //Encapsulation
    public BListe getBooks(){
        return bListeBooks;
    }
    
    public void addBook(Borrowed boughtBook){
        bListeBooks.addElementLast(boughtBook);
    }
    
    public void deleteBook(Borrowed boughtBook){
        bListeBooks.deleteNode(boughtBook);
    }
    
    
}
