package apptive.basic.item.controller;


import apptive.basic.item.dto.ItemCreateDto;
import apptive.basic.item.dto.ItemResponseDto;
import apptive.basic.item.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/v1/items")
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemCreateDto itemCreateDto, BindingResult bindingResult) {

        if (itemCreateDto.getPrice() * itemCreateDto.getQuantity() <= 100) {
            bindingResult.reject("totalPrice", "총 가격은 100이상이어야합니다.");
        }

        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        ItemResponseDto saved = itemService.save(itemCreateDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/v2/items")
    public ResponseEntity<ItemResponseDto> createItem(@Valid @RequestBody ItemCreateDto itemCreateDto) {


        ItemResponseDto saved = itemService.save(itemCreateDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


}
