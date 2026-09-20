package factorymethod;

/** CONCRETE PRODUCT #1 */
public class WebServer implements CloudServer {

    private static final int HTTPS_PORT = 443;

    private final ServerSpecs specs;

    public WebServer(ServerSpecs specs) {
        this.specs = specs;
    }

    @Override
    public void provision() {
        System.out.println("Provisioning web server [" + specs + "]");
    }

    @Override
    public void start() {
        System.out.println("Web server started, listening on port " + HTTPS_PORT);
    }
}
