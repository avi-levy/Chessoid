package com.gtunes

import grails.test.*

class SongTests extends GrailsUnitTestCase {

    Artist the_dBs = new Artist(
    	name: "The dB's",
    	albums: null)

    Album selfTitled = new Album(
    	title: the_dBs.name)

    Song getFixture() {
    	def song = new Song(
    		title: 'Black and White',
    		duration: 42)
    }

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

	void testTitle() {
		def song = getFixture()
		// getters/setters will be auto-gen'd at runtime
		def title = song.getTitle()
		assertEquals 'Black and White', title
		song.setTitle('new temp title')
		assertEquals 'new temp title', song.title
		song.title = title
		assertEquals title, song.title
		assertEquals song.getTitle(), song.title
	}

	void testAlbumField() {
		def song = getFixture()
		assertEquals selfTitled, song.belongsTo
	}

	void testMinimumDuration() {
		//mock the behavior or the Song domain class
		mockDomain(Song)
		//create a Song with an invalid duration
		def song = new Song(duration:0)
		//make sure validation fails
		assertFalse 'validation should have failed for neg. duration value',
			song.validate()
		//make sure validation failed for the expected reason
		assertEquals 'min', song.errors.duration
	}
}

