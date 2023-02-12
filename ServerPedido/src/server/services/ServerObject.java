package server.services;

import common.infra.Console;
import java.rmi.RemoteException;
import server.access.Factory;
import server.access.IClientAccess;
import server.controllers.ControllerManageNotify;
import server.controllers.ControllerManagerPedido;
import server.utilities.RegisterServer;

/**
 *
 * @author fabian
 */
public class ServerObject {

    public static void main(String[] args) throws RemoteException, Exception {
        int numPortRMIRegistry = 0;
        String addressIpRMIRegistry = " ";
        addressIpRMIRegistry = Console.read("Cuál es la dirección ip donde se encuentra el rmiRegistry ?", addressIpRMIRegistry, false);
        numPortRMIRegistry = Console.read("Cuál es el numero de puerto por el cual escucha el rmiRegistry ?", numPortRMIRegistry, false);
        
        IClientAccess serviceLog = Factory.getInstance().getClienteService();
        ControllerManageNotify objRemoteNotify = new ControllerManageNotify();
        ControllerManagerPedido objRemotePedido = new ControllerManagerPedido(objRemoteNotify, serviceLog);
       
        try {
            RegisterServer.runNS(numPortRMIRegistry);
            RegisterServer.registerObjectRemote(objRemotePedido, addressIpRMIRegistry, numPortRMIRegistry, "objServicioGestionPedido");
            RegisterServer.registerObjectRemote(objRemoteNotify, addressIpRMIRegistry, numPortRMIRegistry, "objServicioGestionNotificaciones");
            
        } catch (Exception e) {
            Console.writeJumpLine("No fue posible arrancar el ns o registrar el objeto remoto " + e.getMessage(), false);
        }
    }   
}