package in.codingeek.moviestage;

import android.content.Intent;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import in.codingeek.movies.mapper.Movie;
import in.codingeek.util.AppUtility;
import in.codingeek.util.DateUtil;
import in.codingeek.util.PropertyReader;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsFragment extends Fragment {

    private PropertyReader propertyReader;
    private AppUtility appUtility;

    public MovieDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        propertyReader = new PropertyReader(this.getActivity());
        appUtility = new AppUtility(propertyReader.getProperties("app.properties"));

        Intent intent = getActivity().getIntent();
        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        if(intent != null) {
            Movie movie = (Movie) intent.getSerializableExtra("movie");
            TextView movieTitle = (TextView) rootView.findViewById(R.id.movie_title);
            TextView movieRatings = (TextView) rootView.findViewById(R.id.movie_ratings);
            TextView movieReleaseDate = (TextView) rootView.findViewById(R.id.movie_release_date);
            TextView movieOverview = (TextView) rootView.findViewById(R.id.movie_overview);

            ImageView imageView = (ImageView) rootView.findViewById(R.id.image_detail_view);
            Picasso.with(getActivity()).load(appUtility.getPropertyValue("movie.backdrop.path") + movie.getBackdrop_path()).into(imageView);

            movieTitle.setText(movie.getTitle());
            movieRatings.setText(movie.getVote_average().toString());
            movieReleaseDate.setText(DateUtil.beautifyDate(movie.getRelease_date()));
            movieOverview.setText(movie.getOverview());

        }
        return rootView;
    }
}
