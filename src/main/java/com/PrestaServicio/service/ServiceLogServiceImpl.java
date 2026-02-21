package com.PrestaServicio.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.PrestaServicio.Interfaz.ILogService;
import com.PrestaServicio.model.LogServiceModel;
import com.PrestaServicio.model.Response2;
import com.PrestaServicio.util.DateUtil;
import com.PrestaServicio.util.consts.CommonConsts;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@Qualifier(CommonConsts.PRESTA_SERVICE)
@Repository
public class ServiceLogServiceImpl implements ILogService {

    @Qualifier(CommonConsts.PRESTA_DAO)
    @Autowired
    private ILogService logDao;

    @Autowired
    private ObjectMapper objectMapper;

        @Override
    public LogServiceModel setRequestData(HttpServletRequest httpRequest, Object request, Long id_usuario) {

        LogServiceModel logModel = new LogServiceModel();

        logModel.setIp(httpRequest.getRemoteAddr());
        logModel.setMethod(httpRequest.getMethod());
        logModel.setEnd_point(httpRequest.getRequestURI());
        logModel.setBegin_date_time(DateUtil.getCurrentDateTime());
        logModel.setId_usuario(id_usuario);
        logModel.setResponse_body("");

        try {
            logModel.setRequest_body(request == null ? null : objectMapper.writeValueAsString(request));

        } catch (JsonProcessingException ex) {
            logModel.setRequest_body(ex.getMessage());
        }

        return logModel;
    }

    @Override
    public <T> void setResponseData(LogServiceModel logModel, Response2<T> response) {
        
        logModel.setError_(response.getErrorMssg());
        logModel.setHttp_status_code(response.getStatusCode().value());
        logModel.setEnd_date_time(DateUtil.getCurrentDateTime());

        try {
                logModel.setResponse_body(objectMapper.writeValueAsString(response));
        } catch (JsonProcessingException ex) {
            logModel.setResponse_body(ex.getMessage());
        }

        logModel.setError_(response.getErrorMssg());
    }

    @Override
    public void insert(LogServiceModel request) {
        logDao.insert(request);
    }

    @Override
    public <T> CompletableFuture<Void> save(LogServiceModel logModel) {
        return CompletableFuture.runAsync(() -> {
            insert(logModel);
        });
    }

    @Override
    public <T> T clone(T request, Class<T> clazz) {
        try {
            // Convertir el objeto a JSON
            String json = objectMapper.writeValueAsString(request);

            // Convertir el JSON de vuelta al objeto
            T responseClon = objectMapper.readValue(json, clazz);

            return responseClon;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public <T> void setResponseDataAndSave(LogServiceModel logModel, Response2<T> response) {
        setResponseData(logModel, response);

        save(logModel);
    }
}
