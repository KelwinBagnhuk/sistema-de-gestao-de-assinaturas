package controller;

import bean.PlanoBean;
import bean.ServicoBean;
import model.PlanoServicoModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class PlanoServicoController {

    public void inserir(Connection con) throws SQLException {
        new PlanoController().listar(con);
        new ServicoController().listar(con);

        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Associar Servico a Plano ---");
        System.out.print("ID do Plano: ");
        int idPlano = Integer.parseInt(input.nextLine().trim());
        System.out.print("ID do Servico: ");
        int idServico = Integer.parseInt(input.nextLine().trim());

        PlanoServicoModel.create(idPlano, idServico, con);
        System.out.println(">> Associacao criada com sucesso!");
    }

    public void remover(Connection con) throws SQLException {
        ArrayList<String> all = PlanoServicoModel.listAll(con);
        System.out.println("\n=== Planos e seus Servicos ===");
        if (all.isEmpty()) {
            System.out.println("  Nenhuma associacao cadastrada.");
            return;
        }
        for (String linha : all) System.out.println(linha);

        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Remover Associacao Plano-Servico ---");
        System.out.print("ID do Plano: ");
        int idPlano = Integer.parseInt(input.nextLine().trim());
        System.out.print("ID do Servico: ");
        int idServico = Integer.parseInt(input.nextLine().trim());

        PlanoServicoModel.delete(idPlano, idServico, con);
        System.out.println(">> Associacao removida com sucesso!");
    }

    public void listar(Connection con) throws SQLException {
        ArrayList<String> all = PlanoServicoModel.listAll(con);
        System.out.println("\n=== Planos e seus Servicos ===");
        if (all.isEmpty()) {
            System.out.println("  Nenhuma associacao cadastrada.");
            return;
        }
        for (String linha : all) System.out.println(linha);
    }
}
