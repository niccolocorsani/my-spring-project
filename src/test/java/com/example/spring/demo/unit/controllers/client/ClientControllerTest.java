package com.example.spring.demo.unit.controllers.client;


import com.example.spring.demo.controllers.client.ClientController;
import com.example.spring.demo.model.client.Client;
import com.example.spring.demo.services.client.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClientService clientService;


    @Test
    public void testAllClientsEmpty() throws Exception {
        System.err.println("oo");

        this.mvc.perform(get("/api/clients")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testAllClientsNotEmpty() throws Exception {

        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1L, "Marco", "Rossi"));
        clients.add(new Client(2L, "Francesco", ""));

        when(clientService.getAllClients()).
                thenReturn(clients);


        this.mvc.perform(get("/api/clients")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Marco")))
                .andExpect(jsonPath("$[0].lastName", is("Rossi")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("Francesco")));
    }


    @Test
    public void testControllerGetClient() throws Exception {

        this.mvc.perform(get("/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void testControllerPutClient() throws Exception {

        Client client = new Client();
        client.setId(1L);
        client.setFirstName("Marco");
        ObjectMapper mapper = new ObjectMapper();
        String clientString = mapper.writeValueAsString(client);
        this.mvc.perform(put("/putClient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clientString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("firstName", is("Marco")));

    }


    @Test
    public void testControllerPutAndUpdateClient() throws Exception {

        Client client = new Client();
        client.setId(1L);
        client.setFirstName("Marco");
        ObjectMapper mapper = new ObjectMapper();
        String clientString = mapper.writeValueAsString(client);
        this.mvc.perform(put("/putClient"));
        client.setFirstName("Andrea");
        clientString = mapper.writeValueAsString(client);
        this.mvc.perform(put("/updateClient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clientString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("firstName", is("Andrea")));

    }


    @Test
    public void testControllerPutAndDeleteClient() throws Exception {

        Client client = new Client();
        client.setId(1L);
        client.setFirstName("Marco");
        ObjectMapper mapper = new ObjectMapper();
        String clientString = mapper.writeValueAsString(client);
        this.mvc.perform(put("/putClient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clientString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        this.mvc.perform(delete("/1"))
                .andExpect(status().isOk());

    }


}
