package org.dev.barsukov.controller.v1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dev.barsukov.controller.fapi.v1.ListenKeyController;
import org.dev.barsukov.service.crud.CrudListenKeyService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ListenKeyController.class)
public class KeyControllerTest {

    @MockBean
    CrudListenKeyService employeeService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testfindAll() throws Exception {
//        mock Service here
        mockMvc.perform(get("/key/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("Lokesh")));
    }
}