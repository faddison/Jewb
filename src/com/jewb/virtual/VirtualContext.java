package com.jewb.virtual;

public class VirtualContext 
{
	private VirtualBoard vboard;
	private String filepath;
	private int delay;
	
	public VirtualContext(VirtualBoard vboard, String filepath)
	{
		this.vboard = vboard;
		this.filepath = filepath;
	}
	
	public VirtualContext(VirtualBoard vboard)
	{
		this(vboard, null);
	}

	public VirtualBoard getVboard() {
		return vboard;
	}

	public void setVboard(VirtualBoard vboard) {
		this.vboard = vboard;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	
}
