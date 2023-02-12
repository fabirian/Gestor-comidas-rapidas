package server.infra;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import common.entities.PedidosDTO;
import common.infra.JsonError;
import common.infra.Utilities;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabian
 */
public class ServerFacturaSocket implements Runnable {
     /**
     * servicio para el socket, "orejita"
     */
    private static ServerSocket ssock;
    /**
     * socket por donde se hace la peticion/respuesta
     */
    private static Socket socket;
    /**
     * permite leer un flujo de datos del socket
     */
    private Scanner input;
    /**
     * permite escibir un flujo de datos en el socket
     */
    private PrintStream output;
    /**
     * Puerto por donde se escucha el server socket
     */
    private static final int PORT = Integer.parseInt(Utilities.loadProperty("server.port"));
    
    public ServerFacturaSocket() {}
    
    /**
     * instancia el server socket y abre el puerto respectivo
     */
    private static void openPort() {
        try {
            ssock = new ServerSocket(PORT);
            Logger.getLogger(ServerFacturaSocket.class.getName()).log(Level.INFO, "Socket conectado");
        } catch (IOException ex) {
            Logger.getLogger(ServerFacturaSocket.class.getName()).log(Level.SEVERE, "Error del server socket al abrir el puerto", ex);
        }
    }

    /**
     * estpera platoD que el cliente se conecte y devuelve un socket
     */
    private static void waitToClient() {
        try {
            //en este punto el socket espera platoD que accept reciba una conexion
            socket = ssock.accept();
            //informara si hubo conexion
            Logger.getLogger("Socket").log(Level.INFO, "Socket conectado");
        } catch (IOException ex) {
            Logger.getLogger(ServerFacturaSocket.class.getName()).log(Level.SEVERE, "Eror al abrir un socket", ex);
        }
    }
    
    /**
     * lanza el hilo, un hilo individual atendera platoD el cliente
     */
    private static void throwThread() {
        new Thread(new ServerFacturaSocket()).start();
    }

    /**
     * arranca el servidor y hace la estructura completa
     */
    public void start() {
        openPort();
        while (true) {
            waitToClient();
            throwThread();
        }
    }
    
    /**
     * crea o inicializa los atributos encargados de leer y escribir, flujos con
     * el socket
     *
     * @throws IOException
     */
    private void createStreams() throws IOException {
        output = new PrintStream(socket.getOutputStream());
        input = new Scanner(socket.getInputStream());
    }

    /**
     * lee el flujo del socket
     */
    private void readStream() {
        if (input.hasNextLine()) {//verifica si hay contenido en el flujo de entrada
            String request = input.nextLine();
            processRequest(request);
        } else {
            output.flush();
            String errorJson = generateErrorJson();
            output.println(errorJson);
        }
    }
    
    /**
     * Cierra los flujos de entrada y salida
     *
     * @throws IOException
     */
    private void closeStream() throws IOException {
        output.close();
        input.close();
        socket.close();
    }
    
    /**
     * Genera un ErrorJson genérico
     *
     * @return error en formato json
     */
    private String generateErrorJson() {
        List<JsonError> errors = new ArrayList<>();
        JsonError error = new JsonError();
        error.setCode("400");
        error.setError("BAD_REQUEST");
        error.setMessage("Error en la solicitud");
        errors.add(error);

        Gson gson = new Gson();
        String errorJson = gson.toJson(errors);

        return errorJson;
    }
    
    @Override
    public void run() {
        try {
            //crea el flujo de datos, inicializa input y output, entrada y salida de datos
            createStreams();
            //crea el flujo de datos para la lectura del socket
            readStream();
            //cierra flujo
            closeStream();
        } catch (IOException ex) {
            Logger.getLogger(ServerFacturaSocket.class.getName()).log(Level.SEVERE, "Eror al leer el flujo", ex);
        }
    }
    
    private void processRequest(String requestJson) {
        Gson gson = new Gson();
        int costoTotal=0; 
                System.out.println("===== Listado de hamburguesas compradas =====");
                    List<PedidosDTO> factura = gson.fromJson(requestJson, new TypeToken<List<PedidosDTO>>(){}.getType());
                    System.out.println();
                    for(PedidosDTO object: factura){
                        System.out.println("Hamburguesa no: "+object.getNo());
                        System.out.println("Identificador: " +object.getIdentificador());
                        System.out.println("Tipo: " +object.getTipo());
                        System.out.println("Cantidad de ingredientes extra: " +object.getCantIngExtra());
                        System.out.println("Costo: " +object.getCostoParcial());
                        costoTotal += object.getCostoParcial()+ (object.getCostoParcial()*0.5);
                        System.out.println();
                    }
                System.out.println("Costo con IVA del pedido: " +costoTotal);
    }
    
}