import matplotlib.pyplot as plt
import pandas as pd
import glob
import pandas as pd
import os
import matplotlib.pyplot as plt

path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/box/data/*.csv"

for fname in glob.glob(path):
    df = (pd.read_csv(fname))
    graph_name = (os.path.basename(fname))

    # gets you only the numeric data
    #df1 = df.select_dtypes(['number'])
    df1 = df._get_numeric_data()

    listhem = []
    labels = []

    final = df1.columns[-1]
    # gets you only the numeric data
    # df1 = df.select_dtypes(['number'])
    df1 = df._get_numeric_data()

    for a, b in enumerate(df1):

        if (df1.columns.values[a] != final):

            dfList = df1[b].tolist()

            listhem.append(dfList[:2])

            labels.append(df1.columns.values[a])

            df11 = pd.DataFrame(listhem, index=labels)


            #plt.subplots_adjust(left=0.25)


            #plt.grid(b=None, which='major', axis='both')

            plt.xlabel(df1.columns.values[a])
            #plt.ylabel(df1.columns.values[a+1])
            plt.title(graph_name)


            plt.savefig('/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/box/' + df1.columns.values[
                    a] +'anovetical'+ '.jpg', bbox_inches='tight')

            plt.show()