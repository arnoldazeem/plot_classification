import matplotlib.pyplot as plt
import numpy as np; np.random.seed(1)

data1=np.random.randn(40,2)
data2=np.random.randn(30,2)

fig, ax = plt.subplots()
bp1 = ax.boxplot(data1, positions=[1,4], widths=0.35,
                 patch_artist=True, boxprops=dict(facecolor="C0"))
bp2 = ax.boxplot(data2, positions=[2,5],  widths=0.35,
                 patch_artist=True, boxprops=dict(facecolor="C2"))

ax.legend([bp1["boxes"][0], bp2["boxes"][0]], ['A', 'B'], loc='upper right')

ax.set_xlim(0,6)
plt.show()