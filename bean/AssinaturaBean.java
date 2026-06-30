package bean;

import java.sql.Date;

/*
Bean da entidade Assinatura.
Representa o vínculo entre um cliente e um plano.
 
*/

public class AssinaturaBean {

    private int    idAssinatura;
    private int    idCliente;
    private int    idPlano;
    private Date   dataInicio;
    private Date   dataFim;     // pode ser null (assinatura sem data de encerramento)
    private String status;

    public AssinaturaBean(int idAssinatura, int idCliente, int idPlano,
                          Date dataInicio, Date dataFim, String status) {
        this.idAssinatura = idAssinatura;
        this.idCliente    = idCliente;
        this.idPlano      = idPlano;
        this.dataInicio   = dataInicio;
        this.dataFim      = dataFim;
        this.status       = status;
    }

    public int    getIdAssinatura()                   { return idAssinatura; }
    public void   setIdAssinatura(int idAssinatura)   { this.idAssinatura = idAssinatura; }

    public int    getIdCliente()                { return idCliente; }
    public void   setIdCliente(int idCliente)   { this.idCliente = idCliente; }

    public int    getIdPlano()              { return idPlano; }
    public void   setIdPlano(int idPlano)   { this.idPlano = idPlano; }

    public Date   getDataInicio()              { return dataInicio; }
    public void   setDataInicio(Date dataInicio) { this.dataInicio = dataInicio; }

    public Date   getDataFim()             { return dataFim; }
    public void   setDataFim(Date dataFim) { this.dataFim = dataFim; }

    public String getStatus()              { return status; }
    public void   setStatus(String status) { this.status = status; }

    public String toString() {
        String fim = (dataFim != null) ? dataFim.toString() : "Sem data de fim";
        return "  ID: " + idAssinatura
             + " | Cliente ID: " + idCliente
             + " | Plano ID: "   + idPlano
             + " | Inicio: "     + dataInicio
             + " | Fim: "        + fim
             + " | Status: "     + status;
    }
}
