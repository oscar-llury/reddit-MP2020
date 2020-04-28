package reddit.mpurjc.ComandosSistema;

import reddit.mpurjc.Comentario;
import reddit.mpurjc.Entradas.Entrada;
import reddit.mpurjc.Foro;
import reddit.mpurjc.Usuario;

public class ComentarComentario extends ComandosSistema {

    private Foro foro;
    private Usuario usuarioActual;
    private Comentario comentarioActual;
    private Entrada entradaActual;
    private String parametros;

    public ComentarComentario(Foro foro) {
        this.foro = foro;
        this.usuarioActual = foro.getUsuarioActual();
        this.entradaActual = foro.getEntradaActual();
        //this.comentarioActual = foro.getComentarioActual();
    }
    
    /**
     * Esre método se utilzará para comentar los comentarios ya expuestos con
     * anterioridad 
     * @param s
     * @return boolean true en el caso de que se haya podido comentar con éxito y que dicho 
     * comentario sea válido, en caso contrario, no podrá ser aceptado 
     */
    @Override
    public boolean ejecutar(String s) {
        if(comprobar(s)){
            // Se podrá comentar dicho comentario siempre y cuando la entrada esté verificada 
            // y el comentario al que se hace referencia está validado
            if(this.entradaActual.isVerificado() && this.comentarioActual.isValidado()){
                Comentario nuevoComentario = new Comentario(usuarioActual,this.parametros);
                nuevoComentario.validar();
                if(nuevoComentario.isValidado()){
                    this.comentarioActual.addComentario(nuevoComentario);
                    return true;
                }else{
                    System.out.println("El comentario no es aceptado");
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
            if(comando.equals("comentarcomentario")){
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
        this.usuarioActual = foro.getUsuarioActual();
        this.entradaActual = foro.getEntradaActual();
        //this.comentarioActual = foro.getComentarioActual();
    }
    
}
