package bean;

/*
Bean da entidade Cliente
*/
public class ClienteBean {

    private int    idCliente;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    public ClienteBean(int idCliente, String nome, String email, String cpf, String telefone) {
        this.idCliente = idCliente;
        this.nome      = nome;
        this.email     = email;
        this.cpf       = cpf;
        this.telefone  = telefone;
    }

    public int    getIdCliente()              { return idCliente; }
    public void   setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNome()            { return nome; }
    public void   setNome(String nome) { this.nome = nome; }

    public String getEmail()             { return email; }
    public void   setEmail(String email) { this.email = email; }

    public String getCpf()           { return cpf; }
    public void   setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone()                { return telefone; }
    public void   setTelefone(String telefone) { this.telefone = telefone; }

    public String toString() {
        return "  ID: " + idCliente
             + " | Nome: "     + nome
             + " | Email: "    + email
             + " | CPF: "      + cpf
             + " | Telefone: " + telefone;
    }
}
