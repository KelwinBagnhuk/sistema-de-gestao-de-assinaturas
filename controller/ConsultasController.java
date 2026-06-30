package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
Controller para as consultas especiais do sistema.

CONSULTA 1 - JOIN entre tabelas:
Lista todas as assinaturas mostrando nome do cliente, nome e periodicidade
do plano, datas e status. Une: assinatura + cliente + plano.

CONSULTA 2 - Subconsulta + Funções de Agregação:
Lista os clientes cujo total de pagamentos é maior do que a média
geral de todos os pagamentos do sistema.
Usa: SUM() por cliente, AVG() dentro de subconsulta, HAVING para filtrar.
*/
public class ConsultasController {

    // CONSULTA 1: JOIN entre assinatura, cliente e plano
    public void listarAssinaturasDetalhadas(Connection con) throws SQLException {
        String sql =
            "SELECT " +
            "    a.id_assinatura, " +
            "    c.nome           AS nome_cliente, " +
            "    p.nome           AS nome_plano, " +
            "    p.valor          AS valor_plano, " +
            "    p.periodicidade, " +
            "    a.data_inicio, " +
            "    a.data_fim, " +
            "    a.status " +
            "FROM assinatura a " +
            "JOIN cliente c ON a.id_cliente = c.id_cliente " +
            "JOIN plano   p ON a.id_plano   = p.id_plano   " +
            "ORDER BY a.id_assinatura";

        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(sql);

        System.out.println("\n=== CONSULTA 1: Assinaturas com dados do Cliente e Plano (JOIN) ===");
        System.out.println("------------------------------------------------------------------");

        boolean encontrou = false;
        while (result.next()) {
            encontrou = true;
            String fim = (result.getDate("data_fim") != null)
                         ? result.getDate("data_fim").toString()
                         : "Sem data de fim";

            System.out.println("  Assinatura #" + result.getInt("id_assinatura")
                + " | Cliente: "       + result.getString("nome_cliente")
                + " | Plano: "         + result.getString("nome_plano")
                + " (R$ "              + String.format("%.2f", result.getDouble("valor_plano"))
                + " / "                + result.getString("periodicidade") + ")"
                + " | Inicio: "        + result.getDate("data_inicio")
                + " | Fim: "           + fim
                + " | Status: "        + result.getString("status"));
        }
        if (!encontrou) {
            System.out.println("  Nenhuma assinatura encontrada.");
        }
        st.close();
    }

    // ---------------------------------------------------------------
    // CONSULTA 2: Subconsulta + Funções de Agregação
    // SUM(pg.valor_pago) -> soma os pagamentos de cada cliente
    // AVG(valor_pago)    -> média global dentro da subconsulta
    // HAVING             -> filtra grupos cujo total > média global
    // ---------------------------------------------------------------
    public void listarClientesAcimaDaMedia(Connection con) throws SQLException {
        String sql =
            "SELECT " +
            "    c.nome, " +
            "    c.email, " +
            "    SUM(pg.valor_pago) AS total_pago " +
            "FROM cliente c " +
            "JOIN assinatura a  ON c.id_cliente   = a.id_cliente " +
            "JOIN pagamento  pg ON a.id_assinatura = pg.id_assinatura " +
            "GROUP BY c.id_cliente, c.nome, c.email " +
            "HAVING SUM(pg.valor_pago) > ( " +
            "    SELECT AVG(valor_pago) FROM pagamento " +
            ") " +
            "ORDER BY total_pago DESC";

        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(sql);

        System.out.println("\n=== CONSULTA 2: Clientes com total pago acima da media geral (Subconsulta + Agregacao) ===");
        System.out.println("------------------------------------------------------------------------------------");

        boolean encontrou = false;
        while (result.next()) {
            encontrou = true;
            System.out.println("  Cliente: "        + result.getString("nome")
                + " | Email: "         + result.getString("email")
                + " | Total Pago: R$ " + String.format("%.2f", result.getDouble("total_pago")));
        }
        if (!encontrou) {
            System.out.println("  Nenhum cliente com total acima da media (verifique se ha pagamentos cadastrados).");
        }
        st.close();
    }
}
