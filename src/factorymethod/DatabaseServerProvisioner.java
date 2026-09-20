package factorymethod;

/** CONCRETE CREATOR #2 - produces a DatabaseServer. */
public class DatabaseServerProvisioner extends ServerProvisioner {

    private static final int DATABASE_CPU_CORES = 8;
    private static final int DATABASE_RAM_GB = 32;

    @Override
    protected CloudServer createServer() {
        return new DatabaseServer(new ServerSpecs(DATABASE_CPU_CORES, DATABASE_RAM_GB));
    }
}
