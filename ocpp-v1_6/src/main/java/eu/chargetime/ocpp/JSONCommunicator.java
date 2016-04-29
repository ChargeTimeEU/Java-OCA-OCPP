package eu.chargetime.ocpp;

import eu.chargetime.ocpp.model.CallMessage;
import eu.chargetime.ocpp.model.CallResultMessage;
import eu.chargetime.ocpp.model.Message;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Calendar;

/**
 * Created by Thomas Volden on 26-Apr-16.
 */
public class JSONCommunicator extends Communicator {

    private static final int INDEX_MESSAGEID = 0;
    private static final int TYPENUMBER_CALL = 2;
    private static final int INDEX_CALL_ACTION = 2;
    private static final int INDEX_CALL_PAYLOAD = 3;
    private static final int TYPENUMBER_CALLRESULT = 3;
    private static final int INDEX_CALLRESULT_PAYLOAD = 2;
    private static final int INDEX_UNIQUEID = 1;
    private static final String CALL_FORMAT = "[2,\"%s\",\"%s\",%s]";
    private static final String CALLRESULT_FORMAT = "[3,\"%s\",%s]";

    public JSONCommunicator(Transmitter transmitter) {
        super(transmitter);
    }

    @Override
    public <T> T unpackPayload(String payload, Class<T> type) {
        T object = null;
        try {
            JSONObject json = new JSONObject(payload);
            object = parseJSON(json, type);
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }
        return object;
    }

    @Override
    public String packPayload(Object payload) {
        return new JSONObject(payload).toString();
    }

    @Override
    protected String makeCallResult(String uniqueId, String payload) {
        return String.format(CALLRESULT_FORMAT, uniqueId, payload);
    }

    @Override
    protected String makeCall(String uniqueId, String action, String payload) {
        return String.format(CALL_FORMAT, uniqueId, action, payload);
    }

    @Override
    protected Message parse(String json) {
        Message message = null;
        JSONArray array = new JSONArray(json);

        if (array.getInt(INDEX_MESSAGEID) == TYPENUMBER_CALL) {
            message = new CallMessage();
            ((CallMessage)message).setAction(array.getString(INDEX_CALL_ACTION));
            message.setPayload(array.get(INDEX_CALL_PAYLOAD).toString());
        } else if (array.getInt(INDEX_MESSAGEID) == TYPENUMBER_CALLRESULT) {
            message = new CallResultMessage();
            message.setPayload(array.get(INDEX_CALLRESULT_PAYLOAD).toString());
        }
        message.setId(array.getString(INDEX_UNIQUEID));

        return message;
    }

    private <T> T parseJSON(JSONObject json, Class<T> type) throws Exception {
        T object = type.newInstance();

        for (Method method: type.getDeclaredMethods()) {
            String key;
            if ((key = extractKey(method)) == null)
                continue; // Skip non-setter method

            if (json.has(key)) {
                method.invoke(object, parseValue(method, json, key));
            }
        }
        return object;
    }

    private Object parseValue(Method method, JSONObject json, String key) throws Exception
    {
        Class<?> type = setterParameterType(method);
        Type genericType = setterGenericParameterType(method);

        Object output;
        if      (type == String.class) {
            output = json.getString(key);
        }
        else if (type == Calendar.class) {
            output = DatatypeConverter.parseDateTime(json.getString(key));
        }
        else if (type == Integer.class || genericType == Integer.TYPE) {
            output = json.getInt(key);
        }
        else if (type == Long.class || genericType == Long.TYPE) {
            output = json.getLong(key);
        }
        else if (type == Double.class || genericType == Double.TYPE) {
            output = json.getDouble(key);
        }
        else if (type == Boolean.class || genericType == Boolean.TYPE) {
            output = json.getBoolean(key);
        }
        else {
            output = parseJSON(json.optJSONObject(key), type);
        }

        return output;
    }

    private Type setterGenericParameterType(Method method) {
        Type[] types = method.getGenericParameterTypes();
        return types.length > 0 ? types[0]: null;
    }

    private Class<?> setterParameterType(Method method) {
        Class<?>[] types = method.getParameterTypes();
        return types.length > 0 ? types[0]: null;
    }

    private String extractKey(Method method) {
        String key = null;
        if (methodIsSetter(method)) {
            key = method.getName().substring(3);
            key = key.substring(0, 1).toLowerCase() + key.substring(1);
        }
        return key;
    }

    private boolean methodIsSetter(Method method) {
        boolean isSetter;
        String methodName = method.getName();

        // Setter must take one parameter and no return type
        isSetter =  method.getParameterCount() == 1;
        isSetter &= method.getReturnType().equals(Void.TYPE);

        // Name convention must be set<ValueName>
        isSetter &= methodName.length() > 3;
        isSetter &= methodName.startsWith("set");
        isSetter &= Character.isUpperCase(methodName.charAt(3));

        return isSetter;
    }
}
