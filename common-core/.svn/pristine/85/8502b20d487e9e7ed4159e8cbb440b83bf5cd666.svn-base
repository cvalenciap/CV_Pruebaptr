package pe.com.sedapal.common.core.utils;

import java.util.ArrayList;
import java.util.Collection;

public class ArrayUtils {
	
	private ArrayUtils() {
	    throw new IllegalStateException("Utility class");
	  }


    public static boolean isEmpty(Collection<?> list) {
        return list!=null ? list.isEmpty() : true;
    }

    public static boolean isNotEmpty(Collection<?> list) {
        return list!=null ? !list.isEmpty() : false;
    }


    public static Collection subtract(Collection firstCollection, Collection secondCollection) {
        Collection collection = new ArrayList<>();

        for (Object object : firstCollection) {
            if (!secondCollection.contains(object)) {
                collection.add(object);
            }
        }

        return collection;
    }
}