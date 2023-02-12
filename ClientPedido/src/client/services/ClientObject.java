package client.services;

import client.utilities.RegisterClient;
import client.view.MenuClient;
import common.infra.Console;
import common.interfaces.IControllerManagerPeticion;

/**
 *
 * @author fallen
 */
public class ClientObject {
    private static IControllerManagerPeticion objRemotePeticion;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numPortRMIRegistry = 0;
        String addressIpRMIRegistry = "";
        addressIpRMIRegistry = Console.read("Cuál es la dirección ip donde se encuentra el rmiregistry ?", addressIpRMIRegistry, false);
        numPortRMIRegistry = Console.read("Cuál es el numero de puerto por el cual escucha el rmiregistry ?", numPortRMIRegistry, false);
        objRemotePeticion = (IControllerManagerPeticion) RegisterClient.getObjectRemote(addressIpRMIRegistry, numPortRMIRegistry, "objServicioGestionPedido");
        MenuClient objMenu = new MenuClient("          === Menu ===          ", new String[]{"Pedir hamburguesa", "Generar Factura"}, objRemotePeticion);
    }  
}