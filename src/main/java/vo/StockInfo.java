package vo;

public class StockInfo {
    private String INFO;
    private String[] info;
    public StockInfo(String s) {
        INFO=s;
        info=s.split("\t");
    }

    public String getResult() { return "ID为："+info[0]+"\t"+"TITLE为："+info[1]+"\t"+"ANSWER为："+info[7]+"\t"+"CONTENT为："+info[5]; }
    public String getALLINFO() { return info[1]+info[7]+info[5]; }

}
