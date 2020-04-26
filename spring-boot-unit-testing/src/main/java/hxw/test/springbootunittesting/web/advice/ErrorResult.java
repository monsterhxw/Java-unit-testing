package hxw.test.springbootunittesting.web.advice;

import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor
public
class ErrorResult {

    private final List<FieldValidationError> fieldErrors = new ArrayList<>();

    public ErrorResult(String field, String message) {
        this.fieldErrors.add(new FieldValidationError(field, message));
    }
}
