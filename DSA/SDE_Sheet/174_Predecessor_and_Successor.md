# Predecessor and Successor

## Problem statement

There is BST given with the root node with the key part as an integer only. You need to find the in-order successor and predecessor of a given key. If either predecessor or successor is not found, then set it to NULL.

Note:- In an inorder traversal the number just smaller than the target is the predecessor and the number just greater than the target is the successor. 

## Approach 1 : Inorder Traversal of BST is sorted

- Time complexity : O(LogN)
- Space complexity : O(LogN)

```cpp
void inorder(Node *root, vector<Node*> &res){
    if(!root)return;
    
    inorder(root -> left, res);
    res.push_back(root);
    inorder(root -> right, res);
}

void findPreSuc(Node* root, Node*& pre, Node*& suc, int target){
    vector<Node*> v;
    inorder(root, v);

    for(int i = 0 ; i < v.size() ; i++){
        if(v[i] -> key > target){
            suc = v[i];
            break;
        }
        if(v[i] -> key < target)pre = v[i];
    }
}
```

## Approach 2 : Recursive + BST Property

- Time complexity : O(LogN)
- Space complexity : O(LogN)

```cpp
void findPre(Node *root, Node *&pre, int key){
    if(!root)return;
    
    if(root -> key < key){
        pre = root;  // Just smaller than key
        findPre(root -> right, pre, key);
    }
    else if(root -> key >= key){
        findPre(root -> left, pre, key);
    }
}
void findSuc(Node *root, Node *&suc, int key){
    if(!root)return;
    
    if(root -> key <= key){
        findSuc(root -> right, suc, key);
    }
    else if(root -> key > key){
        suc = root;  // Just greater find in left of root
        findSuc(root -> left, suc, key);
    }
}

void findPreSuc(Node* root, Node*& pre, Node*& suc, int key){
    findSuc(root, suc, key);
    findPre(root, pre, key);
}
```

## Approach 3 : Recursive + BST Property

- Time complexity : O(LogN)
- Space complexity : O(LogN)

```cpp
Node* findPre(Node* root){
    //Move to left then Move to extreme right
        Node* temp = root -> left;
        while(temp->right)temp = temp -> right;
        return temp;
};

Node* findSuc(Node* root){
    //Move to right then Move to extreme left
        Node* temp = root -> right;
        while(temp->left)temp = temp -> left;
        return temp;
};

void findPreSuc(Node* root, Node*& pre, Node*& suc, int key){
    if(!root)return;
    // 3 Cases

    // Root equals key
    if(key == root -> key){
        
        // Update pre and suc if possible
        if(root->left)pre = findPre(root);
        if(root->right)suc = findSuc(root);
        return;
    }
    
    // Key larget search in right subtree
    if(key > root -> key){
        pre = root;
        findPreSuc(root -> right, pre, suc, key);
    }else if(key < root -> key){
    // Key smaller search in left subtree
        suc = root;
        findPreSuc(root -> left, pre, suc, key);
    }
}
```

