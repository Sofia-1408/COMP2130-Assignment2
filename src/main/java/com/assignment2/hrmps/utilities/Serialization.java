package com.assignment2.hrmps.utilities;

import java.io.*;
import java.util.List;

public class Serialization {

    public static <T> void saveList(List<T> list, String filePath) throws IOException {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs(); // if the "data" folder is missing, create it
        }

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list); //Writing into the file
        }
    }

    public static <T> List<T> loadList(String filePath) throws IOException, ClassNotFoundException {
        File f = new File(filePath);
        if (!f.exists()) {
            return new java.util.ArrayList<>();
        }
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(f))) {
            return (List<T>) ois.readObject(); //Reading from the file
        }
    }
}
