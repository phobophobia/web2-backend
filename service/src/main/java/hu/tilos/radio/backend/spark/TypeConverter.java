package hu.tilos.radio.backend.spark;

import hu.tilos.radio.backend.author.AuthorDetailed;
import hu.tilos.radio.backend.author.AuthorSimple;
import hu.tilos.radio.backend.contribution.Contribution;
import hu.tilos.radio.backend.contribution.ShowContribution;
import hu.tilos.radio.backend.contribution.ShowReference;
import hu.tilos.radio.backend.data.types.ContributionData;
import hu.tilos.radio.backend.data.types.SchedulingSimple;
import hu.tilos.radio.backend.data.types.ShowStats;
import hu.tilos.radio.backend.data.types.UrlData;
import hu.tilos.radio.backend.mix.MixSimple;
import hu.tilos.radio.backend.show.ShowDetailed;
import hu.tilos.radio.backend.show.ShowSimple;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by elek on 10/2/15.
 */
public class TypeConverter {
    public static void main(String[] args) {
        new TypeConverter().run();
    }

    private void run() {
        System.out.println("definitions:");
        generate(ShowSimple.class);
        generate(ShowReference.class);
        generate(ShowDetailed.class);
        generate(UrlData.class);
        generate(ContributionData.class);
        generate(Contribution.class);
        generate(AuthorSimple.class);
        generate(AuthorDetailed.class);
        generate(MixSimple.class);
        generate(ShowStats.class);
        generate(ShowContribution.class);
        generate(SchedulingSimple.class);
    }

    private void generate(Class<?> dataClass) {

        System.out.println("  " + dataClass.getSimpleName() + ":");
        System.out.println("    properties:");
        for (Field field : dataClass.getDeclaredFields()) {
            printField(field);
        }
    }

    private void printField(Field field) {
        System.out.println("      " + field.getName() + ":");
        Class<?> type = field.getType();
        if (field.getGenericType() instanceof ParameterizedType) {
            printType("        ", type, (ParameterizedType) field.getGenericType());
        } else {
            printType("        ", type, null);
        }
    }

    private void printType(String prefix, Class type, ParameterizedType genericType) {
        if (type.equals(String.class)) {
            System.out.println("        type: string");
        } else if (type.equals(int.class)) {
            System.out.println("        type: integer");
            System.out.println("        format: int32");
        } else if (type.equals(Date.class)) {
            System.out.println("        type: integer");
            System.out.println("        format: epoch");
        } else if (type.equals(boolean.class)) {
            System.out.println("        type: boolean");
        } else if (type.isEnum()) {
            System.out.println("        type: string");
            System.out.println("        enum:");
            for (Object o : type.getEnumConstants()) {
                System.out.println("          - " + o);
            }
        } else if (type.equals(List.class) || type.equals(Set.class)) {
            System.out.println(prefix + "type: array");
            System.out.println(prefix + "items:");
            printType(prefix + "  ", (Class) genericType.getActualTypeArguments()[0], null);
        } else if (!type.isPrimitive()) {
            System.out.println(prefix + "type: object");
            System.out.println(prefix + "$ref: \"#/definitions/" + type.getSimpleName() + "\"");
        }
    }

}
