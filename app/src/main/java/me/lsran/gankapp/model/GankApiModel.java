package me.lsran.gankapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author lssRan
 */

public class GankApiModel implements Parcelable{

    @SerializedName("error")
    private String error;

    @SerializedName("results")
    private List<GankDataModel> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<GankDataModel> getResults() {
        return results;
    }

    public void setResults(List<GankDataModel> results) {
        this.results = results;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.error);
        dest.writeTypedList(this.results);
    }

    public GankApiModel() {
    }

    protected GankApiModel(Parcel in) {
        this.error = in.readString();
        this.results = in.createTypedArrayList(GankDataModel.CREATOR);
    }

    public static final Creator<GankApiModel> CREATOR = new Creator<GankApiModel>() {
        @Override
        public GankApiModel createFromParcel(Parcel source) {
            return new GankApiModel(source);
        }

        @Override
        public GankApiModel[] newArray(int size) {
            return new GankApiModel[size];
        }
    };
}
