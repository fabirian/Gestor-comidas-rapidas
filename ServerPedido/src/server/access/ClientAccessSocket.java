package server.access;

import server.utilities.LogSocket;
import com.google.gson.Gson;
import common.entities.PedidosDTO;
import common.infra.JsonError;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;

/**
 *
 * @author fabian
 */
public class ClientAccessSocket implements IClientAccess {
    private LogSocket mySocket;
    
    public ClientAccessSocket() {
        mySocket = new LogSocket();
    }
    
    private String proccessConnection(String requestJson) throws Exception {
        String jsonResponse = null;
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendStream(requestJson);
            mySocket.closeStream();
            mySocket.disconnect();
        } catch (IOException ex) {
            ex.getMessage();
        }
        if (jsonResponse == null) {
            throw new Exception("no se pudo conectar al servidor");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error, usar mejor login
                out.println("hubo algun tipo de error");
                throw new Exception(this.extractMessages(jsonResponse));
            } else {
                //Devuelve la respuesta del servidor
                return jsonResponse;
            }
        }
    }
    
    private String extractMessages(String jsonResponse) {
        var errors = jsonToErrors(jsonResponse);
        var msjs = "";
        for (var error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }
    
    private JsonError[] jsonToErrors(String jsonError) {
        var gson = new Gson();
        var error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }
    
    private String buildJson(ArrayList<PedidosDTO> factura) {
        Gson gson = new Gson();
        String requestJson = gson.toJson(factura);
        out.println("Json construido: " + requestJson);
        return requestJson;
    }
   
    @Override
    public boolean sendMessageFactura(ArrayList<PedidosDTO> factura) throws Exception {
        String requestJson = this.buildJson(factura);
        String value = this.proccessConnection(requestJson);
        return true;
    }
}