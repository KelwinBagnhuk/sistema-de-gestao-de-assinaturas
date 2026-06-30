package bean;

/*
Bean da entidade Plano.
Representa um plano de assinatura (ex: Básico, Premium).
ciclo de cobrança: mensal, trimestral, anual, etc.

*/
public class PlanoBean {

    private int    idPlano;
    private String nome;
    private String descricao;
    private double valor;
    private String periodicidade;

    public PlanoBean(int idPlano, String nome, String descricao,
                     double valor, String periodicidade) {
        this.idPlano       = idPlano;
        this.nome          = nome;
        this.descricao     = descricao;
        this.valor         = valor;
        this.periodicidade = periodicidade;
    }

    public int    getIdPlano()              { return idPlano; }
    public void   setIdPlano(int idPlano)   { this.idPlano = idPlano; }

    public String getNome()            { return nome; }
    public void   setNome(String nome) { this.nome = nome; }

    public String getDescricao()                 { return descricao; }
    public void   setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor()             { return valor; }
    public void   setValor(double valor) { this.valor = valor; }

    public String getPeriodicidade()                     { return periodicidade; }
    public void   setPeriodicidade(String periodicidade) { this.periodicidade = periodicidade; }

    public String toString() {
        return "  ID: " + idPlano
             + " | Nome: "          + nome
             + " | Valor: R$ "      + String.format("%.2f", valor)
             + " | Periodicidade: " + periodicidade
             + " | Descricao: "     + descricao;
    }
}
