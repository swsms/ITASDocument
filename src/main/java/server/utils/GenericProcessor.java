package server.utils;

import server.entities.ObjectEntity;

import java.util.ArrayList;
import java.util.List;

public class GenericProcessor {

    public static <T extends ObjectEntity> List<T> makeGeneric(List objects, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (Object obj : objects) {
            ObjectEntity entity = (ObjectEntity) obj;
            T customObject = makeGeneric(entity, clazz);
            list.add(customObject);
        }
        return list;
    }

    public static <T extends ObjectEntity> T makeGeneric(Object object, Class<T> clazz) {
        return clazz.cast(object);
    }
}
