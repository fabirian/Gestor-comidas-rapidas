package common.entities;

import java.io.Serializable;

/**
 *
 * @author fallen
 */
public class PedidosDTO implements Serializable {
    private int No;
    private String identificador;
    private String tipo;
    private int cantIngExtra;
    private int costoParcial;

    public PedidosDTO(int No, String identificador, String tipo, int cantIngExtra, int costoParcial) {
        this.No = No;
        this.identificador = identificador;
        this.tipo = tipo;
        this.cantIngExtra = cantIngExtra;
        this.costoParcial = costoParcial;
    }
    
    public PedidosDTO(){
        
    }

    public int getNo() {
        return No;
    }

    public void setNo(int No) {
        this.No = No;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantIngExtra() {
        return cantIngExtra;
    }

    public void setCantIngExtra(int cantIngExtra) {
        this.cantIngExtra = cantIngExtra;
    }

    public int getCostoParcial() {
        return costoParcial;
    }

    public void setCostoParcial(int costoParcial) {
        this.costoParcial = costoParcial;
    }

    
}