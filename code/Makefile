dataset:
	bash prepareData.sh
tensor_run:
	python tensorFacenet.py
freeze:
	python freeze_optimize.py
android_port:
	cp optimized_facenet.pb ./MyApplication/app/src/main/assets/
clean_data:
	rm -rf dataset
clean_freeze:
	rm -rf frozen_facenet.pb optimized_facenet.pb facenet.pbtxt
clean_all:
	rm -rf dataset checkpoint facenet.ckpt.* frozen_facenet.pb optimized_facenet.pb facenet.pbtxt imTransform.pyc

