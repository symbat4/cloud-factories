package factorymethod;

/** CONCRETE PRODUCT #2 */
public class DatabaseServer implements CloudServer {

    private static final int POSTGRES_PORT = 5432;

    private final ServerSpecs specs;

    public DatabaseServer(ServerSpecs specs) {
        this.specs = specs;
    }

    @Override
    public void provision() {
        System.out.println("Provisioning database server [" + specs + "]");
    }

    @Override
    public void start() {
        System.out.println("Database server started, accepting connections on port " + POSTGRES_PORT);
    }
}
