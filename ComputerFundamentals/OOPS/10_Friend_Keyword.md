# Friend Keyword

## Some points 

- A friend function can be -> Member of another class, Global function 
- Friend function should be used only for limited purpose, exhaustive uses loosen encapsulation
- Friendship is not mutual : If class A is friend of B, then B does'nt become a friend of A automatically
- Friendship is not inherited

## Access private, protected method or data members 

- Example 1
```cpp
class Student{
	int a = 10;
	friend void show(Student x);
};

// The function can be defined anywhere in the code file and we need not use the keyword friend or the scope resolution, operator.
void show(Student x){
	cout << x.a << endl;
}

int main(){
	Student s1;
	show(s1);
	// s1.a = 10;
	// cout << s1.a << endl;  // Private (to use this either use public getter fn or friend)
}
```

- Example 2
```cpp
class Complex{
private:
	int i;
	int a;
public:
	void setNumber(int a, int i){
		this -> i = i;
		this -> a = a;
	}

	void printNumber(){
		cout << "Number is : " << a << " + " << i << "i" << endl;
	}

	// Telling the function getSum of class complex to treat him like friend and let it have the access of private and protected members
	friend Complex getSum(Complex c1, Complex c2);
};

Complex getSum(Complex c1, Complex c2){
	Complex c;
	c.setNumber(c1.a + c2.a, c1.i + c2.i);
	return c;
}

int main(){
	Complex c1, c2, c3;

	c1.setNumber(2, 3);
	c1.printNumber();

	c2.setNumber(5, 6);
	c2.printNumber();

	c3 = getSum(c1, c2);
	c3.printNumber();
}
```

- Example 3
```cpp
#include<bits/stdc++.h>
using namespace std;

class person2;

class person{
	int x;
public:
	friend void exchange(person &, person2 &);
	void set(int y){
		this -> x = y;
	}

	void print(){cout << x;}
};

class person2{
	int y;
public:
	friend void exchange(person &, person2 &);
	void set(int z){
		this -> y = z;
	}

	void print(){cout << y;}

};


// Trying to access private data members
void exchange(person &p1, person2 &p2){
	swap(p1.x, p2.y);
}

int main(){
	person p1;
	p1.set(20);

	person2 p2;
	p2.set(40);

	// Swap data 
	exchange(p1, p2);

	// Printing after swapping
	cout << "P1 data after swapping -> ";
	p1.print();
	cout << endl;
	cout << "P2 data after swapping -> ";
	p2.print();
	cout << endl;
}
```

## Declaring entire class as friend

```cpp
class FriendClass {
private:
    int privateData;

public:
    FriendClass(int data) : privateData(data) {}

    friend class MyClass; // MyClass is a friend of FriendClass
};

class MyClass {
public:

// We can access anything private inside FriendClass becuase it is declared as friend of the class MyClass
// Declaring class as friend rather than individually declaring variables and functions as friend saves time
    void accessFriendClass(FriendClass &obj) {
        // MyClass can access the privateData of FriendClass
        cout << "MyClass accessed FriendClass's privateData: " << obj.privateData << std::endl;
    }
};

int main(){
	FriendClass f1(100);
	MyClass m1;
	m1.accessFriendClass(f1);
}
```

