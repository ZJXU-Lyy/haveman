package haveman.HaveMan;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName FileAdapter
 * @Description: TODO
 * @Author Lenovo
 * @Date 2020/5/16 0:31
 */
public class FileAdapter {

    public class Request {
        public static final String GET = "GET";

        public static final String POST = "POST";
    }

    private URL url;

    private String encoding;

    private String filename;

    private String downloadDir;

    private Integer timeOut;

    private String requestMethod;

    /**
     * 本地读取使用该构造方法
     */
    public FileAdapter() {

    }

    /**
     * 网络资源使用以下构造方法
     *
     * @param url
     */
    public FileAdapter(URL url) {
        this.url = url;
    }

    public FileAdapter(String UrlPath) {
        try {
            URL url = new URL(UrlPath);
            this.url = url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public List<Byte> asBytes() {
        InputFileMakeTree.ReadFileByBytes readFileByBytes = new InputFileMakeTree.ReadFileByBytes() {
            @Override
            public void readFileByBytes() {
                try {
                    if (filename == null || filename.isEmpty())
                        throw new Exception("Text path is indispensable");
                    File file = new File(filename);
                    if (file.isFile() && file.exists()) {
                        InputStream inputStream = new FileInputStream(file);
                        byte[] tempByte = new byte[1000];
                        inputStream.read(tempByte);
                        inputStream.close();
                        auto(tempByte);
                    } else {
                        throw new Exception("Text does not exist");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        return readFileByBytes.getBytes();
    }

    public List<Character> asCharacters() {
        InputFileMakeTree.ReadFileByChars readFileByChars = new InputFileMakeTree.ReadFileByChars() {
            @Override
            public void readFileByChars() {
                try {
                    if (filename == null || filename.isEmpty())
                        throw new Exception("Text path is indispensable");
                    File file = new File(filename);
                    if (file.isFile() && file.exists()) {
                        InputStreamReader inputStreamReader = null;
                        if (encoding == null || encoding.isEmpty())
                            inputStreamReader = new InputStreamReader(new FileInputStream(file));
                        else
                            inputStreamReader = new InputStreamReader(new FileInputStream(file),
                                    encoding);
                        int tempchar;
                        while ((tempchar = inputStreamReader.read()) != -1) {
                            /* 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                            但如果这两个字符分开显示时，会换两次行。
                            因此，屏蔽掉\r，或者屏蔽\n，否则，将会多出很多空行。*/
                            if (((char) tempchar) != '\r') {
                                auto((char) tempchar);
                            }
                        }
                        inputStreamReader.close();
                    } else {
                        throw new Exception("Text does not exist");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        return readFileByChars.getCharacters();
    }

    public List<String> asStrings() {
        InputFileMakeTree.ReadFileByString readFileByString = new InputFileMakeTree.ReadFileByString() {
            @Override
            public void readFileByString() {
                try {
                    if (filename == null || filename.isEmpty())
                        throw new Exception("Text path is indispensable");
                    File file = new File(filename);
                    if (file.isFile() && file.exists()) {
                        InputStreamReader inputStreamReader = null;
                        if (encoding == null || encoding.isEmpty())
                            inputStreamReader = new InputStreamReader(new FileInputStream(file));
                        else
                            inputStreamReader = new InputStreamReader(new FileInputStream(file),
                                    encoding);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String s = bufferedReader.readLine();
                        while (s != null) {
                            auto(s);
                            s = bufferedReader.readLine();
                        }
                        bufferedReader.close();
                        inputStreamReader.close();
                    } else {
                        throw new Exception("Text does not exist");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        return readFileByString.getString();
    }

    public FileAdapter Download(String path) {
        File file = null;
        try {
            if (url == null)
                throw new Exception("Unified resource positioning exception");
            if (encoding == null || encoding.isEmpty())
                throw new Exception("No encoding set");
            URLConnection connection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setConnectTimeout(Objects.requireNonNullElse(timeOut, 5000));
            httpURLConnection.setRequestMethod(Objects.requireNonNullElse(requestMethod, Request.GET));
            httpURLConnection.setRequestProperty("Charset", Objects.requireNonNullElse(encoding, "UTF-8"));
            httpURLConnection.connect();
            // 文件大小
            int fileLength = httpURLConnection.getContentLength();
            // 控制台打印文件大小
            System.out.println("您要下载的文件大小为:" + fileLength / (1024 * 1024) + "MB");
            // 建立链接从请求中获取数据
            URLConnection con = url.openConnection();
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            file = new File(downloadDir);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[2048];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
                // 控制台打印文件下载的百分比情况
                System.out.println("下载了-------> " + len * 100 / fileLength + "%\n");
            }
            bin.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public FileAdapter RequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public FileAdapter Timeout(Integer timeOut) {
        this.timeOut = timeOut;
        return this;
    }

    public FileAdapter downloadDir(String downloadDir) {
        this.downloadDir = downloadDir;
        return this;
    }

    public FileAdapter encoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    public FileAdapter fileName(String filename) {
        this.filename = filename;
        return this;
    }

}
