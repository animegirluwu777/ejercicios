package org.example;

import java.util.Scanner;

public class TresRaya {

    private static char[][] tablero;
    private static Scanner in=new Scanner(System.in);

    private static char jugadorActual='X';

    public static void main(String[] args) {
        initMetodos();
    }

    private static void pedirDimension(Scanner in){
        System.out.println("Hola buenas de que dimension quieres el tablero, 3d,4d,5d....");
        int dimension= in.nextInt();
        tablero=new char[dimension][dimension];

        if (!comprobarDimensiones(tablero)){
            throw new IllegalArgumentException("Introduce una dimension mayor que 2 ok");
        }
    }

    private static boolean comprobarDimensiones(char[][] t){
        return t.length == 3;
    }
    private static void cambiarTurno() {
        jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
    }

    private static void jugar(){
        boolean ganador=false;

        while (!ganador){
            imprimirTablero();
            System.out.println("Turno del jugador " + jugadorActual);
            pedirPosiciones(in, tablero);
            ganador = comprobarGanador(tablero);
            if (ganador){
                System.out.println("¡Felicidades! El jugador " + jugadorActual + " ha ganado.");
            }
            cambiarTurno();
        }

    }

    private static void pedirPosiciones(Scanner in, char[][] t){

        int dimension = t.length;
        int fila, columna;

        do {
            System.out.println("Ingresa la fila y la columna (separadas por espacio):");
            fila = in.nextInt();
            columna = in.nextInt();

            if (fila < 0 || fila >= dimension || columna < 0 || columna >= dimension) {
                System.out.println("Posición fuera del rango. Inténtalo de nuevo.");
            } else if (t[fila][columna] != ' ') {
                System.out.println("La casilla está ocupada. Inténtalo de nuevo.");
            }
        } while (fila < 0 || fila >= dimension || columna < 0 || columna >= dimension || t[fila][columna] != ' ');

        t[fila][columna] = jugadorActual;
    }

    public static void generarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    public static void imprimirTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean comprobarGanador(char[][] t){
        for (int i = 0; i < t.length; i++) {
            if (t[i][0] != ' ' && t[i][0] == t[i][1] && t[i][1] == t[i][2]) {
                return true;
            }
        }


        for (int j = 0; j < t.length; j++) {
            if (t[0][j] != ' ' && t[0][j] == t[1][j] && t[1][j] == t[2][j]) {
                return true;
            }
        }


        if (t[0][0] != ' ' && t[0][0] == t[1][1] && t[1][1] == t[2][2]) {
            return true;
        }


        if (t[0][2] != ' ' && t[0][2] == t[1][1] && t[1][1] == t[2][0]) {
            return true;
        }
        return false;
    }

    public char[][] getTablero() {
        return tablero;
    }

    public void setTablero(char[][] tablero) {
        TresRaya.tablero = tablero;
    }
    private static void initMetodos(){
        pedirDimension(in);
        generarTablero();
        jugar();
    }
}
