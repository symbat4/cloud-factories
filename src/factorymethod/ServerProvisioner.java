package factorymethod;

/**
 * CREATOR: declares the factory method (createServer) and uses it in deploy().
 * Clean Code: deploy() is a small method that does one thing and reads like a sentence.
 */
public abstract class ServerProvisioner {

    /** The FACTORY METHOD - subclasses decide which concrete server to build. */
    protected abstract CloudServer createServer();

    public CloudServer deploy() {
        CloudServer server = createServer();
        server.provision();
        server.start();
        return server;
    }
}
