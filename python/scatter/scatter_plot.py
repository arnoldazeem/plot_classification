import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
from pandas.api.types import is_numeric_dtype
import random
import seaborn as sns
import matplotlib.pyplot
import pylab


path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/good/*.csv"

def plotfunction(x, y, x_axis, y_axis, name,makr):
    plt.scatter(x, y, marker= makr,color='k', s=25,)
    plt.title(graph_name)
    plt.xlabel(x_axis)
    plt.ylabel(y_axis)
    plt.legend()
    plt.savefig('/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/scatter_plot' + 'now' + name + '.jpg')
    plt.show()

for fname in glob.glob(path):
    print(fname)
    df = (pd.read_csv(fname,encoding= "ISO-8859-1"))
    df1 = df._get_numeric_data()
    graph_name = (os.path.basename(fname))
    mark = ['o', '*', '.', '+','x']

    final = df1.columns[-1]

    # whole of the csv file
    for a, b in enumerate(df1):


        if (df1.columns.values[a] != final):

            #x_axis = df.iloc[:10, col_1].abs().values.tolist()
            x_axis = df1.iloc[:20, a].values.tolist()
            # make them positve
            x_label = df1.columns[a]


            y_axis = df1.iloc[:20, a+ 1].values.tolist()
            # make them positve
            y_label = df1.columns[a+1]

            marker = random.choice(mark)

            # plot
            plotfunction(x_axis, y_axis, x_label, y_label, x_label,marker)




            #matplotlib.pyplot.scatter(x_axis,y_axis,marker=marker)

            #matplotlib.pyplot.show()



