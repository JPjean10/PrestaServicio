package com.PrestaServicio.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PrestaServicio.Interfaz.ILogService;
import com.PrestaServicio.Interfaz.UsuarioInterfaz;
import com.PrestaServicio.model.LogServiceModel;
import com.PrestaServicio.model.Response2;
import com.PrestaServicio.model.UsuarioModel;
import com.PrestaServicio.util.consts.ApiConst;
import com.PrestaServicio.util.consts.CommonConsts;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(ApiConst.USUARIO)
@CrossOrigin("*")
public class UsuarioControlador {

    @Qualifier(CommonConsts.PRESTA_SERVICE)
    @Autowired
    UsuarioInterfaz service;

    @Qualifier(CommonConsts.PRESTA_SERVICE)
    @Autowired
    private ILogService serviceLog;

    @PostMapping(produces = ApiConst.PRODUCES)
    public ResponseEntity<?> insert(HttpServletRequest http, @RequestBody UsuarioModel usuario) {
        LogServiceModel logModel = serviceLog.setRequestData(http, usuario,null);

        Response2<String> out;

        try {
            service.insertarUsuario(usuario);
            out = new Response2<>(HttpStatus.CREATED, "Usuario registrado exitosamente.", true);
        } catch (Exception ex) {
            out = new Response2<>(ex);
        }
        serviceLog.setResponseDataAndSave(logModel, out);

        return ResponseEntity.status(out.getStatusCode()).body(out);
    }
}
