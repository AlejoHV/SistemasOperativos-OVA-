package com.example.sistemas.ovasistemaso.service;

public class NativeSJF {
    static {
        try {
	    System.loadLibrary("nativesjf");
	    System.out.println("Librería cargada exitosamente");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Error cargando la librería nativa: " + e.getMessage());
        }
    }

    public native int[] scheduleSJF(int[] ids, int[] arrivals, int[] bursts);
}
