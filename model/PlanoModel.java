package model;

import bean.PlanoBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Model da entidade Plano.
 * Operações com o banco de dados para a tabela 'plano'.
 */
public class PlanoModel {

    /** Insere um novo plano no banco. */
    public static void create(PlanoBean p, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "INSERT INTO plano (nome, descricao, valor, periodicidade) VALUES (?, ?, ?, ?)"
        );
        st.setString(1, p.getNome());
        st.setString(2, p.getDescricao());
        st.setDouble(3, p.getValor());
        st.setString(4, p.getPeriodicidade());
        st.execute();
        st.close();
    }

    /** Remove um plano pelo ID. */
    public static void delete(int id, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "DELETE FROM plano WHERE id_plano = ?"
        );
        st.setInt(1, id);
        st.execute();
        st.close();
    }

    /** Retorna todos os planos cadastrados. */
    public static ArrayList<PlanoBean> listAll(Connection con) throws SQLException {
        ArrayList<PlanoBean> list = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(
            "SELECT id_plano, nome, descricao, valor, periodicidade FROM plano ORDER BY id_plano"
        );
        while (result.next()) {
            list.add(new PlanoBean(
                result.getInt(1),
                result.getString(2),
                result.getString(3),
                result.getDouble(4),
                result.getString(5)
            ));
        }
        st.close();
        return list;
    }
}
