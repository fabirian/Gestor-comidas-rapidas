package common.entities;

import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author fallen
 */
public class NotifyDTO implements Serializable {
    private final ArrayList<PedidosDTO> pedidos;

    public NotifyDTO(ArrayList<PedidosDTO> pedidos) {
        this.pedidos = pedidos;
    }

    public ArrayList<PedidosDTO> getPedidos() {
        return pedidos;
    }
    
    
    
}