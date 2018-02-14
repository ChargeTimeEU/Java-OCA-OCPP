package eu.chargetime.ocpp.model.localauthlist;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.model.core.IdTagInfo;
import eu.chargetime.ocpp.utilities.ModelUtil;

public class AuthorizationData implements Validatable {
	private String idTag;
	private IdTagInfo idTagInfo;

	public void setIdTag(String idTag) throws PropertyConstraintException {
		if(!ModelUtil.validate(idTag, 20)) throw new PropertyConstraintException("idTag", idTag, "Exceeds length");
		this.idTag = idTag;
	}
	
	public String getIdTag() {
		return idTag;
	}
	
	public void setIdTagInfo(IdTagInfo idTagInfo) throws PropertyConstraintException {
		if(!idTagInfo.validate()) throw new PropertyConstraintException("idTagInfo", idTagInfo, "Failed Validation");
		this.idTagInfo = idTagInfo;
	}
	
	public IdTagInfo getIdTagInfo() {
		return idTagInfo;
	}
	
	@Override
	public boolean validate() {
		return ModelUtil.validate(idTag, 20) && idTagInfo.validate();
	}
}
