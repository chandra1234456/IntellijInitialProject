package lowleveldesign;

// Implement class Car

class Car {
    private final String brand;
    private final String model;
    private int speed = 0;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public int accelerate(int amount) {
        speed += amount;
        return speed;
    }

    public int brake(int amount) {
        speed = Math.max(0, speed - amount);
        return speed;
    }

    public int getSpeed() {
        return speed;
    }

    public String describe() {
        return brand + " " + model + " at " + speed + " km/h";
    }
}



public class CarMain {
    public static void main(String[] args) {
        Car obj = new Car("Toyota", "Corolla");
        String param_5 = obj.describe();
        int param_1 = obj.accelerate(20);
        int param_2 = obj.brake(10);
        int param_3 = obj.getSpeed();
        String param_4 = obj.describe();
        System.out.println(param_5);
        System.out.println(param_1);
        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_4);
    }
}


