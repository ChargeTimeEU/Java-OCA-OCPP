package eu.chargetime.ocpp;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Calendar;
import java.util.Collection;

/**
 * Created by Thomas Volden on 26-Apr-16.
 */
public class JSONCommunicator implements Communicator
{
    @Override
    public <T> T unpack(String payload, Class<T> type) {
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
    public String pack(Object payload) {
        return new JSONObject(payload).toString();
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
        else if (type == Collection.class) {
            output = parseArray(type, json.getJSONArray(key));
        }
        else {
            output = parseJSON(json.optJSONObject(key), type);
        }

        return output;
    }

    private <T> T parseArray(Class<T> type, JSONArray jsonArray) throws Exception
    {
        T output = type.newInstance();
        TypeVariable<Class<T>> typeParameter = type.getTypeParameters()[0];
        Method addMethod = type.getMethod("add");

        for(int i = 0; i < jsonArray.length(); i++) {
            addMethod.invoke(output, parseJSON(jsonArray.getJSONObject(i), typeParameter.getGenericDeclaration()));
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
