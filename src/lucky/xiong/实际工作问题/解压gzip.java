package lucky.xiong.实际工作问题;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * @author XiongJl
 * @date 2020/11/27 11:25
 */
public class 解压gzip {

    public static void main(String[] args) {
        String zipPath = "D:\\workplace\\algorithm\\1606445948842goodFile";
        String unZipDir = "D:\\workplace\\algorithm\\gzip.tar";
        GZIPInputStream gzs = null;
        BufferedOutputStream bos = null;
        try {
            gzs = new GZIPInputStream(new FileInputStream(zipPath));
            bos = new BufferedOutputStream(new FileOutputStream(unZipDir));

            byte[] buf = new byte[102400];
            int len = -1;
            while ((len = gzs.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            bos.flush();
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            close(gzs, bos);
        }
    }
    public static void close(Closeable ...io){
        for (Closeable temp : io) {
            try {
                if(temp != null){
                    temp.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}

