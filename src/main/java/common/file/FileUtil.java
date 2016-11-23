package common.file;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Administrator
 *
 * 1.windows中的换行符是\r\n
 * 2.linux/unix下的换行符是\n
 *
 */
public class FileUtil {

    /**
     * 创建文件
     */
    public static void createFile(String path) {
        File file = new File(path);
        if (!file.exists()) {// 判断文件是否存在
            try {
                file.createNewFile(); // 创建文件

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        /* 获取文件名 */
        String name = file.getName();
        /* 获取文件路径 */
        String filePath = file.getPath();
        /* 获取绝对路径名 */
        String absPath = file.getAbsolutePath();
        /* 获取父亲文件路径 */
        String parent = file.getParent();
        /* 文件大小 */
        long size = file.length();
        /* 最后一次修改时间 */
        long time = file.lastModified();
        System.out.println("文件名:" + name);
        System.out.println("文件路径:" + filePath);
        System.out.println("文件的绝对路径:" + absPath);
        System.out.println("文件的父文件路径:" + parent);
        System.out.println("文件的大小:" + size);
        System.out.println("文件最后一次修改时间:" + time);
        // file.delete(); //删除文件

    }

    /**
     * 创建文件夹
     */
    public static void createDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs(); // 创建文件夹
        }
    }

    /**
     * 判断空文件夹
     */
    public static boolean isEmptyDir(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            String[] files = file.list();
            if (files.length == 0) {
                // file.delete(); 若文件夹为空,则删除文件夹
                return true;
            }
        }
        return false;
    }

    /**
     * 遍历文件夹中的文件并显示
     */
    public static void fileTest(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        // System.out.println(files.length);

        for (File f : files) {
            if (f.isFile()) {
                System.out.println(f.getName() + "是文件!");
            } else if (f.isDirectory()) {
                fileTest(f.getPath());
            }
        }

    }

    /**
     * 移动文件或重命名
     */
    public static void reFileName(String fromPath, String toPath) {
        File file1 = new File(fromPath);
        File file2 = new File(toPath);
        /* 判断file2文件夹路径存在与否,不存在则创建 */
        if (!file2.exists()) {
            new File(file2.getParent()).mkdirs();
        }
        file1.renameTo(file2); // 修改文件名
    }
}
