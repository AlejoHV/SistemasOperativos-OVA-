package com.example.sistemas.ovasistemaso.controller;

import com.example.sistemas.ovasistemaso.dto.SJFRequest;
import com.example.sistemas.ovasistemaso.service.NativeSJF;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sjf")
public class SJFController {

    private final NativeSJF nativeSJF = new NativeSJF();

    @PostMapping
    public int[] ejecutarSJF(@RequestBody SJFRequest request) {
        return nativeSJF.scheduleSJF(
                request.getIds(),
                request.getArrivals(),
                request.getBursts()
        );
    }
}
