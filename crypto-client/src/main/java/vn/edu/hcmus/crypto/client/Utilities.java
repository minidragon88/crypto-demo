package vn.edu.hcmus.crypto.client;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public final class Utilities
{
    private Utilities() {}
    public static Retrofit getRetrofit(final String baseUrl)
    {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }
}
