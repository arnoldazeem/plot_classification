import glob
import pandas as pd
import os
import matplotlib.pyplot as plt
from pandas.api.types import is_numeric_dtype
import random
import seaborn as sns


path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/box/data/*.csv"


for fname in glob.glob(path):
    df = (pd.read_csv(fname))
    graph_name = (os.path.basename(fname))

    # gets you only the numeric data
    #df1 = df.select_dtypes(['number'])
    df1 = df._get_numeric_data()

    listhem = []

    listhem2 = []

    final = df1.columns[-1]

    # gets you only the numeric data
    # df1 = df.select_dtypes(['number'])
    df1 = df._get_numeric_data()

    for a, b in enumerate(df1):

        if (df1.columns.values[a] != final):

            dfList = df1[b].tolist()

            listhem.append(dfList[:20])

            listhem2.append(dfList[20:39])

            # figure related code
            fig = plt.figure()
            fig.suptitle('bold figure suptitle', fontsize=14, fontweight='bold')



            # Create an axes instance
            ax = fig.add_subplot(111)

            bp1 = ax.boxplot(listhem,  widths=0.35,
                             patch_artist=True, boxprops=dict(facecolor="C0"))
            bp2 = ax.boxplot(listhem2,  widths=0.35,
                             patch_artist=True, boxprops=dict(facecolor="C2"))

            ax.set_title(graph_name)

            ax.legend([bp1["boxes"][0], bp2["boxes"][0]], ['A', 'B'], loc='upper right')

            # Create the boxplot
            #ax.boxplot(listhem)

            ax.set_xlim(0, 6)

            # Save the figure
            fig.savefig('/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/box/'+'real'+ df1.columns.values[a] + '.jpg', bbox_inches='tight')

            fig.show()




