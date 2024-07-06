package Classes;


import DataStructures.BST;
import DataStructures.HashTable01;
import DataStructures.MyStackMember;
import DataStructures.SQL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Datas {
    SQL sql = new SQL();
    //Static olarak otomasyonda kullanılacak veriler burada tutulur.
    static HashTable01<Member> users;
    static BST Books ;
    public Admin admin = new Admin("a", "a", "a", "a", "a", "123", LocalDate.now());
    public User user = new User("1", "1", "1", "1", "1", "1", LocalDate.now());

    public Datas(){
        try {
            setUsers();
            setAdmin();
            setBooks();
        } catch (SQLException ex) {
            Logger.getLogger(Datas.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    //SQL veri tabanından veriler çekilir.
    public void setUsers() throws SQLException{
        users = new HashTable01<>(2000);
        MyStackMember myStackMember = sql.getUsersFromSQL();
        //yığındaki veriler pop edilerek hash tablosuna eklenirler.
        boolean next = true;
        while(next){
            User user = (User) myStackMember.pop();
            if(user == null){
                next = false;
            }
            else{
                users.ekle((int)user.getNickname().charAt(0), user);
            }
        }
  
    }

    public void setAdmin() throws SQLException{
        MyStackMember myStackMember = sql.getAdminsFromSQL();
        boolean next = true;
        //yığındaki veriler pop edilerek hash tablosuna eklenirler.
        while(next){
            Admin admin = (Admin) myStackMember.pop();
            if(admin == null){
                next = false;
            }
            else{
                users.ekle((int)admin.getNickname().charAt(0), admin);
            }
        }
    }
    
    public void setBooks() throws SQLException{
        Books = new BST();
        Queue<Book> queue = sql.getBooksFromSQL();
        //sıradaki veriler pop edilerek ağaca eklenirler.
        boolean next = true;
        while(next){
            Book book = queue.poll();
            if(book == null){
                next = false;
            }else{ 
                Books.insert(book);
                
                
            }
        }
    }
    
    public static void main(String[] args){
//        Book book = new Book();
//        book.setBookName("A");
//        
//        Book book1 = new Book();
//        book1.setBookName("B");
//        
//        Book book2 = new Book();
//        book2.setBookName("C");
//        
//        Books.insert(book);                
//        Books.insert(book1);
//        Books.insert(book2);
//                
//        System.out.println(Books.search("A"));
//        System.out.println(Books.search("C"));
//        
//        System.out.println(Books.getNumber(Books.root));
//        System.out.println(Books.right_height(Books.root));        
//        
//        Books.inorder();


//        User user1 = new User();
//        BoughtBook boughtBook = new BoughtBook();
//        boughtBook.setId(1);
//        boughtBook.setBookName("Çalı Kuşu");
//        user1.addBook(boughtBook);
//        System.out.println(user1.getBooks().searchNode(1).getBookName());
        
        
        
    }
    
    //encapsulation
    public void addUser (Member member){
        users.ekle((int) member.getName().charAt(0), member);
    }
    
    public HashTable01<Member> getUsers(){
        return users;
    }
    
    public void addBook (Book book){
        Books.insert(book);
    }
    
    public BST getBooks(){
        return Books;
    }
    
    public void deleteBook(Book book){
        Books.deleteKey(book.getBookName());
    }
}
