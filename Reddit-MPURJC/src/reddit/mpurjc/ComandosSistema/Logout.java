package reddit.mpurjc.ComandosSistema;

import reddit.mpurjc.Foro;

public class Logout extends ComandosSistema {

    private Foro foro;
    private String parametros;

    public Logout(Foro foro) {
        this.foro = foro;
    }

    /**
     * Este método nos servirá simplemente para cerrar la sesión del usuario
     *
     * @param s
     * @return true si el cierre de sesión ha sido correcta
     */
    @Override
    public boolean ejecutar(String s) {
        if (comprobar(s)) {
            setForo(foro);
            actualizarNotificaciones();
            System.out.println("LogOut correcto.");
            foro.setEntradaActual(null);
            foro.setSubForoActual(null);
            foro.setUsuarioActual(null);
            return true;
        } else {
            return false;
        }
    }

    //Comando para la clase Logout en el Foro
    @Override
    public boolean comprobar(String s) {
        setForo(this.foro);

        int ini = s.indexOf("(");
        int fin = s.lastIndexOf(")");
        String comando = s.substring(0, ini).toLowerCase();
        if (comando.equals("logout")) {
            this.parametros = s.substring(ini + 1, fin);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void setForo(Foro foro) {
        this.foro = foro;
    }

    private void actualizarNotificaciones() {
        if ((foro.getUsuarioActual() != null) && (foro.getUsuarioActual().getEntradasVistas() != null)) {
            foro.getUsuarioActual().getEntradasVistas().entrySet().forEach((entry) -> {
                foro.getUsuarioActual().setEntradasVistas(foro.getSubForo(entry.getKey()));
            });
        }
    }

}
