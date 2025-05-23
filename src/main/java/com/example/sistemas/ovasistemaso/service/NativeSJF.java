package com.example.sistemas.ovasistemaso.service;

public class NativeSJF {
    static {
        try {
            System.loadLibrary("libalgoritmo");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Error cargando la librería nativa: " + e.getMessage());
        }
    }

    public native int scheduleSJF(int[] ids, int[] arrivals, int[] bursts);
}
