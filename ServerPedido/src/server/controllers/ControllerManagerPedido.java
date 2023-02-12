package server.controllers;

import com.google.gson.Gson;
import common.entities.PedidosDTO;
import common.entities.NotifyDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import server.access.IClientAccess;
import server.domain.ClientService;
import common.interfaces.IControllerManagerPeticion;

/**
 *
 * @author fabian
 */
public class ControllerManagerPedido extends UnicastRemoteObject implements IControllerManagerPeticion {
    private final ControllerManageNotify objManageNotify;
    private ClientService service;
    private final ArrayList<PedidosDTO> pedidos;
    
    public ControllerManagerPedido(ControllerManageNotify objManageNotify, IClientAccess service) throws RemoteException {
        super();
        this.objManageNotify = objManageNotify;
        this.service = new ClientService(service);
         this.pedidos = new ArrayList();
    }
    
    @Override
    public boolean registerPedido(List<PedidosDTO> pedido) throws RemoteException {
        int costoParcial;
        for(int i=0; i<pedido.size();i++){
            if (pedido.get(i).getTipo().equals("pequeña")) {
                if (pedido.get(i).getCantIngExtra() > 0) {
                    costoParcial = 1000 + (pedido.get(i).getCantIngExtra() * 1000);
                    PedidosDTO ped = new PedidosDTO(pedido.get(i).getNo(),pedido.get(i).getIdentificador(), 
                            pedido.get(i).getTipo(), pedido.get(i).getCantIngExtra(), costoParcial);
                    pedidos.add(ped);
                } else {
                    costoParcial = 1000;
                    PedidosDTO ped = new PedidosDTO(pedido.get(i).getNo(), pedido.get(i).getIdentificador(), pedido.get(i).getTipo(), pedido.get(i).getCantIngExtra(), costoParcial);
                    pedidos.add(ped);
                }
            }
            if (pedido.get(i).getTipo().equals("mediana")) {
                if (pedido.get(i).getCantIngExtra() > 0) {
                    costoParcial = 2000 + (pedido.get(i).getCantIngExtra() * 1000);
                    PedidosDTO ped = new PedidosDTO(pedido.get(i).getNo(),pedido.get(i).getIdentificador(), pedido.get(i).getTipo(), pedido.get(i).getCantIngExtra(), costoParcial);
                    pedidos.add(ped);
                } else {
                    costoParcial = 2000;
                    PedidosDTO ped = new PedidosDTO(pedido.get(i).getNo(),pedido.get(i).getIdentificador(), pedido.get(i).getTipo(), pedido.get(i).getCantIngExtra(), costoParcial);
                    pedidos.add(ped);
                }
            }
            if (pedido.get(i).getTipo().equals("grande")) {
                if (pedido.get(i).getCantIngExtra() > 0) {
                    costoParcial = 3000 + (pedido.get(i).getCantIngExtra() * 1000);
                    PedidosDTO ped = new PedidosDTO(pedido.get(i).getNo(),pedido.get(i).getIdentificador(), pedido.get(i).getTipo(), pedido.get(i).getCantIngExtra(), costoParcial);
                    pedidos.add(ped);
                } else {
                    costoParcial = 3000;
                    PedidosDTO ped = new PedidosDTO(pedido.get(i).getNo(),pedido.get(i).getIdentificador(), pedido.get(i).getTipo(), pedido.get(i).getCantIngExtra(), costoParcial);
                    pedidos.add(ped);
                }
            }
        }
        return true;
    }
    private void sendMessageToFactura(ArrayList<PedidosDTO> factura) {
        try {
            this.service.sendMessageFactura(factura);
        } catch (Exception ex) {
            System.out.println("No fue posible conectarse");
        }
    }

    @Override
    public boolean generarFactura() throws RemoteException {
        NotifyDTO objNotify = new NotifyDTO(pedidos);
        this.objManageNotify.notifyClient(objNotify);
        sendMessageToFactura(pedidos);
        pedidos.clear();
        return true;
    }
}
