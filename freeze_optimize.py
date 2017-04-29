#!/usr/bin/env python

from tensorflow.python.tools import freeze_graph 
from tensorflow.python.tools import optimize_for_inference_lib
import tensorflow as tf

MODEL_NAME = 'facenet'

# Freeze the graph

input_graph_path = MODEL_NAME+'.pbtxt'
checkpoint_path = './'+MODEL_NAME+'.ckpt'
input_saver_def_path = ""
input_binary = False
input_node_names = "I"
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
	["I"], # an array of the input node(s)
	["out"], # an array of output nodes
	tf.float32.as_datatype_enum)

# Save the optimized graph

f = tf.gfile.FastGFile(output_optimized_graph_name, "w")
f.write(output_graph_def.SerializeToString())

	
