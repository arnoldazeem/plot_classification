import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
from pandas.api.types import is_numeric_dtype
import random
import seaborn as sns


path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/*.csv"

def plotfunction(x, y, x_axis, y_axis, name,makr):
    plt.scatter(x, y, marker= makr)
    plt.title(graph_name)
    plt.xlabel(x_axis)
    plt.ylabel(y_axis)
    plt.grid(False)
    plt.savefig('/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter' + name + '.jpg')
    plt.show()

col_1 = 0

for fname in glob.glob(path):
    df = (pd.read_csv(fname))
    graph_name = (os.path.basename(fname))
    mark = ['o', '*', '.', '+']

    # whole of the csv file
    for a in df:

        marker = random.choice(mark)
        #you are sure its not the last column
        #else no need to go into loop
        if (df.columns[-1] != df.columns[col_1]):

            #check to see if it numeric
            if is_numeric_dtype(df.iloc[:10, col_1]):
                #x_axis = df.iloc[:10, col_1].abs().values.tolist()
                x_axis = df.iloc[:10, col_1].values.tolist()
                # make them positve
                x_label = df.columns[col_1]
                col_2 = col_1 + 1


                if is_numeric_dtype(df.iloc[:10, col_2]):
                    y_axis = df.iloc[:10, col_2].values.tolist()
                    # make them positve
                    y_label = df.columns[col_2]
                    # plot
                    plotfunction(x_axis, y_axis, x_label, y_label, x_label,marker)
                    col_1 = col_2

                else:
                    print("not numeric")
                    col_2 = col_1 + 1
            else:
                col_1 += 1
                # end

