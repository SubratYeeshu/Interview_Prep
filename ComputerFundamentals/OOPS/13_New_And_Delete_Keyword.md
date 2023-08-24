# New And Delete Keyword

- New keyword is used to allocate memory where as delete keyword is used to release the memory

## Code Snippet

```cpp
// Infinitley expanding data strucutre because a node will have a left and right,
// the left and right will itself have left and right and so on
// class Node{
// public:
// 	int data;
// 	Node left;  // Problematic
// 	Node right;  // Problematic

// 	Node(int data){
// 		this -> data = data;
// 	}
// };

// Now the address is unique
class Node{
public:
	int data;
	Node* left;
	Node* right;

	Node(int data){
		this -> data = data;
	}
};


int main(){
	int *ptr;
	cout << ptr << endl;
	int *ptr2;
	cout << ptr2 << endl;

	// New keyword is use to allocate memory in heap and returns a pointer
	// Node *myNode(10);  // Incorrect
	Node *myNode = new Node(10);
	cout << (*myNode).data << endl;
	cout << myNode -> data << endl;

	// We use delete keyword to free memory 
	delete myNode;

	int *ptr3 = new int(1000);
	cout << *ptr3 << endl;
	delete ptr3;
	cout << *ptr3 << endl;
	


	map<int, int> mp;

	mp[0] = 100;
	mp[1] = 200;
	mp[2] = 300;
	mp[3] = 400;

	// Map is class with pointer to the its node, hence we use -> operator to acces data
	map<int, int> :: iterator it = mp.begin(); // auto it = mp.begin();
	cout << it -> first << " " << it -> second << endl;
	
	// Will not work because the pointers are read only
	// auto it2 = mp.begin();
	// it2 -> first = -1;
	// it2 -> second = -100;
	// cout << it2 -> first << " " << it2 -> second << endl;

	auto it3 = mp.begin();
	it3 -> second = 200;
	cout << it3 -> second << endl;


	// Pair class
	pair<int, int> p;
	// p -> first = 1; // Will not work
	p.first = 10;
	p.second = 20;
	cout << p.first << " " << p.second << endl;
}
```