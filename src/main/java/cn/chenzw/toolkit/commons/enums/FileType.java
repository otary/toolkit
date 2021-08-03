package cn.chenzw.toolkit.commons.enums;

/**
 * 文件类型
 *
 * @author chenzw
 */
public enum FileType {

    JPEG(4, "FFD8FFE1", ".jpg", ""),
    PNG(4, "89504E47", ".png", ""),
    XML(5, "3C3F786D6C", ".xml", ""),
    GIF(4, "47494638", ".gif", ""),
    TIFF(4, "49492A00", ".tif", ""),
    BITMAP(2, "424D", ".bmp", ""),
    CAD(4, "41433130", ".dwg", ""),
    PSD(4, "38425053", ".psd", ""),
    RTF(5, "7B5C727466", ".rtf", "Rich Text Format"),
    HTML(5, "68746D6C3E", ".html", ""),
    EMAIL(14, "44656C69766572792D646174653A", ".eml", ""),
    OUTLOOK_EXPRESS(8, "CFAD12FEC5FD746F", ".dbx", ""),
    OUTLOOK(4, "2142444E", ".pst", ""),
    MS_ACCESS(10, "5374616E64617264204A", ".mdb", ""),
    WORD_PREFECT(4, "FF575043", ".wpd", ""),
    ADOBE_ACROBAT(7, "255044462D312E", ".pdf", ""),
    QUICKEN(4, "AC9EBD8F", ".qdf", ""),
    WINDDOWS_PASSWORD(4, "E3828596", ".pwl", ""),
    ZIP(4, "504B0304", ".zip", ""),
    RAR(4, "52617221", ".rar", ""),
    WAVE(4, "57415645", "wav", ""),
    AVI(4, "41564920", ".avi", ""),
    REAL_AUDIO(4, "2E7261FD", ".ram", ""),
    REAL_MEDIA(4, "2E524D46", ".rm", ""),
    MPEG(4, "000001BA", ".mpg", ""),
    MPEG_2(4, "000001B3", ".mpg", ""),
    MOV(4, "6D6F6F76", ".mov", "Quicktime"),
    ASF(8, "3026B2758E66CF11", ".asf", "Windows Media"),
    MID(4, "4D546864", ".mid", "MIDI"),
    JAR(5, "504B03040A", ".jar", ""),
    JAVA(10, "7061636B616765207765", ".java", ""),
    BAT(10, "406563686F206F66660D", ".bat", ""),
    CLASS(10, "CAFEBABE0000002E0041", ".class", ""),
    DOCX(10, "504B0304140006000800", ".docx", "");


    private int headBytes;
    private String signatureCode;
    private String suffix;
    private String remark;

    FileType(int headBytes, String signatureCode, String suffix, String remark) {
        this.headBytes = headBytes;
        this.signatureCode = signatureCode;
        this.suffix = suffix;
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

}
