package com.lakshya.referrerflow;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewRequestsEntry implements Parcelable{
    private static final String TAG = NewRequestsEntry.class.getSimpleName();
    String name;
    String location;
    String experience;
    String currentRole;
    String qualification;
    String college;

    public NewRequestsEntry(String name, String experience, String location, String currentRole, String qualification, String college){
        this.name=name;
        this.location=location;
        this.experience=experience;
        this.currentRole=currentRole;
        this.qualification=qualification;
        this.college=college;

    }

    protected NewRequestsEntry(Parcel in) {
        name = in.readString();
        location = in.readString();
        experience = in.readString();
        currentRole = in.readString();
        qualification = in.readString();
        college = in.readString();
    }


    public static final Creator<NewRequestsEntry> CREATOR = new Creator<NewRequestsEntry>() {
        @Override
        public NewRequestsEntry createFromParcel(Parcel in) {
            return new NewRequestsEntry(in);
        }

        @Override
        public NewRequestsEntry[] newArray(int size) {
            return new NewRequestsEntry[size];
        }
    };

    /**
     * Loads a raw JSON at R.raw.products and converts it into a list of ProductEntry objects
     */
    public static List<NewRequestsEntry> initNewRequestsEntryList(Resources resources) {
        InputStream inputStream = resources.openRawResource(R.raw.new_requests);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int pointer;
            while ((pointer = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, pointer);
            }
        } catch (IOException exception) {
            Log.e(TAG, "Error writing/reading from the JSON file.", exception);
        } finally {
            try {
                inputStream.close();
            } catch (IOException exception) {
                Log.e(TAG, "Error closing the input stream.", exception);
            }
        }
        String jsonMyOpenRolesString = writer.toString();
        Gson gson = new Gson();
        Type newRequestsListType = new TypeToken<ArrayList<NewRequestsEntry>>() {
        }.getType();
        return gson.fromJson(jsonMyOpenRolesString, newRequestsListType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(location);
        parcel.writeString(experience);
        parcel.writeString(currentRole);
        parcel.writeString(qualification);
        parcel.writeString(college);
    }

}
