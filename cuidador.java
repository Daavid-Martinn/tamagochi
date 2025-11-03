package org.cuatrovientos.dam.psp.entregaTamagochi;

import java.util.ArrayList;
import java.util.List;

public class cuidador {
    private List<Tamagochi> tamas;
    private List<Thread> hilos;

    public cuidador(int cantidad) {
        tamas = new ArrayList<>();
        hilos = new ArrayList<>();

        for (int i = 0; i < cantidad; i++) {
            Tamagochi t = new Tamagochi("Tama" + (i + 1));
            tamas.add(t);
            hilos.add(new Thread(t)); // cada Tamagotchi corre en su propio hilo
        }
    }

    public void iniciar() {
        for (Thread h : hilos) {
            h.start();
        }
    }

    public void mostrarEstados() {
        for (Tamagochi t : tamas) {
            t.estado();
        }
    }

    public void alimentar(int indice) {
        if (indiceValido(indice))
            tamas.get(indice).alimentar();
    }

    public void limpiar(int indice) {
        if (indiceValido(indice))
            tamas.get(indice).limpiar();
    }

    public void jugar(int indice, int respuesta) {
        if (indiceValido(indice))
            tamas.get(indice).jugar(respuesta);
    }

    public void matar(int indice) {
        if (indiceValido(indice))
            tamas.get(indice).matar();
    }

    private boolean indiceValido(int i) {
        return i >= 0 && i < tamas.size();
    }

    public int getCantidad() {
        return tamas.size();
    }
}
