package apptive.basic.item.controller;

import apptive.basic.item.dto.ItemCreateDto;
import apptive.basic.item.dto.ItemResponseDto;
import apptive.basic.item.dto.ItemUpdateDto;
import apptive.basic.item.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void failSave() throws Exception {
        ItemCreateDto itemCreateDto = new ItemCreateDto(100,1);

        String request = objectMapper.writeValueAsString(itemCreateDto);

        mockMvc.perform(post("/v2/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isBadRequest());
    }

    @Test
    void failUpdate() throws Exception {
        ItemCreateDto itemCreateDto = new ItemCreateDto(100,10);
        ItemResponseDto saved = itemService.save(itemCreateDto);

        ItemUpdateDto itemUpdateDto = new ItemUpdateDto(100, 1);

        String request = objectMapper.writeValueAsString(itemUpdateDto);

        mockMvc.perform(patch("/v2/items/{id}", saved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isBadRequest());


    }
}