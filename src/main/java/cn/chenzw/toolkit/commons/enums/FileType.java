package cn.chenzw.toolkit.commons.enums;

/**
 * 文件类型
 *
 * @author chenzw
 */
public enum FileType {

    JPEG(4, "FFD8FFE1", ".jpg", "", "JPEG", ""),
    PNG(4, "89504E47", ".png", "", "PNG", ""),
    XML(5, "3C3F786D6C", ".xml", "", "XML", ""),
    GIF(4, "47494638", ".gif", "", "GIF", ""),
    TIFF(4, "49492A00", ".tif", "", "TIFF", ""),
    BITMAP(2, "424D", ".bmp", "", "BITMAP", ""),
    CAD(4, "41433130", ".dwg", "", "CAD", ""),
    PSD(4, "38425053", ".psd", "","PSD文件", ""),
    RTF(5, "7B5C727466", ".rtf", "","RTF","Rich Text Format"),
    HTML(5, "68746D6C3E", ".html", "", "HTML", ""),
    EMAIL(14, "44656C69766572792D646174653A", ".eml", "", "EMAIL", ""),
    OUTLOOK_EXPRESS(8, "CFAD12FEC5FD746F", ".dbx", "", "OUTLOOK_EXPRESS", ""),
    OUTLOOK(4, "2142444E", ".pst", "", "OUTLOOK", ""),
    MS_ACCESS(10, "5374616E64617264204A", ".mdb", "", "ACCESS", ""),
    WORD_PREFECT(4, "FF575043", ".wpd", "", "WORD_PREFECT", ""),
    ADOBE_ACROBAT(7, "255044462D312E", ".pdf", "", "ADOBE_ACROBAT", ""),
    QUICKEN(4, "AC9EBD8F", ".qdf", "", "QUICKEN", ""),
    WINDDOWS_PASSWORD(4, "E3828596", ".pwl", "", "WINDDOWS_PASSWORD", ""),
    ZIP(4, "504B0304", ".zip", "", "ZIP", ""),
    RAR(4, "52617221", ".rar", "", "RAR", ""),
    WAVE(4, "57415645", "wav", "", "WAVE", ""),
    AVI(4, "41564920", ".avi", "", "AVI", ""),
    REAL_AUDIO(4, "2E7261FD", ".ram", "", "REAL_AUDIO", ""),
    REAL_MEDIA(4, "2E524D46", ".rm", "", "REAL_MEDIA", ""),
    MPEG(4, "000001BA", ".mpg", "", "MPEG", ""),
    MPEG_2(4, "000001B3", ".mpg", "", "MPEG", ""),
    MOV(4, "6D6F6F76", ".mov", "Quicktime", "MOV", ""),
    ASF(8, "3026B2758E66CF11", ".asf", "Windows Media", "ASF", ""),
    MID(4, "4D546864", ".mid", "", "MID", ""),
    JAR(5, "504B03040A", ".jar", "", "JAR", ""),
    JAVA(10, "7061636B616765207765", ".java", "", "", ""),
    BAT(10, "406563686F206F66660D", ".bat", "", "BAT", ""),
    CLASS(10, "CAFEBABE0000002E0041", ".class", "", "CLASS", ""),
    DOCX(10, "504B0304140006000800", ".docx", "", "DOCX", "");


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
