package dataObjects;

import java.util.ArrayList;

public class MarkDataList {
	
	//Simple class that contains a list of MarkData objects.
	
	private ArrayList<MarkData> mList = new ArrayList<MarkData>();
	
	public MarkDataList(MarkWrapper[] marks) {
		
		for(MarkWrapper mark : marks) {
			mList.add(new MarkData(mark));
		}
		
	}
	
	public ArrayList<MarkData> getList(){
		return mList;
		
	}

}
