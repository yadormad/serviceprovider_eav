package model.client;

import model.ProviderObject;
import model.service.obj.ServiceObject;
import model.service.obj.ServiceTypeObject;

import java.util.HashMap;
import java.util.Map;

public class ClientObject implements ProviderObject{
    private Integer id;
    private String login;
    private String info;
    private String pass;
    private Map<ServiceTypeObject, ServiceObject> serviceObjectMap;

    public ClientObject(Integer id, String login, String info, String pass) {
        this.id = id;
        this.login = login;
        this.info = info;
        this.pass = pass;
    }

    public ClientObject(String login, String info, String pass) {
        this.login = login;
        this.info = info;
        this.pass = pass;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Map<ServiceTypeObject, ServiceObject> getServiceObjectMap() {
        if(this.serviceObjectMap == null) this.serviceObjectMap = new HashMap<>();
        return serviceObjectMap;
    }

    public void setServiceObjectMap(Map<ServiceTypeObject, ServiceObject> serviceObjectMap) {
        this.serviceObjectMap = serviceObjectMap;
    }

}
