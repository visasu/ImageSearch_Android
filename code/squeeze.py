# MIT License
#
# Copyright (c) 2017 Raajgopaalan.S
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.



import tensorflow as tf
import tensorflow.contrib.slim as s

def squeezenet(inputs,
                noofinput,
                noofoutput
                ):
    with tf.variable_scope([inputs]):
        with s.arg_scope([s.conv2d, s.max_pool2d]):                         # expand and squeeze
            inputnet = s.conv2d(noofinput,noofoutput, [1, 1], stride=1)
            x = s.conv2d(inputnet,   noofoutput, [1, 1], stride=1)
            y = s.conv2d(inputnet,   noofoutput, [3, 3] )
            outputs = tf.concat([x, y], 3)
            return outputs

def inference(img):
    batchnorm = {

        'decay': 0.995,
    }
    with s.arg_scope([s.conv2d],
                        weights=s.contrib.layers.variance_scaling_initializer(factor=2.0, uniform=True, dtype=tf.float32),
                        regularizer=s.l2_regularizer(0.01),
                        normalizer=batchnorm):
        with tf.variable_scope([img]):
            with s.arg_scope([ s.dropout]):
                n = s.conv2d(img, 32, [7, 7], stride=2)                  # Adding conv layer
                n = s.max_pool2d( n, [3, 3], stride=2)                   # Adding max_pool layer
                n = squeezenet(n, 8, 8)
                n = squeezenet(n, 8, 8)                                  #Sqeeuzenet to perform compression
                n = s.max_pool2d(n, [2, 2], stride=2)
                n = squeezenet(n, 16, 32)
                n = squeezenet(n, 24, 64)
                n = squeezenet(n, 24, 64)
                n = s.max_pool2d(n, [3, 3], stride=2)
                n = s.dropout(n, 0.5)                                   # adding dropout layer
                n = s.conv2d(n, 256, [1, 1])
                n = s.avg_pool2d(n, n.get_shape())
                network = tf.squeeze(n, [1, 2])
                return network