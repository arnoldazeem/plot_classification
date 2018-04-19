import plotly.plotly as py
import plotly.graph_objs as go
import numpy as np
import glob
import pandas as pd
import os
import random


#kumar05
#IW5WKDoNWeWWon8haT7r

#plotly.tools.set_credentials_file(username='zanzy', api_key='6tzTYqsLP9zeG91eamft')

py.sign_in('kumar05', 'IW5WKDoNWeWWon8haT7r') # Replace the username, and API key with your credentials.

path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/data/*.csv"


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

            # Create a trace
            trace = go.Scatter(
                x = x_axis,
                y = y_axis,
                mode = 'markers'
            )

            data = [trace]


            layout = go.Layout(title=graph_name, width=640, height=480)
            fig = go.Figure(data=data, layout=layout)
            py.image.save_as(fig, filename= 'scatter_plot' + '1' + x_label + '.jpeg')

            from IPython.display import Image

            Image('scatter_plot' + '1' + x_label + '.jpeg')