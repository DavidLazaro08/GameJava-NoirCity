package juegoprog.escenarios;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class EscenarioDistritoSombrio extends BaseEscenario {
    private BufferedImage imagenFondo;
    private int offsetX = 0;
    private int offsetY = 0;

    public EscenarioDistritoSombrio() {
        super(3192, 4096);

        // 🔹 Aseguramos que el panel tenga el tamaño correcto y sea visible
        setSize(3192, 4096);
        setPreferredSize(new Dimension(3192, 4096));
        setVisible(true);

        inicializar();
    }

    // Variables para almacenar el último offset impreso
    private int lastOffsetX = -1;
    private int lastOffsetY = -1;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagenFondo != null) {
            // 🔹 Solo imprimimos si el offset ha cambiado desde la última vez
            if (offsetX != lastOffsetX || offsetY != lastOffsetY) {
                System.out.println("🎨 Dibujando imagen del escenario... OffsetX: " + offsetX + " | OffsetY: " + offsetY);

                // 🔹 Actualizamos los valores para la próxima verificación
                lastOffsetX = offsetX;
                lastOffsetY = offsetY;
            }

            // 🔹 Dibuja la imagen del fondo en su nueva posición con el offset
            g.drawImage(imagenFondo, -offsetX, -offsetY, this.getWidth(), this.getHeight(), this);
        }
    }




    private void inicializar() {
        cargarImagen();
    }

    private void cargarImagen() {
        try {
            URL recurso = getClass().getClassLoader().getResource("escenarios/distrito_sombrio.png");
            if (recurso != null) {
                imagenFondo = ImageIO.read(recurso);
                System.out.println("✅ Imagen del escenario cargada correctamente.");
            } else {
                System.out.println("❌ Error: No se encontró la imagen del escenario.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error al cargar la imagen del escenario.");
        }
    }

    public void actualizarOffset(int x, int y) {
        this.offsetX = x;
        this.offsetY = y;
        repaint();
    }

    public int getAncho() {
        return 3192;
    }

    public int getAlto() {
        return 4096;
    }
}
