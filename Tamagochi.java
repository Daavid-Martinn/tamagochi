package org.cuatrovientos.dam.psp.entregaTamagochi;

import java.util.Random;

public class Tamagochi implements Runnable {
    private String nombre;
    private boolean vivo;
    private boolean ocupado;
    private int suciedad;
    private long nacimiento;
    private Random random;

    public Tamagochi(String nombre) {
        this.nombre = nombre;
        this.vivo = true;
        this.ocupado = false;
        this.suciedad = 0;
        this.random = new Random();
        this.nacimiento = System.currentTimeMillis();
    }

    @Override
    public void run() {
        System.out.println(nombre + ": ¡He nacido! ");
        while (vivo) {
            try {
                Thread.sleep(20000); // cada 20 segundos se ensucia
                suciedad++;

                if (suciedad == 5) {
                    System.out.println(nombre + ": Estoy sucio ");
                }
                if (suciedad >= 10) {
                    System.out.println(nombre + ": Morí por suciedad ");
                    vivo = false;
                    break;
                }

                // muerte por vejez (5 minutos)
                if (System.currentTimeMillis() - nacimiento > 300000) {
                    System.out.println(nombre + ": Morí de viejo ");
                    vivo = false;
                    break;
                }

            } catch (InterruptedException e) {
                // ignorar interrupciones (por ejemplo al matar)
            }
        }
    }

    public synchronized void alimentar() {
    	
        if (vivo && !ocupado) {
            ocupado = true;
            System.out.println(nombre + ": Comienzo a comer ");
            try {
                Thread.sleep(2000 + random.nextInt(4000)); // entre 2 y 6 segundos
            } catch (InterruptedException e) {}
            System.out.println(nombre + ": Terminé de comer ");
            ocupado = false;
        } else {
            System.out.println(nombre + ": No puedo comer ahora.");
        }
    }

    public synchronized void limpiar() {
        if (vivo && !ocupado) {
            ocupado = true;
            System.out.println(nombre + ": Me estoy bañando ");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {}
            suciedad = 0;
            System.out.println(nombre + ": Estoy limpio ");
            ocupado = false;
        } else {
            System.out.println(nombre + ": No puedo bañarme ahora.");
        }
    }

    public synchronized void jugar(int respuestaCuidador) {
        if (vivo && !ocupado) {
            ocupado = true;
            int a = random.nextInt(4) + 1;
            int b = random.nextInt(4) + 1;
            System.out.println(nombre + ": ¿Cuánto es " + a + " + " + b + "?");

            if (respuestaCuidador == a + b) {
                System.out.println(nombre + ": ¡Bien! ");
            } else {
                System.out.println(nombre + ": No... intenta otra vez ");
            }

            ocupado = false;
        } else {
            System.out.println(nombre + ": No puedo jugar ahora.");
        }
    }

    public synchronized void matar() {
        if (!ocupado) {
            vivo = false;
            System.out.println(nombre + ": He sido destruido ");
        } else {
            System.out.println(nombre + ": Estoy ocupado, no puedo morir aún.");
        }
    }

    public synchronized void estado() {
        System.out.println(nombre + " -> vivo=" + vivo + ", suciedad=" + suciedad + ", ocupado=" + ocupado);
    }

    public boolean estaVivo() { return vivo; }

    public String getNombre() { return nombre; }
}
