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
    public <T> T unpack(String message, Class<T> type) {
        JSONArray array = new JSONArray(message);

        T object = null;
        try {
            JSONObject json = array.optJSONObject(2);
            object = parseJSON(json, type);
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }
        return object;
    }

    @Override
    public String pack(Object payload) {
        return null;
    }

    private <T> T parseJSON(JSONObject json, Class<T> type) throws Exception {
        T object = type.newInstance();

        for (Method method: type.getDeclaredMethods()) {
            String key;
            if ((key = extractKey(method)) == null)
                continue; // Skip non-setter method

            method.invoke(object, parseValue(method, json, key));
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

        // Setter must take one parameter and no return type
        if (method.getParameterCount() != 1 || !method.getReturnType().equals(Void.TYPE))
            return null;

        String methodName = method.getName();
        if (methodName.length() <= 2)
            return null;

        String key;
        if (methodName.length() > 3 && methodName.startsWith("set") && Character.isUpperCase(methodName.charAt(3))) {
            key = methodName.substring(3);
        }
        else if (methodName.startsWith("is") && Character.isUpperCase(methodName.charAt(2))) {
            key = methodName.substring(2);
        }
        else {
            return null;
        }

        key = key.substring(0,1).toLowerCase() + key.substring(1);

        return key;
    }
}
