package com.jade.myapp.board.model;

import java.util.Arrays;

public class BoardUploadFile {

	private int fileId;
	private int boardId;
	private String fileName;
	private long fileSize;
	private String fileContentType;
	private byte[] fileDate;
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public byte[] getFileDate() {
		return fileDate;
	}
	public void setFileDate(byte[] fileDate) {
		this.fileDate = fileDate;
	}
	@Override
	public String toString() {
		return "BoardUploadFile [fileId=" + fileId + ", boardId=" + boardId + ", fileName=" + fileName + ", fileSize="
				+ fileSize + ", fileContentType=" + fileContentType + ", fileDate=" + Arrays.toString(fileDate) + "]";
	}

	
	
	
}
