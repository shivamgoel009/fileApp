package com.prj.api.fileApp.Entity;

public class FilePath implements Comparable<FilePath> {

	String path;
	long size;

	public int compareTo(FilePath f) {
		return (int) (f.size - this.size);
	}

	public FilePath(String path, long size) {
		this.path = path;
		this.size = size;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
