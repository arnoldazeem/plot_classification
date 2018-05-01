import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
from pandas.api.types import is_numeric_dtype
import random
import seaborn as sns
import matplotlib.pyplot
import pylab


path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/data/*.csv"

for fname in glob.glob(path):
    print(fname)
    df = (pd.read_csv(fname,encoding= "ISO-8859-1"))
    df1 = df._get_numeric_data()
    graph_name = (os.path.basename(fname))

    mark = ['-', '--', '-.', ':']

    marks = ['o', '.', 's', '*']


    final = df1.columns[-1]

    # whole of the csv file
    for a, b in enumerate(df1):

        if (df1.columns.values[a] != final):

            #x_axis = df.iloc[:10, col_1].abs().values.tolist()
            x_axis = df1.iloc[:5, a].values.tolist()
            w_axis = df1.iloc[6:12, a].values.tolist()
            # make them positve
            x_label = df1.columns[a]
            y_axis = df1.iloc[:5, a+1].values.tolist()
            z_axis = df1.iloc[6:12, a+1].values.tolist()
            # make them positve
            y_label = df1.columns[a+1]

            marker = random.choice(mark)

            markers = random.choice(marks)

            #sns.set_style("darkgrid")
            plt.xlabel(x_label)
            plot1, = plt.plot(x_axis,linestyle=marker,marker=markers)

            #sns.set_style("darkgrid")
            plt.xlabel(x_label)
            plot3, = plt.plot(w_axis,linestyle=marker,marker=markers)

            #sns.set_style("darkgrid")
            plt.ylabel(y_label)
            plot2, = plt.plot(z_axis,marker=markers)

            plt.legend([plot1,plot3,plot2], ["plot 1","plot 3", "plot 2"])
            #plt.legend([plot1, plot3], ["plot 1", "plot 3"])

            filename, file_extension = os.path.splitext(graph_name)

            plt.title(filename)

            plt.savefig(
                '/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/line/python line data' + 'seaborn' + x_label + '.jpg')
            plt.show()
