package com.gtunes

class Song {

	String title
	Integer duration

	static belongsTo = Album

	/**
	 * objects will not be written to the database if
	 * these constraints fail
	 */
    static constraints = {
    	title(blank: false)
    	duration(min: 1)
    }

    String toString() {
    	return title
    }
}
