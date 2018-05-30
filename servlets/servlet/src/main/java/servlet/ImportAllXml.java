package servlet;

import org.xml.sax.SAXException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@MultipartConfig
public class ImportAllXml extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InitialContext initialContext = null;
       // try {
            //initialContext = new InitialContext();
            //UserSessionBean userSessionBean = (UserSessionBean) initialContext.lookup("java:app/ejb_ejb/UserSessionEJB!hibernate.controller.UserSessionBean");
            //Part importXmlFilePart = req.getPart("xmlfile");
            String message = "";
            String errorMessage = "xml export not supported yet";
            /*try {
                userSessionBean.importXml(importXmlFilePart);
                message = "xml import success";
            } catch (SAXException e) {
                message = "xml file isn't valid";
                errorMessage = e.getMessage();
            } catch (JAXBException e) {
                e.printStackTrace();
            } finally {*/
                resp.sendRedirect(req.getContextPath() + "/import.jsp?ImportMessage=" + message + "&ErrorMessage=" + errorMessage);
            /*}
        } catch (NamingException e) {
            e.printStackTrace();
        }*/
    }
}
