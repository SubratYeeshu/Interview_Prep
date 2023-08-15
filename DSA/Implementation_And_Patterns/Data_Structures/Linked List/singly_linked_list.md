# Single linked list

- Linked list is a linear collection of data elements whose order is not given by their physical placement in memory. Instead, each element points to the next. It is a data structure consisting of a collection of nodes which together represent a sequence. 

## Time complexity 
- Insertion at Head : O(1)
- Insertion at a Specific Position : O(position)
- Deletion at Head : O(1)
- Deletion at Tail : O(N) if no tail pointer else O(1)
- Deletion at a Specific Position : O(position)
- Searching for an Element : O(N)

## Space complexity
- O(N) For storing the linked list

## Uses
- Linked lists are fundamental data structures that find applications in various domains due to their flexibility and efficient memory utilization. Here are some common use cases for linked lists:
- Dynamic Memory Allocation: Linked lists allow for dynamic memory allocation at runtime, making them suitable for cases where the number of elements is unknown or can change dynamically.
- Text Editors: Linked lists can be used to implement the undo/redo functionality in text editors. Each edit can be stored as a node in the linked list, allowing easy traversal through the edit history.
- Memory Management: Linked lists are used by operating systems to manage memory allocation. Free blocks of memory can be maintained as a linked list, making allocation and deallocation efficient.
- Music and Video Playlists: Linked lists are commonly used to create playlists in music and video players. Each song or video can be a node in the list, allowing easy navigation and customization of playlists.
- LRU Cache: Linked lists are used in LRU (Least Recently Used) cache implementations to efficiently manage recently used items. When an item is accessed, it can be moved to the front of the list.
- Symbol Tables: Linked lists can be used in symbol tables where each node stores a key-value pair. They are the basis for associative arrays or dictionaries.
- Storing the browser history navigation
- Garbage Collection: Linked lists can be used in garbage collection algorithms to keep track of allocated memory blocks and their relationships.


## Code snippet
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