# Flattening a Linked List

## Problem statement

- Given a Linked List of size N, where every node represents a sub-linked-list and contains two pointers:
- A next pointer to the next node,
- A bottom pointer to a linked list where this node is head.
- Each of the sub-linked-list is in sorted order.
- Flatten the Link List such that all the nodes appear in a single level while maintaining the sorted order. 


## Approach 1 : Recursion

Time complexity : O(N)
Auxiliary space : O(1)

```cpp
Node* mergeTwoLists(Node* list1, Node* list2) {
    if(!list1)return list2;
    if(!list2)return list1;
    
    // Dummy node for easier operation
    Node* dummy = new Node(0);
    Node* curr = dummy;
    
    while(list1 && list2){
        if(list1 -> data <= list2 -> data){
            curr -> bottom = list1;
            list1 = list1 -> bottom;
        }else{
            curr -> bottom = list2;
            list2 = list2 -> bottom;
        }
        curr = curr -> bottom;
    }
    
    if(list1)curr -> bottom = list1;
    if(list2)curr -> bottom = list2;
    
    return dummy -> bottom;
}

Node *flatten(Node *root){
   if(!root || !root -> next)return root;
   
   // Ask recursion to merge the list next to root
   root -> next = flatten(root -> next);
   
   // Combining the two list smaller problem
   mergeTwoLists(root, root -> next);
   
   // Return the result
   return root;
   
}
```