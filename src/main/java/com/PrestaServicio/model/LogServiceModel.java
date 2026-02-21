package com.PrestaServicio.model;

import java.time.LocalDateTime;

public class LogServiceModel {

    private Long log_service_id;
    private Long id_usuario;
    private String request_code;
    private int http_status_code;
    private String ip;
    private String method;
    private String end_point;
    private String request_header;
    private String request_body;
    private String response_body;
    private String error_;
    private LocalDateTime begin_date_time;
    private LocalDateTime end_date_time;

    public Long getLog_service_id() {
        return log_service_id;
    }
    public void setLog_service_id(Long log_service_id) {
        this.log_service_id = log_service_id;
    }
    public Long getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }
    public String getRequest_code() {
        return request_code;
    }
    public void setRequest_code(String request_code) {
        this.request_code = request_code;
    }
    public int getHttp_status_code() {
        return http_status_code;
    }
    public void setHttp_status_code(int http_status_code) {
        this.http_status_code = http_status_code;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getEnd_point() {
        return end_point;
    }
    public void setEnd_point(String end_point) {
        this.end_point = end_point;
    }
    public String getRequest_header() {
        return request_header;
    }
    public void setRequest_header(String request_header) {
        this.request_header = request_header;
    }
    public String getRequest_body() {
        return request_body;
    }
    public void setRequest_body(String request_body) {
        this.request_body = request_body;
    }
    public String getResponse_body() {
        return response_body;
    }
    public void setResponse_body(String response_body) {
        this.response_body = response_body;
    }
    public String getError_() {
        return error_;
    }
    public void setError_(String error_) {
        this.error_ = error_;
    }
    public LocalDateTime getBegin_date_time() {
        return begin_date_time;
    }
    public void setBegin_date_time(LocalDateTime begin_date_time) {
        this.begin_date_time = begin_date_time;
    }
    public LocalDateTime getEnd_date_time() {
        return end_date_time;
    }
    public void setEnd_date_time(LocalDateTime end_date_time) {
        this.end_date_time = end_date_time;
    }

}
