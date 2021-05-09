package wsb.database;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObjectToSql {

    private static final String INSERT_QUERY = "INSERT INTO %s (%s) VALUES (%s);";
    private static final String DELIMITER = ", ";

    public String insert(Object objectToInsert) {
        String className = objectToInsert.getClass().getSimpleName().toLowerCase();

        return String.format(INSERT_QUERY,
                className,
                getColumns(objectToInsert),
                getValues(objectToInsert));
    }

    private String getColumns(Object object) {
        return getStreamOfAllClassFields(object)
                .map(Field::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    private String getValues(Object object) {
        return getStreamOfAllClassFields(object)
                .map(field -> {
                    try {
                        field.setAccessible(true);
                        return field.get(object);
                    } catch (IllegalAccessException e) {
                        System.out.println("Error getting value for field: " + field.getName() + " " + e.getMessage());
                        return "null";
                    }
                })
                .map(v -> "'" + v + "'")
                .collect(Collectors.joining(DELIMITER));
    }

    private Stream<Field> getStreamOfAllClassFields(Object obj) {
        Stream<Field> fields = Stream.of(obj.getClass().getDeclaredFields());

        Class<?> clazz = obj.getClass().getSuperclass();
        while (clazz!= null) {
            fields = Stream.concat(fields, Stream.of(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }
}
