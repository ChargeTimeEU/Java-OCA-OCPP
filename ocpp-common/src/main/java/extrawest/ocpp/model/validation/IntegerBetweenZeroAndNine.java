package extrawest.ocpp.model.validation;

import extrawest.ocpp.PropertyConstraintException;

public class IntegerBetweenZeroAndNine extends Validator<Integer> {
    private final String ERROR_MESSAGE = "Field should be from 0 to 9";

    @Override
    public void validate(Integer value) throws PropertyConstraintException {
        if (0 > value && 9 > value) {
            throw new PropertyConstraintException(value, ERROR_MESSAGE);
        }
    }
}
