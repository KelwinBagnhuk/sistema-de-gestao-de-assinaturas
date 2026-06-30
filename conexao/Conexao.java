package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
conexão com o banco de dados PostgreSQL
PARA QUEM FOR RODAR: Altere os campos 'user', 'senha' e 'url' para os seus dados
*/
public class Conexao {

    private Connection con;

    public Conexao() {
        String driver = "org.postgresql.Driver";
        String user   = "postgres";
        String senha  = "postgres";  // Alterar para a sua senha do PostgreSQL
        String url    = "jdbc:postgresql://localhost:5432/subscriptions"; // nome do banco deve ser "subscriptions" no PostgreSQL

        try {
            Class.forName(driver);
            this.con = (Connection) DriverManager.getConnection(url, user, senha);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.exit(1);
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void closeConnection() {
        try {
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
