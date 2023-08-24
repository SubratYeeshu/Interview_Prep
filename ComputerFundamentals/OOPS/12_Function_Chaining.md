# Function Chaining / Method Chaining 

- It is common sytax for making multiple method calls in OOPS programming languages. Each named method returns an object, allowing the calls to be chained in a single statement together in a simple statement without requiring variables to store the intermediate results.

## Code Snippet

```cpp
class Calculator {
private:
    double result;

public:
    Calculator() : result(0) {}

    Calculator& add(double value) {
        result += value;
        return *this;
    }

    Calculator& subtract(double value) {
        result -= value;
        return *this;
    }

    Calculator& multiply(double value) {
        result *= value;
        return *this;
    }

    Calculator& divide(double value) {
        if (value != 0) {
            result /= value;
        } else {
            cout << "Error: Division by zero!" << std::endl;
        }
        return *this;
    }

    double getResult() const {
        return result;
    }
};

int main() {
    Calculator calc;

    double finalResult = calc.add(10).multiply(2).subtract(5).divide(2).getResult();
    cout << "Final Result: " << finalResult << std::endl;

    return 0;
}
```