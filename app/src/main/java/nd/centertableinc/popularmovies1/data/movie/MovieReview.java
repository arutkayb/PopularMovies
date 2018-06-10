package nd.centertableinc.popularmovies1.data.movie;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieReview implements Parcelable{
    private String id;
    private String author;
    private String content;
    private String url;

    public static final String PARCELABLE_NAME = "movieReview";

    public static final Parcelable.Creator<MovieReview> CREATOR = new Parcelable.Creator<MovieReview>() {
        public MovieReview createFromParcel(Parcel in) {
            return new MovieReview(in);
        }

        public MovieReview[] newArray(int size) {
            return new MovieReview[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(id);
        parcel.writeString(content);
        parcel.writeString(url);
    }

    public MovieReview(Parcel parcel) {
        author = parcel.readString();
        id = parcel.readString();
        content = parcel.readString();
        url = parcel.readString();
    }

    public MovieReview(String id, String author, String content, String url) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.url = url;
    }

    public MovieReview() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
