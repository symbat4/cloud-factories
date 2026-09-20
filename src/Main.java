import abstractfactory.AwsFactory;
import abstractfactory.AzureFactory;
import abstractfactory.InfrastructureDeployer;
import factorymethod.DatabaseServerProvisioner;
import factorymethod.ServerProvisioner;
import factorymethod.ServerSpecs;
import factorymethod.WebServerProvisioner;

public class Main {

    private static final String BACKUP_FILE = "backup.zip";

    public static void main(String[] args) {
        runFactoryMethodDemo();
        runAbstractFactoryDemo();
        runValidationDemo();
    }

    private static void runFactoryMethodDemo() {
        System.out.println("=== Part A: Factory Method ===");
        ServerProvisioner web = new WebServerProvisioner();
        ServerProvisioner database = new DatabaseServerProvisioner();
        web.deploy();
        database.deploy();
    }

    private static void runAbstractFactoryDemo() {
        System.out.println("\n=== Part B: Abstract Factory ===");
        new InfrastructureDeployer(new AwsFactory()).deploy(BACKUP_FILE);
        new InfrastructureDeployer(new AzureFactory()).deploy(BACKUP_FILE);
    }

    private static void runValidationDemo() {
        System.out.println("\n=== Validated construction ===");
        try {
            new ServerSpecs(0, 4);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
