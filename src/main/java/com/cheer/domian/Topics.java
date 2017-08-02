package com.cheer.domian;

public class Topics
{
	private Integer no;
	
	private Exercise exercise;
	
	//考生选中的答案
	private String checked;

	public Integer getNo()
	{
		return no;
	}

	public void setNo(Integer no)
	{
		this.no = no;
	}

	public Exercise getExercise()
	{
		return exercise;
	}

	public void setExercise(Exercise exercise)
	{
		this.exercise = exercise;
	}

	public String getChecked()
	{
		return checked;
	}

	public void setChecked(String checked)
	{
		this.checked = checked;
	}

	@Override
	public String toString()
	{
		return "Topics [no=" + no + ", exercise=" + exercise + ", checked=" + checked + "]";
	}
	
	
}
