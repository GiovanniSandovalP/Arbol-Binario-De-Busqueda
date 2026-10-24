package back_end;

public record Empleado(
    int id,
    String nombre, 
    String puesto,
    String departamento,
    Double salario) {

    @Override 
    public String  toString(){
        return id + " - " + nombre + "" + puesto + "" + departamento;
        
    }
    }
s