import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class AppSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setMaxInactiveInterval(600);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        /*try {
            UserSessionBean userSessionBean = (UserSessionBean) new InitialContext().lookup("java:app/ejb_ejb/UserSessionEJB!hibernate.controller.UserSessionBean");
            userSessionBean.removeBlocks((String) httpSessionEvent.getSession().getAttribute("login"));
        } catch (NamingException e) {
            e.printStackTrace();
        }*/
    }
}
