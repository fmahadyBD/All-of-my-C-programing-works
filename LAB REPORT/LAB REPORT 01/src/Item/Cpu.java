package Item;

public class Cpu extends Computer {
    int ram, rom;

    public Cpu(int id, String name, int age, int projectCost, int Deadline, String Brand, int ram, int rom) {
        super(id, name, age, projectCost, Deadline, Brand);
        this.ram = ram;
        this.rom = rom;
    }

    public void show() {
        super.show();
        System.out.print(" Ram: " + ram + "ROM: " + rom);

    }

}
