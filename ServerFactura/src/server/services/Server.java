package server.services;

import server.infra.ServerFacturaSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabian
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerFacturaSocket server = new ServerFacturaSocket();
            server.start();
        } catch (Exception ex) {
             Logger.getLogger("Server").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
        }
    }
}