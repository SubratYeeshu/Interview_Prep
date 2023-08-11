# Copy List with Random Pointer

## Problem statement 

-A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null. Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list. For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
Return the head of the copied linked list. The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
- val: an integer representing Node.val
- random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
- Your code will only be given the head of the original linked list.

## Approach 1 : Using extra space

Time complexity : O(N) 
Space complexity : O(N)

```cpp
Node* copyRandomList(Node* head) {
    // Original -> Copy
    unordered_map<Node*, Node*> mp;

    // Mapping of copy
    Node* temp = head;
    while(temp != NULL){
        mp[temp] = new Node(temp -> val);
        temp = temp -> next;
    }
    
    temp = head;
    
    // Linkage 
    while(temp != NULL){
        mp[temp] -> next = mp[temp -> next];
        mp[temp] -> random = mp[temp -> random];
        temp = temp -> next;
    }
    
    return mp[head];
}
```

## Approach 2 : Without using extra space

Time complexity : O(N) 
Space complexity : O(1)

```cpp
/*

    1> Create a cross connection in the original list and the nodes of the new list.
    2> Attach random pointers in the new nodes.
    3> Seperate the new linked list from the original.

*/

// Creating a copy of the list and creating linkages (zig zag linkage and copy)
void copyList(Node *head) {
    Node* temp = head;
    Node* forward = head;
    while(temp != NULL) {
        // temp -> next is actually the copy
        forward = temp -> next;
        temp -> next = new Node(temp -> val);
        temp -> next -> next = forward;
        temp = temp -> next -> next;
    }
}

// Linkage of random pointers in copied list (random pointers)
    void attachRandom(Node *head) {
    Node *temp = head;
    while (temp != NULL) {
        if (temp -> random)temp -> next -> random = temp -> random -> next;
        else temp -> next -> random = NULL;
        temp = temp -> next -> next;
    }
}

// Creating the main linkage of both the list (next pointer)
Node* seperate(Node* head){
    Node* temp = head, *copyTemp = head -> next, *originalPointer = head -> next;
    
    while(temp != NULL){
        temp -> next = copyTemp -> next;  // Main linkage (next) in the original list
        if(temp -> next){  // If copy exists create the main linkage (next)
            copyTemp -> next = temp -> next -> next; 
        }else copyTemp -> next = NULL;
        
        temp = temp -> next;
        copyTemp = copyTemp -> next;
    }
    
    return originalPointer;
}    

Node* copyRandomList(Node* head) {
    if(!head)return head;
    copyList(head);
    attachRandom(head);
    return seperate(head);
}
```