package admin;

import admin.block.PessimisticBlockEnum;

import javax.ejb.Stateless;
import javax.naming.AuthenticationException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Stateless(name = "Authentification2EJB")
public class AuthentificationBean {
    private DataSource dataSource;

    public AuthentificationBean() {
        try {
            InitialContext context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:/PostgresDS");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String login(String login, String pass) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement userStatement = null;
        ResultSet clientResultSet = null;
        try {
            conn = dataSource.getConnection();
            userStatement = conn.prepareStatement("SELECT * FROM public.users WHERE login = ? AND pass = ?");
            userStatement.setString(1, login);
            userStatement.setString(2, pass);
            clientResultSet = userStatement.executeQuery();
            if(clientResultSet.next()){
                initBlocks(login);
                return login;
            } else {
                throw new AuthenticationException("Wrong login/password");
            }
        } finally {
            try { Objects.requireNonNull(clientResultSet).close(); } catch (Exception e) { e.printStackTrace(); }
            try { Objects.requireNonNull(userStatement).close(); } catch (Exception e) { e.printStackTrace(); }
            try { Objects.requireNonNull(conn).close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    private void initBlocks(String login) {
        for (PessimisticBlockEnum blocksMap : PessimisticBlockEnum.values()) {
            blocksMap.addUser(login);
        }
    }
}
