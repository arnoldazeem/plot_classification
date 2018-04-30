## numpy is used for creating fake data
import numpy as np
import matplotlib as mpl
import glob
import pandas as pd
import os
import matplotlib.pyplot as plt

## agg backend is used to create plot as a .png file

mpl.use('agg')

path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/data/*.csv"


for fname in glob.glob(path):

    df = (pd.read_csv(fname,encoding= "ISO-8859-1"))
    graph_name = (os.path.basename(fname))

    df1 = df._get_numeric_data()

    listhem = []

    final = df1.columns[-1]


    final = df1.columns[-1]

    for a, b in enumerate(df1):

        if (df1.columns.values[a] != final):


            #x_axis = df.iloc[:10, col_1].abs().values.tolist()
            x_axis = df1.iloc[:5, a].values.tolist()
            # make them positve
            x_label = df1.columns[a]

            y_axis = df1.iloc[:5, a+1].values.tolist()
            # make them positve
            y_label = df1.columns[a+1]
            # plot
            plt.barh(x_axis,y_axis, label=df1.columns[a])

            xn_axis = df1.iloc[5:10, a].values.tolist()
            # make them positve
            xn_label = df1.columns[a]


            yn_axis = df1.iloc[5:10,a+1].values.tolist()
            # make them positve
            yn_label = df1.columns[a+1]
            # plot


            ## combine these different collections into a list
            data_to_plot = [x_axis, yn_axis, xn_axis, y_axis]


            fig = plt.figure(1, figsize=(9, 6))

            # Create an axes instance
            ax = fig.add_subplot(111)

            # Create the boxplot
            bp = ax.boxplot(data_to_plot)

            plt.show()

            plt.savefig('/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/box/' + 'real' +
                        df1.columns.values[a] + '.jpg', bbox_inches='tight')

        else:
            print('done')
            # end


