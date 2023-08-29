# Binary Tree

```cpp
#include <bits/stdc++.h>
using namespace std;

class TreeNode{
public:
	int data;
	TreeNode *left;
	TreeNode *right;

	TreeNode(int data_){
		this -> data = data_;
		this -> left = NULL;
		this -> right = NULL;
	}
};

void preorder(TreeNode *root){
	if(!root)return;

	cout << root -> data;
	preorder(root -> left);
	preorder(root -> right);
}

TreeNode* buildTree(TreeNode *root){
	cout << "Enter the data : " << endl;
	int value = 0;
	cin >> value;
	root = new TreeNode(value);

	if(value == -1)return NULL;

	cout << "Enter the data for left Node of " << value << endl;
	root -> left = buildTree(root -> left);
	cout << "Enter the data for right Node : " << value << endl;
	root -> right = buildTree(root -> right);

	return root;
}

int main(){
	TreeNode *root = NULL;

	root = buildTree(root);

	preorder(root);
}
```

# N-Array Tree

```cpp
#include <bits/stdc++.h>
using namespace std;

class TreeNode {
public:
    int data;
    vector<TreeNode*> children;

    TreeNode(int data_) {
        this->data = data_;
    }
};

void preorder(TreeNode* root) {
    if (!root) return;

    cout << root->data << " ";
    for (TreeNode* child : root->children) {
        preorder(child);
    }
}

TreeNode* buildTree() {
    cout << "Enter the data : ";
    int value = 0;
    cin >> value;

    if (value == -1) return NULL;

    TreeNode* root = new TreeNode(value);

    cout << "Enter the number of children for node " << value << " : ";
    int numChildren = 0;
    cin >> numChildren;

    for (int i = 0; i < numChildren; ++i) {
        TreeNode* child = buildTree();
        root->children.push_back(child);
    }

    return root;
}

int main() {
    TreeNode* root = buildTree();

    cout << "Preorder traversal: ";
    preorder(root);

    return 0;
}
```