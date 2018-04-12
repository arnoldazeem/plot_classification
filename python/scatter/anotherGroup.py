import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
from pandas.api.types import is_numeric_dtype
import random
import pylab


path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/data/*.csv"

def plotfunction(datas,groups,colors,name):

    x,y,z = datas
    plt.scatter(x,y)
    plt.title(name)
    pylab.scatter(x,y)
    plt.xlabel(x_label)
    plt.ylabel(y_label)
    plt.savefig('/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/'+ 'grouped' + name + '.jpg')
    pylab.show()

col_1 = 0

for fname in glob.glob(path):
    df = (pd.read_csv(fname,encoding= "ISO-8859-1"))
    graph_name = (os.path.basename(fname))

    #gets you only the numeric data
    #
    df1 = df.select_dtypes(['number'])
    df1 = df._get_numeric_data()

    final = df1.columns[-2]



    # whole of the csv file
    for a, b in enumerate(df1):

        if (df1.columns.values[a] != final):

            #check to see if it numeric
            x_axis = df.iloc[:10, a].values.tolist()
            x_label = df.columns[a]

            y_axis = df.iloc[:10, a + 1].values.tolist()
            y_label = df.columns[a + 1]

            z_axis = df.iloc[:10, a + 2].values.tolist()
            z_label = df.columns[a + 2]


            #plot
            data = (x_axis,y_axis,z_axis)

            groups = (x_label,y_label,z_label)

            colors = ("red","green","blue")

            plotfunction(data,groups,colors,y_label)




