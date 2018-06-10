package nd.centertableinc.popularmovies1.data.movie;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Rutkay on 04.03.2018.
 */

public class MovieItem implements Parcelable{
    private int voteCount;
    private int id;
    private boolean isVideo;
    private double voteAverage;
    private String title;
    private double popularity;
    private String posterPath;
    private String origLanguage;
    private String origTitle;
    private String backdropPath;
    private boolean isAdult;
    private String overview;
    private String releaseDate;

    private List<MovieReview> review;

    public static final String PARCELABLE_NAME = "movieItem";

    public static final Parcelable.Creator<MovieItem> CREATOR = new Parcelable.Creator<MovieItem>() {
        public MovieItem createFromParcel(Parcel in) {
            return new MovieItem(in);
        }

        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };
    
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(voteCount);
        parcel.writeInt(id);
        parcel.writeValue(isVideo);
        parcel.writeDouble(voteAverage);
        parcel.writeString(title);
        parcel.writeDouble(popularity);
        parcel.writeString(posterPath);
        parcel.writeString(origLanguage);
        parcel.writeString(origTitle);
        parcel.writeString(backdropPath);
        parcel.writeValue(isAdult);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
    }

    public MovieItem(Parcel parcel) {
        voteCount = parcel.readInt();
        id = parcel.readInt();;
        isVideo = (Boolean)parcel.readValue(getClass().getClassLoader());
        voteAverage = parcel.readDouble();
        title = parcel.readString();
        popularity = parcel.readDouble();;
        posterPath = parcel.readString();
        origLanguage = parcel.readString();
        origTitle = parcel.readString();
        backdropPath = parcel.readString();
        isAdult = (Boolean)parcel.readValue(getClass().getClassLoader());;
        overview = parcel.readString();
        releaseDate = parcel.readString();
    }

    public MovieItem(){
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOrigLanguage() {
        return origLanguage;
    }

    public void setOrigLanguage(String origLanguage) {
        this.origLanguage = origLanguage;
    }

    public String getOrigTitle() {
        return origTitle;
    }

    public void setOrigTitle(String origTitle) {
        this.origTitle = origTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
