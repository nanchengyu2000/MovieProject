package util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

public class SpliceUpload {

    public static Boolean Collector(MultipartFile file, int count, int current,String filepath,String Outpath) throws IOException {
        InputStream inputStream = file.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] bytes = new byte[(int) file.getSize()];
        bufferedInputStream.read(bytes);
        bufferedInputStream.close();
        RandomAccessFile rw = new RandomAccessFile(filepath+current+".mp4", "rw");
        rw.write(bytes);
        rw.close();
        if (count==current+1){
            int merge = merge(count, filepath, Outpath);
            if (merge==1){


                return true;
            }
        }
        return false;
    }

    private static int merge(int coust,String path,String outpath) throws IOException {
        ArrayList<FileInputStream> fileInputStreams = new ArrayList<>();
        for (int i = 0; i < coust; i++) {
            fileInputStreams.add(new FileInputStream(path+i+".mp4"));
        }
        Iterator<FileInputStream> iterator = fileInputStreams.iterator();
        Enumeration<FileInputStream> en = new Enumeration<FileInputStream>(){
            public boolean hasMoreElements()
            {
                return iterator.hasNext();
            }
            public FileInputStream nextElement()
            {
                return iterator.next();
            }
        };
        SequenceInputStream sis = new SequenceInputStream(en);
        FileOutputStream fos = new FileOutputStream(outpath);
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = sis.read(buf)) != -1)
        {
            fos.write(buf, 0, len);
        }

        fos.close();
        sis.close();
        return 1;
    }
}
