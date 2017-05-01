#!/usr/bin/env python
"""
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
"""

import tensorflow as tf
import time

import numpy as np
import random as ran
import imTransform

learning_rate = 0.0005
training_iters = 1600;
batch_size = 10;
display_step = 4;

n_input = 64*64
n_classes = 9;
dropout = 0.75

x = tf.placeholder(tf.float32, shape = [None, n_input], name = 'x')
y = tf.placeholder(tf.float32, shape = [None, n_classes], name = 'y')
keep_prob = tf.placeholder(tf.float32, name='drop') #dropout

def conv2d(x, W, b, strides=1):
	x = tf.nn.conv2d(x, W, strides=[1, strides, strides,1 ], padding='SAME')
	x = tf.nn.bias_add(x, b)
	return tf.nn.relu(x)

def maxpool2d(x, k=2):
	return tf.nn.max_pool(x, ksize=[1, k, k, 1], strides=[1, k, k, 1], padding='SAME')

def conv_net(x, weights, biases, dropout):
	x = tf.reshape(x, shape=[-1, 64, 64, 1])
	
	conv1 = conv2d(x, weights['wc1'], biases['bc1'])
	pool1 = maxpool2d(conv1, k=2)
	#rnorm1
	conv2a = conv2d(pool1, weights['wc2a'], biases['bc2a'])
	conv2 = conv2d(conv2a, weights['wc2'], biases['bc2'])
	#rnorm2
	pool2 = maxpool2d(conv2, k=2)
	conv3a = conv2d(pool2, weights['wc3a'], biases['bc3a'])
	conv3 = conv2d(conv3a, weights['wc3'], biases['bc3'])
	pool3 = maxpool2d(conv3, k=2)
	conv4a = conv2d(pool3, weights['wc4a'], biases['bc4a'])
	conv4 = conv2d(conv4a, weights['wc4'], biases['bc4'])
	conv5a = conv2d(conv4, weights['wc5a'], biases['bc5a'])
	conv5 = conv2d(conv5a, weights['wc5'], biases['bc5'])
	conv6a = conv2d(conv5, weights['wc6a'], biases['bc6a'])
	conv6 = conv2d(conv6a, weights['wc6'], biases['bc6'])
	pool4 = maxpool2d(conv6, k=2)
	
	concat = tf.reshape(conv4, [-1, weights['wd1'].get_shape().as_list()[0]])
	fc1 = tf.add(tf.matmul(concat, weights['wd1']), biases['bd1'])
	#maxout
	fc2 = tf.add(tf.matmul(fc1, weights['wd2']), biases['bd2'])
	#maxout
	fc7128 = tf.add(tf.matmul(fc2, weights['wd7128']), biases['bd7128'])
	fc7128r = tf.nn.relu(fc7128)
	
	L2 = tf.nn.dropout(fc7128r, dropout)
	
	out = tf.add(tf.matmul(L2, weights['out']), biases['out'], name="out")
	return out

weights = {
	# 7x7 conv, 1 input, 64 outputs
	'wc1': tf.Variable(tf.random_normal([7, 7, 1, 64]), name="wc1"),
	# 1x1 conv, 64 inputs, 64 outputs
	'wc2a': tf.Variable(tf.random_normal([1, 1, 64, 64]), name="wc2a"),
	# 3x3 conv, 64 inputs, 192 outputs
	'wc2': tf.Variable(tf.random_normal([3, 3, 64, 192]), name="wc2"),
	# 1x1 conv, 192 inputs, 192 outputs
	'wc3a': tf.Variable(tf.random_normal([1, 1, 192, 192]), name="wc3a"),
	# 3x3 conv, 192 inputs, 384 outputs
	'wc3': tf.Variable(tf.random_normal([3, 3, 192, 384]), name="wc3"),
	# 1x1 conv, 384 inputs, 384 outputs
	'wc4a': tf.Variable(tf.random_normal([1, 1, 384, 384]), name="wc4a"),
	# 3x3 conv, 384 inputs, 256 outputs
	'wc4': tf.Variable(tf.random_normal([3, 3, 384, 256]), name="wc4"),
	# 1x1 conv, 256 inputs, 256 outputs
	'wc5a': tf.Variable(tf.random_normal([1, 1, 256, 256]), name="wc5a"),
	# 3x3 conv, 256 inputs, 256 outputs
	'wc5': tf.Variable(tf.random_normal([3, 3, 256, 256]), name="wc5"),
	# 1x1 conv, 256 inputs, 256 outputs
	'wc6a': tf.Variable(tf.random_normal([1, 1, 256, 256]), name="wc6a"),
	# 3x3 conv, 256 inputs, 256 outputs
	'wc6': tf.Variable(tf.random_normal([3, 3, 256, 256]), name="wc6"),
	# fully connected, 8*8*256 inputs, 128 outputs
	'wd1': tf.Variable(tf.random_normal([8*8*256, 128]), name="wd1"),
	# fully connected, 128 inputs, 128 outputs
	'wd2': tf.Variable(tf.random_normal([128, 128]), name="wd2"),
	# fully connected, 128 inputs, 128 outputs
	'wd7128': tf.Variable(tf.random_normal([128, 128]), name="wc7128"),
	# 128 inputs, 9 outputs (class prediction)
	'out': tf.Variable(tf.random_normal([128, n_classes]), name="wout")
}

biases = {
	'bc1': tf.Variable(tf.random_normal([64]), name="bc1"),
	'bc2a': tf.Variable(tf.random_normal([64]), name="bc2a"),
	'bc2': tf.Variable(tf.random_normal([192]), name="bc2"),
	'bc3a': tf.Variable(tf.random_normal([192]), name="bc3a"),
	'bc3': tf.Variable(tf.random_normal([384]), name="bc3"),
	'bc4a': tf.Variable(tf.random_normal([384]), name="bc4a"),
	'bc4': tf.Variable(tf.random_normal([256]), name="bc4"),
	'bc5a': tf.Variable(tf.random_normal([256]), name="bc5a"),
	'bc5': tf.Variable(tf.random_normal([256]), name="bc5"),
	'bc6a': tf.Variable(tf.random_normal([256]), name="bc6a"),
	'bc6': tf.Variable(tf.random_normal([256]), name="bc6"),
	'bd1': tf.Variable(tf.random_normal([128]), name="bd1"),
	'bd2': tf.Variable(tf.random_normal([128]), name="bd2"),
	'bd7128': tf.Variable(tf.random_normal([128]), name="bc7128"),
	'out': tf.Variable(tf.random_normal([n_classes]), name="bout")
}

pred = conv_net(x, weights, biases, keep_prob)

cost = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=pred, labels=y))
optimizer = tf.train.AdamOptimizer(learning_rate=learning_rate).minimize(cost)
correct_pred = tf.equal(tf.argmax(pred,1), tf.argmax(y,1))
accuracy = tf.reduce_mean(tf.cast(correct_pred, tf.float32))

saver = tf.train.Saver()
init = tf.global_variables_initializer()

with tf.Session() as sess:
	sess.run(init)
	step = 1
	start = time.time()
	print("Executing Model")
	tf.train.write_graph(sess.graph_def, '.', 'facenet.pbtxt')
	while (step * batch_size < training_iters):
		batch_x, batch_y = imTransform.batches_train();
		
		sess.run(optimizer, feed_dict={x: batch_x, y: batch_y, keep_prob: dropout})
		
		if (step % display_step == 0):
			loss, acc = sess.run([cost, accuracy], feed_dict={x: batch_x, 
									  y: batch_y,
									  keep_prob: 1.0})
			print("Iter "+str(step*batch_size) + ", Minibatch Loss= " + \
			      "{:.6f}".format(loss) + ", Training Accuracy= " + \
			      "{:.5f}".format(acc))
			end = time.time()
			print("Time:"+str(end - start))	
		step += 1

	print("Optimization Finished!")
	
	saver.save(sess, 'facenet.ckpt')
	batch_x, batch_y = imTransform.batches_test();
	print("Testing Accuracy:", \
	      sess.run(accuracy, feed_dict={x: batch_x, 
										y: batch_y,
										keep_prob: 1.0}))
