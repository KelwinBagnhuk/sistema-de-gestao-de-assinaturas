package bean;

import java.sql.Date;

/*
Bean da entidade Pagamento.
exemplo de status: 'pago', 'pendente', 'atrasado'
e metodo possíveis: 'cartao_credito', 'boleto', 'pix', 'debito_automatico'
 */
public class PagamentoBean {

    private int    idPagamento;
    private int    idAssinatura;
    private Date   dataPagamento;
    private Date   dataVencimento;
    private Date   mesReferencia;
    private String metodo;
    private double valorPago;
    private String status;

    public PagamentoBean(int idPagamento, int idAssinatura, Date dataPagamento,
                         Date dataVencimento, Date mesReferencia, String metodo,
                         double valorPago, String status) {
        this.idPagamento    = idPagamento;
        this.idAssinatura   = idAssinatura;
        this.dataPagamento  = dataPagamento;
        this.dataVencimento = dataVencimento;
        this.mesReferencia  = mesReferencia;
        this.metodo         = metodo;
        this.valorPago      = valorPago;
        this.status         = status;
    }

    public int    getIdPagamento()                { return idPagamento; }
    public void   setIdPagamento(int v)           { this.idPagamento = v; }

    public int    getIdAssinatura()               { return idAssinatura; }
    public void   setIdAssinatura(int v)          { this.idAssinatura = v; }

    public Date   getDataPagamento()              { return dataPagamento; }
    public void   setDataPagamento(Date v)        { this.dataPagamento = v; }

    public Date   getDataVencimento()             { return dataVencimento; }
    public void   setDataVencimento(Date v)       { this.dataVencimento = v; }

    public Date   getMesReferencia()              { return mesReferencia; }
    public void   setMesReferencia(Date v)        { this.mesReferencia = v; }

    public String getMetodo()                     { return metodo; }
    public void   setMetodo(String v)             { this.metodo = v; }

    public double getValorPago()                  { return valorPago; }
    public void   setValorPago(double v)          { this.valorPago = v; }

    public String getStatus()                     { return status; }
    public void   setStatus(String v)             { this.status = v; }

    public String toString() {
        String pago = (dataPagamento != null) ? dataPagamento.toString() : "Nao pago";
        return "  ID: " + idPagamento
             + " | Assinatura ID: "  + idAssinatura
             + " | Vencimento: "     + dataVencimento
             + " | Pagamento: "      + pago
             + " | Mes Ref: "        + mesReferencia
             + " | Metodo: "         + metodo
             + " | Valor: R$ "       + String.format("%.2f", valorPago)
             + " | Status: "         + status;
    }
}
