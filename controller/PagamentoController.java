package controller;

import bean.PagamentoBean;
import model.PagamentoModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class PagamentoController {

    public void inserir(Connection con) throws SQLException {
        new AssinaturaController().listar(con);

        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Inserir Pagamento ---");

        System.out.print("ID da Assinatura: ");
        int idAssinatura = Integer.parseInt(input.nextLine().trim());

        System.out.print("Data de vencimento (AAAA-MM-DD): ");
        Date dataVencimento = Date.valueOf(input.nextLine().trim());

        System.out.print("Mes de referencia - primeiro dia do mes (AAAA-MM-01): ");
        Date mesReferencia = Date.valueOf(input.nextLine().trim());

        System.out.print("Metodo (cartao_credito / boleto / pix / debito_automatico): ");
        String metodo = input.nextLine().trim();

        System.out.print("Valor pago (ex: 29.90): ");
        double valorPago = Double.parseDouble(input.nextLine().trim().replace(",", "."));

        System.out.print("Status (pago / pendente / atrasado): ");
        String status = input.nextLine().trim();

        System.out.print("Data do pagamento (AAAA-MM-DD) ou Enter se ainda nao pago: ");
        String pagoStr = input.nextLine().trim();
        Date dataPagamento = pagoStr.isEmpty() ? null : Date.valueOf(pagoStr);

        PagamentoModel.create(
            new PagamentoBean(0, idAssinatura, dataPagamento, dataVencimento,
                              mesReferencia, metodo, valorPago, status), con
        );
        System.out.println(">> Pagamento inserido com sucesso!");
    }

    public void remover(Connection con) throws SQLException {
        ArrayList<PagamentoBean> all = PagamentoModel.listAll(con);
        System.out.println("\n=== Pagamentos ===");
        if (all.isEmpty()) {
            System.out.println("  Nenhum pagamento cadastrado.");
            return;
        }
        for (PagamentoBean p : all) System.out.println(p);

        Scanner input = new Scanner(System.in);
        System.out.print("\nDigite o ID do pagamento a remover: ");
        int id = Integer.parseInt(input.nextLine().trim());

        PagamentoModel.delete(id, con);
        System.out.println(">> Pagamento removido com sucesso!");
    }

    public void listar(Connection con) throws SQLException {
        ArrayList<PagamentoBean> all = PagamentoModel.listAll(con);
        System.out.println("\n=== Pagamentos ===");
        if (all.isEmpty()) {
            System.out.println("  Nenhum pagamento cadastrado.");
            return;
        }
        for (PagamentoBean p : all) System.out.println(p);
    }
}
