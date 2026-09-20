package factorymethod;

/** CONCRETE CREATOR #1 - produces a WebServer. */
public class WebServerProvisioner extends ServerProvisioner {

    private static final int WEB_CPU_CORES = 2;
    private static final int WEB_RAM_GB = 4;

    @Override
    protected CloudServer createServer() {
        return new WebServer(new ServerSpecs(WEB_CPU_CORES, WEB_RAM_GB));
    }
}
