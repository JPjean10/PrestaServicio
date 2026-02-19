package com.PrestaServicio.controlador;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PrestaServicio.util.consts.ApiConst;

@RestController
@RequestMapping(ApiConst.LINE)
@CrossOrigin("*")
public class ControllerLine {

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> Line() {
            Map<String, String> response = new HashMap<>();
    response.put("userMssg", "conectado");

    return ResponseEntity.ok(response);
    }

}
