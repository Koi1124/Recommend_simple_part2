package util;

import vo.StockInfo;

import java.io.File;

public interface FileHandler {

    StockInfo[] getStockInfoFromFile(File file);

}
