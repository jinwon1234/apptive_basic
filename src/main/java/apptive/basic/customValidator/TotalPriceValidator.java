package apptive.basic.customValidator;

import apptive.basic.customAnno.TotalPrice;
import apptive.basic.item.dto.ItemCreateDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TotalPriceValidator implements ConstraintValidator<TotalPrice, ItemCreateDto> {
    @Override
    public boolean isValid(ItemCreateDto itemCreateDto, ConstraintValidatorContext constraintValidatorContext) {

        if (itemCreateDto.getQuantity() * itemCreateDto.getPrice() <= 100) {
            log.info("{} {}",itemCreateDto.getPrice(), itemCreateDto.getPrice());
            return false;
        }

        return true;
    }
}
