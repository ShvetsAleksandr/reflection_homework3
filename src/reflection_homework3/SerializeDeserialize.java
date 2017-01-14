package reflection_homework3;

import java.io.*;
import java.lang.reflect.Field;

public class SerializeDeserialize {

    public static void serialize(String path, Object object) throws IllegalAccessException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Class<?> cl = object.getClass();
        Field[] fields = cl.getDeclaredFields();
        for(Field field : fields){
            if(field.isAnnotationPresent(Save.class)) {
                field.setAccessible(true);
                stringBuilder.append(field.getName() + ":");
                if (field.getType() == String.class) {
                    stringBuilder.append((String)field.get(object));
                } else if (field.getType() == int.class) {
                    stringBuilder.append((field.getInt(object)));
                } else if (field.getType() == boolean.class) {
                    stringBuilder.append(field.getBoolean(object));
                }
                stringBuilder.append(";");
            }
        }
        String s = stringBuilder.toString();
        writeFile(path, s);
    }

    public static void writeFile(String path, String s) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write(s);
        fw.flush();
        fw.close();
    }

    public static String readFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String s = br.readLine();
        br.close();
        return s;
    }

    public static <T> T deserialize(String path, Class<T> cls) throws Exception {
        String s = readFile(path);
        Object obj =(T)cls.newInstance();
        String[] strings = s.split(";");
        for(String string : strings){
            String[] st = string.split(":");

            String name = st[0];
            String value = st[1];
            Field field = cls.getDeclaredField(name);
                field.setAccessible(true);
            if(field.isAnnotationPresent(Save.class)){
                if(field.getType() == int.class){
                    field.setInt(obj, Integer.parseInt(value));
                }else if(field.getType() == String.class){
                    field.set(obj, value);
                }else if(field.getType() == boolean.class){
                    field.setBoolean(obj, Boolean.parseBoolean(value));
                }
            }
        }
        return (T)obj;
    }
}
