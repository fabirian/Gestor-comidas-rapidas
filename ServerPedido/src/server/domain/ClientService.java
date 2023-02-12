package server.domain;

import common.entities.PedidosDTO;
import java.util.ArrayList;
import server.access.IClientAccess;

/**
 * servicios que el cliente puede usar del servidor (mascaras)
 * se comunica con la capa de bajo nivel que envia la solicitud
 * @author fallen
 */
public class ClientService {
    private final IClientAccess service;
    
    /**
     * inyeccion de dependencias
     * @param service un clase concreta que implementa la interfaz de acceso, se instancia con una fabrica
     */
    public ClientService(IClientAccess service) {
        this.service = service;
    }
    
    public boolean sendMessageFactura(ArrayList<PedidosDTO> factura) throws Exception {
        return this.service.sendMessageFactura(factura);
    }
}