# Single linked list

```cpp
#include <bits/stdc++.h>
using namespace std;

class Node{
    public: 
    Node* next;
    int val;
    
    Node(int val){
        this -> val = val;
        this -> next = NULL;
    }
};

void printList(Node* &head){
    Node* temp = head;
    while(temp != NULL){
        cout << temp -> val << " ";
        temp = temp -> next;
    }
    cout << endl;
}

void addToHead(int val, Node* &head){
    Node* newNode = new Node(val);
    newNode -> next = head;
    head = newNode;
}

void addToTail(int val, Node* &tail){
    Node* newNode = new Node(val);
    tail -> next = newNode;
    tail = newNode;
}

void insertAtPos(int pos, int val, Node* &head, Node* &tail){
    // Edge case : Adding to head
    if(pos == 1){
        addToHead(val, head);
        return;
    }
    
    Node* temp = head;
    Node* newNode = new Node(val);
    
    for(int i = 1 ; i < pos -  1 ; i++)temp = temp -> next;
    
    // Edge case : Tail updation when adding to tail
    if(temp -> next == NULL){
        addToTail(val, tail);
        return;
    }
    
    newNode -> next = temp -> next;
    temp -> next = newNode;
}

void deleteNode(int pos, Node* &head){
    if(pos == 1){
        Node* temp = head;
        head = head -> next;
        temp -> next = NULL;
        delete temp;
    }else{
        Node* curr = head;
        Node* prev = NULL;
        for(int i = 1 ; i < pos ; i++){
            prev = curr;
            curr = curr -> next;
        }
        
        prev -> next = curr -> next;
        curr -> next = NULL;
        delete curr;
        
    }
}

int main(){
    
    /* -------------------------------------- Singly Linked List --------------------------------------*/
    Node* head = new Node(20);
    Node* tail = head;
    
    /* -------------------Insertion-------------------*/
    // Insert at head 
    addToHead(10, head);
    printList(head);
    
    // Insert at tail
    addToTail(40, tail);
    printList(head);
    
    // Insert at pos
    insertAtPos(3, 30, head, tail);
    insertAtPos(5, 50, head, tail);
    printList(head);
    
    // Checking head and tail 
    cout << "Head -> " << head -> val << ", Tail -> " << tail -> val << endl; 
    
    /* -------------------Deletion-------------------*/
    deleteNode(2, head);
    printList(head);
    
    
    return 0;
}
```