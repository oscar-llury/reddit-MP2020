package reddit.mpurjc.ComandosSistema;

import reddit.mpurjc.Comentario;
import reddit.mpurjc.Entradas.Entrada;
import reddit.mpurjc.Foro;
import reddit.mpurjc.Usuario;

public class ComentarEntrada extends ComandosSistema {

    private Foro foro;
    private Usuario usuarioActual;
    private Entrada entradaActual;
    private String parametros;

    public ComentarEntrada(Foro foro) {
        this.foro = foro;
        this.usuarioActual = foro.getUsuarioActual();
        this.entradaActual = foro.getEntradaActual();
    }
    
    /**
     * Este método se utilizará para comentar las entradas propuestas
     * @param s
     * @return boolean true en caso de que se haya comentado con éxito la entrada o 
     * false en otros casos 
     */
    @Override
    public boolean ejecutar(String s) {
        if(comprobar(s)){
            if(this.entradaActual.isVerificado()){
                Comentario nuevoComentario = new Comentario(usuarioActual,this.parametros);
                nuevoComentario.validar();
                if(nuevoComentario.isValidado()){
                    this.entradaActual.addComentario(nuevoComentario);
                    return true;
                }else{
                    System.out.println("El comentario no es aceptado.");
                    //add penalizacion
                    return false;
                }
            }else{
                System.out.println("No se ha podido comentar la entrada.");
                return false;
            }
        }else{
            System.out.println("Es necesario tener iniciada sesión.");
            return false;
        }
    }

    @Override
    public boolean comprobar(String s) {
        setForo(this.foro);
        if(this.usuarioActual == null){
            return false;
        } else {
            int ini = s.indexOf("(");
            int fin = s.lastIndexOf(")");
            String comando = s.substring(0,ini).toLowerCase();
            if(comando.equals("comentarentrada")){
                this.parametros = s.substring(ini+1,fin);
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public void setForo(Foro foro) {
        this.foro = foro;
        this.usuarioActual = this.foro.getUsuarioActual();
        this.entradaActual = this.foro.getEntradaActual();
    }
    
}
