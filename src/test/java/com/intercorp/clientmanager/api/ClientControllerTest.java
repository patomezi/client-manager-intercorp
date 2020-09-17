package com.intercorp.clientmanager.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.intercorp.clientmanager.domain.Cliente;
import com.intercorp.clientmanager.dto.ClientRequest;
import com.intercorp.clientmanager.service.IClientsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.support.ReflectionSupport;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.FieldSetter;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientControllerTest {
    public static final String CLIENTS_PATH = "/api/clientes";
    private static final String CLIENTS_KPI_PATH = "/api/clientes/kpi";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @InjectMocks
    private ClientController clientController;
    @Mock
    IClientsService clientsSearcher;

    MockMvc mockMvc;

    @BeforeAll
    static void setUpForAllTests() {
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @BeforeEach
    void setUp() {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void a_put_request_on_clients_path_should_return_a_method_not_valid_response_status() throws Exception {
        mockMvc.perform(put(CLIENTS_PATH)).andExpect(status().isMethodNotAllowed());
    }

    @Test
    void a_post_request_on_clients_with_invalid_client_request_should_return_bad_request_response_status() throws Exception {
        mockMvc.perform(post(CLIENTS_PATH)
                .content("an_invalid_json_content")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isBadRequest());
    }

    @Test
    void a_post_request_on_clients_should_return_bad_request_if_request_has_null_values() throws Exception {
        ClientRequest clienteRequest = new ClientRequest();
        clienteRequest.setApellido("apellido");
        clienteRequest.setNombre("nombre");
        clienteRequest.setEdad(24);
        mockMvc.perform(post(CLIENTS_PATH)
                .content(OBJECT_MAPPER.writeValueAsString(clienteRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isBadRequest());
    }

    @Test
    void a_post_request_on_clients_should_return_bad_request_if_age_is_negative_value() throws Exception {
        ClientRequest clienteRequest = new ClientRequest();
        clienteRequest.setApellido("apellido");
        clienteRequest.setNombre("nombre");
        clienteRequest.setEdad(-24);
        clienteRequest.setFechaDeNacimiento(Instant.now());
        mockMvc.perform(post(CLIENTS_PATH)
                .content(OBJECT_MAPPER.writeValueAsString(clienteRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isBadRequest());
    }

    @Test
    void a_post_request_on_clients_should_return_bad_request_if_birth_date_has_hour_value() throws Exception {
        ClientRequest clienteRequest = new ClientRequest();
        clienteRequest.setApellido("apellido");
        clienteRequest.setNombre("nombre");
        clienteRequest.setEdad(20);
        Instant instantWithNotZeroHour = Instant.ofEpochMilli(652712400000L); //Friday, September 7, 1990 10:00:00 AM in ARGENTINA.
        clienteRequest.setFechaDeNacimiento(instantWithNotZeroHour);
        mockMvc.perform(post(CLIENTS_PATH)
                .content(OBJECT_MAPPER.writeValueAsString(clienteRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isBadRequest());
    }

    @Test
    void a_post_request_on_clients_should_return_bad_request_if_age_does_not_match_with_birth_date() throws Exception {
        ClientRequest clienteRequest = new ClientRequest();
        clienteRequest.setApellido("apellido");
        clienteRequest.setNombre("nombre");
        Instant instanteOfBirthdate = Instant.ofEpochMilli(652676400000L); //Friday, September 7, 1990 00:00:00 AM in ARGENTINA
        clienteRequest.setFechaDeNacimiento(instanteOfBirthdate);
        ZonedDateTime dateTime = clienteRequest.getFechaDeNacimiento().atZone(ZoneId.systemDefault());
        int currentYear = LocalDate.now().getYear();
        clienteRequest.setEdad(currentYear - dateTime.getYear() + 1); //Óne year added to force the validation
        mockMvc.perform(post(CLIENTS_PATH)
                .content(OBJECT_MAPPER.writeValueAsString(clienteRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isBadRequest());
    }

    @Test
    void a_post_request_on_clients_with_valid_body_should_return_200_ok() throws Exception {
        ClientRequest clienteRequest = new ClientRequest();
        clienteRequest.setApellido("apellido");
        clienteRequest.setNombre("nombre");
        Instant instanteOfBirthdate = Instant.ofEpochMilli(652676400000L); //Friday, September 7, 1990 00:00:00 AM in ARGENTINA
        clienteRequest.setFechaDeNacimiento(instanteOfBirthdate);
        ZonedDateTime dateTime = clienteRequest.getFechaDeNacimiento().atZone(ZoneId.systemDefault());
        int currentYear = LocalDate.now().getYear();
        clienteRequest.setEdad(currentYear - dateTime.getYear());
        mockMvc.perform(post(CLIENTS_PATH)
                .content(OBJECT_MAPPER.writeValueAsString(clienteRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk());
    }

    @Test
    void a_get_request_on_clients_should_return_204_if_there_is_no_clients_in_database() throws Exception {
        when(clientsSearcher.getClientsWithProbablyDeathDate()).thenReturn(Collections.emptyList());
        mockMvc.perform(get(CLIENTS_PATH))
                .andExpect(status().isNoContent());
    }

    @Test
    void a_get_request_on_clients_should_return_200_if_there_is_clients_in_database() throws Exception {
        Cliente cliente = new Cliente("nombre", "apellido", 28, LocalDate.of(1992, 7, 9));
        FieldSetter.setField(cliente, cliente.getClass().getDeclaredField("id"), 1L);
        when(clientsSearcher.getClientsWithProbablyDeathDate())
                .thenReturn(Collections.singletonList(cliente));
        mockMvc.perform(get(CLIENTS_PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].nombre", is("nombre")))
                .andExpect(jsonPath("$[0].apellido", is("apellido")))
                .andExpect(jsonPath("$[0].edad", is(28)));

    }

    @Test
    void a_get_request_on_clients_kpi_path_should_return_204_if_there_is_not_clients_in_database() throws Exception{
        mockMvc.perform(get(CLIENTS_KPI_PATH))
                .andExpect(status().isNoContent());
    }

    @Test
    void a_get_request_on_clients_kpi_path_should_return_200_if_there_is_there_is_clients_in_database() throws Exception{
        when(clientsSearcher.calculateAgeAverage()).thenReturn(20.5);
        mockMvc.perform(get(CLIENTS_KPI_PATH))
                .andExpect(status().isOk());
    }

}
