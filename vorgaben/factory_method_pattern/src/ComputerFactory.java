import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;

public class ComputerFactory {
    private static Logger logger = Logger.getLogger(ComputerFactory.class.getName());;
    private static ConsoleHandler consoleHandler = new ConsoleHandler();


    private ComputerFactory() {
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new ConsoleFormatter());
        logger.addHandler(consoleHandler);
    };

    public static Computer buildComputer(String product, String specs) {
        if (product.matches("stationaer")) {
            if (specs.matches("leise schnurrend")) {
                Computer computer = new DesktopComputer(new Ram(16), new Hdd(256), new Cpu(8, 1.21f));
                logger.info("\nComputer: " + product + ", " + specs + "\nCPU: " + computer.getCpu().getCores() +" Cores, " + computer.getCpu().getFrequency() + " GHZ\n" + "RAM: " + computer.getRam().getSize() + " GB\n" + "Drive: " + computer.getDrive().getStorageSize() + " GB " + computer.getDrive().getDescription() + "\n---\n");
                return computer;
            }
            if (specs.matches("viel Wumms")) {
                Computer computer = new DesktopComputer(new Ram(32), new Ssd(2000), new Cpu(16, 4.2f));
                logger.info("\nComputer: " + product + ", " + specs + "\nCPU: " + computer.getCpu().getCores() +" Cores, " + computer.getCpu().getFrequency() + " GHZ\n" + "RAM: " + computer.getRam().getSize() + " GB\n" + "Drive: " + computer.getDrive().getStorageSize() + " GB " + computer.getDrive().getDescription() + "\n---\n");
                return computer;
            }
        }

        if (product.matches("mobil")) {
            if (specs.matches("leise schnurrend")) {
                Computer computer = new LaptopComputer(new Ram(8), new Hdd(256), new Cpu(4, 1.21f));
                logger.info("\nComputer: " + product + ", " + specs + "\nCPU: " + computer.getCpu().getCores() +" Cores, " + computer.getCpu().getFrequency() + " GHZ\n" + "RAM: " + computer.getRam().getSize() + " GB\n" + "Drive: " + computer.getDrive().getStorageSize() + " GB " + computer.getDrive().getDescription() + "\n---\n");
                return computer;
            }
            if (specs.matches("viel Wumms")) {
                Computer computer = new LaptopComputer(new Ram(16), new Ssd(256), new Cpu(8, 2.4f));
                logger.info("\nComputer: " + product + ", " + specs + "\nCPU: " + computer.getCpu().getCores() +" Cores, " + computer.getCpu().getFrequency() + " GHZ\n" + "RAM: " + computer.getRam().getSize() + " GB\n" + "Drive: " + computer.getDrive().getStorageSize() + " GB " + computer.getDrive().getDescription() + "\n---\n");
                return computer;
            }
        }

        if (product.matches("nicht daheim")) {
            if (specs.matches("leise schnurrend")) {
                Computer computer = new CloudComputer(new Ram(24), new Hdd(1000), new Cpu(8, 1.21f));
                logger.info("\nComputer: " + product + ", " + specs + "\nCPU: " + computer.getCpu().getCores() +" Cores, " + computer.getCpu().getFrequency() + " GHZ\n" + "RAM: " + computer.getRam().getSize() + " GB\n" + "Drive: " + computer.getDrive().getStorageSize() + " GB " + computer.getDrive().getDescription() + "\n---\n");
                return computer;
            }
            if (specs.matches("viel Wumms")) {
                Computer computer = new CloudComputer(new Ram(128), new Ssd(10000), new Cpu(42, 9.001f));
                logger.info("\nComputer: " + product + ", " + specs + "\nCPU: " + computer.getCpu().getCores() +" Cores, " + computer.getCpu().getFrequency() + " GHZ\n" + "RAM: " + computer.getRam().getSize() + " GB\n" + "Drive: " + computer.getDrive().getStorageSize() + " GB " + computer.getDrive().getDescription() + "\n---\n");
                return computer;
            }
        }

        Computer computer = new ErrComputer();
        logger.warning("\n---\nUngueltige Konfiguration\n---");

        return computer;
    }

    public static void main(String[] args) {
        Computer stationärLeise = ComputerFactory.buildComputer("stationaer", "leise schnurrend");
        Computer stationärWumms = ComputerFactory.buildComputer("stationaer", "viel Wumms");
        Computer mobilLeise = ComputerFactory.buildComputer("mobil", "leise schnurrend");
        Computer mobilWumms = ComputerFactory.buildComputer("mobil", "viel Wumms");
        Computer cloudLeise = ComputerFactory.buildComputer("nicht daheim", "leise schnurrend");
        Computer cloudWumms = ComputerFactory.buildComputer("nicht daheim", "viel Wumms");
        Computer error = ComputerFactory.buildComputer("super", "geil");
    }
}
