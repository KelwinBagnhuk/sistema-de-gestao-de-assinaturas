package model;

import bean.PagamentoBean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class PagamentoModel {

    public static void create(PagamentoBean p, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "INSERT INTO pagamento " +
            "(id_assinatura, data_pagamento, data_vencimento, mes_referencia, metodo, valor_pago, status) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)"
        );
        st.setInt(1, p.getIdAssinatura());
        // data_pagamento pode ser null quando o status é 'pendente'
        if (p.getDataPagamento() != null) {
            st.setDate(2, p.getDataPagamento());
        } else {
            st.setNull(2, java.sql.Types.DATE);
        }
        st.setDate(3, p.getDataVencimento());
        st.setDate(4, p.getMesReferencia());
        st.setString(5, p.getMetodo());
        st.setDouble(6, p.getValorPago());
        st.setString(7, p.getStatus());
        st.execute();
        st.close();
    }

    public static void delete(int id, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement(
            "DELETE FROM pagamento WHERE id_pagamento = ?"
        );
        st.setInt(1, id);
        st.execute();
        st.close();
    }

    public static ArrayList<PagamentoBean> listAll(Connection con) throws SQLException {
        ArrayList<PagamentoBean> list = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(
            "SELECT id_pagamento, id_assinatura, data_pagamento, data_vencimento, " +
            "       mes_referencia, metodo, valor_pago, status " +
            "FROM pagamento ORDER BY id_pagamento"
        );
        while (result.next()) {
            list.add(new PagamentoBean(
                result.getInt(1),
                result.getInt(2),
                result.getDate(3),   // pode ser null
                result.getDate(4),
                result.getDate(5),
                result.getString(6),
                result.getDouble(7),
                result.getString(8)
            ));
        }
        st.close();
        return list;
    }
}
