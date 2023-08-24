# Declaring And Defining Functions


## Code snippet
```cpp
class Student{
public: 
	string name;
	int age;
	double height;
	double weight;

	void greetStudent(){
		cout << "Good Morning : " << this -> name << ", " << "Your Age is : " << age << endl;
	}

	// Declaring function inside the class
	double getBMI();

	double getBMI2(double height, double weight);

	// Constructor
	Student(string name, int age, double height, double weight){
		this -> name = name;
		this -> age = age;
		this -> height = height;
		this -> weight = weight;
	}
};

// Defining function outside the class
// syntax : returnType className::funcitionName(params)

double Student::getBMI(){
    double heightMeters = this -> height * 0.3048;
    double bmi = this -> weight / (heightMeters * heightMeters);
    cout << bmi << endl;
    return bmi;
}

double Student::getBMI2(double height, double weight){
    double heightMeters = height * 0.3048;
    double bmi = weight / (heightMeters * heightMeters);
    cout << bmi << endl;
    return bmi;
}
 
int main(){
	Student myStudent("Subrat", 23, 5.11, 80);
	myStudent.greetStudent();
	myStudent.getBMI();
	myStudent.getBMI2(5.11, 80);

}
```