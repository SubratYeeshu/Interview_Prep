import java.util.*;

/*
 - T -> S: T is derived class of S, then object of type T should be replaceble with object of type S
 - Dont reduce capability of class
 - Solution: In parent class put generic methods only 
*/

// LSP Violation
// class Vehicle {
//     public Integer getNumberOfWheel() { 
//         return 2;
//     }

//     public Boolean hasEngine() {
//         return true;
//     }
// }

// class MotorCycle extends Vehicle {}

// class Car extends Vehicle {
//     @Override
//     public Integer getNumberOfWheel() {
//         return 4;
//     }
// }

// class Bicycle extends Vehicle {
//     @Override
//     public Boolean hasEngine() {  // Nnarrowing down the functionality
//         return null;
//     }
// }


// public class LiskovSubstitutionPrinciple {
//     public static void main(String[] args) {
//         List<Vehicle> vehicleList = new ArrayList<>();
//         vehicleList.add(new Car());
//         vehicleList.add(new MotorCycle());
//         vehicleList.add(new Bicycle());

//         for(Vehicle vehicle: vehicleList){
//             System.out.println("Engine: " + vehicle.hasEngine().toString() + " Wheel: " + vehicle.getNumberOfWheel());  // NPE
//         }
//     }
// }


// Following LSP
class Vehicle {
    public Integer getNumberOfWheel() {
        return 2;
    }
}

class EngineVehicle extends Vehicle {
    public Boolean hasEngine() {
        return true;
    }
}

class MotorCycle extends EngineVehicle {

}

class Car extends EngineVehicle {
    @Override
    public Integer getNumberOfWheel() {
        return 4;
    }
}

class Bicycle extends Vehicle {

}

public class LiskovSubstitutionPrinciple {
    public static void main(String[] args) {
        // Code: 1
        List<Vehicle> vehicleList1 = new ArrayList<>();
        vehicleList1.add(new Car());
        vehicleList1.add(new MotorCycle());
        vehicleList1.add(new Bicycle());

        for(Vehicle vehicle: vehicleList1){
            System.out.println("Engine: " + vehicle.hasEngine().toString() + " Wheel: " + vehicle.getNumberOfWheel());  // Compile time error
        }

        // Code: 2
        List<EngineVehicle> vehicleList2 = new ArrayList<>();
        vehicleList2.add(new Car());
        vehicleList2.add(new MotorCycle());
        vehicleList2.add(new Bicycle());  // Compile time error



    }
}








/*
 * 
 Shape Example standard 
 LSP Violation
class Shape {
    public double area() {
        return 0.0;
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}

class Square extends Rectangle {
    public Square(double side) {
        super(side, side); // A square is a rectangle with equal width and height
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        super.setHeight(width); // Adjust height to keep it a square
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        super.setWidth(height); // Adjust width to keep it a square
    }
}

public class ShapeAreaCalculator {
    public static void printArea(Shape shape) {
        System.out.println("Area: " + shape.area());
    }

    public static void main(String[] args) {
        Shape rectangle = new Rectangle(5, 4);
        Shape square = new Square(4);

        printArea(rectangle); // Area: 20.0
        printArea(square);     // Area: 16.0

        rectangle.setWidth(6); // Rectangle area now is 24.0
        System.out.println("Modified Rectangle Area: " + rectangle.area());

        square.setWidth(5); // This will also set the height to 5, maintaining square properties
        System.out.println("Modified Square Area: " + square.area()); // Expected area is 25.0

    }
}

 Following LSP
abstract class Shape {
    public abstract double area();
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}

class Square extends Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return side * side;
    }

    public void setSide(double side) {
        this.side = side;
    }
}

public class ShapeAreaCalculator {
    public static void printArea(Shape shape) {
        System.out.println("Area: " + shape.area());
    }

    public static void main(String[] args) {
        Shape rectangle = new Rectangle(5, 4);
        Shape square = new Square(4);

        printArea(rectangle); // Area: 20.0
        printArea(square);     // Area: 16.0

        rectangle.setWidth(6); // Rectangle area now is 24.0
        System.out.println("Modified Rectangle Area: " + rectangle.area());

        square.setSide(5); // Square area now is 25.0
        System.out.println("Modified Square Area: " + square.area()); // Expected area is 25.0
    }
}


 * 
 */