package DataStructures;

import Classes.Admin;
import Classes.Member;
import Classes.User;

public class MyStackMember {
    //Yığının head değeri null olarak atanır.
    StackNodeMember head = null;//Head değişkeni 
    private int numberOfElement;//Stack içindeki eleman sayısı
    public void addElement(Member member){//Stack içine eleman ekleme fonksiyonu
        StackNodeMember newNode = new StackNodeMember();//Node tipinde bir değişken oluşturduk
        newNode.member = member;//Oluşturulan newNode değişkeninin char değerine parametre değeri atandı
        if(head == null){//Stack boşsa head değerine eşitlenir
            head = newNode;//
        }
        else{//Eğer stack içinde eleman varsa yeni eleman artık stack'in head değeri olacak
           newNode.next = head;
           head = newNode;
        }
        numberOfElement++;//Eleman sayısı 1 artacak
    }
    
    public Member pop(){//Stack'in içinde eleman silme fonksiyonu
        if(head!=null && head.member instanceof User){
            User user = (User) head.member;//head değeri artık head değerinin next değeri olacak ve böylelikle en üstteki eleman stack'den silinecek
            head = head.next;
            numberOfElement--;//Eleman sayısı 1 azalacak,
            return user;
        }else if(head!=null && head.member instanceof Admin){
            Admin admin = (Admin) head.member;//head değeri artık head değerinin next değeri olacak ve böylelikle en üstteki eleman stack'den silinecek
            head = head.next;
            numberOfElement--;//Eleman sayısı 1 azalacak
            return admin;
        }
        return null;
    }
    
    public int getNumberOfElements(){//stack içindeki eleman sayısı döndürülecek
        return numberOfElement;
    }
    
    public StackNodeMember getHead(){
        return head;
    }
    
    public void printLinkedList()//Stack değerleri ekrana bastırılır
    {
       StackNodeMember temp = head;       
       while(temp != null)//Stack içinde gezmek için while döngüsüne girilir
       {
           System.out.print(temp.member.getNickname() +" => ");//Stack içindeki elemanların char değerleri bastırılır
           temp = temp.next;//Temp değerine temp değerinin next değeri atanır
       }
        System.out.println("Null");               
    }
    
}
