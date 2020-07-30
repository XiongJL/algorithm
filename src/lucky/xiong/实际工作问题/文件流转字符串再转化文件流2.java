package lucky.xiong.实际工作问题;

import java.io.*;
import java.util.Scanner;

/**
 * @author XiongJl
 * @date 2020/7/29 14:35
 */
public class 文件流转字符串再转化文件流2 {


    public static void main(String[] args) throws IOException {
        Scanner filePath = new Scanner(System.in);
        System.out.println("输入要转换的文件绝对路径:  例如： D:\\A\\B");
        String filePaths = filePath.nextLine();
        Scanner type = new Scanner(System.in);
        System.out.println("请输入转换方式:  str: ascii -> str ; ascii :str->ascii");
        String ty = type.nextLine();
        StringBuffer buffer = new StringBuffer();
        BufferedReader bf= new BufferedReader(new FileReader(filePaths));



        String str ;
        if ("str".equals(ty)){
            String s = null;
            while((s = bf.readLine())!=null){
                //使用readLine方法，一次读一行
                buffer.append(s);
            }
            String xml = buffer.toString();
            str= Ascii10ToString(xml);
            craeteFile(ty, str);

        }else if ("ascii".equals(ty)){
            int num = 0;
            while ((num = bf.read())!=-1){
                buffer.append(num);
                buffer.append(" ");
            }
            craeteFile(ty, buffer.toString());
        }

    }

    private static void craeteFile(String ty, String str) {
        String path = System.currentTimeMillis()+".txt";
        File file = new File(ty+path);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(str);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static String stringToAscii(String value)
//    {
//        StringBuffer sbu = new StringBuffer();
//        char[] chars = value.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            if(i != chars.length - 1)
//            {
//                sbu.append((int)chars[i]).append(" ");
//            }
//            else {
//                sbu.append((int)chars[i]);
//            }
//        }
//        return sbu.toString();
//    }

    public static String Ascii10ToString(String value)
    {
        StringBuffer sbu = new StringBuffer();
        String[] strs = value.split(" ");
        for (String str : strs){
            int code = Integer.parseInt(str,10);
            char result = (char)code;
            sbu.append(result);
        }
        return sbu.toString();
    }


}




