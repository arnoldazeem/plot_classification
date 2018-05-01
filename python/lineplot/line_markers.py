import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
from pandas.api.types import is_numeric_dtype
import random
import seaborn as sns


path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/line/data/*.csv"


for fname in glob.glob(path):
    df = (pd.read_csv(fname,encoding= "ISO-8859-1"))
    graph_name = (os.path.basename(fname))

    linestyl = ['-', '--', '-.', ':']

    mark = ['o', '.', 's', '*']

    # whole of the csv file
    df1 = df._get_numeric_data()

    print(graph_name)

    # whole of the csv file
    final = df1.columns[-1]
    # whole of the csv file
    for a, b in enumerate(df1):

        linetype = random.choice(linestyl)

        marker = random.choice(mark)
        #you are sure its not the last column

        if (df1.columns.values[a] != final):
            #x_axis = df.iloc[:10, col_1].abs().values.tolist()
            x_axis = df.iloc[:7, a].values.tolist()
            # make them positve
            x_label = df.columns[a]

            y_axis = df.iloc[:7, a + 1].values.tolist()
            # make them positve
            y_label = df.columns[a + 1]
            # plot
            plt.xlabel(x_label)
            plt.ylabel(y_label)
            plt.title(graph_name)
            plt.plot(x_axis,y_axis,linestyle=linetype, marker=marker)
            plt.savefig(
                '/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/line/python line data' + 'diffsix' + x_label + '.jpg')

            plt.show()



