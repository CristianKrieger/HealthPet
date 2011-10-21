package com.NullPointerException.HealthPet.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;


public class UI {
	public UI (Context context){
	}
	
	public static void showToast(Context context, String message){    	
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
		return;
    }
	
	public static void showInfoDialog(final Activity activity){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle("HealthPet")
				.setMessage("Some")
				.setCancelable(false)
				.setNeutralButton("OK", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		               activity.finish();

		           }
		       });

		AlertDialog alert = builder.create();
		
		alert.show();		
}

	public static void showExitDialog(final Activity activity){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle("HealthPet")
				.setMessage("Are you sure you want to exit?")
				.setCancelable(true)
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       })
		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					activity.finish();
				}
			});
				

		AlertDialog alert = builder.create();
		
		alert.show();		
}
	
}


