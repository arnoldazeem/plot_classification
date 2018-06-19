import numpy as np
import matplotlib as mpl
import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
from pylab import *
"""
========
Barchart
========

A bar plot with errorbars and height labels on individual bars
"""
import numpy as np
import matplotlib.pyplot as plt


path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/bar/data/*.csv"


for fname in glob.glob(path):
    df = (pd.read_csv(fname,encoding= "ISO-8859-1"))
    graph_name = (os.path.basename(fname))

    filename, file_extension = os.path.splitext(graph_name)

    df1 = df._get_numeric_data()
    final = df1.columns[-1]

    print(graph_name)


    for a, b in enumerate(df1):

        if (df1.columns.values[a] != final):
            #x_axis = df.iloc[:10, col_1].abs().values.tolist()
            x_axis = df1.iloc[:5, a].values.tolist()
            # make them positve
            x_label = df1.columns[a]

            y_axis = df1.iloc[:5, a+1].values.tolist()
            # make them positve
            y_label = df1.columns[a+1]

            xn_axis = df1.iloc[6:10, a].values.tolist()
            # make them positve
            xn_label = df1.columns[a]

            yn_axis = df1.iloc[6:10,a+1].values.tolist()
            # make them positve
            yn_label = df1.columns[a+1]
            # plot

            ## combine these different collections into a list
            N = 5

            men_std = (2, 3, 4, 1, 2)

            women_std = (3, 5, 2, 3, 3)

            ind = np.arange(N)  # the x locations for the groups
            width = 0.35  # the width of the bars

            fig, ax = plt.subplots()
            rects1 = ax.bar(ind, x_axis, width, color='r', yerr=men_std)


            rects2 = ax.bar(ind + width, y_axis, width, color='y', yerr=women_std)

            # add some text for labels, title and axes ticks
            ax.set_ylabel('Scores')
            ax.set_title('Example of barchart from ' + filename)
            ax.set_xticks(ind + width / 2)
            ax.set_xticklabels(('G1', 'G2', 'G3', 'G4', 'G5'))

            ax.legend((rects1[0], rects2[0]), (x_label, y_label))


            def autolabel(rects):
                """
                Attach a text label above each bar displaying its height
                """
                for rect in rects:
                    height = rect.get_height()
                    ax.text(rect.get_x() + rect.get_width() / 2., 1.05 * height,
                            '%d' % int(height),
                            ha='center', va='bottom')

            print("here")

            autolabel(rects1)
            autolabel(rects2)

            plt.savefig(
                '/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/bar/' + 'python2' +
                df1.columns.values[a] + '.jpeg')

            plt.show()

        else:
            print('done')
            # end




