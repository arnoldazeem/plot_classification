import matplotlib.pyplot as plt
import pandas as pd
import glob
import pandas as pd
import os
import matplotlib.pyplot as plt

path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/*.csv"

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

            listhem.append(dfList[:10])

            labels.append(df1.columns.values[a])

            df11 = pd.DataFrame(listhem, index=labels)

            df11.T.boxplot(vert=False)

            plt.subplots_adjust(left=0.25)

            plt.grid(b=None, which='major', axis='both')

            plt.savefig('/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter' + df1.columns.values[
                    a] +'vetical'+ '.jpg', bbox_inches='tight')

            plt.show()