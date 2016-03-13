package in.codingeek.moviestage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

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
 * Created by root on 13/3/16.
 */
public class MoviesImpl implements retrofit.Callback<MoviesResult> {

    private View view;
    private PropertyReader propertyReader;
    private AppUtility appUtility;
    private Context context;
    private ProgressDialog dialog;

    public MoviesImpl(Context context, View view) {
        this.view = view;
        this.context = context;
        propertyReader = new PropertyReader(context);
        appUtility = new AppUtility(propertyReader.getProperties("app.properties"));
    }

    public void fetchMovies(String order) {

        if(dialog == null) {
            dialog = new ProgressDialog(context);
            dialog.setMessage("Please wait...");
            dialog.setCancelable(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(appUtility.getPropertyValue("base.url"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoviesApi moviesApi = retrofit.create(MoviesApi.class);
        Call<MoviesResult> call = moviesApi.loadMovies(appUtility.getPropertyValue("api.key"), order);

        call.enqueue(this);
    }

    @Override
    public void onResponse(Response<MoviesResult> response, Retrofit retrofit) {
        final GridView gridView = (GridView) this.view.findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageAdapter(this.view.getContext(), response.body().getResults()));
        if(dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, MovieDetails.class);
                Movie movie = (Movie) parent.getAdapter().getItem(position);
                intent.putExtra("movie", movie);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
