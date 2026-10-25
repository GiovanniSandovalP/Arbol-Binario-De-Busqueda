package back_end;

public class ArbolBST {

    private NodoBST raiz;

    public ArbolBST() {
        this.raiz = null;
    }

    public void insertar(Empleado empleado) {
        if (empleado == null) {
            return;
        }
        raiz = insertarNodo(raiz, empleado);
    }

    private NodoBST insertarNodo(NodoBST nodo, Empleado empleado) {
        if (nodo == null) {
            return new NodoBST(empleado);
        }

        int idActual = nodo.getDato().id();
        int idNuevo = empleado.id();

        if (idNuevo < idActual) {
            nodo.setIzquierdo(insertarNodo(nodo.getIzquierdo(), empleado));
        } else if (idNuevo > idActual) {
            nodo.setDerecho(insertarNodo(nodo.getDerecho(), empleado));
        }

        return nodo;
    }

    public Empleado buscar(Integer id) {
        if (id == null || raiz == null) {
            return null;
        }
        NodoBST encontrado = buscarNodo(raiz, id.intValue());
        if (encontrado == null) {
            return null;
        }
        return encontrado.getDato();
    }

    private NodoBST buscarNodo(NodoBST nodo, int id) {
        if (nodo == null) {
            return null;
        }

        int idActual = nodo.getDato().id();

        if (id == idActual) {
            return nodo;
        } else if (id < idActual) {
            return buscarNodo(nodo.getIzquierdo(), id);
        } else {
            return buscarNodo(nodo.getDerecho(), id);
        }
    }

    public void eliminar(Integer id) {
        if (id == null || raiz == null) {
            return;
        }
        raiz = eliminarNodo(raiz, id.intValue());
    }

    private NodoBST eliminarNodo(NodoBST nodo, int id) {
        if (nodo == null) {
            return null;
        }

        int idActual = nodo.getDato().id();

        if (id < idActual) {
            nodo.setIzquierdo(eliminarNodo(nodo.getIzquierdo(), id));
            return nodo;
        } else if (id > idActual) {
            nodo.setDerecho(eliminarNodo(nodo.getDerecho(), id));
            return nodo;
        }

        if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
            return null;
        }

        if (nodo.getIzquierdo() == null) {
            return nodo.getDerecho();
        }

        if (nodo.getDerecho() == null) {
            return nodo.getIzquierdo();
        }

        NodoBST sucesor = obtenerMinimo(nodo.getDerecho());
        nodo.setDato(sucesor.getDato());
        nodo.setDerecho(eliminarNodo(nodo.getDerecho(), sucesor.getDato().id()));

        return nodo;
    }

    private NodoBST obtenerMinimo(NodoBST nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }

    public String mostrarInOrden() {
        StringBuilder texto = new StringBuilder();
        recorrerInOrden(raiz, texto);
        return texto.toString();
    }

    private void recorrerInOrden(NodoBST nodo, StringBuilder texto) {
        if (nodo != null) {
            recorrerInOrden(nodo.getIzquierdo(), texto);
            texto.append(nodo.getDato().toString()).append("\n");
            recorrerInOrden(nodo.getDerecho(), texto);
        }
    }

    public String mostrarPreOrden() {
        StringBuilder texto = new StringBuilder();
        recorrerPreOrden(raiz, texto);
        return texto.toString();
    }

    private void recorrerPreOrden(NodoBST nodo, StringBuilder texto) {
        if (nodo != null) {
            texto.append(nodo.getDato().toString()).append("\n");
            recorrerPreOrden(nodo.getIzquierdo(), texto);
            recorrerPreOrden(nodo.getDerecho(), texto);
        }
    }

    public String mostrarPostOrden() {
        StringBuilder texto = new StringBuilder();
        recorrerPostOrden(raiz, texto);
        return texto.toString();
    }

    private void recorrerPostOrden(NodoBST nodo, StringBuilder texto) {
        if (nodo != null) {
            recorrerPostOrden(nodo.getIzquierdo(), texto);
            recorrerPostOrden(nodo.getDerecho(), texto);
            texto.append(nodo.getDato().toString()).append("\n");
        }
    }

    public int profundidad() {
        return calcularProfundidad(raiz);
    }

    private int calcularProfundidad(NodoBST nodo) {
        if (nodo == null) {
            return 0;
        }

        int profundidadIzquierda = calcularProfundidad(nodo.getIzquierdo());
        int profundidadDerecha = calcularProfundidad(nodo.getDerecho());

        if (profundidadIzquierda > profundidadDerecha) {
            return profundidadIzquierda + 1;
        } else {
            return profundidadDerecha + 1;
        }
    }

    public int amplitud() {
        if (raiz == null) {
            return 0;
        }

        int maxAmplitud = 0;
        NodoBST[] cola = new NodoBST[1000];
        int frente = 0;
        int fin = 0;

        cola[fin++] = raiz;

        while (frente < fin) {
            int nodosPorNivel = fin - frente;
            if (nodosPorNivel > maxAmplitud) {
                maxAmplitud = nodosPorNivel;
            }

            for (int i = 0; i < nodosPorNivel; i++) {
                NodoBST actual = cola[frente++];
                if (actual.getIzquierdo() != null) {
                    cola[fin++] = actual.getIzquierdo();
                }
                if (actual.getDerecho() != null) {
                    cola[fin++] = actual.getDerecho();
                }
            }
        }

        return maxAmplitud;
    }

    public void balancear() {
        if (raiz == null) {
            return;
        }

        int tamano = contarNodos(raiz);
        if (tamano <= 1) {
            return;
        }

        Empleado[] arreglo = new Empleado[tamano];
        final int[] indice = new int[]{0};
        llenarArregloInOrden(raiz, arreglo, indice);

        raiz = null;
        raiz = construirBalanceado(arreglo, 0, tamano - 1);
    }

    private int contarNodos(NodoBST nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.getIzquierdo()) + contarNodos(nodo.getDerecho());
    }

    private void llenarArregloInOrden(NodoBST nodo, Empleado[] arreglo, final int[] indice) {
        if (nodo != null) {
            llenarArregloInOrden(nodo.getIzquierdo(), arreglo, indice);
            arreglo[indice[0]] = nodo.getDato();
            indice[0]++;
            llenarArregloInOrden(nodo.getDerecho(), arreglo, indice);
        }
    }

    private NodoBST construirBalanceado(Empleado[] arreglo, int inicio, int fin) {
        if (inicio > fin) {
            return null;
        }

        int medio = (inicio + fin) / 2;
        NodoBST nuevo = new NodoBST(arreglo[medio]);
        nuevo.setIzquierdo(construirBalanceado(arreglo, inicio, medio - 1));
        nuevo.setDerecho(construirBalanceado(arreglo, medio + 1, fin));
        return nuevo;
    }

    public void destruirArbol() {
        raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public String mostrarEstructura() {
        StringBuilder sb = new StringBuilder();
        if (raiz == null) {
            return "Árbol vacío.";
        }
        mostrarEstructuraRec(raiz, "", true, sb);
        String resultado = sb.toString();
        return resultado.isEmpty() ? "Árbol vacío." : resultado;
    }

    private void mostrarEstructuraRec(NodoBST nodo, String prefijo, boolean esUltimo, StringBuilder sb) {
        if (nodo == null) {
            return;
        }
        sb.append(prefijo);
        if (esUltimo) {
            sb.append("└── ");
        } else {
            sb.append("├── ");
        }
        sb.append(nodo.getDato().id()).append(" - ").append(nodo.getDato().nombre()).append("\n");
        mostrarEstructuraRec(nodo.getIzquierdo(), prefijo + (esUltimo ? "    " : "│   "), false, sb);
        mostrarEstructuraRec(nodo.getDerecho(), prefijo + (esUltimo ? "    " : "│   "), true, sb);
    }
}
