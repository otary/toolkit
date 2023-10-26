package cn.chenzw.toolkit.core.enums;

/**
 * 文件类型
 *
 * @author chenzw
 */
public enum FileType {
    A7Z(6, "377ABCAF271C", ".7z", "", "7Z", ""),
    AI(4, "25504446", ".ai", "Adobe Illustrator", "Adobe Illustrator", ""),
    ADOBE_ACROBAT(7, "255044462D312E", ".pdf", "", "ADOBE_ACROBAT", ""),
    ASF(8, "3026B2758E66CF11", ".asf", "Windows Media", "ASF", ""),
    AVI(4, "41564920", ".avi", "", "AVI", ""),
    AVI_2(4, "52494646", ".avi", "", "AVI", ""),

    BAT(10, "406563686F206F66660D", ".bat", "", "BAT", ""),
    BITMAP(2, "424D", ".bmp", "", "BITMAP", ""),

    CAB(4, "4D534346", ".cab", "", "CAB", "CAB Installer file"),
    CAD(4, "41433130", ".dwg", "", "CAD", ""),
    CLASS(4, "CAFEBABE", ".class", "", "CLASS", ""),
    DOCX(10, "504B0304140006000800", ".docx", "", "DOCX", ""),
    DLL(2, "4D5A", ".dll", "", "DLL", "Dynamic Library"),
    DOC(8, "D0CF11E0A1B11AE1", ".doc", "", "DOC", ""),

    EXE(2, "4D5A", ".exe", "", "EXE", "Executable file"),
    EMAIL(14, "44656C69766572792D646174653A", ".eml", "", "EMAIL", ""),
    ESP(20, "252150532D41646F62652D332E3020455053462D", ".esp", "", "ESP", "EPS File"),

    FLV(3, "464C56", ".flv", "", "FLV", ""),

    GIF(4, "47494638", ".gif", "", "GIF", ""),
    GZIP(3, "1F8B08", ".gz", "", "GZip", ""),

    HLP(4, "3F5F0300", ".hlp", "", "HLP", "Help file"),
    ICO(4, "00000100", ".ico", "", "ICO", ""),

    JPEG(2, "FFD8", ".jpg", "", "JPEG", ""),
    JP2(10, "0000000C6A5020200D0A", ".jp2", "", "", ""),
    JAR(5, "504B03040A", ".jar", "", "JAR", ""),


    MS_ACCESS(10, "5374616E64617264204A", ".mdb", "", "ACCESS", ""),
    MPEG(4, "000001BA", ".mpg", "", "MPEG", ""),
    MPEG_2(4, "000001B3", ".mpg", "", "MPEG", ""),
    MOV(4, "6D6F6F76", ".mov", "Quicktime", "MOV", ""),
    MID(4, "4D546864", ".mid", "", "MID", ""),
    MP3(5, "4944330300", ".mp3", "", "MP3", ""),
    MP3_2(5, "FFF328C400", ".mp3", "", "MP3", ""),
    MP4(12, "00000018667479706D703432", ".mp4", "", "MP4", ""),
    MSI(8, "D0CF11E0A1B11AE1", ".msi", "", "MSI", "Microsoft Installer\t"),
    MDB(12, "5374616E64617264204A6574", ".mdb", "", "MDB", "Microsoft Database"),

    OUTLOOK_EXPRESS(8, "CFAD12FEC5FD746F", ".dbx", "", "OUTLOOK_EXPRESS", ""),
    OUTLOOK(4, "21424442", ".pst", "", "OUTLOOK", "Outlook Post Office file"),
    OBJ(2, "4C01", ".obj", "", "OBJ", "Object Code File"),
    OUTLOOK_MESSAGE(8, "D0CF11E0A1B11AE1", ".msg", "", "MSG", ""),


    PNG(4, "89504E47", ".png", "", "PNG", ""),
    PSD(4, "38425053", ".psd", "Photoshop", "PSD文件", ""),
    PDF(4, "25504446", ".pdf", "", "PDF", ""),
    PPT(8, "D0CF11E0A1B11AE1", ".ppt", "", "PPT", ""),
    PPTX(4, "504B0304", ".pptx", "", "PPT 2010", "PPT 2010"),
    QUICKEN(4, "AC9EBD8F", ".qdf", "", "QUICKEN", ""),

    RAR(7, "526172211A0700", ".rar", "", "RAR", ""),
    REAL_AUDIO(4, "2E7261FD", ".ram", "", "REAL_AUDIO", ""),
    REAL_MEDIA(4, "2E524D46", ".rm", "", "REAL_MEDIA", ""),
    RTF(6, "7B5C72746631", ".rtf", "", "RTF", "Rich Text Format"),

    SWF(3, "465753", ".swf", "", "SWF", "Flash Shockwave"),
    SYS(2, "4D5A", ".sys", "", "SYS", "SYS file"),
    SDF(2, "789C", ".sdf", "", "SDF", "SDF file"),
    SQL_LITE(5, "53514C6974", ".db", "", "SqlLite", "SQL Lite数据库文件"),

    // 测试 4949
    TIF(4, "49492A00", ".tif", "", "TIFF", ""),
    TAR(5, "7573746172", ".tar", "", "TAR", ""),

    VMDK(4, "4B444D56", ".wmdk", "", "VMDK", "VMWare Disk file"),
    VISIO(8, "D0CF11E0A1B11AE1", ".vsd", "", "", ""),

    WORD_PREFECT(4, "FF575043", ".wpd", "", "WORD_PREFECT", ""),
    WINDDOWS_PASSWORD(4, "E3828596", ".pwl", "", "WINDDOWS_PASSWORD", ""),
    WAVE(4, "52494646", ".wav", "", "WAVE", "WAV audio file"),
    WAVE_2(4, "57415645", ".wav", "", "WAVE", ""),
    WMF(4, "D7CDC69A", ".wmf", "", "WMF", "Windows Meta File"),
    WMV(7, "3026B2758E66CF", ".wmv", "", "WMV", ""),
    WMA(7, "3026B2758E66CF", ".wma", "", "WMA", ""),

    XML(5, "3C3F786D6C", ".xml", "", "XML", ""),
    XLS(8, "D0CF11E0A1B11AE1", ".xls", "", "EXCEL", ""),
    XLSX(4, "504B0304", ".xlsx", "", "EXCEL 2010", "Excel 2010"),

    ZLIB(2, "789C", ".zlib", "", "Zlib", ""),
    ZIP(4, "504B0304", ".zip", "", "ZIP", "");


    private int headBytes;
    private String signatureCode;
    private String suffix;
    private String remark;
    private String name;
    private String program;


    FileType(int headBytes, String signatureCode, String suffix, String program, String name, String remark) {
        this.headBytes = headBytes;
        this.signatureCode = signatureCode;
        this.suffix = suffix;
        this.program = program;
        this.name = name;
        this.program = program;
        this.remark = remark;
    }

    public int headBytes() {
        return headBytes;
    }

    public String signatureCode() {
        return signatureCode;
    }

    public String suffix() {
        return suffix;
    }

    public String remark() {
        return remark;
    }

    public String name2() {
        return name;
    }

    public String program() {
        return program;
    }

}
