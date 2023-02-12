package client.view;

import common.entities.PedidosDTO;
import java.rmi.RemoteException;
import common.infra.Console;
import common.infra.Menu;
import java.util.ArrayList;
import java.util.List;
import common.interfaces.IControllerManagerPeticion;

/**
 *
 * @author fallen
 */
public class MenuClient extends Menu {
    private final IControllerManagerPeticion objRemoteHamburguesa;
    
    public MenuClient(String title, String [] options, IControllerManagerPeticion objRemoteHamburguesa) {
        super(title, options);
        this.objRemoteHamburguesa = objRemoteHamburguesa;
        this.repeatedMenu();
    }
    
    @Override
    public void processOption() {
        switch (option) {
            case 1 -> {
                this.registerHamburguesa();
            }
            case 2 ->{
                this.generarFactura();
            }
            case 3 -> {
                Console.writeJumpLine("Salir...", false);
            }
        }
    }
    
    private void registerHamburguesa() {
        try {
            boolean valor = false;
            int cantHamb = 0, cantExtra =0;
            String tipo =" ", nombre = "";
            cantHamb = Console.read("Ingrese la cantidad de hamburguesas: ", cantHamb, false);
            List<PedidosDTO> lstHamburguesas = new ArrayList<>();
            for(int i = 0; i < cantHamb;i++){
                nombre = Console.read("Ingrese el nombre de la hamburguesa: ", nombre, false);
                tipo = Console.read("Ingrese el tipo (pequeña, mediana, grande): ", tipo, false);
                cantExtra = Console.read("Ingrese la cantidad de ingredientes extra: ", cantExtra, false);
                PedidosDTO objHamburguesa= new PedidosDTO(i+1, nombre, tipo, cantExtra,0);
                lstHamburguesas.add(objHamburguesa);

            }
            valor = objRemoteHamburguesa.registerPedido(lstHamburguesas);
            String messageOut = (valor) ? "Registro realizado satisfactoriamente..." : "No se pudo realizar el registro";
            Console.writeJumpLine(messageOut, false);
        } catch (RemoteException e) {
            Console.writeJumpLine("La operación no se pudo completar, intente nuevamente...", false);
        }
    }
    
    private void generarFactura(){
        boolean valor = false;
        try {
            valor = objRemoteHamburguesa.generarFactura();
            String messageOut = (valor) ? "Factura generada satisfactoriamente..." : "No se pudo generar la factura";
            Console.writeJumpLine(messageOut, false);
        } catch (RemoteException ex) {
           Console.writeJumpLine("La operación no se pudo completar, intente nuevamente...", false);
        }
    }
}