import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
from pandas.api.types import is_numeric_dtype
import random
import seaborn as sns


path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/*.csv"


for fname in glob.glob(path):
    df = (pd.read_csv(fname))
    graph_name = (os.path.basename(fname))

    # gets you only the numeric data
    #df1 = df.select_dtypes(['number'])
    df1 = df._get_numeric_data()

    listhem = []
    final = df1.columns[-1]


col_1 = 0

for fname in glob.glob(path):
    df = (pd.read_csv(fname))
    graph_name = (os.path.basename(fname))

    mark = ['-', '--', '-.', ':']

    # whole of the csv file
    for a in df:

        marker = random.choice(mark)
        #you are sure its not the last column
        if (df.columns[-1] != df.columns[col_1]):
            #x_axis = df.iloc[:10, col_1].abs().values.tolist()
            x_axis = df.iloc[:5, col_1].values.tolist()
            # make them positve
            x_label = df.columns[col_1]
            # plot
            plt.plot(x_axis,linestyle=marker)
            plt.show()
            col_1 += 1
        else:
            print('done')
            # end
