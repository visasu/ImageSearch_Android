#!/usr/bin/env python
<<<<<<< HEAD
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
=======
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1

from tensorflow.python.tools import freeze_graph 
from tensorflow.python.tools import optimize_for_inference_lib
import tensorflow as tf

MODEL_NAME = 'facenet'

# Freeze the graph

input_graph_path = MODEL_NAME+'.pbtxt'
checkpoint_path = './'+MODEL_NAME+'.ckpt'
input_saver_def_path = ""
input_binary = False
<<<<<<< HEAD
input_node_names = "x"
=======
input_node_names = "I"
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
output_node_names = "out"
restore_op_name = "save/restore_all"
filename_tensor_name = "save/Const:0"
output_frozen_graph_name = 'frozen_'+MODEL_NAME+'.pb'
output_optimized_graph_name = 'optimized_'+MODEL_NAME+'.pb'
clear_devices = True

print("Freezing Graph\n");
freeze_graph.freeze_graph(input_graph_path, input_saver_def_path,
			  input_binary, checkpoint_path, output_node_names,
			  restore_op_name, filename_tensor_name,
			  output_frozen_graph_name, clear_devices, "")

input_graph_def = tf.GraphDef()
with tf.gfile.Open(output_frozen_graph_name, "r") as f:
	data = f.read()
	input_graph_def.ParseFromString(data)

print("Optimizing graph for inference\n");
output_graph_def = optimize_for_inference_lib.optimize_for_inference(
	input_graph_def,
<<<<<<< HEAD
	["x"], # an array of the input node(s)
=======
	["I"], # an array of the input node(s)
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
	["out"], # an array of output nodes
	tf.float32.as_datatype_enum)

# Save the optimized graph

f = tf.gfile.FastGFile(output_optimized_graph_name, "w")
f.write(output_graph_def.SerializeToString())

	
