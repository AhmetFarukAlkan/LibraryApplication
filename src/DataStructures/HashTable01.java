package DataStructures;

package datastructure_search_alg;

import Classes.Member;

public class HashTable01 <T> {
    HT_Node[] hashDizisi;
    int size;
    
    //Hash tablosu büyüklük verisi girilerek oluşturulur.
    public  HashTable01(int size){
        this.size=size;
        hashDizisi = new HT_Node[size];
        for(int i=0;i<size;i++){
            hashDizisi[i]=new HT_Node();
        }
    }
    //Buradan key değerinin size değerine göre mod değeri alınır.
    int getHash(int key){
        return key%size;
    }
    
    //Eleman alırken ilk başta mod değerine bakılır.
    //Daha sonra dizide mod değerinde elemanın olup olmadığı bakılır.
    //Eğer yoksa o indise eleman eklenir.
    //Eğer eleman varsa bir sonraki elemana geçilir ve boş indis bulunana kadar devam eder.
    public void ekle(int key, Object value){
        int Hindex = getHash(key);
        HT_Node deger = hashDizisi[Hindex];
        
        while(get(Hindex)!=null){
            
            Hindex++;
            deger = hashDizisi[Hindex];
        }
        
        HT_Node yeni = new HT_Node(Hindex, (Member) value);  
                
        yeni.next=deger.next;
        deger.next=yeni;
    }
    //Hash tablosunda eleman aranırken yine mod değerine bakılır ve bu mod değerinden sonraki elemanlara kontrol edilir.
    public Member get(int key){
        if(key<0){
            return null;
        }
        T value = null;
        int Hindex=getHash(key);
        HT_Node arrayValue = hashDizisi[Hindex];
        while(arrayValue != null){
            //System.out.println(arrayValue.getKey());           
            if(arrayValue.getKey()==key){                
                value=(T)arrayValue.getObject();
            }
            arrayValue=arrayValue.next;
        }
        return (Member) value;
    }
    
    //Hash tablosunda eleman aranırken yine mod değerine bakılır ve bu mod değerinden sonraki elemanlara kontrol edilir.

    public int search(String name){ 
        int Hindex=getHash((int) name.charAt(0));
        try{          
          while(!get(Hindex).getNickname().equals(name)){
              Hindex++;
          }   
        }catch(NullPointerException e){
            return -1;
        }
               
        return Hindex;
    }
    
    public HT_Node getMember(int key){
        T value = null;
        int Hindex=getHash(key);
        HT_Node arrayValue=hashDizisi[Hindex];
        while(arrayValue != null){
            if(arrayValue.getKey()==key ){
                return arrayValue;
            }
            arrayValue=arrayValue.next;
        }
        return null;
    }
    //Hash tablosunda eleman aranırken yine mod değerine bakılır ve bu mod değerinden sonraki elemanlara kontrol edilir.
    //Eğer eleman bulunursa silinir.
    public void deleteMember(int key){        

        int Hindex=getHash(key);
        HT_Node arrayValue=hashDizisi[Hindex];
        while(arrayValue != null){
            if(arrayValue.getKey()==key){
                arrayValue.key = 0;
                arrayValue.value = null;
            }
            arrayValue=arrayValue.next;
        }
    }
    
}