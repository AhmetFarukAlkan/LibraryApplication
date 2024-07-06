package DataStructures;

import Classes.Book;


public class BST { 
    
    //İkili arama ağacı düğümü
    public class Node { 
        public Book key; 
        public Node left, right; 
   
        public Node(Book data){ 
            key = data; 
            left = right = null; 
        } 
    } 
    public Node root; 
  
    public BST(){ 
        root = null; 
    }
    //recursive yapı ile ağaçtan eleman aranacak ve silinecek
    public void deleteKey(String key) { 
        root = delete_Recursive(root, key); 
    } 
   
    
    // silme işleminde elemanların isimleri karşılaştırılarak dallanma gerçekleştiriliyor.
    public Node delete_Recursive(Node root, String key)  { 
        if (root == null)  return root; 

        if (key.compareTo(root.key.getBookName()) == -1)     //traverse left subtree 
            root.left = delete_Recursive(root.left, key); 
        else if (key.compareTo(root.key.getBookName()) == 1)  //traverse right subtree
            root.right = delete_Recursive(root.right, key); 
        else  { 
            // node contains only one child
            if (root.left == null) 
                return root.right; 
            else if (root.right == null) 
                return root.left; 
   
            root.key.setBookName(minValue(root.right)); 
   
            root.right = delete_Recursive(root.right, root.key.getBookName()); 
        } 
        return root; 
    } 
   
    //Ağacın min değeri için null olana kadar sol dala yönlendirilir.
    public String minValue(Node root)  { 
        //initially minval = root
        String minval = root.key.getBookName(); 
        //find minval
        while (root.left != null)  { 
            minval = root.left.key.getBookName(); 
            root = root.left; 
        } 
        return minval; 
    } 
    //Recursive yağı kullanılarak yaprak sayısı hesaplanır.
    public int getLeafCount()
    {
        return getLeafCount(root);
    }
    
    public int getLeafCount(Node node)
    {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;
        else
            return getLeafCount(node.left) + getLeafCount(node.right);
    }
    
    //Recursive yapı kullanarak ağaca eleman eklenir.
    public void insert(Book key)  { 
        root = insert_Recursive(root, key); 
    } 
   
    // eleman ekleme işleminde elemanların isimleri karşılaştırılarak dallanma gerçekleştiriliyor.

    public Node insert_Recursive(Node root, Book key) { 
        if (root == null) { 
            root = new Node(key); 
            return root; 
        } 

        if (key.getBookName().compareTo(root.key.getBookName()) == -1)     //insert in the left subtree
            root.left = insert_Recursive(root.left, key); 
        else if (key.getBookName().compareTo(root.key.getBookName()) == 1)    //insert in the right subtree
            root.right = insert_Recursive(root.right, key); 
        else{
            root.right = insert_Recursive(root.right, key);
        }
        return root; 
    } 
    //Ağacın sol yüksekliği hesaplanırken null olana kadar sol dala yönlendirilir.
    public  int left_height(Node node)
    {
        int ht = 0;
        while (node!=null) {
            ht++;
            node = node.left;
        }

        return ht;
    }
     //Ağacın sağ yüksekliği hesaplanırken null olana kadar sağ dala yönlendirilir.
    public  int right_height(Node node)
    {
        int ht = 0;
        while (node!=null) {
            ht++;
            node = node.right;
        }

        return ht;
    }
    
    //Ağacçtaki eleman sayısı sol ve sağ ağaçtaki eleman sayısına 1 eklenerek bulunur.
    public  int getNumber(Node root)
    {

        if (root == null)
            return 0;

        int lh = left_height(root);
        int rh = right_height(root);

        return 1 + getNumber(root.left)
               + getNumber(root.right);
    }
    //recursive yapı kullanarak ağaçta arama gerçekleştirilir.
    public Node search(String key)  { 
        Node root1 = search_Recursive(root, key); 
        if (root1!= null)
            return root1;
        else
            return null;
    } 
    // arama işleminde elemanların isimleri karşılaştırılarak dallanma gerçekleştiriliyor.
    public Node search_Recursive(Node root, String key)  { 
        if (root==null || root.key.getBookName().equals(key)) 
            return root; 
        if (root.key.getBookName().compareTo(key)==1) 
            return search_Recursive(root.left, key); 
        return search_Recursive(root.right, key); 
    } 
    
}
