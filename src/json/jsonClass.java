package json;

import java.util.ArrayList;

public class jsonClass {
	private ArrayList<jsonSetGet> lJSG;
	private int status;
	private String msg;
//-----コンストラクタ
	public jsonClass(){
		lJSG = new ArrayList<jsonSetGet>();
		status = 0;
		msg ="";
	}
//-----set
	public void SetLJSG(ArrayList<jsonSetGet> lJSG){
		this.lJSG = lJSG;
	}
	public void SetStatus(int status){
		this.status = status;
	}
	public void SetMsg(String msg){
		this.msg =msg;
	}
//-----get
	public ArrayList<jsonSetGet> GetLJSG(){
		return this.lJSG;
	}
	public int GetStatus(){
		return this.status;
	}
	public String GetMsg(){
		return this.msg;
	}


}
