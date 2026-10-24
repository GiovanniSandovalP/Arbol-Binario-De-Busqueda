package back_end;

public class NodoBST {
    private Empleado dato;
    private NodoBST izquierdo;
    private NodoBST derecho;

    public NodoBST(Empleado dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Empleado getDato() {
        return dato;
    }

    public void setDato(Empleado dato) {
        this.dato = dato;
    }

    public NodoBST getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoBST izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoBST getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoBST derecho) {
        this.derecho = derecho;
    }
}
