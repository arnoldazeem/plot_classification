import os
import glob


#path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/R/scatter/R scatter data/*"

pathjav =  "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/matlab/sets/matlab scatter plot/*"

#path2 = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/R/scatter/R scatter data/"

pathjav2 = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/matlab/sets/matlab scatter plot/"


for i,fname in enumerate (glob.glob(pathjav)):

    graph_name = (os.path.basename(fname))

    filename, file_extension = os.path.splitext(graph_name)

    os.rename(pathjav2 + graph_name, pathjav2 + "matlab_scatter_plot" + "_" + str(i) + file_extension)

    print('done')