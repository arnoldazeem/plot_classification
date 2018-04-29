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


    df1 = df._get_numeric_data()

    final = df1.columns[-1]

    for a, b in enumerate(df1):

        width = [0.5, 0.6,0.7, 0.8, 0.9,1.0]

        height = [0.5, 0.6, 0.7, 0.8, 0.9, 1.0]

        marker = random.choice(width)

        if (df1.columns.values[a] != final):
            #x_axis = df.iloc[:10, col_1].abs().values.tolist()
            x_axis = df1.iloc[:10, a].values.tolist()
            # make them positve
            x_label = df1.columns[a]


            y_axis = df1.iloc[:10, a+1].values.tolist()
            # make them positve
            y_label = df1.columns[a+1]
            # plot

            plt.xlabel(x_label)
            plt.ylabel(y_label)

            plt.title(graph_name)

            plt.barh(x_axis,y_axis)

            plt.savefig('/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/bar/bar data' + 'simplehorizont23' + x_label + '.jpg')


            plt.show()

        else:
            print('done')
            # end


