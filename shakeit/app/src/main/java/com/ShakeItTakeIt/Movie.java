package com.ShakeItTakeIt;

/**
 * Höfundur: Kristín Helga Magnúsdóttir
 * Útgáfa: 1.2
 * Dagsetning: 18. nóvember 2014
 *
 * Klasinn Movie tekur inn titil myndar þegar búið er að fá tillögu og geymir hana til að birta
 * ef notandinn óskar eftir því að vista hana.
 *
 */

public class Movie {
    String title;

    public String getTitle() {
        return title;
    }

    public Movie(String title) {
        super();
        this.title = title;
    }

}