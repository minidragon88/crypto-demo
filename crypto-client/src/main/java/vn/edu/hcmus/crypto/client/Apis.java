package vn.edu.hcmus.crypto.client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Apis
{
    @Headers({
        "Accept: text/html",
        "Content-Type: text/html"
    })
    @GET("key/public-key")
    Call<String> getPublicKey();

    @Headers({
        "Accept: text/html",
        "Content-Type: text/html"
    })
    @POST("key/register-key")
    Call<String> registerKey(@Body String key);

    @Headers({
        "Accept: text/html",
        "Content-Type: text/html"
    })
    @GET("key/agree-key")
    Call<String> agreeKey();

    @Headers({
        "Accept: text/html",
        "Content-Type: text/html"
    })
    @POST("communicate/change-algorithm")
    Call<String> changeAlgorithm(@Body String algorithm);

    @Headers({
        "Accept: text/html",
        "Content-Type: text/html"
    })
    @POST("communicate/message")
    Call<String> message(@Body String message);
}
