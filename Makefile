data:
	svn checkout https://github.com/visasu/ImageSearch_Android/trunk/data
code:
	svn checkout https://github.com/visasu/ImageSearch_Android/trunk/code
image:
	svn checkout https://github.com/visasu/ImageSearch_Android/trunk/code
all:
	svn checkout https://github.com/visasu/ImageSearch_Android/trunk/code
	svn checkout https://github.com/visasu/ImageSearch_Android/trunk/data
	svn checkout https://github.com/visasu/ImageSearch_Android/trunk/image
clean:
	rm -rf code image data

