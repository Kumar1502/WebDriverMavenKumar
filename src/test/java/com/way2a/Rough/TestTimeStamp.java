package com.way2a.Rough;

import java.util.Date;

public class TestTimeStamp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Date d=new Date();
		String screenShotName=d.toString().replace(":", " ").replace(" ", "_")+".jpg";
		System.out.println(screenShotName);
	}

}
