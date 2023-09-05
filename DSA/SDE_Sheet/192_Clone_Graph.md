# Clone Graph

## Problem statement

Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

```cpp
class Node {
    public int val;
    public List<Node> neighbors;
}
```
 
Test case format:

For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.

An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

## Approach 1 : DFS 

- Time complexity : O(N)  
- Auxiliary space : O(N)

```cpp
/*
class Node {
public:
    int val;
    vector<Node*> neighbors;
    Node() {
        val = 0;
        neighbors = vector<Node*>();
    }
    Node(int _val) {
        val = _val;
        neighbors = vector<Node*>();
    }
    Node(int _val, vector<Node*> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/
class Solution {
public:
    void dfs(Node *&myNode, Node *node, vector<Node*> &vis){
        vis[myNode -> val] = myNode;
        
        for(auto child : node -> neighbors){
            if(!vis[child -> val]){
                // No copy of this node made till now, make copy of its neighbour
                Node *newNode = new Node(child -> val);
                (myNode -> neighbors).push_back(newNode);
                dfs(newNode, child, vis);
            }else{
                // Copy already made get address of neighbour(copied version) from vis array
                (myNode -> neighbors).push_back(vis[child -> val]);
            }
        }
    }
    
    Node* cloneGraph(Node* node) {
        vector<Node*> vis (1001, NULL);
        Node *myNode = new Node(node -> val);
        dfs(myNode, node, vis);
        return myNode;
    }
};
```