# Constructors

- Constructor is a member function of a class which initializes objects of a class. Constructor is called automatically when object(instance of class) is created. 

## Constructors differs from normal member function in the following ways 
- Constructors have same name as the class itself
- Constructors don't have return type
- Automatically called upon invocation of objects
- If we do not specify a constructor, compiler automatically creates one (default constructor)

## There are three types of constructors
- Default constructors
- Parametrized constructors
- Copy constructors

```cpp
#include<bits/stdc++.h>
using namespace std;

class Student{
public:
	string firstName;
	string lastName;
	int age;

	// Default constructor
	Student(){
		cout << "Default constructor called" << endl;
	}

	Student(string x, string y, int age){
		this -> firstName = x;
		this -> lastName = y;
		this -> age = age;
		cout << "Parametrized constructor called" << endl;
		print();
	}

	void print();
};

void Student::print(){
	cout << firstName <<" " << lastName << " " << age << endl;
}


class Phone{
public:
	string name;
	long long price;

	// Paremtrized Constructor Shorthand
	Phone(string x, long long p):name(x), price(p){}
};


class Car{
public:
	string name;
	int age;

	Car(string name, int age):age(age),name(name){};

	Car(const Car &other) : name(other.name), age(other.age){};
};

int main(){
	Student s1;  // Implicit invocation of constructor (even if it is not manually created)
	Student s2 = Student("Subrat", "Yeeshu", 20); 


	// Pointer to the object
	// New keyword returns a pointer so for accessing we have to derefference it and use . operator to access data members
	// We use pointer to class for making linked list node becuase at runtime its space increases
	Phone *iPhone = new Phone("Apple", 78999);
	cout << (*iPhone).name << " ";
	cout << iPhone -> price << endl;

	// Without using pointer
	Phone samsung("Samsung", 124999);
	cout << samsung.price << endl;


	// Copy constructor
	Car mycar("Indica", 17);
	cout << "Car name -> " << mycar.name << ", Car age -> " << mycar.age << endl;

	Car mycar2 = (mycar);
	cout << "Car name -> " << mycar2.name << ", Car age -> " << mycar2.age << endl;
}
```

## Destructors

```cpp
class Animal{
public:
	string name;
	int age;

	~Animal(){
		cout << "Destructor called" << endl;
	}
};

int main(){
Animal dog;
	dog.name = "Husky";
	dog.age = 10;
}
```