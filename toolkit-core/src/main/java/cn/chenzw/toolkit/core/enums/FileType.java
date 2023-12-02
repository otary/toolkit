package cn.chenzw.toolkit.core.enums;

/**
 * 文件类型
 *
 * @author chenzw
 */
public enum FileType {
    A7Z(6, "377ABCAF271C", ".7z", "", "", "7Z", "", "7-Zip压缩文件", "该格式以主要的压缩算法LZMA为基础。其最初用于7-Zip文件。该格式向开发人员开放架构以来，其他压缩算法可用于7z格式。该格式具有高压缩系数。加密算法是AES-256。"),
    AI(4, "25504446", ".ai", "", "Adobe Illustrator", "AI", "Adobe Illustrator", "", "AI文件是Adobe Illustrator保存的默认文件，与Photoshop的.PSD文件相似，都是分层文件，用户可以对图形内所存在的层进行操作，所不同的是AI文件是基于矢量输出，可在任何尺寸大小下按最高分辨率输出，而PSD文件是基于位图输出。"),
    // ADOBE_ACROBAT(7, "255044462D312E", ".pdf", "Adobe Reader", "ADOBE_ACROBAT", "", "", "PDF是一种由Adobe Systems使用某种语言功能PostScript设计的电子文件格式。是以Adobe Reader这种格式查看文档的官方应用程序。大多数情况下，PDF文件是文本与光栅和矢量图形和文本格式、用JavaScript编写的脚本以及其他类型项目的组合。"),
    ASF(8, "3026B2758E66CF11", ".asf", "", "Windows Media", "ASF", "Advanced Systems Format", "", "ASF是Microsoft Windows Media Player使用的媒体容器。它专为在线流式传输音频和视频文件而设计。容器确定数据流的结构，但不确定如何编码这些数据。通常，Microsoft创建的文件具有.wma或.wmv扩展名，其他文件的扩展名为.asf。此类文件通常具有允许在线发送文件的MIME类型。 ASF的主要功能是流式播放功能，即在通过网络下载数据时直接播放视频或音频的功能。"),
    AVI(4, "41564920", ".avi", "", "", "AVI", "Audio Video Interleave", "", "AVI是一个多媒体容器，在PC和Mac用户中非常受欢迎。该格式由Microsoft于1992年开发。通常AVI用作视频格式，但与当时的其他格式不同，AVI可能包含使用不同编解码器组合压缩的音频和视频数据。更常见的是像DivX和XVID这样的视频编解码器与.avi文件一起使用。对于音频播放，使用诸如MP3，AC3和PCM之类的编解码器。可以使用Windows Media Player或其他类似程序播放AVI文件。"),
    AVI_2(4, "52494646", ".avi", "", "", "AVI", "", "Audio Video Interleave", "AVI是一个多媒体容器，在PC和Mac用户中非常受欢迎。该格式由Microsoft于1992年开发。通常AVI用作视频格式，但与当时的其他格式不同，AVI可能包含使用不同编解码器组合压缩的音频和视频数据。更常见的是像DivX和XVID这样的视频编解码器与.avi文件一起使用。对于音频播放，使用诸如MP3，AC3和PCM之类的编解码器。可以使用Windows Media Player或其他类似程序播放AVI文件。"),
    AC3(2, "0B77", ".ac3", "audio/vnd.dolby.dd-raw", "", "AC3", "AC3 Audio File", "AC3音频文件", "杜比数字电影格式，为电影音轨编码而开发。该格式最多可支持6个音轨：左、右、前、后、上、下。正因为如此，你才可以存储“三维”音轨。该格式在电影院以及用户自己在家中将音轨存储到电影库都广为使用。"),
    ARJ(2, "60EA", ".arj", "application/x-arj", "", "ARJ", "Archived by Robert ", "ARJ压缩文件", "ARJ文件是使用arj压缩格式压缩的文件存档。arj是早期DOS操作系统下压缩格式的一种，是著名的DOS压缩软件arj所压缩文件的存档格式，支持长文件名、文件版本管理、数据完整性保护和多卷存档。"),
    AVRO(4, "4F626A01", ".avro", "application/avro", "", "AVRO", "Object container file developed by Apache Avro", "Apache Avro开发的对象容器文件", "Avro是Hadoop中的一个子项目，基于二进制数据传输高性能的中间件"),

    BAT(10, "406563686F206F66660D", ".bat", "", "", "BAT", "Batch file", "批处理文件", "批处理文件是 DOS、OS/2 和 Microsoft Windows 中的脚本文件。"),
    BITMAP(2, "424D", ".bmp", "image/bmp", "", "BITMAP", "Bitmap Image File", "位图图像文件", "BMP是用于存储位图图像的格式。利用此格式，你只能保存位图单层。不同文件中每个像素可能有不同的位数。BMP的最新版本具有控制色彩的能力。特别是，你可以指定端点进行伽玛校正以及嵌入的颜色调校ICC。"),
    BZ2(3, "", ".bz2", "application/x-bzip2", "", "BZ2", "bz2", "压缩文件", "BZ2文件是使用bzip2压缩算法压缩的文件。bzip2是Julian Seward开发并按照自由软件/开源软件协议发布的数据压缩算法及程序。bzip2使用块排序压缩压缩（BWT）算法以及行程长度编码（RLE）算法，可以实现高水平压缩，通常用于Linux软件包分发。"),


    CAB(4, "4D534346", ".cab", "", "", "CAB", "CAB Installer file", "机柜档案", "CAB - 用于在Microsoft Windows操作系统压缩文件。存档在操作系统安装包（Windows安装程序、AdvPack，设备安装程序、API）中使用，并支持数字签名。该格式使用取决于特定存档的3种压缩方法。"),
    CAD(4, "41433130", ".dwg", "", "", "CAD", "Drawing", "", "DWG文件（Drawing）是AutoCAD软件开发的一种专有文件格式，也是目前最广泛使用的CAD文件格式之一。AutoCAD是一款流行的CAD软件，广泛应用于建筑设计、工程制图、产品设计等领域。而DWG文件作为AutoCAD的默认文件格式，被广泛用于存储和交换CAD图形数据。"),
    CLASS(4, "CAFEBABE", ".class", "application/java-vm", "", "CLASS", "Java Class", "", "Java代码编译后的二进制文件"),
    CPIO(2, "C771", ".cpio", "application/x-cpio", "", "CPIO", "Cpio archive", "", "CPIO文件是使用备份程序cpio创建的归档文件，cpio是Unix操作系统的一个文件备份程序及文件格式。cpio格式是一种用于将多个文件不经过压缩而打包成一个文件的归档格式，类似于.TAR格式，可以使用压缩工具gzip将其压缩为.CPGZ文件。"),

    DOCX(10, "504B0304140006000800", ".docx", "", "", "DOCX", "Microsoft Office Open XML", "", "自2007年起，微软开始使用docx这种文件格式，这是通过使用Office Open XML创建的。该格式是一种zip文件，其中包含XML、图形和其他数据格式的文本，可以使用专利保护的二进制格式转换为字节的序列。起初，人们认为这种格式将取代doc，但是这两种格式沿用至今。"),
    DLL(2, "4D5A", ".dll", "", "", "DLL", "Dynamic Link Library", "动态链接库", "DLL是一种共享库文件，包含了程序所需的代码和数据。与静态链接库不同，动态链接库可以在程序运行时动态加载，使得程序的内存占用更小，同时也方便了程序的更新和维护。"),
    DOC(8, "D0CF11E0A1B11AE1", ".doc", "", "", "DOC", "Microsoft Word Document", "Microsoft Word文档", "DOC是一种文字处理文档文件的扩展名。这主要是与Microsoft Word应用程序相关联。DOC文件也可以包含图表和表格、视频、图像、声音和图表。它支持几乎所有的操作系统。"),
    DMG(2, "7801", ".dmg", "application/x-apple-diskimage", "", "DMG", "Apple Disk Image", "", "软件开发人员通常使用DMG、.APP或.PKG文件格式作为Mac OS系统的安装程序。而DMG是最为常用的应用分发格式，因为它允许使用密码保护以及文件压缩，可以保证文件安全和文件分发功能，安装时还可以验证文件内容，确保磁盘映像在分发过程中没有被修改或损坏。"),


    EXE(2, "4D5A", ".exe", "application/x-msdownload", "", "EXE", "Executable file", "可执行文件", "EXE文件是一种Windows可执行文件"),
    EMAIL(14, "44656C69766572792D646174653A", ".eml", "", "", "EMAIL", "EMAIL", "电子邮件软件的通用格式", "EML格式是微软公司在Outlook中所使用的一种遵循RFC822及其后续扩展的文件格式,并成为各类电子邮件软件的通用格式。"),
    ESP(20, "252150532D41646F62652D332E3020455053462D", ".esp", "", "Adobe Illustrator", "ESP", "Encapsulated PostScript", "封装的PostScript格式", "EPS主要用于矢量图像和光栅图像的存储，采用 PostScript语言进行描述，并且可以保存其他一些类型信息。"),

    FLV(3, "464C56", ".flv", "", "", "FLV", "Flash Video", "Flash视频", "FLV是一个Flash视频容器，用于存储音频，视频和其他数据的流行托管网站。它通常使用h.264或h.263编解码器进行视频存储，使用MP3进行音频处理。 Windows和Mac操作系统有很多程序可以打开.flv文件。"),

    GIF(4, "47494638", ".gif", "image/gif", "", "GIF", "Graphics Interchange Format", "图形交换格式", "GIF是一种用于图像交换的格式。这是一种流行的图像格式。利用这种格式能够存储压缩的数据，如果采用不超过256种颜色的格式，不会有质量损失。 GIF格式是1987年（GIF87a）由CompuServe公司开发的一种通过网络传输光栅图像的格式。1989年，该格式经过修改（GIF89a），增加了支持透明和动画。"),
    GZIP(3, "1F8B08", ".gz", "application/gzip", "", "GZIP", "Archive file", "压缩文件", "GZ文件是由标准GNU zip（gzip）压缩算法压缩的存档文件。Gzip格式可以有效地压缩和解压缩单个或多个文件，通常用于在Unix和Linux系统上压缩文件。"),

    HLP(4, "3F5F0300", ".hlp", "", "", "HLP", "Windows Help File", "帮助文件", "帮助文件"),
    ICO(4, "00000100", ".ico", "", "", "ICO", "Icon", "图标", "ICO是一种通常包含不同分辨率（16×16，32×32，64×64像素）和各种颜色深度（16种颜色，32，64，128，256，16位等）的小图像图标的文件格式。用于在图形用户界面（GUI）操作系统中显示文件和文件夹。这种格式可以利用应用程序ACDSee打开。"),

    JLS(4, "FFD8FFF7", ".jls", "image/jls", "", "JLS", "Lossless/near-lossless compression standard for continuous-tone images", "无损JPEG图片", "用来压缩原始图像无损画面的图像格式，可以提供比有损JPEG更好的压缩效率。"),
    JPEG(3, "FFD8FF", ".jpg", "image/jpeg", "", "JPEG", "Joint Photographic Experts Group", "联合图像专家组", "JPEG是用于存储静态图像和类似图像最流行的图像格式之一。JPEG算法能够有损和无损压缩图像。最广泛使用的JPEG通过因特网以数码摄影和图片存档及通讯形式接收。JPEG压缩不适合图纸、文字和形象的图像。"),
    JP2(10, "0000000C6A5020200D0A", ".jp2", "", "", "JP2", "JPEG 2000", "", "JP2是位图图像格式的JPEG2000。其可以是照片、监视摄像机的图像、数字传真等的图像。JPEG 2000采用小波变换技术来压缩文件。JP2的图像更平滑和更清晰且同等质量下，相比JPEG，文件大小更小。"),
    JAR(5, "504B03040A", ".jar", "", "", "JAR", "Java ARchive", "Java档案文件", "这种格式是一种Java存档，并作为一种典型的ZIP文件，该程序的一部分是用Java语言编写的。"),
    JXR(3, "4949BC", ".jxr", "image/vnd.ms-photo", "", "JXR", "JPEG extended range", "", "JXR文件是以JPEG XR格式存储的图像。JPEG XR是由微软为其媒体软件开发的图像格式， 支持48位RGB的深色图像，支持无损和有损压缩，用于高分辨率专业图像和照片编辑。"),

    MDB(10, "5374616E64617264204A", ".mdb", "", "Microsoft Access", "MDB", "Microsoft Database", "", "Microsoft Access软件使用的一种存储格式，因其对数据操作的方便性，常用在一些中小型程序中。"),
    MPEG(4, "000001BA", ".mpg", "", "", "MPEG", "Moving Picture Experts Group", "电影专家组", "MPG是用于使用MPEG-1或MPEG-2标准压缩的文件的扩展。 MPG文件可以在Windows，Mac和任何移动设备上播放。这种格式的第一个版本非常有限，支持低视频分辨率和限制数据带宽，但最新的标准更新允许它甚至用于HDTV广播。"),
    MPEG_2(4, "000001B3", ".mpg", "", "", "MPEG-2", "MPEG-2", "", "MPEG-2是一种视频编码格式，首先在1995年标准化并在2013年进行了修订。由于计算复杂度相对较低，该标准在视频硬件中得到普及和大规模实现。因此，MPEG-2实际上已成为卫星和有线广播的标准。出于同样的原因（DVD播放器的硬件支持），MPEG-2也用于创建DVD。"),
    MOV(4, "6D6F6F76", ".mov", "", "Quicktime", "MOV", "Apple QuickTime Movie", "", "MOV是Apple开发的一种格式，用于电影和其他视频数据存储。它兼容Windows和Mac。但是，Windows Media Player只能打开该格式的早期版本。如果您有2.0及更高版本，则需要Apple QuickTime Player，或者您可以将其转换为与您的播放器兼容的其他格式。"),
    MID(4, "4D546864", ".mid", "", "", "MID", "Musical Instrument Digital Interface", "音乐设备数字接口", "MID文件是音乐创作软件、电子乐器以及计算机之间使用的标准 MIDI（音乐数字接口）文件。 MID文件记录了音乐数据的属性，例如播放的音符、播放时间、每个音符的持续时间以及每个音符的播放音量，通过专门软件和设备可以再现音乐播放。"),
    MP3(5, "4944330300", ".mp3", "audio/mpeg", "", "MP3", "MP3 Audio Format", "MP3音频格式", "用于存储音频的最常见的格式。几乎任何平台上的任何播放器都可以打开mp3文件。音频压缩会伴有质量的损失，但这种损失对于一般用户可以忽略不计，文件大小通常比原始文件要小。"),
    MP3_2(5, "FFF328C400", ".mp3", "audio/mpeg", "", "MP3", "MP3 Audio Format", "MP3音频格式", "用于存储音频的最常见的格式。几乎任何平台上的任何播放器都可以打开mp3文件。音频压缩会伴有质量的损失，但这种损失对于一般用户可以忽略不计，文件大小通常比原始文件要小。"),
    MP4(12, "00000018667479706D703432", ".mp4", "", "", "MP4", "MPEG-4 Part 14", "", "MP4是由MPEG-4视频标准和AAC音频标准定义的扩展。它是一个容器，支持各种媒体，如视频，音频，字幕，2D和3D图形。可以在Windows上几乎任何播放器打开MP4文件，但在Mac上你应该使用插件或只是将文件转换为另一种格式。"),
    MSI(8, "D0CF11E0A1B11AE1", ".msi", "", "", "MSI", "Microsoft Installer", "Windows安装程序", "MSI文件是Windows的程序安装包，其中包含了安装程序所需要的信息，包括要安装的文件、安装位置和安装选项等等。MSI文件常用于Windows更新以及第三方软件的安装。"),

    OUTLOOK_EXPRESS(8, "CFAD12FEC5FD746F", ".dbx", "", "Outlook", "OUTLOOK_EXPRESS", "Outlook Express", "Outlook Express电子邮件文件夹", ""),
    OUTLOOK(4, "21424442", ".pst", "", "Outlook", "OUTLOOK", "Personal Storage Table", "个人存储表", "PST文件是Microsoft Outlook的数据存储文件，主要是用来存储Outlook中的电子邮件、日历、联系人、任务、备忘录等个人信息数据。PST文件通常用于备份、导入/导出数据以及在不同的Outlook账户之间共享数据。"),
    OBJ(2, "4C01", ".obj", "", "", "OBJ", "Object Code File", "", "OBJ文件是一种标准3D模型文件，可以被各种3D图形软件导出和打开，适合用于3D软件模型之间的互导。OBJ文件包含一个3D对象，包括3D坐标、纹理贴图、多边形面以及其他对象信息。OBJ文件还可以引用对一个或多个.MTL材质库文件的，它存储了对象的表面材质纹理。"),
    OUTLOOK_MESSAGE(8, "D0CF11E0A1B11AE1", ".msg", "", "Outlook", "MSG", "Outlook Message", "", "MSG文件是Windows邮件客户端Outlook的项目文件，当使用Outlook创建或保存的电子邮件、联系人、约会或任务时，由系统自动创建，储存了有关该项目的所有信息，例如发件人、主题、收件人、日期、邮件内容等信息"),

    PNG(4, "89504E47", ".png", "", "", "PNG", "Portable Network Graphic", "可携式网络图形格式", "PNG是一种使用无损压缩算法来压缩的栅格图形数据存储格式。开发PNG这种格式是为了取代GIF。PNG支持三种主要类型的光栅图像：灰度图像、颜色索引图像和彩色图像。 PNG格式通过一种压缩形式存储图像信息。"),
    PSD(4, "38425053", ".psd", "", "Photoshop", "PSD文件", "Adobe Photoshop bitmap", "", "这个格式被用于在Adobe Photoshop中的项目文件中存储的位图图像。它存储在未压缩的形式，它包括色彩空间，层用掩模，双色设置，层结构和其它数据，使位图的照片，以精细地进行编辑。"),
    PDF(4, "25504446", ".pdf", "", "", "PDF", "Portable Document Format", "便携式文档格式", "PDF是一种由Adobe Systems使用某种语言功能PostScript设计的电子文件格式。是以Adobe Reader这种格式查看文档的官方应用程序。大多数情况下，PDF文件是文本与光栅和矢量图形和文本格式、用JavaScript编写的脚本以及其他类型项目的组合。"),
    PPT(8, "D0CF11E0A1B11AE1", ".ppt", "", "Microsoft PowerPoint", "PPT", "Microsoft PowerPoint", "幻灯片演示文稿格式", "PPT文件是1987年随PowerPoint发行而引入的，并以二进制文件格式保存。它是保存PowerPoint演示文稿的主要文件类型，直到在PowerPoint 2007中将其替换为以OpenXML格式存储的.PPTX文件。"),
    PPTX(4, "504B0304", ".pptx", "", "", "Microsoft PowerPoint", "Microsoft PowerPoint", "幻灯片演示文稿格式", "PPTX文件是Microsoft Office 2007之后版本使用，基于新的Open XML压缩文件格式创建，取代了原来使用二进制文件格式存储的.PPT文件。PPTX文件比PPT文件兼容更多的图形、渐变、动画效果，而且PPTX更容易使用，在文件数据管理和恢复方面更有优势。"),
    QUICKEN(4, "AC9EBD8F", ".qdf", "", "", "QUICKEN", "", "", ""),

    RAR(7, "526172211A0700", ".rar", "", "", "RAR", "WinRAR Compressed Archive", "WinRAR压缩文档", "这是最常见的文件压缩格式之一。它是由俄罗斯程序员Evgeny Roshal创建的。该格式让你可以将一个存档文件分成几个部分，以阻止存档，防止意外的修改，并提高数据恢复（帮助更新损坏的文档）。它具有128位的AES算法。"),
    REAL_AUDIO(4, "2E7261FD", ".ram", "", "", "REAL_AUDIO", "RealMedia Audio Metafile", "", "RAM文件是一种存储RealAudio文件地址的链接文件，通过流媒体播放器RealPlayer可以播放离线或在线音频。RAM文件以纯文本的格式保存了流媒体.RM或.RA音频文件的URL地址，通过浏览器打开该网址后，如果支持下载就可以下载该音频文件。"),
    REAL_MEDIA(4, "2E524D46", ".rm", "", "", "REAL_MEDIA", "RealMedia file format", "", "RM格式是RealNetworks公司开发的一种流媒体视频文件格式，可以根据网络数据传输的不同速率制定不同的压缩比率，从而实现低速率的Internet上进行视频文件的实时传送和播放。"),
    RTF(6, "7B5C72746631", ".rtf", "", "", "RTF", "Rich Text Format", "富文本格式", "RTF是一种用于存储标记文本文档的跨平台格式，由微软和Adobe联手创造，用作1982年Word格式的元标签。Adobe在1985年继续开发这种格式，创造出PostScript语言。其基础是一种简单的文本格式、为非可更改宏的控制序列以及具有固定行为的小组。"),

    SWF(3, "465753", ".swf", "application/x-shockwave-flash", "", "SWF", "Small Web Format", "小型Web格式", "SWF是一种用于存储矢量图形和动画的格式，它可能包含声音，视频，文本和其他数据。这些文件广泛用于在网页上创建动画，游戏和播放视频和音频。可以使用Adobe Flash Player或安装了Flash插件的浏览器打开SWF文件。"),
    SWF2(3, "435753", ".swf", "application/x-shockwave-flash", "", "SWF", "Small Web Format", "小型Web格式", "SWF是一种用于存储矢量图形和动画的格式，它可能包含声音，视频，文本和其他数据。这些文件广泛用于在网页上创建动画，游戏和播放视频和音频。可以使用Adobe Flash Player或安装了Flash插件的浏览器打开SWF文件。"),
    SYS(2, "4D5A", ".sys", "", "", "SYS", "SYS file", "系统文件", "SYS文件是DOS和Windows操作系统使用的系统文件。SYS文件包含系统设置、变量以及用于运行操作系统的函数，通常用于存储设备驱动程序和其他核心Windows功能。"),
    SDF(2, "789C", ".sdf", "", "", "SDF", "SDF file", "", ""),
    SQL_LITE(5, "53514C6974", ".db", "", "", "SqlLite", "SQL Lite数据库文件", "", "DB文件一般是各种软件用来存储数据的文件，是一种通用数据库格式。DB文件以结构化格式存储数据，通常由一组表、表字段、字段数据类型和字段值组成。不同软件保存数据格式各不相同，因此即使是同样使用.db扩展名的文件，打开方式也是各不相同。"),

    // 测试 4949
    TIF(4, "49492A00", ".tif", "", "", "TIFF", "Tagged Image File Format", "标记图像文件格式", "TIFF是一种灵活的高质量图像格式，支持多种色彩模式，可以有损压缩和无损压缩。TIFF文件还支持多个图层和页面。"),
    TAR(5, "7573746172", ".tar", "", "", "TAR", "Tape Archive File", "磁带归档文件", "这是在Unix环境中数据压缩的标准格式。该格式用来将多个文件存储在一个存档中，然后创建一个文件系统存档，并用于软件分发。在一个tar存档中，信息是与所有者、文件组、目录结构和文件时间戳有关的日志。不同的bzip2和gzip工具用于创建存档。"),

    VMDK(4, "4B444D56", ".wmdk", "", "", "VMDK", "VMWare Disk file", "", ""),
    VISIO(8, "D0CF11E0A1B11AE1", ".vsd", "", "Microsoft Visio", "VISIO", "Microsoft Visio", "Microsoft Visio绘图文件", "VSD文件是由Microsoft Visio创建的绘图文件，Visio是微软开发的一款专业的图形绘制工具。VSD文件可以包含各种形状、连接线、文本和其他图形元素，用于创建和编辑各种类型的图表和图形，广泛应用于各个行业和领域。"),

    WORD_PREFECT(4, "FF575043", ".wpd", "", "Corel WordPerfect", "WORD_PREFECT", "WordPerfect Document", "WordPerfect文档", "WPD文件是由Corel WordPerfect创建的文本文档，Corel WordPerfect是WordPerfect Office套件的文字处理器，类似于Microsoft Word。WPD文件可以包含格式化的文本、表格、图表、绘制的对象和图像。"),
    WINDOWS_PASSWORD(4, "E3828596", ".pwl", "", "", "WINDDOWS_PASSWORD", "Windows Password List", "口令列表文件", "Windows密码存储文件"),
    WAVE(4, "52494646", ".wav", "", "", "WAVE", "Waveform Audio File Format", "波形音频文件格式", "一种最常见的音频格式。该格式是由微软（与IBM协作）开发的，并且通常利用脉冲编码调制存储未压缩的音频数据，但该格式可以用来存储利用其他音频编解码器处理过的声音。"),
    WAVE_2(4, "57415645", ".wav", "", "", "WAVE", "Waveform Audio File Format", "波形音频文件格式", "一种最常见的音频格式。该格式是由微软（与IBM协作）开发的，并且通常利用脉冲编码调制存储未压缩的音频数据，但该格式可以用来存储利用其他音频编解码器处理过的声音。"),
    WMF(4, "D7CDC69A", ".wmf", "", "", "WMF", "Windows Meta File", "Windows图元文件", "WMF是一种由微软开发的图像文件格式，用于在Windows系统中存储矢量图形。WMF文件是一种二进制格式文件，可以包含矢量图形、文本、位图和其他图形元素。"),
    WMV(7, "3026B2758E66CF", ".wmv", "", "", "WMV", "Windows Media Video", "", "WMV是一种编解码器，通常用于ASF格式的视频压缩。几乎任何播放器都可以在Mac和Windows上打开WMV文件，或者将格式转换为另一种格式。 WMV支持阻止用户复制信息的DRM保护。这是该格式被在线销售数字视频和音频的公司广泛使用的主要原因。此格式在用户中也是众所周知的，因为WMV文件用于流行的Xbox 360设备。低比特率使这些文件成为HD DVD和蓝光光盘的理想解决方案。"),
    WMA(7, "3026B2758E66CF", ".wma", "", "", "WMA", "Windows Media Audio", "", "WMA文件是以微软开发的ASF容器格式封装并使用WMA（Windows Media Audio）编码压缩的音频文件。WMA文件还包括元数据，例如歌曲的标题、作者、专辑和流派。"),

    // XML(5, "3C3F786D6C", ".xml", "", "XML", "", "", ""),
    XLS(8, "D0CF11E0A1B11AE1", ".xls", "", "", "EXCEL", "Excel spreadsheet", "", "XLS文件是由Microsoft Excel创建的电子表格文件。它包含一个或多个工作表，这些工作表以表格形式保存和显示数据。XLS文件还可以存储数学函数、图表、样式和格式。"),
    XLSX(4, "504B0304", ".xlsx", "", "", "EXCEL 2010", "Excel 2010", "", "XLSX文件是由Microsoft Excel创建的Excel电子表格。它将数据存储在工作表中，该工作表包含以行和列的网格排列的单元格，还可以包含图表、数学函数、样式和格式。XLSX文件通常用于记录和处理各种数据。"),

    Z(2, "1FA0", ".Z", "application/x-compress", "", "Z", "", "", "Z文件是Unix系统环境中创建的压缩文件，它使用了一种简单的压缩算法，可以存档文件并压缩文件大小。Z文件已经被Gzip压缩文件（.GZ文件）所取代"),
    Z2(2, "1F9D", ".Z", "application/x-compress", "", "Z", "", "", "Z文件是Unix系统环境中创建的压缩文件，它使用了一种简单的压缩算法，可以存档文件并压缩文件大小。Z文件已经被Gzip压缩文件（.GZ文件）所取代"),
    ZLIB(2, "789C", ".zlib", "", "", "Zlib", "Zlib Archive", "", "zlib使用DEFLATE算法，用于数据压缩和解压缩"),
    ZIP(4, "504B0304", ".zip", "", "", "ZIP", "Compressed Archive File", "压缩档案文件", "一种压缩数据的常见格式。大量应用程序利用该格式交换数据，备份和压缩文件。在ZIP文档中，该格式使用不同的压缩算法。密码算法是专有的，所以机密数据利用更复杂的保护系统（RAR、7Z等等）被更好存储在文档中。");

    // ZLIB(2, "", "", "", "", "", "", "", ""),


    private int headBytes;
    private String signatureCode;
    private String suffix;
    private String remark;
    private String name;
    private String program;
    private String fullEnName;
    private String fullCnName;
    private String mine;

    FileType(int headBytes, String signatureCode, String suffix, String mine, String program, String name, String fullEnName, String fullCnName, String remark) {
        this.headBytes = headBytes;
        this.signatureCode = signatureCode;
        this.suffix = suffix;
        this.program = program;
        this.name = name;
        this.fullCnName = fullCnName;
        this.fullEnName = fullEnName;
        this.program = program;
        this.remark = remark;
        this.mine = mine;
    }

    public int headBytes() {
        return this.headBytes;
    }

    public String signatureCode() {
        return this.signatureCode;
    }

    public String suffix() {
        return this.suffix;
    }

    public String remark() {
        return this.remark;
    }

    public String name2() {
        return this.name;
    }

    public String program() {
        return this.program;
    }

    public String fullEnName() {
        return this.fullEnName;
    }

    public String fullCnName() {
        return this.fullCnName;
    }

    public String mine() {
        return this.mine;
    }
}
