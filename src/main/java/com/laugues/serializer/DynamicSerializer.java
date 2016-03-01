package com.laugues.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by lds on 29/02/2016.
 */
public class DynamicSerializer<T> {

    private Class<T> classType;

    public DynamicSerializer(Class<T> classType) {
        this.classType = classType;
    }

    /**
     * Deserialize json into object
     * @param jsonToDeserialize the json to deserialize
     * @return
     * @throws IOException
     */
    public T deSerialize(String jsonToDeserialize) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonToDeserialize, this.classType);
    }


    /**
     *
     * @param objectToSerialize
     * @return
     * @throws IOException
     */
    public String serialize(T objectToSerialize) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectToSerialize);
    }
}
