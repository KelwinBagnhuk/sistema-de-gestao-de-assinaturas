package model;

import bean.ServicoBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServicoModel {

    public static void create(ServicoBean s, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "INSERT INTO servico (nome, descricao) VALUES (?, ?)"
        );
        st.setString(1, s.getNome());
        st.setString(2, s.getDescricao());
        st.execute();
        st.close();
    }

    public static void delete(int id, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "DELETE FROM servico WHERE id_servico = ?"
        );
        st.setInt(1, id);
        st.execute();
        st.close();
    }

    public static ArrayList<ServicoBean> listAll(Connection con) throws SQLException {
        ArrayList<ServicoBean> list = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(
            "SELECT id_servico, nome, descricao FROM servico ORDER BY id_servico"
        );
        while (result.next()) {
            list.add(new ServicoBean(
                result.getInt(1),
                result.getString(2),
                result.getString(3)
            ));
        }
        st.close();
        return list;
    }
}
