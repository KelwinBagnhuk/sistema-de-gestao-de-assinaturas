package model;

import bean.ServicoBean;
import bean.PlanoBean;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
Model da tabela associativa PlanoServico (relação entre Plano e Servico).
Ao listar, exibe os nomes dos planos e serviços com JOIN
*/
public class PlanoServicoModel {

    public static void create(int idPlano, int idServico, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "INSERT INTO plano_servico (id_plano, id_servico) VALUES (?, ?)"
        );
        st.setInt(1, idPlano);
        st.setInt(2, idServico);
        st.execute();
        st.close();
    }

    public static void delete(int idPlano, int idServico, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "DELETE FROM plano_servico WHERE id_plano = ? AND id_servico = ?"
        );
        st.setInt(1, idPlano);
        st.setInt(2, idServico);
        st.execute();
        st.close();
    }

    public static ArrayList<String> listAll(Connection con) throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(
            "SELECT p.id_plano, p.nome AS plano, s.id_servico, s.nome AS servico " +
            "FROM plano_servico ps " +
            "JOIN plano   p ON ps.id_plano   = p.id_plano " +
            "JOIN servico s ON ps.id_servico = s.id_servico " +
            "ORDER BY p.id_plano, s.id_servico"
        );
        while (result.next()) {
            list.add("  Plano ID: "    + result.getInt("id_plano")
                   + " | Plano: "      + result.getString("plano")
                   + " | Servico ID: " + result.getInt("id_servico")
                   + " | Servico: "    + result.getString("servico"));
        }
        st.close();
        return list;
    }
}
