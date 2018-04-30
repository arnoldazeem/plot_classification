import plotly.plotly as py
import plotly.graph_objs as go
import numpy as np
import glob
import pandas as pd
import os
import random

path = "/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/python/box/data/*.csv"

#ashishbbhayani
#5czLDBg2FdjjsBd1JXLh

#py.sign_in('kumar05', 'IW5WKDoNWeWWon8haT7r')
py.sign_in('ashishbbhayani', '5czLDBg2FdjjsBd1JXLh')
#py.sign_in('zanzy', '6tzTYqsLP9zeG91eamft')

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
            x_axis = df1.iloc[:20, a].values.tolist()

            w_axis = df1.iloc[5:20, a].values.tolist()
            # make them positve
            x_label = df1.columns[a]


            y_axis = df1.iloc[:20, a+ 1].values.tolist()

            z_axis = df1.iloc[5:20, a].values.tolist()
            # make them positve
            y_label = df1.columns[a+1]


            # Create a trace
            trace = go.Box(
                y = x_axis,
                boxpoints=True,
                marker=dict(
                    color='rgb(214, 12, 140)'),
                line=dict(
                    color='rgb(214, 12, 140)')
            )

            # Create a trace
            trace2 = go.Box(
                y=y_axis,
                boxpoints=True,
                marker=dict(
                    color='rgb(9,56,125)'),
                line=dict(
                    color='rgb(9,56,125)')
            )


            # Create a trace
            trace3 = go.Box(
                y=w_axis,
                boxpoints=True,
                marker=dict(
                    color='rgb(0, 128, 128)'),
                line=dict(
                    color='rgb(0, 128, 128)')
            )

            # Create a trace
            trace4 = go.Box(
                y=z_axis,
                boxpoints=True,
                marker=dict(
                    color='rgb(0,0,125)'),
                line=dict(
                    color='rgb(0,0,125)')
            )

            data = [trace,trace2,trace3,trace4]

            layout = go.Layout(width=640, height=480,title='Plot Title',
                xaxis=dict(
                    title='x Axis',
                    titlefont=dict(
                        family='Courier New, monospace',
                        size=16,
                        color='#7f7f7f'
                    )
                ),
                yaxis=dict(
                    title='y Axis',
                    titlefont=dict(
                        family='Courier New, monospace',
                        size=16,
                        color='#7f7f7f'
                    )
                )
            )

            fig = go.Figure(data=data, layout=layout)

            py.image.save_as(fig, filename= 'scatter_plot' + 'plotlyboxesfourhori' + x_label + '.jpeg')

            from IPython.display import Image

            Image('scatter_plot' + '1' + x_label + '.jpeg')



