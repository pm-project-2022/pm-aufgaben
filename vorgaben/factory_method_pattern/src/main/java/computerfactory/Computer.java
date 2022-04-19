package computerfactory;

public abstract class Computer {
    private Ram ram;
    private Drive drive;
    private Cpu cpu;
    private String err;

    public Computer(Ram ram, Drive drive, Cpu cpu) {
        this.ram = ram;
        this.drive = drive;
        this.cpu = cpu;
    }

    public Computer(String err) {
        this.err = err;
    }

    public Ram getRam() {
        return ram;
    }

    public Drive getDrive() {
        return drive;
    }

    public Cpu getCpu() {
        return cpu;
    }

    @Override
    public String toString() {
        if(this.err == null){
            return "---\nKonfiguration existiert nicht\n---";
        }
        return "---\nCPU: " + this.cpu.getCores() + " Cores" + ", " + this.cpu.getFrequency() + " GHZ\n" + "RAM: " + this.ram.getSize() + " GB\n" + "Drive: " + this.drive.getStorageSize() + " " + this.drive.getDescription() + "\n---";
    }
}
