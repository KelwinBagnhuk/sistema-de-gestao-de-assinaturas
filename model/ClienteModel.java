package model;

import bean.ClienteBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteModel {

    public static void create(ClienteBean c, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "INSERT INTO cliente (nome, email, cpf, telefone) VALUES (?, ?, ?, ?)"
        );
        st.setString(1, c.getNome());
        st.setString(2, c.getEmail());
        st.setString(3, c.getCpf());
        st.setString(4, c.getTelefone());
        st.execute();
        st.close();
    }

    public static void delete(int id, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "DELETE FROM cliente WHERE id_cliente = ?"
        );
        st.setInt(1, id);
        st.execute();
        st.close();
    }

    public static ArrayList<ClienteBean> listAll(Connection con) throws SQLException {
        ArrayList<ClienteBean> list = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(
            "SELECT id_cliente, nome, email, cpf, telefone FROM cliente ORDER BY id_cliente"
        );
        while (result.next()) {
            list.add(new ClienteBean(
                result.getInt(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5)
            ));
        }
        st.close();
        return list;
    }
}
