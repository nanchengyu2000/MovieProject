package util;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;
import java.io.IOException;

public class FFmpegProcessor{

    public static void convertMediaToM3u8ByHttp(String srouce, String m3u8Url) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        String commint="ffmpeg -i "+srouce+" -threads 2000 -codec: copy -start_number 0 -hls_time 10 -hls_list_size 0 -f hls "+m3u8Url;
        runtime.exec(commint).isAlive();
    }
}
