package extrawest.ocpp.model.dataTypes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import extrawest.ocpp.model.Validatable;
import extrawest.ocpp.model.validation.RequiredValidator;
import extrawest.ocpp.model.validation.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


/**
 * Sales_ Tariff
 * urn:x-oca:ocpp:uid:2:233272
 * NOTE: This dataType is based on dataTypes from &lt;<ref-ISOIEC15118-2,ISO 15118-2>>.
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customData",
        "id",
        "salesTariffDescription",
        "numEPriceLevels",
        "salesTariffEntry"
})
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class SalesTariffType implements Validatable {

    private transient Validator<Object> requiredValidator = new RequiredValidator();

    /**
     * This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
     *
     */
    @JsonProperty("customData")
    private CustomDataType customDataType;
    /**
     * Identified_ Object. MRID. Numeric_ Identifier
     * urn:x-enexis:ecdm:uid:1:569198
     * SalesTariff identifier used to identify one sales tariff. An SAID remains a unique identifier for one schedule throughout a charging session.
     *
     * (Required)
     *
     */
    @JsonProperty("id")
    private Integer id;
    /**
     * Sales_ Tariff. Sales. Tariff_ Description
     * urn:x-oca:ocpp:uid:1:569283
     * A human readable title/short description of the sales tariff e.g. for HMI display purposes.
     *
     *
     */
    @JsonProperty("salesTariffDescription")
    private String salesTariffDescription;
    /**
     * Sales_ Tariff. Num_ E_ Price_ Levels. Counter
     * urn:x-oca:ocpp:uid:1:569284
     * Defines the overall number of distinct price levels used across all provided SalesTariff elements.
     *
     *
     */
    @JsonProperty("numEPriceLevels")
    private Integer numEPriceLevels;
    /**
     *
     * (Required)
     *
     */
    @JsonProperty("salesTariffEntry")
    private List<SalesTariffEntryType> salesTariffEntryType;

    public SalesTariffType(Integer id, List<SalesTariffEntryType> salesTariffEntryType) {
        this.id = id;
        this.salesTariffEntryType = salesTariffEntryType;
    }

    public void setCustomDataType(CustomDataType customDataType) {
        this.customDataType = customDataType;
    }

    public void setId(Integer id) {
        requiredValidator.validate(id);
        this.id = id;
    }

    public void setSalesTariffDescription(String salesTariffDescription) {
        this.salesTariffDescription = salesTariffDescription;
    }

    public void setNumEPriceLevels(Integer numEPriceLevels) {
        this.numEPriceLevels = numEPriceLevels;
    }

    public void setSalesTariffEntryType(List<SalesTariffEntryType> salesTariffEntryType) {
        this.salesTariffEntryType = salesTariffEntryType;
    }

    @Override
    public boolean validate() {
        return requiredValidator.safeValidate(id)
                &&salesTariffEntryType.stream().filter(SalesTariffEntryType::validate).count()==salesTariffEntryType.size();
    }
}
