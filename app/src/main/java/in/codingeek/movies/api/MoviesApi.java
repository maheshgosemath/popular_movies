package in.codingeek.movies.api;

import in.codingeek.movies.mapper.MoviesResult;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by root on 13/3/16.
 */
public interface MoviesApi {
    @GET("/3/discover/movie?page=1")
    Call<MoviesResult> loadMovies(@Query("api_key") String apiKey, @Query("sort_by") String sortBy);
}
