package back_end;


public class NodoBST {
    private Empleado dato;
    private NodoBST izquierdo;
    private NodoBST derecho;

public NodoBST(Empleado dato){
    this.dato = dato;
    this.izquierdo = null;
    this.derecho = null;
}    

    public Empleado dato(){
        return dato;
    }

    public NodoBST setEmpleado(){
        return setEmpleado();
    }

    public void setEmpleado(Empleado dato){
        this.dato = dato;
    }

    public NodoBST getEmpleado(){
        return getEmpleado();
    }

    public void getEmpleado(Empleado dato){
        this.dato = dato;
    }

    public NodoBST getIzquierdo(){
        return izquierdo;
    }

    public void setIzquierdo(NodoBST izquierdo){
        this.izquierdo = izquierdo;
    }

    public NodoBST getDerecho(){
        return derecho;
    }

    public void setDerecho(NodoBST derecho){
        this.derecho = derecho;
    }


}
