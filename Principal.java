import conexao.Conexao;
import controller.ClienteController;
import controller.PlanoController;
import controller.ServicoController;
import controller.PlanoServicoController;
import controller.AssinaturaController;
import controller.PagamentoController;
import controller.ConsultasController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
menu principal e os submenus de cada entidade
 
Estrutura do menu:
    1 - Clientes       -> inserir / remover / listar
    2 - Planos         -> inserir / remover / listar
    3 - Servicos       -> inserir / remover / listar
    4 - Plano-Servico  -> inserir / remover / listar
    5 - Assinaturas    -> inserir / remover / listar
    6 - Pagamentos     -> inserir / remover / listar
    7 - Consultas      -> JOIN / Subconsulta+Agregacao
    0 - Sair
 */
public class Principal {

    public static void main(String[] args) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConnection();
        System.out.println("Conexao estabelecida com sucesso!");

        int op = 0;
        do {
            op = menuPrincipal();
            try {
                switch (op) {
                    case 1: menuClientes(con);      break;
                    case 2: menuPlanos(con);         break;
                    case 3: menuServicos(con);       break;
                    case 4: menuPlanoServico(con);   break;
                    case 5: menuAssinaturas(con);    break;
                    case 6: menuPagamentos(con);     break;
                    case 7: menuConsultas(con);      break;
                    case 0: System.out.println("Encerrando o programa..."); break;
                    default: System.out.println("Opcao invalida. Tente novamente.");
                }
            } catch (SQLException ex) {
                System.out.println("Erro no banco de dados: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Erro de entrada: " + ex.getMessage()
                    + " - Verifique o formato digitado.");
            }
        } while (op != 0);

        con.close();
    }

    private static int menuPrincipal() {
        System.out.println("\n============================================");
        System.out.println("       SUBSCRIPTION MANAGER");
        System.out.println("============================================");
        System.out.println("1 - Clientes");
        System.out.println("2 - Planos");
        System.out.println("3 - Servicos");
        System.out.println("4 - Planos e Servicos (associacoes)");
        System.out.println("5 - Assinaturas");
        System.out.println("6 - Pagamentos");
        System.out.println("7 - Consultas Especiais");
        System.out.println("0 - Sair");
        System.out.print("Opcao: ");
        return lerInt();
    }


    private static void menuClientes(Connection con) throws SQLException {
        int op;
        do {
            System.out.println("\n--- CLIENTES ---");
            System.out.println("1 - Inserir novo cliente");
            System.out.println("2 - Remover cliente");
            System.out.println("3 - Listar todos os clientes");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = lerInt();
            switch (op) {
                case 1: new ClienteController().inserir(con); break;
                case 2: new ClienteController().remover(con); break;
                case 3: new ClienteController().listar(con);  break;
            }
        } while (op >= 1 && op <= 3);
    }


    private static void menuPlanos(Connection con) throws SQLException {
        int op;
        do {
            System.out.println("\n--- PLANOS ---");
            System.out.println("1 - Inserir novo plano");
            System.out.println("2 - Remover plano");
            System.out.println("3 - Listar todos os planos");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = lerInt();
            switch (op) {
                case 1: new PlanoController().inserir(con); break;
                case 2: new PlanoController().remover(con); break;
                case 3: new PlanoController().listar(con);  break;
            }
        } while (op >= 1 && op <= 3);
    }

 
    private static void menuServicos(Connection con) throws SQLException {
        int op;
        do {
            System.out.println("\n--- SERVICOS ---");
            System.out.println("1 - Inserir novo servico");
            System.out.println("2 - Remover servico");
            System.out.println("3 - Listar todos os servicos");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = lerInt();
            switch (op) {
                case 1: new ServicoController().inserir(con); break;
                case 2: new ServicoController().remover(con); break;
                case 3: new ServicoController().listar(con);  break;
            }
        } while (op >= 1 && op <= 3);
    }

    private static void menuPlanoServico(Connection con) throws SQLException {
        int op;
        do {
            System.out.println("\n--- PLANOS E SERVICOS (associacoes) ---");
            System.out.println("1 - Associar servico a um plano");
            System.out.println("2 - Remover associacao");
            System.out.println("3 - Listar todas as associacoes");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = lerInt();
            switch (op) {
                case 1: new PlanoServicoController().inserir(con); break;
                case 2: new PlanoServicoController().remover(con); break;
                case 3: new PlanoServicoController().listar(con);  break;
            }
        } while (op >= 1 && op <= 3);
    }

    private static void menuAssinaturas(Connection con) throws SQLException {
        int op;
        do {
            System.out.println("\n--- ASSINATURAS ---");
            System.out.println("1 - Inserir nova assinatura");
            System.out.println("2 - Remover assinatura");
            System.out.println("3 - Listar todas as assinaturas");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = lerInt();
            switch (op) {
                case 1: new AssinaturaController().inserir(con); break;
                case 2: new AssinaturaController().remover(con); break;
                case 3: new AssinaturaController().listar(con);  break;
            }
        } while (op >= 1 && op <= 3);
    }

    private static void menuPagamentos(Connection con) throws SQLException {
        int op;
        do {
            System.out.println("\n--- PAGAMENTOS ---");
            System.out.println("1 - Inserir novo pagamento");
            System.out.println("2 - Remover pagamento");
            System.out.println("3 - Listar todos os pagamentos");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = lerInt();
            switch (op) {
                case 1: new PagamentoController().inserir(con); break;
                case 2: new PagamentoController().remover(con); break;
                case 3: new PagamentoController().listar(con);  break;
            }
        } while (op >= 1 && op <= 3);
    }

    private static void menuConsultas(Connection con) throws SQLException {
        int op;
        do {
            System.out.println("\n--- CONSULTAS ESPECIAIS ---");
            System.out.println("1 - Assinaturas com nome do cliente e do plano  [JOIN]");
            System.out.println("2 - Clientes com total pago acima da media       [Subconsulta + Agregacao]");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = lerInt();
            switch (op) {
                case 1: new ConsultasController().listarAssinaturasDetalhadas(con); break;
                case 2: new ConsultasController().listarClientesAcimaDaMedia(con);  break;
            }
        } while (op >= 1 && op <= 2);
    }

    // auxiliar tava dano ero
    private static int lerInt() {
        try {
            Scanner sc = new Scanner(System.in);
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
