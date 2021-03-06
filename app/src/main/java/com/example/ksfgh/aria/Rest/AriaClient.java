package com.example.ksfgh.aria.Rest;

import com.example.ksfgh.aria.Model.AlbumModel;
import com.example.ksfgh.aria.Model.BandCreationModel;
import com.example.ksfgh.aria.Model.BandGenreModel;
import com.example.ksfgh.aria.Model.BandMemberModel;
import com.example.ksfgh.aria.Model.BandModel;
import com.example.ksfgh.aria.Model.CustomModelForAlbum;
import com.example.ksfgh.aria.Model.CustomSearchModel;
import com.example.ksfgh.aria.Model.EventModel;
import com.example.ksfgh.aria.Model.FacebookUserModel;
import com.example.ksfgh.aria.Model.MemberModel;
import com.example.ksfgh.aria.Model.NotificationModel;
import com.example.ksfgh.aria.Model.PlaylistModel;
import com.example.ksfgh.aria.Model.PlistModel;
import com.example.ksfgh.aria.Model.PreferenceModel;
import com.example.ksfgh.aria.Model.SongModel;
import com.example.ksfgh.aria.Model.UserModel;
import com.example.ksfgh.aria.Model.VideoModel;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by ksfgh on 16/11/2017.
 */

public interface AriaClient {

    @GET("bands")
    Observable<BandModel[]> getbands();

    @GET("getusers")
    Observable<FacebookUserModel[]> getUsers();

    @GET("getusers")
    Observable<UserModel[]> getUsers2();

    @POST("saveUser")
    Observable<FacebookUserModel> createAccount(@Body FacebookUserModel facebookUserModel);

    @POST("createBand")
    Observable<BandMemberModel> createBand(@Body BandCreationModel bandCreationModel);

    @Multipart
    @POST("editbandPic")
    Observable<ResponseBody> editBandPic(
            @Part("bandId") RequestBody bandId,
            @Part MultipartBody.Part pic
    );

    @Multipart
    @POST("addAlbum")
    Observable<AlbumModel> addAlbum(
            @Part("band_id") RequestBody bandId,
            @Part("album_name") RequestBody albumName,
            @Part("album_desc") RequestBody albumDesc,
            @Part("released_date") RequestBody releaseDate,
            @Part MultipartBody.Part albumImage
    );

    @Multipart
    @POST("addSongs")
    Observable<SongModel> addSong(
            @Part("album_id") RequestBody albumId,
            @Part("song_title") RequestBody songTitle,
            @Part("song_desc") RequestBody songDesc,
            @Part("genre_id") RequestBody genreId,
            @Part("band_id") RequestBody bandId,
            @Part MultipartBody.Part song
    );


    @POST("addSongToPlaylist")
    @FormUrlEncoded
    Observable<ResponseBody> addSongToPlaylist(@Field("genre_id") String genre,
                                               @Field("song_id") String songId,
                                               @Field("pl_id") String playlistId);

    @Multipart
    @POST("addVideo")
    Observable<VideoModel> addVideo(
            @Part("band_id") RequestBody bandId,
            @Part("video_title") RequestBody vidTitle,
            @Part("video_desc") RequestBody vidDesc,
            @Part MultipartBody.Part video
    );

    @GET("getAllPlaylist")
    Observable<PlaylistModel[]> getPlaylists();

    @POST("getPlistById")
    @FormUrlEncoded
    Observable<PlistModel[]> getPlaylistSongsByPlaylistId(@Field("pl_id") String playlistId);

    @GET("songs")
    Observable<SongModel[]> getAllSongs();

    @GET("AllAlbums")
    Observable<AlbumModel[]> getAllAlbums();

    @GET("videos")
    Observable<VideoModel[]> getAllVideos();

    @POST("bandvideos")
    @FormUrlEncoded
    Observable<VideoModel[]> getBandVideos(@Field("band_id") String bandId);

    @GET("members")
    Observable<MemberModel[]> getBandMembers();

    @GET("getEvents")
    Observable<EventModel[]> getEvents();

    @Multipart
    @POST("addBandCoverPhoto")
    Observable<ResponseBody> addBandCoverPhoto(@Part("bandId") RequestBody bandId,
                                               @Part MultipartBody.Part pic);

    @POST("addEvent")
    @FormUrlEncoded
    Observable<EventModel> addEvent(@Field("band_id") String bandId,
                                    @Field("event_name") String eventName,
                                    @Field("event_date") String eventDate,
                                    @Field("event_time") String eventTime,
                                    @Field("event_venue") String eventVenue,
                                    @Field("event_location") String eventLocation);
    @Multipart
    @POST("AddPlayList")
    Observable<PlaylistModel> addPlaylist(@Part("user_id") RequestBody userId,
                                          @Part("pl_title") RequestBody plTitle,
                                          @Part MultipartBody.Part pic);

    @POST("bandsongs")
    @FormUrlEncoded
    Observable<CustomModelForAlbum> getAlbumSongs(@Field("album_id") String albumId);

    @POST("followPlaylist")
    @FormUrlEncoded
    Observable<Integer> followPlaylist(@Field("user_id") String userId,
                                            @Field("pid") String pl_id);

    @POST("unFollowPlaylist")
    @FormUrlEncoded
    Observable<Integer> unFollowPlaylist(@Field("user_id") String userId,
                                            @Field("pid") String pl_id);

    @POST("preferences")
    @FormUrlEncoded
    Observable<PreferenceModel[]> getUserPreferences(@Field("user_id") String userId);

    @POST("followBand")
    @FormUrlEncoded
    Observable<Integer> followBand(@Field("user_id") String userId,
                                        @Field("band_id") String bandId);

    @POST("unfollowBand")
    @FormUrlEncoded
    Observable<Integer> unFollowBand(@Field("user_id") String userId,
                                        @Field("band_id") String bandId);

    @POST("likeAlbum")
    @FormUrlEncoded
    Observable<PreferenceModel> likeAlbum(@Field("user_id") String userId,
                                     @Field("album_id") String albumId);

    @POST("unLikeAlbum")
    @FormUrlEncoded
    Observable<ResponseBody> unLikeAlbum(@Field("user_id") String userId,
                                  @Field("album_id") String albumId);

    @GET("getBandGenres")
    Observable<BandGenreModel[]> getBandGenre();

    @GET("searchFunction")
    Observable<CustomSearchModel> searchResults(@Query("term") String text);

    @POST("inviteUser")
    @FormUrlEncoded
    Observable<String> inviteUser(@Field("band_id") String bandId,
                                   @Field("user_id") String userId,
                                   @Field("band_role") String bandRole,
                                   @Field("invitor_id") String invitorId);

    @POST("visitCount")
    @FormUrlEncoded
    Observable<String> visitPage(@Field("band_id") String bandId);

    @GET("getUserNotification")
    Observable<NotificationModel[]> getUserNotifications(@Query("user_id") String userId);

    @POST("declineInvitation")
    Observable<String> declineInvitation(@Body NotificationModel notificationModel);

    @POST("addmember")
    @FormUrlEncoded
    Observable<ResponseBody> addBandMember(@Field("add-band-member-id") String userId,
                                           @Field("add-band-member-band-id") int bandId,
                                           @Field("add-band-member-role") String bandRole);
    @GET("scoringfunc")
    Observable<ResponseBody> scoringFunc();

    @POST("songPlayed")
    @FormUrlEncoded
    Observable<String> songPlayed(@Field("user_id") String userId,
                                  @Field("song_id") String songId,
                                  @Field("category") String category);

}
