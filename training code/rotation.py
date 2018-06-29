import time
import math
import random
import os
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import tensorflow as tf
import dataset
import cv2
from sklearn.metrics import confusion_matrix
from datetime import timedelta
import matplotlib.image as mpimg
import glob
from math import pi
from PIL import Image
import numpy as np
import scipy.misc
# Convolutional Layer 1.
filter_size1 = 11
num_filters1 = 96
# Convolutional Layer 2.
filter_size2 = 5
num_filters2 = 256
# Convolutional Layer 3.
filter_size3 = 3
num_filters3 = 384
# Convolutional Layer 4.
filter_size4 = 3
num_filters4 = 384
# Convolutional Layer 5.
filter_size5 = 3
num_filters5 = 256
# Fully-connected layer.
fc_size = 128             # Number of neurons in fully-connected layer.
# Number of color channels for the images: 1 channel for gray-scale.
num_channels = 3
# image dimensions (only squares for now)
img_size = 320
# Size of image when flattened to a single dimension
img_size_flat = img_size * img_size * num_channels
# Tuple with height and width of images used to reshape arrays.
img_shape = (img_size, img_size)


#Prepare input data
classes = os.listdir('data/trains')

num_classes = len(classes)

# batch size
batch_size = 32

# validation split
validation_size = .20

train_path = 'data/trains/'
test_path = 'data/test/'
checkpoint_dir = "models/"

# how long to wait after validation loss stops improving before terminating training
early_stopping = None  # use None if you don't want to implement early stoping

data = dataset.read_train_sets(train_path, img_size, classes, validation_size=validation_size)

#data2 = dataset.read_test_sets(test_path, img_size, classes)

#test_images, test_ids = dataset.read_test_set(test_path, img_size)


print("Size of:")
print("- Training-set:\t\t{}".format(len(data.train.labels)))
# print("- Test-set:\t\t{}".format(len(test_images)))
#print("- Validation-set:\t{}".format(len(data.valid.labels)))
#print("- Test-set:\t\t{}".format(len(data2.test.labels)))


def tf_resize_images(X_img_file_paths):
    X_data = []
    tf.reset_default_graph()
    X = tf.placeholder(tf.float32, (None, None, 3))
    tf_img = tf.image.resize_images(X, (img_size, img_size),
                                    tf.image.ResizeMethod.NEAREST_NEIGHBOR)
    with tf.Session() as sess:
        sess.run(tf.global_variables_initializer())
        # Each image is resized individually as different image may be of different size.
        path = os.path.join(X_img_file_paths, '*g')
        files = glob.glob(path)
        for file_path in (files):
            img = mpimg.imread(file_path)[:, :, :3]  # Do not read alpha channel.
            resized_img = sess.run(tf_img, feed_dict={X: img})
            X_data.append(resized_img)

    X_data = np.array(X_data, dtype=np.float32)  # Convert to numpy

    return X_data



def central_scale_images(X_imgs, scales):
    # Various settings needed for Tensorflow operation
    boxes = np.zeros((len(scales), 4), dtype=np.float32)
    for index, scale in enumerate(scales):
        x1 = y1 = 0.5 - 0.5 * scale  # To scale centrally
        x2 = y2 = 0.5 + 0.5 * scale
        boxes[index] = np.array([y1, x1, y2, x2], dtype=np.float32)
    box_ind = np.zeros((len(scales)), dtype=np.int32)
    crop_size = np.array([img_size, img_size], dtype=np.int32)

    X_scale_data = []
    tf.reset_default_graph()
    X = tf.placeholder(tf.float32, shape=(1, img_size, img_size, 3))
    # Define Tensorflow operation for all scales but only one base image at a time
    tf_img = tf.image.crop_and_resize(X, boxes, box_ind, crop_size)


    with tf.Session() as sess:
        sess.run(tf.global_variables_initializer())

        for img_data in X_imgs:
            batch_img = np.expand_dims(img_data, axis=0)
            scaled_imgs = sess.run(tf_img, feed_dict={X: batch_img})
            X_scale_data.extend(scaled_imgs)

    X_scale_data = np.array(X_scale_data, dtype=np.float32)
    return X_scale_data



def rotate_images(X_imgs, start_angle, end_angle, n_images):
    X_rotate = []
    iterate_at = (end_angle - start_angle) / (n_images - 1)

    tf.reset_default_graph()
    X = tf.placeholder(tf.float32, shape=(None, img_size, img_size, 3))
    radian = tf.placeholder(tf.float32, shape=(len(X_imgs)))
    tf_img = tf.contrib.image.rotate(X, radian)
    with tf.Session() as sess:
        sess.run(tf.global_variables_initializer())
        for index in range(n_images):
            degrees_angle = start_angle + index * iterate_at
            radian_value = degrees_angle * pi / 180  # Convert to radian
            radian_arr = [radian_value] * len(X_imgs)
            rotated_imgs = sess.run(tf_img, feed_dict={X: X_imgs, radian: radian_arr})
            X_rotate.extend(rotated_imgs)
    X_rotate = np.array(X_rotate, dtype=np.float32)
    return X_rotate


# Start rotation at -90 degrees, end at 90 degrees and produce totally 14 images
rotated_imgs = rotate_images(data.train.images, -90, 90, 14)

#scaled = scaled_imgs = central_scale_images(data.train.images, [0.99])

path  = 'data/trains/box_plot/'

#resized  = tf_resize_images(path)

for index, im in enumerate(rotated_imgs):
    #Image.fromarray((i * 255).astype('uint8'), mode='L').save('my' + str(i) + '.png')
    array_image = im
    array_resized_image = scipy.misc.imresize(array_image, (img_size, img_size), interp='nearest', mode=None)
    scipy.misc.imsave('my' + repr(index) + '.png', array_resized_image)
    #img.show()