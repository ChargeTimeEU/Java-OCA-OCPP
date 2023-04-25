package extrawest.ocpp.model.validation;

import extrawest.ocpp.PropertyConstraintException;

public class IntegerGreaterThanOrEqualToZeroValidator extends Validator<Integer> {

    private final String ERROR_MESSAGE = "Field should be greater than 0 or equal 0";

    @Override
    public void validate(Integer value) throws PropertyConstraintException {
        if (0 > value) {
            throw new PropertyConstraintException(value, ERROR_MESSAGE);
        }
    }
}
