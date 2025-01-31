package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeerMatriz {
    private int[][] matriz;
    private int filas;
    private int columnas;

    public void lectura() {
        try {
            Scanner sc = new Scanner(new File("src/matrix.txt"));

            List<String[]> lines = new ArrayList<>();
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine().split(","));
            }

            filas = lines.size();
            columnas = lines.get(0).length;
            matriz = new int[filas][columnas];

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    matriz[i][j] = Integer.parseInt(lines.get(i)[j]);
                }
            }

            sc.close();

        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no existe");
        }
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
