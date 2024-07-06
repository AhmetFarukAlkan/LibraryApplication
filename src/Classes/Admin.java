/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 *
 * @author dursun
 */
public class Admin extends Member{
    
    //Constructor
    public Admin() {
    }
    //Constructor
    public Admin(String Nickname, String Password, String Name, String Surname, String Mail, String TelNo, LocalDate BirthDate) {
        super(Nickname, Password, Name, Surname, Mail, TelNo, BirthDate);
    }
    
    //sql den kullanıcının id numarası döndürülür.

    public void setİdFromSQL() throws SQLException{
        int id = -1;
        Statement st;
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","ucanayak2002")) {
            st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from admins");
            while(rs.next()){
                User user = new User(rs.getString("Nickname"), rs.getString("Password"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Mail"), rs.getString("TelNo"), rs.getDate("BirthDate").toLocalDate() );
                if(user.getNickname().equals(getNickname())){
                    setId(Integer.valueOf(rs.getString("id")));
                }
            }
        }
        st.close();   
    }
}
