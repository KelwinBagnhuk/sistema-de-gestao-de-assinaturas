package bean;

/*
Bean da entidade Servico.
Representa um serviço que pode estar incluído em um ou mais planos
ex: Streaming de Vídeo
*/
public class ServicoBean {

    private int    idServico;
    private String nome;
    private String descricao;

    public ServicoBean(int idServico, String nome, String descricao) {
        this.idServico = idServico;
        this.nome      = nome;
        this.descricao = descricao;
    }

    public int    getIdServico()                { return idServico; }
    public void   setIdServico(int idServico)   { this.idServico = idServico; }

    public String getNome()            { return nome; }
    public void   setNome(String nome) { this.nome = nome; }

    public String getDescricao()                 { return descricao; }
    public void   setDescricao(String descricao) { this.descricao = descricao; }

    public String toString() {
        return "  ID: " + idServico
             + " | Nome: "      + nome
             + " | Descricao: " + descricao;
    }
}
