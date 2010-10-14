package com.gtunes

import java.io.*

class FileUploaderController {

    def index = {

    }

    def upload = {
    	//def file = request.getFile('myFile')
    	//def reader = new BufferedReader(new InputStreamReader(file.getInputStream()))
    	//render('file uploaded, first line: ' + reader.readLine())
    	def txt = request.inputStream.text
    	render "You sent $txt"
    }
}

