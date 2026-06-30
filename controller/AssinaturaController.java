package controller;

import bean.AssinaturaBean;
import model.AssinaturaModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/*
Controller da entidade Assinatura.
*/
public class AssinaturaController {

    public void inserir(Connection con) throws SQLException {
        new ClienteController().listar(con);
        new PlanoController().listar(con);

        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Inserir Assinatura ---");
        System.out.print("ID do Cliente: ");
        int idCliente = Integer.parseInt(input.nextLine().trim());
        System.out.print("ID do Plano: ");
        int idPlano = Integer.parseInt(input.nextLine().trim());
        System.out.print("Data de inicio (AAAA-MM-DD): ");
        Date dataInicio = Date.valueOf(input.nextLine().trim());
        System.out.print("Data de fim (AAAA-MM-DD) ou Enter para sem data: ");
        String fimStr = input.nextLine().trim();
        Date dataFim = fimStr.isEmpty() ? null : Date.valueOf(fimStr);
        System.out.print("Status (ativa / cancelada / suspensa): ");
        String status = input.nextLine().trim();
        
        if (dataFim != null && dataInicio.after(dataFim)) {
            System.out.println(">> ERRO: A data de inicio não pode ser posterior à data de fim!");
            return; // volta pro menu
        }

        AssinaturaModel.create(
            new AssinaturaBean(0, idCliente, idPlano, dataInicio, dataFim, status), con
        );
        System.out.println(">> Assinatura inserida com sucesso!");
    }

    public void remover(Connection con) throws SQLException {
        ArrayList<AssinaturaBean> all = AssinaturaModel.listAll(con);
        System.out.println("\n=== Assinaturas ===");
        if (all.isEmpty()) {
            System.out.println("  Nenhuma assinatura cadastrada.");
            return;
        }
        for (AssinaturaBean a : all) System.out.println(a);

        Scanner input = new Scanner(System.in);
        System.out.print("\nDigite o ID da assinatura a remover: ");
        int id = Integer.parseInt(input.nextLine().trim());
        
        System.out.println("(ATENÇÃO: os pagamentos vinculados a esta assinatura também serão removidos em cascata). Continuar?\n(1) SIM\n(2) NÃO");
        int ans = Integer.parseInt(input.nextLine().trim());
        if(ans != 1){
            System.out.println(">> Retornando...");
            return;
        }
        
        AssinaturaModel.delete(id, con);
        System.out.println(">> Assinatura removida com sucesso!");
    }

    public void listar(Connection con) throws SQLException {
        ArrayList<AssinaturaBean> all = AssinaturaModel.listAll(con);
        System.out.println("\n=== Assinaturas ===");
        if (all.isEmpty()) {
            System.out.println("  Nenhuma assinatura cadastrada.");
            return;
        }
        for (AssinaturaBean a : all) System.out.println(a);
    }
}
