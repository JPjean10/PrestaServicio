package com.PrestaServicio.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.PrestaServicio.Interfaz.UsuarioInterfaz;
import com.PrestaServicio.model.BaseData;
import com.PrestaServicio.model.UsuarioModel;
import com.PrestaServicio.util.consts.CommonConsts;
import com.PrestaServicio.util.consts.DbConst;

@Qualifier(CommonConsts.PRESTA_DAO)
@Repository
public class UsuarioDaoImpl implements UsuarioInterfaz {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private BaseData data;

    @Override
    public void insertarUsuario(UsuarioModel usuario) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(data.getSchema())
                .withProcedureName(DbConst.SP_INSERTAR_USUARIO);

        SqlParameterSource input = new MapSqlParameterSource()
                .addValue("p_usuario", usuario.getUsuario())
                .addValue("p_contrasena", usuario.getContrasena())
                .addValue("p_nombre", usuario.getNombre());

        jdbcCall.execute(input);
    }

}
