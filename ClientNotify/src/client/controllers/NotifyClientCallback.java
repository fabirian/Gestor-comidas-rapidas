package client.controllers;

import client.view.GUICliente;
import common.entities.NotifyDTO;
import common.interfaces.INotifyClientNotificationsCallback;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author fabian
 */
public class NotifyClientCallback extends UnicastRemoteObject implements INotifyClientNotificationsCallback {
    private GUICliente objGUI;
    
    public NotifyClientCallback() throws RemoteException {
        super();
        this.objGUI = new GUICliente();
    } 
    
    @Override
    public void notifyClient(NotifyDTO notify) throws RemoteException {
        this.objGUI.showNotification(notify);
    }
}