//package com.backend.DonateDrift.entity;
//
//import com.backend.DonateDrift.enums.FileType;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//
//import static com.backend.DonateDrift.enums.FileType.PDF;
//
//@Entity
//public class UserDocument {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long id;
//
//	private String fileUrl;
//
//	private String fileName="jj";
//
//	private FileType fileType=PDF;
//
//	@ManyToOne
//	@JoinColumn(name="fundraiser_id")
//	private Fundraiser fundraiser;
//
//	public Fundraiser getFundraiser() {
//		return fundraiser;
//	}
//
//	public void setFundraiser(Fundraiser fundraiser) {
//		this.fundraiser = fundraiser;
//	}
//
//	public UserDocument(long id, String fileUrl, String fileName, FileType fileType, Fundraiser fundraiser) {
//		super();
//		this.id = id;
//		this.fileUrl = fileUrl;
//		this.fileName = fileName;
//		this.fileType = fileType;
//		this.fundraiser = fundraiser;
//	}
//
//	public UserDocument() {
//
//	}
//
//	public UserDocument(long id, String fileUrl, String fileName, FileType fileType) {
//		super();
//		this.id = id;
//		this.fileUrl = fileUrl;
//		this.fileName = fileName;
//		this.fileType = fileType;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getFileUrl() {
//		return fileUrl;
//	}
//
//	public void setFileUrl(String fileUrl) {
//		this.fileUrl = fileUrl;
//	}
//
//	public String getFileName() {
//		return fileName;
//	}
//
//	public void setFileName(String fileName) {
//		this.fileName = fileName;
//	}
//
//	public FileType getFileType() {
//		return fileType;
//	}
//
//	public void setFileType(FileType fileType) {
//		this.fileType = fileType;
//	}
//
//
//
//
//
//}
