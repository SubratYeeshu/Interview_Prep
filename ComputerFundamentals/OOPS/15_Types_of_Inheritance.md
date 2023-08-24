# Types of Inheritance

- There are six types inheritance 
- Single, Multiple, Multilevel, Hierarchical, Hybrid

## There are five types of inheritance

## Single Inheritance: 
- In single inheritance, a subclass inherits properties and behaviors from a single superclass. This is the simplest form of inheritance where each class has only one direct parent class.

```cpp
class Person{
public:
	string firstName;
	string lastName;

	void setName(string x, string y){
		this -> firstName = x;
		this -> lastName = y;
	}

	void getName(){
		cout << firstName << " " << lastName << endl;
	}
};


class Employee:public Person{
public:
	long long salary;

	void setSalary(long long x){
		this -> salary = x;
	}

	void printInfo(){
		cout << "Person with name : " << firstName << " " << lastName << " has salary -> " << salary;
	}
};

int main(){
	Employee e1;
	e1.setName("Subrat", "Yeeshu");
	e1.setSalary(2499999);
	e1.printInfo();
}
```

##  Multiple Inheritance:
- Multiple inheritance occurs when a subclass inherits properties and behaviors from more than one superclass. This allows a class to inherit features from multiple classes. However, it can lead to complexity and conflicts in some cases.

```cpp
class Mammal {
  public:
    Mammal() {
      cout << "Mammals can give direct birth." << endl;
    }
};

class WingedAnimal {
  public:
    WingedAnimal() {
      cout << "Winged animal can flap." << endl;
    }
};

class Bat: public Mammal, public WingedAnimal {};

int main() {
    Bat b1;
    return 0;
}
```

## Multilevel Inheritance:
- In multilevel inheritance, a class derives from another class, which in turn derives from another class. This forms a chain of classes, where each class inherits properties and behaviors from its immediate parent class.

```cpp
class Person{
public:
	string firstName;
	string lastName;

	void setName(string x, string y){
		this -> firstName = x;
		this -> lastName = y;
	}
};


class Employee:public Person{
public:
	long long salary;

	void setSalary(long long x){
		this -> salary = x;
	}
};

class EmployeeBenefits:public Employee{
public:
	int DA;
	int HRA;

	void setDA(int x){
		this -> DA = x;
	}
	void setHRA(int x){
		this -> HRA = x;
	}

	void printInfo(){
		cout << "Person with name : " << firstName << " " << lastName << " has salary -> " << salary << endl;
		cout << "His Employee Benfits : " << "DA -> " << DA << " HRA -> " << HRA << endl;
	}
};

int main(){
	Employee e1;
	e1.setName("Subrat", "Yeeshu");
	e1.setSalary(2499999);

	EmployeeBenefits e2;
	e2.setName("Yeeshu", "Kumar");
	e2.setSalary(6999999);
	e2.setDA(6);
	e2.setHRA(42);

	e2.printInfo();
}
```

## Hierarchical Inheritance:
- In hierarchical inheritance, multiple subclasses inherit properties and behaviors from a single superclass. This creates a tree-like structure, with one superclass and multiple subclasses.

```cpp
// C++ program to demonstrate hierarchical inheritance

#include <iostream>
using namespace std;

// base class
class Animal {
   public:
    void info() {
        cout << "I am an animal." << endl;
    }
};

// derived class 1
class Dog : public Animal {
   public:
    void bark() {
        cout << "I am a Dog. Woof woof." << endl;
    }
};

// derived class 2
class Cat : public Animal {
   public:
    void meow() {
        cout << "I am a Cat. Meow." << endl;
    }
};

int main() {
    // Create object of Dog class
    Dog dog1;
    cout << "Dog Class:" << endl;
    dog1.info();  // Parent Class function
    dog1.bark();

    // Create object of Cat class
    Cat cat1;
    cout << "\nCat Class:" << endl;
    cat1.info();  // Parent Class function
    cat1.meow();

    return 0;
}
```

## Hybrid (or Mixed) Inheritance:
- Hybrid inheritance is a combination of any of the above types. It's a situation where multiple types of inheritance are used in a single program. For example, a combination of single and multiple inheritance.

```cpp
/*
    A
    |
    V
    B
    |
    V
    D <-- C
*/

Class A{  
    statement(s)  
}:  
Class B: public A{  
    statement(s);  
};  
Class C{  
    statement(s);  
};  
Class D: public B, public C{  
    statement(s);  
};  
```

## Diamond Problem (Hybrid Inheritance):
- Names related to this : Diamond of Death, Disinheritance, Virtual Base Class, Diamon Problem, Virtual Inheritance
- Diamond Problem is a situation that arises in multiple inheritance when a class inherits from two classes that have a common base class. This can create ambiguity in certain cases and lead to the diamond problem. Programming languages have mechanisms to address this issue, such as virtual inheritance.
- The diamond problem The diamond problem occurs when two superclasses of a class have a common base class. 

- Example 1
```cpp
class Person {
// Data members of person
public:
	Person(int x) { cout << "Person::Person(int ) called" << endl; }
};

class Faculty : public Person {
// data members of Faculty
public:
	Faculty(int x):Person(x) {
	cout<<"Faculty::Faculty(int ) called"<< endl;
	}
};

class Student : public Person {
// data members of Student
public:
	Student(int x):Person(x) {
		cout<<"Student::Student(int ) called"<< endl;
	}
};

class TA : public Faculty, public Student {
public:
	TA(int x):Student(x), Faculty(x) {
		cout<<"TA::TA(int ) called"<< endl;
	}
};

int main() {
	TA ta1(30);
}
```
## Output
```cpp
Person::Person(int ) called
Faculty::Faculty(int ) called
Person::Person(int ) called
Student::Student(int ) called
TA::TA(int ) called
```

- In the above program, constructor of ‘Person’ is called two times. Destructor of ‘Person’ will also be called two times when object ‘ta1’ is destructed. So object ‘ta1’ has two copies of all members of ‘Person’, this causes ambiguities. The solution to this problem is ‘virtual’ keyword. We make the classes ‘Faculty’ and ‘Student’ as virtual base classes to avoid two copies of ‘Person’ in ‘TA’ class.

- Example 2
```cpp
/*

      ClassA
     /      \
ClassB1     ClassB2
     \      /
      ClassC

*/
class A{
public:
	int a;
	void f1(){
		cout << "Hello f1" << endl;
	}
};

class B1:public A{
public:
	int b1;
	void f2(){
		cout << "Hello f2" << endl;
	}
};

class B2:public A{
public:
	int b2;
	void f3(){
		cout << "Hello f3" << endl;
	}
};

class C:public B1, public B2{
public:
	int c;
	void f4(){
		cout << "Hello f4" << endl;
	}
};


int main(){
	C obj;
	cout << sizeof(obj) << endl;  // Four variables each of 4 bytes. total size must be 4*4	
	// cout << obj.a;  // Ambiguous

	obj.f4();
	obj.f3();
	obj.f2();
	// obj.f1();  // Ambiguity error
};
```

## Solution to diamond problem

- Virtual Base Class
- Add virtual keyword to the derived class of problamatic class while inheriting

```cpp
/*

      ClassA
     /   |   \
ClassB1  |   ClassB2
     \   |   /
      ClassC
 Fixes early binding
*/
class A{
public:
	int a;
	void f1(){
		cout << "Hello f1" << endl;
	}
};

class B1:virtual public A{  // Creating the ambiguous class as virtual 
public:
	int b1;
	void f2(){
		cout << "Hello f2" << endl;
	}
};

class B2:virtual public A{  // Creating the ambiguous class as virtual 
public:
	int b2;
	void f3(){
		cout << "Hello f3" << endl;
	}
};

class C:public B1, public B2{
public:
	int c;
	void f4(){
		cout << "Hello f4" << endl;
	}
};


int main(){
	C obj;
	cout << sizeof(obj) << endl;  // Four variables each of 4 bytes. total size must be 4*4	
	// cout << obj.a;  // Ambiguous

	obj.f4();
	obj.f3();
	obj.f2();
	obj.f1();  // Ambiguity error
};
```