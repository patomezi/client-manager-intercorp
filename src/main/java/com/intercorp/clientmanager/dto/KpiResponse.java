package com.intercorp.clientmanager.dto;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public class KpiResponse {
    Double promedioEdad;
    Double desviacionEstandar;

    public KpiResponse(Double promedioEdad, Double desviacionEstandar) {
        this.promedioEdad = promedioEdad;
        this.desviacionEstandar = desviacionEstandar;
    }

    public static ResponseEntity<KpiResponse> from(Map<String, Double> calculatedKpi) {
        return calculatedKpi.get("promedioEdad") != null && calculatedKpi.get("desviacionEstandar") != null ?
                ResponseEntity.ok(new KpiResponse(calculatedKpi.get("promedioEdad"), calculatedKpi.get("desviacionEstandar"))) :
                ResponseEntity.noContent().build();
    }

    public Double getPromedioEdad() {
        return promedioEdad;
    }

    public void setPromedioEdad(Double promedioEdad) {
        this.promedioEdad = promedioEdad;
    }

    public Double getDesviacionEstandar() {
        return desviacionEstandar;
    }

    public void setDesviacionEstandar(Double desviacionEstandar) {
        this.desviacionEstandar = desviacionEstandar;
    }
}
