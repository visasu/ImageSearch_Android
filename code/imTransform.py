#!/usr/bin/env python

from scipy.misc import imread
from scipy.misc import imresize
import os
import tensorflow as tf
import numpy as np
import random

def get_imagelist(path = "./dataset/test/"):
	sep = "/"
	classes = os.listdir(path)
	classes.sort();
	image_list = []
	labels = []
	filenames = []
	count=0;
	print(classes);
	for c in classes:
		#print(c)
		samples = os.listdir(path+c);
		for sample in samples:
			filepath = path+c+sep+sample
			#image_tensor = tf.image.decode_jpeg(image, channel=1)
			labels.append(c);
			filenames.append(filepath);
			count =count +1;
	print("Files Read:"+str(count));
	#print(filenames)
	return filenames, labels, classes

def encode(labels, classes):
	enclabels = np.zeros(shape=(labels.shape[0], len(classes)));
	for k in range(0, labels.shape[0]):
		for i in range(0,len(classes)):
			if(classes[i]==labels[k]):
				enclabels[k][i]=1;
	print ("Encoding order:"+str(len(classes)));
	return enclabels, classes

def read_image(file_list, label_list):
	image_list=np.empty(shape=(0,64*64));
	count=0;
	image_list=[]
	for i in range(0,len(file_list)):
		file = file_list[i];
		count=count+1;
		#print(file, count)
		content=imread(file, mode='F');
		#content=imresize(content,0.2);
		content=content.flatten();
		#print (len(content))
		#content=content.reshape((1,content.shape[0]*content.shape[1]));
		content=np.multiply(content,1.0/255.0)
		image_list.append(content)
		#print(image_list, content);
		#np.concatenate((image_list,content), axis=0);
		#print (file,content)
	image_list=np.array(image_list);
	label_list=np.array(label_list);
	print("Images Processed:"+str(count));
	#print (image_list)
	return image_list, label_list

def batches_train():
	file_list, label_list, classes = get_imagelist("./dataset/train/")
	image_list, label_list = read_image(file_list, label_list)
	label_list, classes = encode(label_list,classes)
	c = list(zip(image_list,label_list));
	random.shuffle(c)
	image_list, label_list = zip(*c)
	print("Train_set\n");
	return image_list, label_list;
def batches_test():
	file_list, label_list, classes = get_imagelist("./dataset/test/")
	image_list, label_list = read_image(file_list, label_list)
	label_list, classes = encode(label_list,classes)
	c = list(zip(image_list,label_list));
	random.shuffle(c)
	image_list, label_list = zip(*c)
	print("Test_set\n")
	return image_list, label_list;
	#print(image_list,label_list)

image_list, label_list = batches_train();
#print(image_list.shape, label_list.shape);
#images = tf.convert_to_tensor(image_list)
#labels = tf.convert_to_tensor(label_list)
#print(labels)

#input_queue = tf.train.slice_input_producer([images, labels], num_epochs=10, shuffle=True)

#image, label = read_image(image_list, label_list)
#print(image.shape)
#print(image)
#print(label)
#image_batch, label_batch =tf.train.batch([image_list, label_list], batch_size=5)
#print (image_batch,label_batch);
