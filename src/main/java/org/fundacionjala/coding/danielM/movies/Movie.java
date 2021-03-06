package org.fundacionjala.coding.danielM.movies;

/**
 * Created by Administrator on 3/21/2017.
 */
public abstract class Movie {
    private String title;

    /**
     * Movie constructor.
     *
     * @param title of String type.
     */
    public Movie(final String title) {
        this.title = title;
    }

    /**
     * Abstract method to calculate the amount of a rented movie.
     *
     * @param daysRented of int type.
     * @return the amount of a rented movie.
     */
    public abstract double calculateAmount(int daysRented);

    /**
     * Abstract method to calculate the frequent renter points
     * of a rented movie.
     *
     * @param daysRented of int type.
     * @return the frequent renter points.
     */
    public abstract int calculateFrequentRenterPoints(int daysRented);

    /**
     * This method returns the title of a movie.
     *
     * @return the title of the movie.
     */
    public String getTitle() {
        return title;
    }
}
