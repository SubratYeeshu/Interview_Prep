# Defining Class and Objects

- There are three methods to declare objects of class

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