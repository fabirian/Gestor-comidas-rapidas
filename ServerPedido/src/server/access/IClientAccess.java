package server.access;

import common.entities.PedidosDTO;
import java.util.ArrayList;

/**
 *
 * @author fallen
 */
public interface IClientAccess {
    public boolean sendMessageFactura(ArrayList<PedidosDTO> factura) throws Exception;
}