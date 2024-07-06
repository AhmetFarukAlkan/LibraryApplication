package DataStructures;

import Classes.Book;

public class Okuyruk {
    // Node  
    public static class Node {  
        Book data;  
       // Düşük değerler yüksek önceliği gösterir
        Node next;  
    }
    static Node node = new Node(); 

   // Yeni node oluşturulması
   public static Node newNode(Book d)  
   {  
       Node temp = new Node();  
       temp.data = d;  
       temp.next = null;  

       return temp;  
   } 
   // baştaki değeri döndür
   public static Book peek(Node head)  
   {  
       return (head).data;  
   }  

   //  En yüksek önceliğe sahip elemanı listeden kaldırır
   public static Node pop(Node head)  
   {  
       Node temp = head;  
       (head)  = (head).next;  
       return head; 
   } 
   // Önceliğe göre itme işlevi
   public static Node push(Node head, Book d){  
       Node start = (head);  

       // Yeni node oluşturma
       Node temp = newNode(d);  

        // Özel Durum: Liste başlığının yeni düğüme göre daha az önceliği vardır. Head düğümünden önce yeni düğüm eklemek ve kafa head değiştirmek için.    

        // Listede gezme ve yeni düğüm ekleme konumu       
        while (start.next != null) {  
            start = start.next;  
        }  
         // Ya listenin sonunda
         // veya gereken konumda
        temp.next = start.next;  
        start.next = temp;  
        
       return head; 
   } 
   // liste boş mu kontrol etme
   public static int isEmpty(Node head)  
   {  
       return ((head) == null)?1:0;  
   }  
  
}
