# Defining Class and Objects

- When a class is defined some memory is allocated to it for designing the layout of class

## There are three methods to declare objects of class

```cpp
// Method 1 : 
class Student{
public: 
	string name;
	int age;
	double height;
}obj1, obj2;


// Method 2 : 
class Student2{
public: 
	string name;
	int age;
	double height;
};

// Method 3 : 
class Student3{
public: 
	string name;
	int age;
	double height;
};

int main(){
	// Method 1 : 
	obj1.name = "Subrat";
	obj1.age = 23;
	obj1.height = 5.9;

	cout << "Information About Student 1 : " << obj1.name << " " << obj1.age << " " << obj1.height << endl;

	// Method 2 : 
	class Student2 obj2;
	obj2.name = "Saurabh";
	obj2.age = 30;
	obj2.height = 6.1;

	cout << "Information About Student 2 : " << obj2.name << " " << obj2.age << " " << obj2.height << endl;

	// Method 3 : 
	Student3 obj3;
	obj3.name = "Yeeshu";
	obj3.age = 10;
	obj3.height = 5.2;

	cout << "Information About Student 3 : " << obj3.name << " " << obj3.age << " " << obj3.height << endl;

}
```

## Classes do take up space

```cpp
class person{
public:
	string name;
	int age;

	string firstName;
	int x;
	int y;
	long long m;

	// int arr[10000]; 

	void fun(){
		cout << this -> name;
	}

	person(string x, int y):name(x),age(y){};
};

int main(){
	cout << sizeof(person) << endl;
	person subrat("Subrat", 10);
	cout << subrat.name << " " << subrat.age << endl;

	cout << sizeof(subrat) << "  " << sizeof(person) << " " << sizeof(subrat.age);
}
```

## Array of objects

```cpp
class Student{
public:
	string firstName;
	string lastName;
	int age;

	// This pointer is passed to its member function invisibly
	void printData(){
		cout << this -> firstName << " " << this -> lastName << " " << this -> age << endl;
	}

	void setData(string x, string y, int z){
		this -> firstName = x;
		this -> lastName = y;
		this -> age = z;
	}
};

int main() {
	Student myStudents[5];

	vector<string> firstNames {"Subrat", "Ankit", "Praduman", "Saurabh", "Vaibhav"};
	vector<string> lastNames {"Yeeshu", "Maurya", "Singh", "Parth", "Shukla"};

	for(int i = 0 ; i < 5 ; i++){
		myStudents[i].setData(firstNames[i], lastNames[i], i*4 + 5);
	}

	for(int i = 0 ; i < 5 ; i++){
		myStudents[i].printData();
	}

	delete [] myStudents;

	for(int i = 0 ; i < 5 ; i++){
		myStudents[i].printData();
	}

}
```