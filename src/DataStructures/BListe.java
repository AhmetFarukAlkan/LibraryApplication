package DataStructures;

import Classes.Borrowed;

public class BListe 
{
    //Listenin ilk elemanı oluşturularak initalize edilmesi
    
    public Node head = null;
    public void initialize(Borrowed data)
    {
        head = new Node();
        head.data = data;
        head.next = null;
    }
    //Listenin sonuna eleman eklemek için head değeri ile birlikte sonraki elemanların next değerine bakılır.
    // Eğer null değer gelirse son eleman olduğu anlaşılır ve next değerine yeni eleman eklenir.
    public void addElementLast(Borrowed data)
    {
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = null;
        if(head == null) {    
             head = newNode;    
        }else{
             Node temp = head;
             while(temp.next != null)
             {
                temp = temp.next;
             }
             temp.next = newNode; 
        } 
    }

    //Dizi içinde while döngüsü ile gezinerek dizi ekrana bastırılır.
    public void printLinkedList()
    {
        Node temp = head;       
        while(temp != null)
        {
            System.out.print(temp.data +" => ");
            temp = temp.next;
        }
        System.out.println("Null");               
    }
    //Dizi içinde while döngüsü ile gezinerek dizinin eleman sayısı ekrana bastırılır.
    public int elementCount()
    {
        Node temp = head;
        int count = 0;
        while(temp != null)
        {
            count++;
            temp = temp.next;
        }
        return count;               
     }
    
    //Dizinin head değeri kullanılarak dizinin başına eleman eklenir.
    public void addElementFront(Borrowed data)
    {     
        Node newNode = new Node();
        newNode.data = data; 
        if(head == null) {    
            head = newNode;    
        }else{
            newNode.next = head;
            head = newNode; 
        }                   
    }
    
    //Dizide elemanların ismi kullanılarak while döngüsü aracılığıyla eleman aranır.
    public Borrowed searchNodebyName(String data){
        Node current = head;
        while(current != null){
            if(current.data.getBookName().equals(data)){
                return current.data;
            }
            current = current.next;
        }
        return null;
    }
    //Dizide elemanların id değeri kullanılarak while döngüsü aracılığıyla eleman aranır.
    public Borrowed searchNodebyid(int data){
        Node current = head;
        while(current != null){
            if(current.data.getId() == data){
                return current.data;
            }
            current = current.next;
        }
        return null;
    }
    
    //Diziden eleman silme işlemi için düğümün data değeri kullanılır.
    //silinecek eleman iki eleman arasındaki bir elemansa önceki elemanın next değeri sonraki elemana 
    // eşit olur eğer head değeri ise head değeri head değerinin next'i olur eğer sonda ise bir önceki elemanın
    //next değeri null olur.
    public boolean deleteNode(Borrowed data)
    {
        boolean durum = false;
        Node current = head;
        Node tmp = null;      
        if (current.data == data)
        {
           tmp = current.next;
           head = tmp;
        }
        else
        {             
            while(current.next != null)
            { 
                if (current.next.data == data)
                {
                  tmp = current.next;
                  current.next = tmp.next;
                  durum = true;
                  break;
                }
                current = current.next;
            }      
        }
        return durum;
    }
}