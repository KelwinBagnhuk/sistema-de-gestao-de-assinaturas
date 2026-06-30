package controller;

import bean.ServicoBean;
import model.ServicoModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ServicoController {

    public void inserir(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Inserir Servico ---");
        System.out.print("Nome do servico (ex: Streaming de Video): ");
        String nome = input.nextLine().trim();
        System.out.print("Descricao: ");
        String descricao = input.nextLine().trim();

        ServicoModel.create(new ServicoBean(0, nome, descricao), con);
        System.out.println(">> Servico inserido com sucesso!");
    }

    public void remover(Connection con) throws SQLException {
        ArrayList<ServicoBean> all = ServicoModel.listAll(con);
        System.out.println("\n=== Servicos ===");
        if (all.isEmpty()) {
            System.out.println("  Nenhum servico cadastrado.");
            return;
        }
        for (ServicoBean s : all) System.out.println(s);

        Scanner input = new Scanner(System.in);
        System.out.print("\nDigite o ID do servico a remover: ");
        int id = Integer.parseInt(input.nextLine().trim());

        System.out.println("(ATENÇÃO: os vínculos deste serviço com os planos também serão removidos em cascata). Continuar?\n(1) SIM\n(2) NÃO");
        int ans = Integer.parseInt(input.nextLine().trim());
        if(ans != 1){
            System.out.println(">> Retornando...");
            return;
        }
       
        ServicoModel.delete(id, con);
        System.out.println(">> Servico removido com sucesso!");
    }

    public void listar(Connection con) throws SQLException {
        ArrayList<ServicoBean> all = ServicoModel.listAll(con);
        System.out.println("\n=== Servicos ===");
        if (all.isEmpty()) {
            System.out.println("  Nenhum servico cadastrado.");
            return;
        }
        for (ServicoBean s : all) System.out.println(s);
    }
}
