package util;

import vo.StockInfo;

import java.io.*;
import java.util.ArrayList;

public class FileHandlerImpl implements FileHandler {

    public StockInfo[] getStockInfoFromFile(File file) {
        StockInfo[] all = null;
        if (file.isFile()&&file.exists()) {
            try {
                String s = null;
                ArrayList<StockInfo> list = new ArrayList<StockInfo>();
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(read);
                while ((s=bufferedReader.readLine())!=null) {
                    StockInfo info = new StockInfo(s);
                    list.add(info);
                }
                int size = list.size();
                all = new StockInfo[size];
                list.toArray(all);
                read.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return all;
        }
        else return null;
    }
}
