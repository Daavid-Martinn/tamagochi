package org.cuatrovientos.dam.psp.entregaTamagochi;

/**
 * Hello world!
 *
 */
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        cuidador cuidador = new cuidador(3); // crea 3 Tamagotchis
        cuidador.iniciar();                  // los lanza al mundo

        Scanner sc = new Scanner(System.in);
        String opcion;

        do {
            System.out.println("\n--- MENÚ DEL CUIDADOR ---");
            System.out.println("1. Alimentar Tamagotchi");
            System.out.println("2. Limpiar Tamagotchi");
            System.out.println("3. Jugar con Tamagotchi");
            System.out.println("4. Matar Tamagotchi");
            System.out.println("5. Mostrar estados");
            System.out.println("0. Salir");
            System.out.print("Elige opción: ");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Número de Tamagotchi (1-" + cuidador.getCantidad() + "): ");
                    int n1 = Integer.parseInt(sc.nextLine()) - 1;
                    cuidador.alimentar(n1);
                    break;
                case "2":
                    System.out.print("Número de Tamagotchi: ");
                    int n2 = Integer.parseInt(sc.nextLine()) - 1;
                    cuidador.limpiar(n2);
                    break;
                case "3":
                    System.out.print("Número de Tamagotchi: ");
                    int n3 = Integer.parseInt(sc.nextLine()) - 1;
                    System.out.print("Tu respuesta: ");
                    int respuesta = Integer.parseInt(sc.nextLine());
                    cuidador.jugar(n3, respuesta);
                    break;
                case "4":
                    System.out.print("Número de Tamagotchi: ");
                    int n4 = Integer.parseInt(sc.nextLine()) - 1;
                    cuidador.matar(n4);
                    break;
                case "5":
                    cuidador.mostrarEstados();
                    break;
                case "0":
                    System.out.println("Saliendo del cuidador...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (!opcion.equals("0"));

        sc.close();
    }
}
