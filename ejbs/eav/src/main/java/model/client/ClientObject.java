package model.client;

import model.ProviderObject;

public class ClientObject implements ProviderObject{
    private Integer id;
    private String login;
    private String info;
    private String pass;

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
}
