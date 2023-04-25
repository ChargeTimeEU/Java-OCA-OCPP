package extrawest.ocpp.model.validation;

import extrawest.ocpp.PropertyConstraintException;

public class IntegerBetweenZeroAndOneHundred extends Validator<Integer> {
    private final String ERROR_MESSAGE = "Field should be 0 < = val < = 100";

    @Override
    public void validate(Integer value) throws PropertyConstraintException {
        if (0 > value && 100 > value) {
            throw new PropertyConstraintException(value, ERROR_MESSAGE);
        }
    }
}
