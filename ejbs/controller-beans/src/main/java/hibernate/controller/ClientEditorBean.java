package hibernate.controller;

import model.client.ClientObject;
import model.service.obj.ServiceObject;
import model.service.obj.ServiceTypeObject;

import javax.ejb.Stateful;
import java.util.*;

@Stateful(name = "ClientEditorEJB")
public class ClientEditorBean {
    private ClientObject editedClient;
    private Map<Integer, ServiceTypeObject> allServiceTypes;

    public void init(Integer clientId) {
        if (editedClient == null || !editedClient.getId().equals(clientId)) {
            editedClient = DaoLoader.getDaoFactory().getClientDao().getById(clientId);
            allServiceTypes = new HashMap<>();
            for(ServiceTypeObject typeObject:DaoLoader.getDaoFactory().getServiceTypeDao().getAll()) {
                allServiceTypes.put(typeObject.getId(), typeObject);
            }
        }
    }

    public void refresh() {
        editedClient = null;
    }

    public ClientObject getEditedClient() {
        return editedClient;
    }

    public String editClient(String info, String pass1, String pass2) {
        if(!pass1.equals(pass2)) return "Passwords don't match";
        editedClient.setInfo(info);
        editedClient.setPass(pass1);
        return "Client " + editedClient.getLogin() + " successfully edited";
    }

    public void saveClient() throws IllegalAccessException, InstantiationException {
        DaoLoader.getDaoFactory().getClientDao().persist(editedClient);
    }

    public Collection<ServiceTypeObject> getAllServiceTypes() {
        return allServiceTypes.values();
    }

    public Collection<ServiceTypeObject> getUnusedServiceTypes() {
        Collection<ServiceTypeObject> unusedTypes = new ArrayList<>(allServiceTypes.values());
        if(!editedClient.getServiceObjectMap().isEmpty()) {
            for(ServiceObject serviceObject:editedClient.getServiceObjectMap().values()) {
                unusedTypes.remove(serviceObject.getServiceType());
            }
        }
        return unusedTypes;
    }

    public void addService(Integer serviceTypeId) throws InstantiationException, IllegalAccessException {
        ServiceTypeObject serviceTypeObject = allServiceTypes.get(serviceTypeId);
        ServiceObject addedService = new ServiceObject(serviceTypeObject);
        editedClient.getServiceObjectMap().put(serviceTypeObject, addedService);
        saveClient();
    }

    public void removeService(Integer serviceTypeId) throws InstantiationException, IllegalAccessException {
        editedClient.getServiceObjectMap().remove(allServiceTypes.get(serviceTypeId));
        saveClient();
    }

    public ServiceObject getService(Integer serviceTypeId) {
        return editedClient.getServiceObjectMap().get(allServiceTypes.get(serviceTypeId));
    }
}
