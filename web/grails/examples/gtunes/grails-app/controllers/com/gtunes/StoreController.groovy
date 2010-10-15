package com.gtunes

import org.hibernate.criterion.Distinct;

/**
 *
 * This the the controller for the entrypoint into the
 * gtunes app, the "home page" of gtunes.
 *
 */
class StoreController {

	/*
	 * just delegates to the view
	 */
	def index = { }
	
	/*
	 * note that this action makes the genres
	 * objects available to the view.
	 */
	def shop = {
		def genreList = Album.withCriteria {
			projections {
				distinct 'genre' 
			}
		}
		[genres:genreList.sort()]
	}
	
}
