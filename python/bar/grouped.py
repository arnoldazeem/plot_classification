import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
from pandas.api.types import is_numeric_dtype
import random
import numpy as np


path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/data/*.csv"

for fname in glob.glob(path):
    df = (pd.read_csv(fname,encoding= "ISO-8859-1"))
    graph_name = (os.path.basename(fname))

    df1 = df._get_numeric_data()

    listhem = []
    final = df1.columns[-1]

    groups = 4

    bar_width = 0.50
    # whole of the csv file
    for a, b in enumerate(df1):

        if (df1.columns.values[a] != final):
            #x_axis = df.iloc[:10, col_1].abs().values.tolist()
            x_axis = df1.iloc[:4, a].values.tolist()
            # make them positve
            x_label = df1.columns[a]

            n_axis = df1.iloc[5:9, a].values.tolist()


            y_axis = df1.iloc[5:9, a+1].values.tolist()
            # make them positve
            y_label = df1.columns[a+1]


            city = [x_label, 'dummy1', y_label, 'dummy3']

            Gender = [x_label, y_label,'dummy']

            pos = np.arange(len(city))

            bar_width = 0.20

            bar_widt = 0.80

            plt.barh(pos, x_axis, bar_width, color='blue', edgecolor='black')

            plt.barh(pos + bar_width, n_axis, bar_width, color='green', edgecolor='black')

            plt.barh(pos + bar_widt, y_axis, bar_width, color='pink', edgecolor='black')

            plt.xticks(pos, city)

            plt.xlabel(x_label)
            plt.ylabel(y_label)

            plt.title(graph_name)

            plt.legend(Gender)

            plt.savefig(
                '/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/bar/bar data' + 'groupedhorin' + x_label + '.jpg')

            plt.show()

        else:
            print('done')
            # end


