dataset:
	bash prepareData.sh
tensor_run:
	python tensorFacenet.py
freeze:
	python freeze_optimize.py
android_port:
	cp optimized_facenet.pb ./MyApplication/app/src/main/assets/
<<<<<<< HEAD
complete:
	bash prepareData.sh;
	python tensorFacenet.py;
	python freeze_optimize.py;
	cp optimized_facenet.pb ./MyApplication/app/src/main/assets/;
=======
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
clean_data:
	rm -rf dataset
clean_freeze:
	rm -rf frozen_facenet.pb optimized_facenet.pb facenet.pbtxt
clean_all:
	rm -rf dataset checkpoint facenet.ckpt.* frozen_facenet.pb optimized_facenet.pb facenet.pbtxt imTransform.pyc

