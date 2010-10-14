package com.gtunes

class Album {

	String title

	byte[] art

	/*
	 * The hasMany property defines the relationship with other domain
	 * classes, in this case Song.  hasMany must be a groovy map object,
	 * where the key is the name of the relationship ('song') and the value
	 * is the type of the relationship (Song is equiv to saying Song.class
	 * in Java).  This is a unidirectional relationship for now.
	 */
	static hasMany = [song:Song]

	static belongsTo = [artist:Artist]

	/*
	 * find out what this means later...
	 */
    static constraints = {
		title(blank:false)
    }

	String toString() {
		return title
	}
}

class AlbumCreateCommand {
	String artist
	String title
	List songs = []
	List durations = []

	static constraints = {
		artist blank:false
		title blank:false
		songs minSize:1, validator: { val, obj ->
			if (val.size() != obj.durations.size())
				return "song.durations.not.equal.size"
		}
	}

	Album createAlbum() {
		def artist = Artist.findByName(artist) ?: new Artist(name:artist)
		def album = new Album(tile:title)
		songs.eachWithIndex { songTitle, i ->
			album.addToSongs(title:songTitle, duration.durations[i])
		}
		return album
	}
}

