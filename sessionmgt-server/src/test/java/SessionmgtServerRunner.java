import com.quanix.app.integration.core.JettyFactory;
import org.eclipse.jetty.server.Server;

/**
 * @author : lihaoquan
 */
public class SessionmgtServerRunner {

    public static final int PORT = 8080;
    public static final String CONTEXT = "/sessionmgt-server";
    public static final String[] TLD_JAR_NAMES
            = new String[] { "sitemesh", "spring-webmvc", "shiro-web" };

    public static final String DEFAULT_WEBAPP_PATH = "sessionmgt-server/src/main/webapp";

    public static void main(String[] args) throws Exception {


        // 启动Jetty
        Server server = JettyFactory.createServerInSource(DEFAULT_WEBAPP_PATH,PORT, CONTEXT);
        JettyFactory.setTldJarNames(server, TLD_JAR_NAMES);

        try {
            System.out.println("[HINT] Don't forget to set -XX:MaxPermSize=128m");

            server.start();
            System.out.println("Server running at http://localhost:" + PORT + CONTEXT);
            System.out.println("[HINT] Hit Enter to reload the application quickly");

            // 等待用户输入回车重载应用.
            while (true) {
                char c = (char) System.in.read();
                if (c == '\n') {
                    JettyFactory.reloadContext(server);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
