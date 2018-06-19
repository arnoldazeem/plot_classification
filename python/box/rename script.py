import os
import glob


path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/bar/python bar dataset/*"

path2 = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/bar/python bar dataset/"


for i,fname in enumerate (glob.glob(path)):

    graph_name = (os.path.basename(fname))

    filename, file_extension = os.path.splitext(graph_name)

    os.rename(path2 + graph_name, path2 +  "python_bar_chart" + "_" + str(i) + file_extension)

    print("done")