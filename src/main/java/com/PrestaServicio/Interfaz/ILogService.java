package com.PrestaServicio.Interfaz;

import java.util.concurrent.CompletableFuture;

import com.PrestaServicio.model.LogServiceModel;
import com.PrestaServicio.model.Response2;

import jakarta.servlet.http.HttpServletRequest;

public interface ILogService {

    LogServiceModel setRequestData(HttpServletRequest httpRequest, Object request, Long id_usuario);

    <T> void setResponseData(LogServiceModel logModel, Response2<T> response);

    void insert(LogServiceModel request);

    <T> CompletableFuture<Void> save(LogServiceModel logModel);

    <T> T clone(T request, Class<T> clazz);

    <T> void setResponseDataAndSave(LogServiceModel logModel, Response2<T> response);


}
