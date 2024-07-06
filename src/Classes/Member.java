package Classes;

import java.time.LocalDate;

//Soyutlanmış sınıf
public abstract class Member {
    private String Nickname, Password, Name, Surname, Mail, TelNo;
    private LocalDate BirthDate;
    private Member next;
    private int id;
    //Constructor 
    public Member() {
        
    }
    //Constructor 
    public Member(String Nickname, String Password, String Name, String Surname, String Mail, String TelNo, LocalDate BirthDate){
        this.Nickname = Nickname;
        this.Password = Password;
        this.Name = Name;
        this.Surname = Surname;
        this.Mail = Mail;
        this.TelNo = TelNo;
        this.BirthDate = BirthDate;
    }
    
    
    /**
     * @return the Nickname
     */
    public String getNickname() {
        return Nickname;
    }

    /**
     * @param Nickname the Nickname to set
     */
    public void setNickname(String Nickname) {
        this.Nickname = Nickname;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Surname
     */
    public String getSurname() {
        return Surname;
    }

    /**
     * @param Surname the Surname to set
     */
    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    /**
     * @return the Mail
     */
    public String getMail() {
        return Mail;
    }

    /**
     * @param Mail the Mail to set
     */
    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    /**
     * @return the TelNo
     */
    public String getTelNo() {
        return TelNo;
    }

    /**
     * @param TelNo the TelNo to set
     */
    public void setTelNo(String TelNo) {
        this.TelNo = TelNo;
    }

    /**
     * @return the BirthDate
     */
    public LocalDate getBirthDate() {
        return BirthDate;
    }

    /**
     * @param BirthDate the BirthDate to set
     */
    public void setBirthDate(LocalDate BirthDate) {
        this.BirthDate = BirthDate;
    }

    /**
     * @return the next
     */
    public Member getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(Member next) {
        this.next = next;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
            
}
