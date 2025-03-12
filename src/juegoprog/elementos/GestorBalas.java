package juegoprog.elementos;

import juegoprog.escenarios.ColisionesPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase auxiliar para gestionar todas las balas activas en el juego.
 * Esta clase centraliza la creación, actualización y dibujo de las balas.
 */
public class GestorBalas {

    /** Lista de todas las balas activas en el juego. */
    private final List<Bala> balas = new ArrayList<>();

    /**
     * Crea y añade una nueva bala al gestor.
     *
     * @param startX     Posición inicial en X de la bala.
     * @param startY     Posición inicial en Y de la bala.
     * @param objetivoX  Coordenada X hacia donde apunta la bala.
     * @param objetivoY  Coordenada Y hacia donde apunta la bala.
     */
    public void disparar(double startX, double startY, double objetivoX, double objetivoY) {
        synchronized (balas) {
            balas.add(new Bala(startX, startY, objetivoX, objetivoY));
        }
    }

    /**
     * Actualiza la posición de todas las balas activas en el juego.
     * Elimina las balas que ya no están activas (colisiones o fuera del mapa).
     */
    public void actualizar(ColisionesPanel colisiones, int desplazamientoX, int desplazamientoY) {
        synchronized (balas) {
            Iterator<Bala> iterador = balas.iterator();
            while (iterador.hasNext()) {
                Bala bala = iterador.next();
                bala.actualizar(colisiones, desplazamientoX, desplazamientoY);

                if (!bala.isActiva()) {
                    iterador.remove();
                }
            }
        }
    }

    /**
     * Dibuja todas las balas activas en la pantalla.
     *
     * @param g Contexto gráfico del juego.
     */
    public void dibujar(Graphics g, int desplazamientoX, int desplazamientoY) {
        synchronized (balas) {
            for (Bala bala : balas) {
                bala.dibujar(g, desplazamientoX, desplazamientoY);
            }
        }
    }

    /**
     * Retorna una referencia a la lista de balas activas.
     *
     * @return Lista de balas activas.
     */
    public List<Bala> getBalas() {
        return balas;
    }
}