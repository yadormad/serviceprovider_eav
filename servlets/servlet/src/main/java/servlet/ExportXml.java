package servlet;

import javax.ejb.FinderException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public class ExportXml extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setContentType("getxml/xml");
        String errorMessage = "xml export not supported yet";
        resp.sendRedirect(req.getContextPath() + "/import.jsp?ErrorMessage=" + errorMessage);
        /*InitialContext initialContext = null;
        try {
            initialContext = new InitialContext();
            UserSessionBean userSessionBean = (UserSessionBean) initialContext.lookup("java:app/ejb_ejb/UserSessionEJB!hibernate.controller.UserSessionBean");
            Integer clientId = null;
            if(req.getParameter("clientId") != null) {
                clientId = Integer.parseInt(req.getParameter("clientId"));
            }
            ServletOutputStream servletOutputStream = null;
            try {
                servletOutputStream = resp.getOutputStream();
                userSessionBean.exportIntoXml(servletOutputStream, clientId);
                servletOutputStream.flush();
            } catch (JAXBException | IOException e) {
                e.printStackTrace();
            } catch (FinderException e) {
                throw new ServletException(e.getMessage());
            } finally {
                if(servletOutputStream != null) servletOutputStream.close();
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }*/
    }
}
