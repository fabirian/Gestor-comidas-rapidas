

package server.controllers;

import common.entities.NotifyDTO;
import common.interfaces.IControllerManageNotify;
import common.interfaces.INotifyClientNotificationsCallback;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabian
 */
public class ControllerManageNotify extends UnicastRemoteObject implements IControllerManageNotify {
    private final List<INotifyClientNotificationsCallback> clientReferences;
    
    public ControllerManageNotify() throws RemoteException {
        super();
        this.clientReferences = new ArrayList<INotifyClientNotificationsCallback>();
    }
    
    @Override
    public boolean saveReferenceRemoteNotify(INotifyClientNotificationsCallback objRefRemote) throws RemoteException {
        return this.clientReferences.add(objRefRemote);
    }
    
    public void notifyClient(NotifyDTO notify) throws RemoteException {
        for (INotifyClientNotificationsCallback objClientReference : clientReferences) {
            objClientReference.notifyClient(notify);
        }
    }
}