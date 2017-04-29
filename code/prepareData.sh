#!/usr/bin/env bash
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

