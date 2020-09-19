package com.intercorp.clientmanager.api;

import com.intercorp.clientmanager.dto.ClientWithProbablyDeathDateResponse;
import com.intercorp.clientmanager.dto.CreatedClientResponse;
import com.intercorp.clientmanager.dto.KpiResponse;
import com.intercorp.clientmanager.dto.NewClientRequest;
import com.intercorp.clientmanager.service.IClientsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static javax.servlet.http.HttpServletResponse.*;

@RestController
@RequestMapping("/api/clientes")
public class ClientController {

    IClientsService clientsService;

    @Autowired
    public ClientController(IClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @ApiOperation(
            value = "Inserta un nuevo cliente en la h2 Database",
            response = CreatedClientResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "ok"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "Datos ingresados incorrectos")
    })
    @PostMapping
    public ResponseEntity crearCliente(@Valid @RequestBody NewClientRequest newClientRequest) {
        return CreatedClientResponse
                .from(clientsService.save(newClientRequest.toDomain()));
    }

    @ApiOperation(
            value = "Obtiene todos los clientes existentes, con su fecha estimada de muerte",
            response = ClientWithProbablyDeathDateResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = ""),
            @ApiResponse(code = SC_NO_CONTENT, message = "No hay clientes! Intenta agregar clientes primero")
    })
    @GetMapping
    public ResponseEntity getClientes() {
        return ClientWithProbablyDeathDateResponse.
                from(clientsService.getClientsWithProbablyDeathDate());
    }

    @ApiOperation(
            value = "Obtiene la edad promedio de los clientes, y su desviacion estandar",
            response = KpiResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = ""),
            @ApiResponse(code = SC_NO_CONTENT, message = "No hay clientes! Intenta agregar clientes primero")
    })
    @GetMapping("/kpi")
    public ResponseEntity<KpiResponse> getKpiAndStandarDeviation() {
        return KpiResponse.from(clientsService.calculateKpi());
    }
}
