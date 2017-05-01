#!/usr/bin/env bash
: '
Copyright (c) 2017 Vishal Srivastava<vsriva10@asu.edu>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
'
wget -4 "http://conradsanderson.id.au/lfwcrop/lfwcrop_grey.zip";
unzip lfwcrop_grey;
file="classes.txt";
while IFS= read -r line
do
	mkdir -p "dataset/train/$line"
	mkdir -p "dataset/test/$line"
	find ./lfwcrop_grey -name "*${line}*" | xargs cp -t "dataset/train/$line/";
	find "dataset/train/${line}" -maxdepth 1 -type f | head -10 | xargs mv -t "dataset/test/${line}/";
	printf '%s\n' "Dataset Created: $line"
done <"$file"
printf '%s\n' "Converting .pgm to .png"
find ./dataset/ -name "*.pgm" -exec bash -c 'convert "$1" "${1%.pgm}".png' - '{}' \;
find ./dataset/ -name "*.pgm" -exec bash -c 'rm -r "$1"' - '{}' \;
printf '%s\n' "All Datasets Created"
rm -r lfwcrop_grey*

