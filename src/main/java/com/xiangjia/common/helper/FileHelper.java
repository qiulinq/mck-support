package com.xiangjia.common.helper;


import java.io.*;

public class FileHelper {
    //校验文件夹是否存在
    public static boolean dirExists(String path) {
        File file = new File(path);
        return file.exists();
    }
    public static boolean dirExists(File file) {
        if (StringHelper.isEmpty(file))return  false;
        return file.exists();
    }

    public static void mkdirs(String path) {
        File file = new File(path);
        file.mkdirs();
    }
    public static void mkdirsNotExists(String path) {
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }
    public static void clearOnExists(String path) {
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }else{
            deleteDir(file);
        }
    }
    public static void mkParentdirs(String path) {
        File file = new File(path);
        String fileName = file.getName();
        String parentPath = path.substring(0,path.length() -fileName.length());
        FileHelper.mkdirs(parentPath);
    }
    public static void mkParentdirs(File file) {
        if(StringHelper.isEmpty(file)) return;
        String path = file.getAbsolutePath();
        String fileName = file.getName();
        String parentPath = path.substring(0,path.length() -fileName.length());
        FileHelper.mkdirs(parentPath);
    }
    public static String dealPath(String path) {
        String str = path.replace("\\\\", "/").replace("//", "/").replace("/","/").replace("\\","/");
        return str;
    }


    public static void deleteDir(File dir) {
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                File file = children[i];
                if (file.isDirectory()) {
                    deleteDir(file);
                } else {
                    String fullPath = file.getAbsolutePath();
                    file.delete();
                }
            }
        }
        // 目录此时为空，可以删除
        String fullPath = dir.getAbsolutePath();
        dir.delete();
    }
    public static void deleteDir(String path) {

        File file = new File(path);
        if (!file.exists()) return;
        if (!file.isDirectory()) return;
        deleteDir(file);
    }


    public static long copyFile(String oldPath, String newPath) throws Exception {

        try {
            long bytesum = 0l;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                fs.close();
            }
            return bytesum;
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean writeContent(String path,String content,boolean append) throws IOException {
        boolean res = true;
        FileWriter writer = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new FileWriter(file,append);
            if(append){
                content = System.getProperty("line.separator")+content;
            }
            writer.write(content);

        } catch (IOException e) {
            res = false;
            throw e;
        }finally {
            writer.flush();
            writer.close();
        }
        return res;
    }


    public static String txt2String(String path){
        String result = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result = result + "\n" +s;
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
