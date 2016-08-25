package moe.haruue.goodhabits.util;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class FileUtils {
    public static String readStringFromFile(File src) {
        Reader reader = null;
        try {
            reader = new FileReader(src);
            char[] flush = new char[10];
            int len;
            StringBuilder sb = new StringBuilder();
            while (-1 != (len = reader.read(flush))) {
                sb.append(flush, 0, len);
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            Log.e("FileUtils", "readStringFromFile", e);
            return null;
        } catch (IOException e) {
            Log.e("FileUtils", "readStringFromFile", e);
            return null;
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("FileUtils", "readStringFromFile", e);
                }
            }
        }
    }

    public static void writeStringToFile(String text, File dest) {
        Writer wr = null;
        try {
            wr = new FileWriter(dest);
            wr.write(text);
            wr.flush();
        } catch (IOException e) {
            Log.e("FileUtils", "writeStringToFile", e);
        } finally {
            if (null != wr) {
                try {
                    wr.close();
                } catch (IOException e) {
                    Log.e("FileUtils", "writeStringToFile", e);
                }
            }
        }
    }

}

