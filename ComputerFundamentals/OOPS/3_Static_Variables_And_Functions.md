# Static Variables And Functions

- Objects in class wok independently of each other because they have different memory location unless the variables which we are working on is not a static variable

- When a variable is declared as static, space for it gets allocated for the lifetime of the program. Even if the function is called multiple times, space for the static variable is allocated only once and the value of the variable in the previous call gets carried through the next function call.  

- Initialization of static variable is done outside the class using scope resolution operator

## Static Variables

```cpp
// Student class example for static variables
class Student{
public:
	int a = 10;
	static int b;

	void print();
};

// Initialization of static variables for a class
// syntax : dataType className::variableName = value
int Student::b = 100;

void show(Student s1){
	cout << s1.a << " " << s1.b;
	cout << endl;
}

void demo(){
	static int a = 10;
	cout << a << " " << endl;
	a++;
}

// Counter example for static variables
class Counter{
public:
	static int count;

	Counter(){
		count++;
	}

	void show(){
		cout << "Number of objects of type Counter class are : "<< count << endl;
	}
};

int Counter::count = 0;

int main(){
	Student s1;

	// s1.b = 10; Static member variables are shared among all instances of the class and need to be defined outside the class just like regular global variables.
	show(s1);

	demo();
	demo();
	demo();

	Counter c1;
	c1.show();
	Counter c2;
	c2.show();
	Counter c3;
	c3.show();

}
```

## Static functions 

```cpp
class MathUtility {
public:
    int add(int a, int b) {
        return a + b;
    }

    static double squareRoot(double num) {
        return sqrt(num);
    }
};

int main(){
	// As the static function and variable are class specific not objects

	// Non static function are object specific, methods of class which are non static can only be invoked on object
	MathUtility node;
	int sum = node.add(5, 10);
	cout << sum << endl;

	// Static function are class specific
	int sqRt = MathUtility::squareRoot(25);
	cout << sqRt << endl;

	// Static variables can be used without creating objects
	cout << MathUtility::mycount << endl;
}
```