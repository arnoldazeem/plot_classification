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

    # gets you only the numeric data
    # df1 = df.select_dtypes(['number'])
    df1 = df._get_numeric_data()

    for a, b in enumerate(df1):

        if (df1.columns.values[a] != final):

            dfList = df1[b].tolist()

            listhem.append(dfList[:30])

            # Create a figure instance
            fig = plt.figure(1, figsize=(9, 6))

            # Create an axes instance
            ax = fig.add_subplot(111)

            # Create the boxplot
            bp = ax.boxplot(listhem)

            # Save the figure

            fig.savefig('/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter' + df1.columns.values[a] + '.jpg', bbox_inches='tight')

            fig.show()




