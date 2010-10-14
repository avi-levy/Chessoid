package com.gtunes

import grails.test.*

class AlbumTests extends GrailsUnitTestCase {

    Album getFixture() {
    	Artist the_dBs = new Artist(
    		name:"The dB's",
    		albums: null)
    	Album selfTitled = new Album(
    		title:the_dBs.name)
    	return selfTitled
    }

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSaveAlbumToDb() {
		//Album album = Album(title: "The dB's", song: null)
    }
}

