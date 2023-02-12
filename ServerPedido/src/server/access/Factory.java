package server.access;

import static common.infra.Utilities.loadProperty;

/**
 * fabrica que retorna una instancia para el acceso al servidor
 * 
 * @author fallen
 */
public class Factory {
    private static Factory instance;
    
    private Factory(){}
    
    public static Factory getInstance(){
        if(instance == null){
            instance = new Factory();
        }
        return instance;
    }
    
    /**
     * crea una clase concreta de ClienteAccessSocket que realiza la comunicacion
     * @return 
     */
    public IClientAccess getClienteService(){
        IClientAccess result = null;
        var type = loadProperty("customer.service");
        switch (type){
            case "default":
                result = new ClientAccessSocket();
                break;
        }
        return result;
    }
}