## numpy is used for creating fake data
import numpy as np
import matplotlib as mpl
import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
from pylab import *
## agg backend is used to create plot as a .png file



path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/data/*.csv"


for fname in glob.glob(path):
    df = (pd.read_csv(fname,encoding= "ISO-8859-1"))
    graph_name = (os.path.basename(fname))
    df1 = df._get_numeric_data()
    final = df1.columns[-1]

    for a, b in enumerate(df1):

        if (df1.columns.values[a] != final):
            #x_axis = df.iloc[:10, col_1].abs().values.tolist()
            x_axis = df1.iloc[:14, a].values.tolist()
            # make them positve
            x_label = df1.columns[a]

            y_axis = df1.iloc[:14, a+1].values.tolist()
            # make them positve
            y_label = df1.columns[a+1]

            xn_axis = df1.iloc[10:24, a].values.tolist()
            # make them positve
            xn_label = df1.columns[a]

            yn_axis = df1.iloc[10:24,a+1].values.tolist()
            # make them positve
            yn_label = df1.columns[a+1]
            # plot

            ## combine these different collections into a list
            data_to_plot = [yn_axis, xn_axis]

            fig = plt.figure(1, figsize=(9, 6))

            # Create an axes instance
            ax = fig.add_subplot(111)

            figure()
            boxplot(data_to_plot, showfliers=True, vert=False)

            plt.ylabel(df1.columns.values[a])
            # plt.ylabel(df1.columns.values[a+1])
            plt.title(graph_name)

            plt.savefig('/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/box/' + 'vertboxesforthree' +
                        df1.columns.values[a] + '.jpeg')

        else:
            print('done')
            # end


