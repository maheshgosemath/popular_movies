package in.codingeek.movies.mapper;

import java.util.List;

/**
 * Created by root on 13/3/16.
 */
public class MoviesResult {
    private Integer page;
    private List<Movie> results;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
