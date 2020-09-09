package ru.r2cloud.jradio.falconsat3;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PacsatFileHeader {

	private Long fileNumber;
	private String filename;
	private String fileExt;
	private Long fileSize;
	private Long createTime;
	private Long lastModifiedTime;
	private Integer seuFlag;
	private FileType type;
	private Integer bodyChecksum;
	private Integer headerChecksumAvailable;
	private Integer headerChecksum;
	private Integer bodyOffset;

	// extended
	private String source;
	private String ax25Uploader;
	private Long uploadTime;
	private Integer downloadCount;
	private List<String> destination;
	private List<String> ax25Downloader;
	private List<Long> downloadTime;
	private Long expireTime;
	private int priority;
	private CompressionType compressionType;
	private int bbsMessageType;
	private String bulletinIdNumber;
	private String title;
	private String keywords;
	private String fileDescription;
	private String compressionDescription;
	private String userFilename;

	public PacsatFileHeader() {
		// do nothing
	}

	public PacsatFileHeader(DataInputStream dis) throws IOException {
		if (dis.readUnsignedByte() != 0xaa) {
			throw new IOException("invalid header");
		}
		if (dis.readUnsignedByte() != 0x55) {
			throw new IOException("invalid header");
		}
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);

		int id = 0;
		while ((id = ldis.readUnsignedShort()) != 0) {
			int length = ldis.readUnsignedByte();
			switch (id) {
			case 0x01:
				fileNumber = ldis.readUnsignedInt();
				break;
			case 0x02:
				filename = readString(ldis, length);
				break;
			case 0x03:
				fileExt = readString(ldis, length);
				break;
			case 0x04:
				fileSize = ldis.readUnsignedInt();
				break;
			case 0x05:
				createTime = ldis.readUnsignedInt();
				break;
			case 0x06:
				lastModifiedTime = ldis.readUnsignedInt();
				break;
			case 0x07:
				seuFlag = ldis.readUnsignedByte();
				break;
			case 0x08:
				type = FileType.valueOfType(ldis.readUnsignedByte());
				break;
			case 0x09:
				bodyChecksum = ldis.readUnsignedShort();
				break;
			case 0x0a:
				headerChecksumAvailable = ldis.available();
				headerChecksum = ldis.readUnsignedShort();
				break;
			case 0x0b:
				bodyOffset = ldis.readUnsignedShort();
				break;
			case 0x10:
				source = readString(ldis, length);
				break;
			case 0x11:
				ax25Uploader = readString(ldis, length);
				break;
			case 0x12:
				uploadTime = ldis.readUnsignedInt();
				break;
			case 0x13:
				downloadCount = ldis.readUnsignedByte();
				break;
			case 0x14:
				String curDestination = readString(ldis, length);
				if (destination == null) {
					destination = new ArrayList<>();
				}
				destination.add(curDestination);
				break;
			case 0x15:
				String curAx25Downloader = readString(ldis, length);
				if (ax25Downloader == null) {
					ax25Downloader = new ArrayList<>();
				}
				ax25Downloader.add(curAx25Downloader);
				break;
			case 0x16:
				long curDownloadTime = ldis.readUnsignedInt();
				if (downloadTime == null) {
					downloadTime = new ArrayList<>();
				}
				downloadTime.add(curDownloadTime);
				break;
			case 0x17:
				expireTime = ldis.readUnsignedInt();
				break;
			case 0x18:
				priority = ldis.readUnsignedByte();
				break;
			case 0x19:
				compressionType = CompressionType.valueOfType(ldis.readUnsignedByte());
				break;
			case 0x20:
				bbsMessageType = ldis.readUnsignedByte();
				break;
			case 0x21:
				bulletinIdNumber = readString(ldis, length);
				break;
			case 0x22:
				title = readString(ldis, length);
				break;
			case 0x23:
				keywords = readString(ldis, length);
				break;
			case 0x24:
				fileDescription = readString(ldis, length);
				break;
			case 0x25:
				compressionDescription = readString(ldis, length);
				break;
			case 0x26:
				userFilename = readString(ldis, length);
				break;
			default:
				// skip unknown tags
				ldis.skipBytes(length);
			}
		}
	}

	private static String readString(LittleEndianDataInputStream ldis, int length) throws IOException {
		byte[] raw = new byte[length];
		ldis.readFully(raw);
		String result = new String(raw, StandardCharsets.US_ASCII).trim();
		if (result.length() == 0) {
			return null;
		}
		return result;
	}

	public Long getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(Long fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Long lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public Integer getSeuFlag() {
		return seuFlag;
	}

	public void setSeuFlag(Integer seuFlag) {
		this.seuFlag = seuFlag;
	}

	public FileType getType() {
		return type;
	}

	public void setType(FileType type) {
		this.type = type;
	}

	public Integer getBodyChecksum() {
		return bodyChecksum;
	}

	public void setBodyChecksum(Integer bodyChecksum) {
		this.bodyChecksum = bodyChecksum;
	}

	public Integer getHeaderChecksum() {
		return headerChecksum;
	}

	public void setHeaderChecksum(Integer headerChecksum) {
		this.headerChecksum = headerChecksum;
	}

	public Integer getBodyOffset() {
		return bodyOffset;
	}

	public void setBodyOffset(Integer bodyOffset) {
		this.bodyOffset = bodyOffset;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAx25Uploader() {
		return ax25Uploader;
	}

	public void setAx25Uploader(String ax25Uploader) {
		this.ax25Uploader = ax25Uploader;
	}

	public Long getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Long uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Integer getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	public List<String> getDestination() {
		return destination;
	}

	public void setDestination(List<String> destination) {
		this.destination = destination;
	}

	public List<String> getAx25Downloader() {
		return ax25Downloader;
	}

	public void setAx25Downloader(List<String> ax25Downloader) {
		this.ax25Downloader = ax25Downloader;
	}

	public List<Long> getDownloadTime() {
		return downloadTime;
	}

	public void setDownloadTime(List<Long> downloadTime) {
		this.downloadTime = downloadTime;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public CompressionType getCompressionType() {
		return compressionType;
	}

	public void setCompressionType(CompressionType compressionType) {
		this.compressionType = compressionType;
	}

	public int getBbsMessageType() {
		return bbsMessageType;
	}

	public void setBbsMessageType(int bbsMessageType) {
		this.bbsMessageType = bbsMessageType;
	}

	public String getBulletinIdNumber() {
		return bulletinIdNumber;
	}

	public void setBulletinIdNumber(String bulletinIdNumber) {
		this.bulletinIdNumber = bulletinIdNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public String getCompressionDescription() {
		return compressionDescription;
	}

	public void setCompressionDescription(String compressionDescription) {
		this.compressionDescription = compressionDescription;
	}

	public String getUserFilename() {
		return userFilename;
	}

	public void setUserFilename(String userFilename) {
		this.userFilename = userFilename;
	}

	public Integer getHeaderChecksumAvailable() {
		return headerChecksumAvailable;
	}

	public void setHeaderChecksumAvailable(Integer headerChecksumAvailable) {
		this.headerChecksumAvailable = headerChecksumAvailable;
	}
}
