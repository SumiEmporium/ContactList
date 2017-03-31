package com.taslima.contactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


	public static final String DB_NAME = "person_info";
	public static final int DB_VERSION = 1;

	public static final String PERSON_TABLE = "person";
	public static final String ID_FIELD = "_id";
	public static final String NAME_FIELD = "name";
	public static final String PHONE_FIELD = "phone";


	public static final String EMPLOYEE_TABLE_SQL = "CREATE TABLE "
			+ PERSON_TABLE + " (" + ID_FIELD + " INTEGER PRIMARY KEY, "
			+ NAME_FIELD + " TEXT, "+ PHONE_FIELD + " TEXT)";

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// create tables
		db.execSQL(EMPLOYEE_TABLE_SQL);
		Log.e("TABLE CREATE", EMPLOYEE_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// upgrade logic

	}


	public int  insertContact(String  name,String  number) {

		try {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();

				values.put(NAME_FIELD, name);
				values.put(PHONE_FIELD,number);
				db.insert(PERSON_TABLE, null, values); //Insert each time for loop count

			db.close();
			return 1;
		}catch (Exception e){
			return 0;
		}

	}


	public ArrayList<ContactModel> getSomeContact() {
		ArrayList<ContactModel> allPhnNumber = new ArrayList<ContactModel>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM person ORDER BY name ASC limit 10", null);
		cursor.moveToFirst();
		if (cursor != null && cursor.getCount() > 0) {

			for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
				String name = cursor.getString(cursor
						.getColumnIndex(NAME_FIELD));

				String phone = cursor.getString(cursor
						.getColumnIndex(PHONE_FIELD));

				ContactModel contactModel = new ContactModel(name,phone);
				allPhnNumber.add(contactModel);

			}
		}else
		{
			Log.d(" Null value in cursor ", " null ");
		}
		cursor.close();
		db.close();

		return allPhnNumber;
	}

    public ArrayList<ContactModel> getMoreContact(String limit, String offset) {
        ArrayList<ContactModel> allPhnNumber = new ArrayList<ContactModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM person ORDER BY name ASC limit "+limit +", "+offset, null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {

            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
                String name = cursor.getString(cursor
                        .getColumnIndex(NAME_FIELD));

                String phone = cursor.getString(cursor
                        .getColumnIndex(PHONE_FIELD));

                ContactModel contactModel = new ContactModel(name,phone);
                allPhnNumber.add(contactModel);

            }
        }else
        {
            Log.d(" Null value in cursor ", " null ");
        }
        cursor.close();
        db.close();


        return allPhnNumber;
    }

}
