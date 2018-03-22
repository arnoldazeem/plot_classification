import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
from pandas.api.types import is_string_dtype
from pandas.api.types import is_numeric_dtype
import seaborn as sns
import random
import  urllib.parse
import json

path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/*.csv"


def plotfunction(x, y, x_axis, y_axis, name,makr):
    colors = (1, 0, 0)
    # area = np.pi*3

    plt.scatter(x, y, marker= makr)

    plt.title(graph_name)
    plt.xlabel(x_axis)
    plt.ylabel(y_axis)
    # plt.grid(True)
    plt.savefig('/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter' + name + '.jpg')
    # plt.show()

col_1 = 0

markers=['.',',','o','v','^','<','>','1','2','3','4','8','s','p','P','*','h','H','+','x','X','D','d','|','_']



for fname in glob.glob(path):
    df = (pd.read_csv(fname))
    graph_name = (os.path.basename(fname))

    # whole of the csv file
    for a in df:

        if is_numeric_dtype(df.iloc[:20, col_1]):

            x_axis = df.iloc[:20, col_1].abs().values.tolist()

            # make them positve
            x_label = df.columns[col_1]
            col_2 = col_1 + 1

            if (df.columns[-1] != df.columns[col_2]):
                if is_numeric_dtype(df.iloc[:10, col_2]):
                    y_axis = df.iloc[:10, col_2].abs().values.tolist()
                    # make them positve

                    y_label = df.columns[col_2]

                    labels = random.choice(('o', '*', '.', ',', '+'))

                    # plot
                    plotfunction(x_axis, y_axis, x_label, y_label, x_label,labels)
                    # sns.pairplot(x_vars=x_axis, y_vars=y_axis, data=df, hue=labels, size=5)

                    col_1 = col_2

                else:
                    print("not numeric")
                    col_2 = col_1 + 1
            else:
                # it was the last column
                if is_numeric_dtype(df.iloc[:10, col_2]):
                    y_axis = df.iloc[:10, col_2].values.tolist()
                    y_label = df.columns[col_2]

                    labels = random.choice(('o', '*', '.', ',', '+'))

                    # plot
                    plotfunction(x_axis, y_axis, x_label, x_label, x_label,labels)
                    # sns.pairplot(x_vars=x_axis, y_vars=y_axis, data=df, hue=labels, size=5)
                else:
                    # last column but not numeric done
                    print("here")

        # not numeric
        else:
            col_1 += 1
            # end