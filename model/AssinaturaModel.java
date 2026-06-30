package model;

import bean.AssinaturaBean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class AssinaturaModel {

    public static void create(AssinaturaBean a, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "INSERT INTO assinatura (id_cliente, id_plano, data_inicio, data_fim, status) " +
            "VALUES (?, ?, ?, ?, ?)"
        );
        st.setInt(1, a.getIdCliente());
        st.setInt(2, a.getIdPlano());
        st.setDate(3, a.getDataInicio());
        if (a.getDataFim() != null) {
            st.setDate(4, a.getDataFim());
        } else {
            st.setNull(4, java.sql.Types.DATE);
        }
        st.setString(5, a.getStatus());
        st.execute();
        st.close();
    }

    public static void delete(int id, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "DELETE FROM assinatura WHERE id_assinatura = ?"
        );
        st.setInt(1, id);
        st.execute();
        st.close();
    }

    public static ArrayList<AssinaturaBean> listAll(Connection con) throws SQLException {
        ArrayList<AssinaturaBean> list = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(
            "SELECT id_assinatura, id_cliente, id_plano, data_inicio, data_fim, status " +
            "FROM assinatura ORDER BY id_assinatura"
        );
        while (result.next()) {
            list.add(new AssinaturaBean(
                result.getInt(1),
                result.getInt(2),
                result.getInt(3),
                result.getDate(4),
                result.getDate(5),
                result.getString(6)
            ));
        }
        st.close();
        return list;
    }
}
