import glob
import shutil
import os
import random
import numpy as np

src_dir = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/collection of datasets/R bar chart/"
dst_dir = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/collection of datasets/train bar chart/"

chosen = []

for jpgfile in glob.iglob(os.path.join(src_dir, "*")):
    chosen.append(jpgfile)
jpgfile = np.random.choice(chosen, 817, replace=False)
print(jpgfile)

for fil in jpgfile:
    shutil.move(fil, dst_dir)