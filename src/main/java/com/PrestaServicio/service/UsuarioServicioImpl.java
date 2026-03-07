package com.PrestaServicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.PrestaServicio.Interfaz.UsuarioInterfaz;
import com.PrestaServicio.model.UsuarioModel;
import com.PrestaServicio.util.consts.CommonConsts;

@Qualifier(CommonConsts.PRESTA_SERVICE)
@Repository
public class UsuarioServicioImpl implements UsuarioInterfaz {

    @Qualifier(CommonConsts.PRESTA_DAO)
    @Autowired
    UsuarioInterfaz usuarioDao;

    @Override
    public void insertarUsuario(UsuarioModel usuario) {
        usuarioDao.insertarUsuario(usuario);
    }

}
