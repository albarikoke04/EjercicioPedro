package Controller;

import java.util.Random;
import javax.swing.JButton;

public class Logica3EnRaya {

    private static final char[][] tablero = new char[3][3];
    private static char jugadorActual = 'X';

    public static void inicializarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    public static void hacerMovimientoCPU(JButton[][] botones) {
        Random r = new Random();
        boolean result = false;
        while (!result) {
            int row = r.nextInt(3);
            int column = r.nextInt(3);
            if (tablero[column][row] == ' ') {
                tablero[column][row] = 'O';
                botones[column][row].setText(String.valueOf(Logica3EnRaya.getJugadorActual()));
                result = true;
            }
        }
    }

    public static char getJugadorActual() {
        return jugadorActual;
    }

    public static boolean movimientoValido(int fila, int columna) {
        return fila >= 0 && fila < 3 && columna >= 0 && columna < 3 && tablero[fila][columna] == ' ';
    }

    public static void hacerMovimiento(int fila, int columna) {
        if (movimientoValido(fila, columna)) {
            tablero[fila][columna] = jugadorActual;
        }
    }

    public static boolean hayGanador() {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] != ' ' && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                return true;
            }
            if (tablero[0][i] != ' ' && tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i]) {
                return true;
            }
        }
        if (tablero[0][0] != ' ' && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            return true;
        }
        if (tablero[0][2] != ' ' && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            return true;
        }
        return false;
    }

    public static boolean esEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void cambiarTurno() {
        jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
    }
}
