package com.android.gank_demo.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * author lssRan
 */

public class GankDataModel implements Parcelable {

    @SerializedName("_id")
    private String _id;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("desc")
    private String desc;

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("source")
    private String source;

    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;

    @SerializedName("used")
    private boolean used;

    @SerializedName("who")
    private String who;

    @SerializedName("images")
    private String[] images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.createdAt);
        dest.writeString(this.desc);
        dest.writeString(this.publishedAt);
        dest.writeString(this.source);
        dest.writeString(this.type);
        dest.writeString(this.url);
        dest.writeByte(this.used ? (byte) 1 : (byte) 0);
        dest.writeString(this.who);
        dest.writeStringArray(this.images);
    }

    public GankDataModel() {
    }

    protected GankDataModel(Parcel in) {
        this._id = in.readString();
        this.createdAt = in.readString();
        this.desc = in.readString();
        this.publishedAt = in.readString();
        this.source = in.readString();
        this.type = in.readString();
        this.url = in.readString();
        this.used = in.readByte() != 0;
        this.who = in.readString();
        this.images = in.createStringArray();
    }

    public static final Creator<GankDataModel> CREATOR = new Creator<GankDataModel>() {
        @Override
        public GankDataModel createFromParcel(Parcel source) {
            return new GankDataModel(source);
        }

        @Override
        public GankDataModel[] newArray(int size) {
            return new GankDataModel[size];
        }
    };
}
