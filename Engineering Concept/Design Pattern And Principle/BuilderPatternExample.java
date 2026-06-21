package myfirstjavaproject.DN5Practice;
class Computer{
    String cpu;
    int ram;
    double storage;
    Computer(Builder builder){
        this.cpu=builder.cpu;
        this.ram=builder.ram;
        this.storage=builder.storage;
    }
    void display() {
        System.out.println("CPU: " + cpu);
        System.out.println("RAM: " + ram + " GB");
        System.out.println("Storage: " + storage + " GB");
    }
    static class Builder{
     String cpu;
        int ram;
        double storage;
        Builder setCpu(String cpu){
            this.cpu=cpu;
            return this;
        }
        Builder setRam(int ram) {
            this.ram = ram;
            return this;
        }

        Builder setStorage(double storage) {
            this.storage = storage;
            return this;
        }

        Computer build() {
            return new Computer(this);
        }

    }
}
public class BuilderPatternExample {
    public static void main(String[] args) {
         Computer gamingPC = new Computer.Builder().setCpu("Intel i9")
         .setRam(32).setStorage(1000).build();

        gamingPC.display();
    }
}
