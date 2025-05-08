package apptive.basic.item.service;

import apptive.basic.domain.Item;
import apptive.basic.item.dto.ItemCreateDto;
import apptive.basic.item.dto.ItemResponseDto;
import apptive.basic.item.dto.ItemUpdateDto;
import apptive.basic.item.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EntityManager em;

    @Test
    void save() {
        ItemCreateDto itemCreateDto = new ItemCreateDto(100, 10);

        ItemResponseDto saved = itemService.save(itemCreateDto);

        flushAndClear();

        Item findItem = itemRepository.findById(saved.getId()).get();


        Assertions.assertThat(saved.getId()).isEqualTo(findItem.getId());
        Assertions.assertThat(saved.getPrice()).isEqualTo(findItem.getPrice());
        Assertions.assertThat(saved.getQuantity()).isEqualTo(findItem.getQuantity());
    }

    @Test
    void update() {
        ItemCreateDto itemCreateDto = new ItemCreateDto(100, 10);
        ItemResponseDto saved = itemService.save(itemCreateDto);


        ItemResponseDto updatedItem = itemService.updateItem(new ItemUpdateDto(1000, 100), saved.getId());

        flushAndClear();

        Item findItem = itemRepository.findById(updatedItem.getId()).get();

        Assertions.assertThat(updatedItem.getId()).isEqualTo(findItem.getId());
        Assertions.assertThat(updatedItem.getPrice()).isEqualTo(findItem.getPrice());
        Assertions.assertThat(updatedItem.getQuantity()).isEqualTo(findItem.getQuantity());
    }

    private void flushAndClear() {
        em.flush();
        em.clear();
    }

}