package com.cheer.domian;

public class Exercise
{
	private int no;
	private String title;
	private String selectA;
	private String selectB;
	private String selectC;
	private String selectD;
	private String answer;
	
	
	public Exercise()
	{
		
	}


	public int getNo()
	{
		return no;
	}
	public void setNo(int no)
	{
		this.no = no;
	}
	
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getSelectA()
	{
		return selectA;
	}
	public void setSelectA(String selectA)
	{
		this.selectA = selectA;
	}
	public String getSelectB()
	{
		return selectB;
	}
	public void setSelectB(String selectB)
	{
		this.selectB = selectB;
	}
	public String getSelectC()
	{
		return selectC;
	}
	public void setSelectC(String selectC)
	{
		this.selectC = selectC;
	}
	public String getSelectD()
	{
		return selectD;
	}
	public void setSelectD(String selectD)
	{
		this.selectD = selectD;
	}
	public String getAnswer()
	{
		return answer;
	}
	public void setAnswer(String answer)
	{
		this.answer = answer;
	}


	@Override
	public String toString()
	{
		return "Exercise [no=" + no + ", title=" + title + ", selectA=" + selectA + ", selectB=" + selectB
				+ ", selectC=" + selectC + ", selectD=" + selectD + ", answer=" + answer + "]";
	}


	
	
}
