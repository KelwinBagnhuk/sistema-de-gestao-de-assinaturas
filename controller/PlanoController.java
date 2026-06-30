package controller;

import bean.PlanoBean;
import model.PlanoModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class PlanoController {

    public void inserir(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Inserir Plano ---");
        System.out.print("Nome do plano (ex: Basico, Premium): ");
        String nome = input.nextLine().trim();
        System.out.print("Descricao: ");
        String descricao = input.nextLine().trim();
        System.out.print("Valor (ex: 29.90): ");
        double valor = Double.parseDouble(input.nextLine().trim().replace(",", "."));
        System.out.print("Periodicidade (mensal / trimestral / semestral / anual): ");
        String periodicidade = input.nextLine().trim();

        PlanoModel.create(new PlanoBean(0, nome, descricao, valor, periodicidade), con);
        System.out.println(">> Plano inserido com sucesso!");
    }

    public void remover(Connection con) throws SQLException {
        ArrayList<PlanoBean> all = PlanoModel.listAll(con);
        System.out.println("\n=== Planos ===");
        if (all.isEmpty()) {
            System.out.println("  Nenhum plano cadastrado.");
            return;
        }
        for (PlanoBean p : all) System.out.println(p);

        Scanner input = new Scanner(System.in);
        System.out.print("\nDigite o ID do plano a remover: ");
        int id = Integer.parseInt(input.nextLine().trim());
        
        System.out.println("(ATENÇÃO: assinaturas e vínculos com serviços deste plano também serão removidos em cascata). Continuar?\n(1) SIM\n(2) NÃO");
        int ans = Integer.parseInt(input.nextLine().trim());
        if(ans != 1){
            System.out.println(">> Retornando...");
            return;
        }
        PlanoModel.delete(id, con);
        System.out.println(">> Plano removido com sucesso!");
    }

    public void listar(Connection con) throws SQLException {
        ArrayList<PlanoBean> all = PlanoModel.listAll(con);
        System.out.println("\n=== Planos ===");
        if (all.isEmpty()) {
            System.out.println("  Nenhum plano cadastrado.");
            return;
        }
        for (PlanoBean p : all) System.out.println(p);
    }
}
