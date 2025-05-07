package apptive.basic.item.service;

import apptive.basic.domain.Item;
import apptive.basic.item.dto.ItemCreateDto;
import apptive.basic.item.dto.ItemResponseDto;
import apptive.basic.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemResponseDto save(ItemCreateDto itemCreateDto) {
        Item item = new Item(itemCreateDto.getPrice(), itemCreateDto.getQuantity());

        Item save = itemRepository.save(item);

        return new ItemResponseDto(save.getId(), save.getPrice(), save.getQuantity());
    }
}
