package controller;

import bean.ClienteBean;
import model.ClienteModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClienteController {

    public void inserir(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Inserir Cliente ---");
        System.out.print("Nome: ");
        String nome = input.nextLine().trim();
        System.out.print("Email: ");
        String email = input.nextLine().trim();
        System.out.print("CPF (somente numeros, ex: 12345678901): ");
        String cpf = input.nextLine().trim();
        System.out.print("Telefone (ex: 47999990000): ");
        String telefone = input.nextLine().trim();

        ClienteModel.create(new ClienteBean(0, nome, email, cpf, telefone), con);
        System.out.println(">> Cliente inserido com sucesso!");
    }

    public void remover(Connection con) throws SQLException {
        ArrayList<ClienteBean> all = ClienteModel.listAll(con);
        System.out.println("\n=== Clientes ===");
        if (all.isEmpty()) {
            System.out.println("  Nenhum cliente cadastrado.");
            return;
        }
        for (ClienteBean c : all) System.out.println(c);

        Scanner input = new Scanner(System.in);
        System.out.print("\nDigite o ID do cliente a remover: ");
        int id = Integer.parseInt(input.nextLine().trim());

        System.out.println("   (ATENCAO: assinaturas deste cliente tambem serao removidas em cascata). Continuar?\n(1) SIM\n(2) NÃO)");
        int ans = Integer.parseInt(input.nextLine().trim());
        if(ans != 1){
            System.out.println(">> Retornando...");
            return;
        }
        ClienteModel.delete(id, con);
        System.out.println(">> Cliente removido com sucesso!");
        
    }

    public void listar(Connection con) throws SQLException {
        ArrayList<ClienteBean> all = ClienteModel.listAll(con);
        System.out.println("\n=== Clientes ===");
        if (all.isEmpty()) {
            System.out.println("  Nenhum cliente cadastrado.");
            return;
        }
        for (ClienteBean c : all) System.out.println(c);
    }
}
