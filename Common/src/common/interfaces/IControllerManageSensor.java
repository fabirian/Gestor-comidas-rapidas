package common.interfaces;

import common.entities.PedidosDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author fallen
 */
public interface IControllerManageSensor extends Remote {
    public boolean registerPedido(List<PedidosDTO> indicator) throws RemoteException;
    public boolean generarFactura() throws RemoteException;
}