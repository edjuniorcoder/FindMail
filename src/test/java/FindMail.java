
import br.com.jerrycoder.model.vo.MailSession;
import br.com.jerrycoder.model.vo.Server;
import br.com.jerrycoder.model.vo.User;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
/**
 *
 * @author jerry
 */
public class FindMail {

    public static void main(String[] args) throws NoSuchProviderException, MessagingException, IOException, InterruptedException {

        /*
        String str = "jerry@terra.com.br";
        List<Server> listaServers = new ArrayList<>();
        listaServers.add(new Server("terra.com.br", "imap.terra.com.br", "993"));

        Server server = null;
        for (Server listaServer : listaServers) {
            if (listaServer.getDominio().equals(str.split("@")[1])) {
                System.out.println("contém;");
                server = listaServer;
                break;
            }
        }

        System.out.println("server: " + server);
        
         */
        JFrame f = new JFrame("JProgressBar Sample");
        // Centralizar o JFrame na tela
f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = f.getContentPane();
		JProgressBar progressBar = new JProgressBar();
		JLabel label = new JLabel();
                
		for (int i = 0; i < 100; i++) {
			label.setText("cadastrando "+i);
			progressBar.setValue(i);
			progressBar.setStringPainted(true);
			Thread.sleep(1000);
			Border border = BorderFactory.createTitledBorder("Reading...");
			progressBar.setBorder(border);
			content.add(progressBar, BorderLayout.NORTH);
			content.add(label,BorderLayout.SOUTH);
			f.setSize(300, 100);
			f.setVisible(true);
		}
    }
}
