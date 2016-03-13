package in.codingeek.moviestage;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.Properties;

import javax.security.auth.callback.Callback;

import in.codingeek.adapters.ImageAdapter;
import in.codingeek.movies.api.MoviesApi;
import in.codingeek.movies.mapper.Movie;
import in.codingeek.movies.mapper.MoviesResult;
import in.codingeek.util.AppUtility;
import in.codingeek.util.PropertyReader;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private View rootView;

    public MainActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        MoviesImpl movies = new MoviesImpl(getContext(), rootView);
        movies.fetchMovies("popularity.desc");
        return rootView;
    }

}
