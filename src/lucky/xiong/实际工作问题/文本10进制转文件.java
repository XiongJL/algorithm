package lucky.xiong.实际工作问题;

import java.io.*;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

/**
 * @author XiongJl
 * @date 2020/11/26 16:32
 */
public class 文本10进制转文件 {
    public static void main(String[] args) throws IOException {

        Scanner filePath = new Scanner(System.in);
        System.out.println("输入要转换的文件绝对路径:  例如： D:\\A\\B");
        String filePaths = filePath.nextLine();

        StringBuffer buffer = new StringBuffer();
        BufferedReader bf = new BufferedReader(new FileReader(filePaths));

        String fileName = System.currentTimeMillis()+"goodFile";
        DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));

        String s;
        while((s = bf.readLine())!=null){
            //使用readLine方法，一次读一行
            buffer.append(s);
        }
        String xml = buffer.toString();
        String[] longXml = xml.split(" ");
        for (String str : longXml ){
            int ten = Integer.valueOf(str);
            out.write(ten);
        }
        out.flush();
        out.close();


        // 解压文件
        String zipPath = System.getProperty("user.dir")+"\\"+fileName;
        String unZipDir = fileName+".tar";
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(gzs, bos);
        }

        // 删除源文件
        File file = new File(fileName);
        file.delete();

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
