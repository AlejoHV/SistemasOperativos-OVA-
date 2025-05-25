package com.example.sistemas.ovasistemaso.controller;

import com.example.sistemas.ovasistemaso.dto.SJFRequest;
import com.example.sistemas.ovasistemaso.service.NativeSJF;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@RestController
@RequestMapping("/api/sjf")
public class SJFController {

    private final NativeSJF nativeSJF = new NativeSJF();

    @PostMapping
    public ResponseEntity<?> ejecutarSJF(@RequestBody SJFRequest request) {
        if (request.getIds() == null || request.getArrivals() == null || request.getBursts() == null) {
            return ResponseEntity.badRequest().body(Map.of(
            "error", "Los campos ids, arrivals (tiempo de llegada) y bursts (tiempo de rafaga) son obligatorios"));
        }

        if (request.getIds().length != request.getArrivals().length ||
                request.getIds().length != request.getBursts().length) {
            return ResponseEntity.badRequest().body(Map.of("error", "Todos los arreglos deben tener la misma longitud"));
        }

        try {
            int[] resultado = nativeSJF.scheduleSJF(
                    request.getIds(),
                    request.getArrivals(),
                    request.getBursts()
            );

            return ResponseEntity.ok().body(Map.of(
                    "resultado", resultado,
                    "mensaje", "Algoritmo SJF ejecutado correctamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", "Error al ejecutar el algoritmo SJF",
                            "detalle", e.getMessage()
                    ));
        }
    }
}
