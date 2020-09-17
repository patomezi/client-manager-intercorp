package com.intercorp.clientmanager.api;

import com.intercorp.clientmanager.domain.Cliente;
import com.intercorp.clientmanager.dto.ClientRequest;
import com.intercorp.clientmanager.dto.ClientWithProbablyDeathDate;
import com.intercorp.clientmanager.service.IClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientController {

    IClientsService clientsService;

    @Autowired
    public ClientController(IClientsService clientsService){
        this.clientsService = clientsService;
    }

    @PostMapping
    public ResponseEntity crearCliente(@Valid @RequestBody ClientRequest clientRequest) {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getClientes() {
        final List<Cliente> clientsWithProbablyDeathDate = clientsService.getClientsWithProbablyDeathDate();
        return clientsWithProbablyDeathDate.isEmpty() ?
                ResponseEntity
                        .noContent()
                        .build() :
                ResponseEntity
                        .ok(ClientWithProbablyDeathDate.from(clientsWithProbablyDeathDate));
    }

    @GetMapping("/kpi")
    public ResponseEntity getKpiAndStandarDeviation() {
        return clientsService.calculateAgeAverage().equals(0.0) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok().build();
    }
}
