package DataStructures;

import Classes.Member;

public class HT_Node {
    int key;
    Member value;
    HT_Node next;

    public HT_Node(int key, Member value){
    this.key=key;
    this.value=(Member) value;
    next=null;
    }
    public HT_Node(){
        next=null;
    }
    public int getKey(){
        return key;
    }
    public Member getObject(){
        return value;
    }
}
