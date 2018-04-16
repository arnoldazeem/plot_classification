import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
from pandas.api.types import is_numeric_dtype
import random
import seaborn as sns


path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/data/*.csv"



for fname in glob.glob(path):
    df = (pd.read_csv(fname,encoding= "ISO-8859-1"))
    graph_name = (os.path.basename(fname))

    mark = ['-', '--', '-.', ':']

    df1 = df._get_numeric_data()

    listhem = []
    final = df1.columns[-1]

    col_1 = 0


    # whole of the csv file
    for a, b in enumerate(df1):

        if (df1.columns.values[a] != final):

            #x_axis = df.iloc[:10, col_1].abs().values.tolist()
            x_axis = df.iloc[:5, a].values.tolist()
            # make them positve
            x_label = df.columns[a]

            y_axis = df.iloc[:5, a+1].values.tolist()
            # make them positve
            y_label = df.columns[a+1]
            # plot
            plt.bar(x_axis,y_axis, label=df.columns[a],align='center',alpha=0.5)

            xn_axis = df.iloc[5:10, a].values.tolist()
            # make them positve
            xn_label = df.columns[a]


            yn_axis = df.iloc[:10, a+1].values.tolist()
            # make them positve
            yn_label = df.columns[a+1]
            # plot
            plt.bar(x_axis, y_axis, label=df.columns[a+1],align='center',alpha=0.5)

            plt.legend()

            plt.xlabel(x_label)
            plt.ylabel(y_label)

            plt.title(graph_name)

            plt.savefig(
                '/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/bar/bar data' + 'vertlegend' + x_label + '.jpg')

            plt.show()



        else:
            print('done')
            # end


