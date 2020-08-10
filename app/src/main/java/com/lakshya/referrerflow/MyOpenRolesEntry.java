package com.lakshya.referrerflow;

import android.content.res.Resources;
import android.net.Uri;
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

/**
 * A product entry in the list of products.
 */
public class MyOpenRolesEntry implements Parcelable {
    private static final String TAG = MyOpenRolesEntry.class.getSimpleName();

    public String jobTitle;
//    public final Uri dynamicUrl;
//    public final String url;
    public String postDate;
    public String requestsCount;
    public String referredCount;

    public MyOpenRolesEntry(
            String jobTitle, String postDate, String requestsCount, String referredCount) {
        this.jobTitle = jobTitle;
//        this.dynamicUrl = Uri.parse(dynamicUrl);
//        this.url = url;
        this.postDate = postDate;
        this.requestsCount = requestsCount;
        this.referredCount = referredCount;
    }

    protected MyOpenRolesEntry(Parcel in) {
        jobTitle = in.readString();
        postDate = in.readString();
        requestsCount = in.readString();
        referredCount = in.readString();
    }

    public static final Creator<MyOpenRolesEntry> CREATOR = new Creator<MyOpenRolesEntry>() {
        @Override
        public MyOpenRolesEntry createFromParcel(Parcel in) {
            return new MyOpenRolesEntry(in);
        }

        @Override
        public MyOpenRolesEntry[] newArray(int size) {
            return new MyOpenRolesEntry[size];
        }
    };

    /**
     * Loads a raw JSON at R.raw.products and converts it into a list of ProductEntry objects
     */
    public static List<MyOpenRolesEntry> initMyOpenRolesEntryList(Resources resources) {
        InputStream inputStream = resources.openRawResource(R.raw.my_open_roles);
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
        Type myOpenRolesListType = new TypeToken<ArrayList<MyOpenRolesEntry>>() {
        }.getType();
        return gson.fromJson(jsonMyOpenRolesString, myOpenRolesListType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        // We just need to write each field into the
        // parcel. When we read from parcel, they
        // will come back in the same order

        parcel.writeString(jobTitle);
        parcel.writeString(postDate);
        parcel.writeString(requestsCount);
        parcel.writeString(referredCount);
    }

    /**
     *
     * Called from the constructor to create this
     * object from a parcel.
     *
     * @param in parcel from which to re-create object
     */
    private void readFromParcel(Parcel in) {
        // We just need to read back each
        // field in the order that it was
        // written to the parcel
        jobTitle=in.readString();
        postDate=in.readString();
        requestsCount=in.readString();
        referredCount=in.readString();
    }

}