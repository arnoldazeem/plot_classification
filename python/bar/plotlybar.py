import plotly.plotly as py
import plotly.graph_objs as go
import numpy as np
import glob
import pandas as pd
import os
import random

path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/scatter/data/*.csv"


#py.sign_in('kumar05', 'IW5WKDoNWeWWon8haT7r')
py.sign_in('kumar05', 'IW5WKDoNWeWWon8haT7r')

for fname in glob.glob(path):
    print(fname)
    df = (pd.read_csv(fname,encoding= "ISO-8859-1"))
    df1 = df._get_numeric_data()
    graph_name = (os.path.basename(fname))

    final = df1.columns[-1]

    # whole of the csv file
    for a, b in enumerate(df1):

        if (df1.columns.values[a] != final):

            #x_axis = df.iloc[:10, col_1].abs().values.tolist()
            x_axis = df1.iloc[:10, a].values.tolist()

            w_axis = df1.iloc[5:10, a].values.tolist()
            # make them positve
            x_label = df1.columns[a]


            y_axis = df1.iloc[:10, a+ 1].values.tolist()

            z_axis = df1.iloc[5:10, a].values.tolist()
            # make them positve
            y_label = df1.columns[a+1]


            # Create a trace
            trace = go.Bar(
                x = x_axis,
                y = y_axis,
                orientation='h'

            )

            # Create a trace
            trace2 = go.Bar(
                x=z_axis,
                y=w_axis,
            )

            # Create a trace
            trace3 = go.Scatter(
                x=y_axis,
                y=w_axis,

            )



            data = [trace]

            layout = go.Layout(width=640, height=480,title='Plot Title',
                xaxis=dict(
                    title='x Axis',
                    titlefont=dict(
                        family='Courier New, monospace',
                        size=18,
                        color='#7f7f7f'
                    )
                ),
                yaxis=dict(
                    title='y Axis',
                    titlefont=dict(
                        family='Courier New, monospace',
                        size=18,
                        color='#7f7f7f'
                    )
                )
            )

            fig = go.Figure(data=data, layout=layout)

            py.image.save_as(fig, filename= 'scatter_plot' + 'plotybarcharthoriz' + x_label + '.jpeg')

            from IPython.display import Image

            Image('scatter_plot' + '1' + x_label + '.jpeg')



